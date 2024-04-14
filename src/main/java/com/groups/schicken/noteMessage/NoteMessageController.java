package com.groups.schicken.noteMessage;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.util.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("getList")
    public ResponseEntity<List<NoteMessageVO>> getList(@AuthenticationPrincipal EmployeeVO loginEmp, Pager pager){
        System.out.println("loginEmp = " + loginEmp);
        System.out.println("pager = " + pager);

        List<NoteMessageVO> list = noteMessageService.getList(loginEmp, pager);

        return ResponseEntity.ok(list);
    }
}
