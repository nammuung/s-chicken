package com.groups.schicken.firebase;

//import com.google.cloud.storage.Blob;
//import com.google.cloud.storage.Bucket;
//import com.google.firebase.cloud.StorageClient;
import com.groups.schicken.util.FileVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class FirebaseService {
    public FileVO uploadFile(MultipartFile file, FileVO fileVO) throws Exception {
        Bucket bucket = StorageClient.getInstance().bucket();
        InputStream content = new ByteArrayInputStream(file.getBytes());
        Blob blob = bucket.create(fileVO.getName(), content, file.getContentType());
        fileVO.setUrl(blob.getMediaLink());
        fileVO.setOrigin_name(file.getOriginalFilename());
        fileVO.setExtension(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
        return fileVO;
    }

    public boolean deleteFile(FileVO fileVO) throws Exception {
        Bucket bucket = StorageClient.getInstance().bucket();
        return bucket.get(fileVO.getName()).delete();
    }
}
