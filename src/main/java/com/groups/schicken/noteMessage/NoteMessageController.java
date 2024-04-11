package com.groups.schicken.noteMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/message/*")
public class NoteMessageController {
    @Autowired
    private NoteMessageService noteMessageService;

    @PostMapping("sendMessage")
    public String sendMessage(NoteMessageVO message, List<String> receivers){
        //나중에 로그인 후 session에서 받아오기
        Long id = 20160607230L;
        message.setSenderId(id);
        Integer result = noteMessageService.sendMessage(message, receivers);
        if(result > 0){
            return "성공적으로 보냈습니다.";
        }
        return "쪽지를 보내지 못했습니다.";
    }
}
