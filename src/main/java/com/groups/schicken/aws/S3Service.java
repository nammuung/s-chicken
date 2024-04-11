package com.groups.schicken.aws;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.groups.schicken.util.FileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3Client amazonS3Client;

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
}
