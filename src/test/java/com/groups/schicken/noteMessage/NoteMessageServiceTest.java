package com.groups.schicken.noteMessage;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.Pager;
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
        String id = "20240402226";
        List<String> receivers = List.of("20160607230,20240409220".split(","));

        NoteMessageVO message = new NoteMessageVO();
        message.setSenderId(id);
        message.setContent("test message 입니다.");

        Integer result = noteMessageService.sendMessage(message, receivers, null);

        assertNotEquals(0, result);
    }

    @Test
    void getList() {
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setId("19990806228");

        Pager pager = new Pager();
        pager.setPage(0L);

        var list = noteMessageService.getList(employeeVO, pager, NoteMessageBoxType.receive);
        System.out.println("list = " + list);

        assertEquals(0, list.size());
    }

    @Test
    void getMessage(){
        NoteMessageVO messageVO = new NoteMessageVO();
        messageVO.setId(1021L);

        var message = noteMessageService.getMessage(messageVO, NoteMessageBoxType.send);
        System.out.println("message = " + message);
    }
}
