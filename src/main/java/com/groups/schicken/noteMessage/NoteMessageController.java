package com.groups.schicken.noteMessage;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.util.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/message/*")
public class NoteMessageController {
    @Autowired
    private NoteMessageService noteMessageService;

    @PostMapping("sendMessage")
    public ResponseEntity<String> sendMessage(@AuthenticationPrincipal EmployeeVO loginEmp ,NoteMessageVO message, String[] receivers, MultipartFile attach) {
        //나중에 로그인 후 session에서 받아오기
        System.out.println("message = " + message);
        System.out.println("receivers = " + Arrays.toString(receivers));

        message.setSenderId(loginEmp.getId());
        try{
            noteMessageService.sendMessage(message, List.of(receivers), attach);
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("쪽지를 보냈습니다.");
    }

    @GetMapping("getList/{type}")
    public ResponseEntity<Map> getList(@AuthenticationPrincipal EmployeeVO loginEmp, Pager pager, @PathVariable("type") NoteMessageBoxType type){
        System.out.println("loginEmp = " + loginEmp);
        System.out.println("pager = " + pager);

        Map<String, Object> map = new HashMap<>();
        List<NoteMessageVO> list;
        if(type.equals(NoteMessageBoxType.send)){
            list = noteMessageService.getSendList(loginEmp, pager);
        } else {
            list = noteMessageService.getList(loginEmp, pager, type);
        }

        map.put("data", list);
        map.put("page", pager);

        if(list == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(map);
    }

    @GetMapping("getMessage")
    public ResponseEntity<NoteMessageVO> getMessage(NoteMessageVO noteMessage, NoteMessageBoxType type){
        noteMessage = noteMessageService.getMessage(noteMessage, type);

        if(noteMessage == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(noteMessage);
    }

    @PostMapping("moveBox/{to}")
    public  ResponseEntity<String[]> moveBox(@AuthenticationPrincipal EmployeeVO loginEmp, @RequestBody String[] messages, @PathVariable NoteMessageBoxType to){
        try {
            String[] result = noteMessageService.moveBox(loginEmp.getId(), messages, to);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new String[]{e.getMessage()});
        }
    }

    @PostMapping("delete")
    public ResponseEntity<String> deleteMessage(@AuthenticationPrincipal EmployeeVO loginEmp, @RequestBody String[] messages){
        try{
            Boolean result = noteMessageService.deleteMessage(loginEmp.getId(), messages);
            return ResponseEntity.ok(result.toString());
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("삭제 실패");
        }
    }
}
