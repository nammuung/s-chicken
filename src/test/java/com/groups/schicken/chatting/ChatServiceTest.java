package com.groups.schicken.chatting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest("spring.profiles.include=dev")
@Transactional
class ChatServiceTest {

    @Autowired
    ChatService service;

    @Test
    void getOneChatrooms() {
        var result = service.getOneChatrooms("19990806228","201505151032");

        assertNull(result);
    }

    @Test
    void createOneChatroom() {
        var vo = service.createOneChatroom("19990806228", "201505151032");

        assertNotNull(vo);
        assertEquals(vo.getId(), "19990806228201505151032");
    }
}
