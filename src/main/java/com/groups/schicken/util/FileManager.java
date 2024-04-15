package com.groups.schicken.util;

import com.groups.schicken.aws.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Component
public class FileManager {
    @Autowired
    private S3Service s3Service;
    @Autowired
    private FileMapper fileMapper;

    public boolean uploadFile(MultipartFile file, FileVO fileVO) throws Exception{
        String uid = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
        fileVO.setName(uid);
        fileVO.setOriginName(file.getOriginalFilename());
        fileVO.setExtension(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
        s3Service.uploadFile(file, fileVO);
        fileMapper.uploadFile(fileVO);
        return true;
    }
//    @Transactional
    public boolean deleteFile(FileVO fileVO) throws Exception {
        fileMapper.deleteFile(fileVO);
        return s3Service.deleteFile(fileVO);
    }
    public List<FileVO> getFiles() {
        return null;
    }
    public ResponseEntity<byte[]> downFile(FileVO fileVO) throws Exception{
    	fileVO = fileMapper.downFile(fileVO);
    	
		return s3Service.downFile(fileVO);
    	
    }
}
