package com.groups.schicken.noteMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/message/*")
public class NoteMessageController {
    @Autowired
    private NoteMessageService noteMessageService;

    @PostMapping("sendMessage")
    public ResponseEntity<String> sendMessage(NoteMessageVO message, String[] receivers, MultipartFile attach) {
        //나중에 로그인 후 session에서 받아오기
        System.out.println("message = " + message);
        System.out.println("receivers = " + Arrays.toString(receivers));

        Long id = 20160607230L;
        message.setSenderId(id);
        try{
            noteMessageService.sendMessage(message, List.of(receivers), attach);
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("쪽지를 보냈습니다.");
    }
}
