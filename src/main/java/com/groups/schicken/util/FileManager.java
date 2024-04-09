package com.groups.schicken.util;

import com.groups.schicken.firebase.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Component
public class FileManager {
    @Autowired
    private FirebaseService firebaseService;
    @Autowired
    private FileMapper fileMapper;

    public boolean uploadFile(MultipartFile file, FileVO fileVO) throws Exception{
        String uid = UUID.randomUUID().toString();
        fileVO.setName(uid);
        firebaseService.uploadFile(file, fileVO);
        fileMapper.uploadFile(fileVO);
        return true;
    }
    public boolean deleteFile(FileVO fileVO) throws Exception {
        fileMapper.deleteFile(fileVO);
        return firebaseService.deleteFile(fileVO);
    }
    public List<FileVO> getFiles() {
        return null;
    }
}
