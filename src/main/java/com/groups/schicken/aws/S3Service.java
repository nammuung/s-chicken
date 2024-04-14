package com.groups.schicken.aws;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.groups.schicken.util.FileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3Client amazonS3Client;
    
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public FileVO uploadFile(MultipartFile file, FileVO fileVO) throws Exception {
        try{
            String fileName = fileVO.getName();
            String fileUrl = "https://"+bucket+"/test"+fileVO.getName();
            ObjectMetadata data = new ObjectMetadata();
            data.setContentType(file.getContentType());
            data.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), data);
            fileVO.setUrl(fileUrl);

            return fileVO;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteFile(FileVO fileVO) throws Exception {
        try{
            amazonS3Client.deleteObject(bucket,fileVO.getName());
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public ResponseEntity<byte[]> downFile(FileVO fileVO) throws IOException{
    	System.out.println(fileVO.getTblId()+"+++++++++++++++++++++++++++++++++");
    	System.out.println(fileVO.getName());
    	String fileName1 = fileVO.getName();
    	
    	S3Object o = amazonS3.getObject(new GetObjectRequest(bucket, fileName1));
    	S3ObjectInputStream objectInputStream = o.getObjectContent();
    	byte[] bytes = IOUtils.toByteArray(objectInputStream);
    	
    	String fileName = URLEncoder.encode(fileName1, "UTF-8").replaceAll("WW+", "%20");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
		
    	
    }
}
