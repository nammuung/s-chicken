package com.groups.schicken.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import com.groups.schicken.common.vo.FileVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import lombok.RequiredArgsConstructor;

@Component("fileDownView")
@RequiredArgsConstructor
public class FileDownView{

	private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ResponseEntity<byte[]> getObject(Map<String, Object> map ,String storedFileName) throws IOException{
    	FileVO fileVO=(FileVO)map.get("FileVO");

    	S3Object o = amazonS3.getObject(new GetObjectRequest(bucket, fileVO.getName()));
    	S3ObjectInputStream objectInputStream = o.getObjectContent();
    	byte[] bytes = IOUtils.toByteArray(objectInputStream);

    	String fileName = URLEncoder.encode(fileVO.getName(), "UTF-8").replaceAll("WW+", "%20");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileVO.getName());

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);


    }




}
