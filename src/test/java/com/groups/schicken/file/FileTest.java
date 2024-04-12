package com.groups.schicken.file;

import com.groups.schicken.util.FileManager;
import com.groups.schicken.common.vo.FileVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.include=dev")
public class FileTest {
    @Autowired
    private FileManager fileManager;

    @Test
    public void test() throws Exception {
        FileVO file = new FileVO();
        file.setName("adae4d75-6821-4b51-ab6a-22282eda1cc4");
        System.out.println(fileManager.deleteFile(file));
    }
}
