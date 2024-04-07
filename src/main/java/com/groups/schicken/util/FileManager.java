package com.groups.schicken.util;

import com.groups.schicken.configuration.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Component
public class FileManager {
    @Autowired
    private FirebaseService firebaseService;

    public FileVO uploadFile(MultipartFile file, FileVO fileVO) throws Exception{
        String uid = UUID.randomUUID().toString();
        fileVO.setUrl(uid);
        firebaseService.uploadFile(file, fileVO);
        return fileVO;
    }
    public int deleteFile(FileVO fileVO){
        return 0;
    }
    public List<FileVO> getFiles() {
        return null;
    }
}
