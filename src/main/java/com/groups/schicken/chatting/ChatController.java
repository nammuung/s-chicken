package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chatrooms/*")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("popup")
    public String getListPage(@AuthenticationPrincipal EmployeeVO employee, Model model){
        List<ChatroomVO> chatroomList = chatService.getChatroomList(employee.getId());
        model.addAttribute("list", chatroomList);
        return "chatting/list";
    }

    @GetMapping("one/{targetId}")
    public ResponseEntity<ChatroomVO> getChatrooms(@PathVariable String targetId, @AuthenticationPrincipal EmployeeVO employee){
        ChatroomVO chatroom = chatService.getOneChatrooms(employee.getId(), targetId);

        if(chatroom == null){
            chatroom = chatService.createOneChatroom(employee.getId(), targetId);
        }

        return ResponseEntity.ok(chatroom);
    }

    @GetMapping("list")
    public ResponseEntity<List<ChatroomVO>> getChatroomsList(@AuthenticationPrincipal EmployeeVO employee){

        List<ChatroomVO> list = chatService.getChatroomList(employee.getId());

        return ResponseEntity.ok(list);
    }

    @PostMapping("join/{chatroomId}")
    public ResponseEntity<Boolean> joinChatroom(@AuthenticationPrincipal EmployeeVO employee, @PathVariable String chatroomId){
        Boolean result = chatService.joinChatroom(employee.getId(), chatroomId);

        return ResponseEntity.ok(result);
    }
}
