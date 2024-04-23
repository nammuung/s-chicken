package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chatrooms/*")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("one/{id}")
    public ResponseEntity<ChatroomVO> getChatrooms(@PathVariable String id, @AuthenticationPrincipal EmployeeVO employeeVO){
        ChatroomVO chatroom = chatService.getOneChatrooms(employeeVO.getId(), id);

        if(chatroom == null){
            chatroom = chatService.createOneChatroom(employeeVO.getId(), id);
        }

        return ResponseEntity.ok(chatroom);
    }

    @GetMapping("list")
    public ResponseEntity<List<ChatroomVO>> getChatroomsList(@AuthenticationPrincipal EmployeeVO employeeVO){

        List<ChatroomVO> list = chatService.getChatroomList(employeeVO.getId());

        return ResponseEntity.ok(list);
    }
}
