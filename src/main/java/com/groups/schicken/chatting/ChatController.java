package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeProfileVO;
import com.groups.schicken.Employee.EmployeeService;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.organization.ChattingEmployeeListVO;
import com.groups.schicken.organization.OrganizationService;
import com.groups.schicken.organization.OrganizationVO;
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
import java.util.PriorityQueue;
import java.util.Queue;

@Controller
@RequestMapping("/chatrooms/*")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final OrganizationService organizationService;
    private final EmployeeService employeeService;

    @GetMapping("popup")
    public String getListPage(@AuthenticationPrincipal EmployeeVO employee, Model model){
        List<ChatroomVO> chatroomList = chatService.getChatroomList(employee.getId());
        model.addAttribute("chatroomList", chatroomList);

        List<ChattingEmployeeListVO> orgList = organizationService.getChattingEmployeeList(employee.getId());
        model.addAttribute("orgList", orgList);

        EmployeeProfileVO myProfile = employeeService.getProfile(employee.getId());
        model.addAttribute("myProfile", myProfile);

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
