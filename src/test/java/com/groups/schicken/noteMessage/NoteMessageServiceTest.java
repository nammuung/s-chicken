package com.groups.schicken.noteMessage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest("spring.profiles.include=dev")
@Transactional
class NoteMessageServiceTest {

    @Autowired
    NoteMessageService noteMessageService;

    @Test
    void sendMessage() {
        Long id = 20240402226L;
        List<String> receivers = List.of("20160607230,20240409220".split(","));

        NoteMessageVO message = new NoteMessageVO();
        message.setSenderId(id);
        message.setContent("test message 입니다.");

        Integer result = noteMessageService.sendMessage(message, receivers);

        assertNotEquals(0, result);
    }
}
