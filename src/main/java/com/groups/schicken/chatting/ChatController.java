package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeProfileVO;
import com.groups.schicken.Employee.EmployeeService;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.organization.ChattingEmployeeListVO;
import com.groups.schicken.organization.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chatrooms/*")
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;
    private final OrganizationService organizationService;
    private final EmployeeService employeeService;

    @GetMapping("popup")
    public String getListPage(@AuthenticationPrincipal EmployeeVO employee, Model model){
        List<ChattingEmployeeListVO> orgList = organizationService.getChattingEmployeeList(employee.getId());
        model.addAttribute("orgList", orgList);

        EmployeeProfileVO myProfile = employeeService.getProfile(employee.getId());
        model.addAttribute("myProfile", myProfile);

        return "chatting/popup";
    }

    @GetMapping("getData/one/{targetId}")
    public ResponseEntity<ChattingVO> getChattingDataOne(@AuthenticationPrincipal EmployeeVO employee, @PathVariable String targetId){
        ChatroomVO chatroom = chatService.getOneChatrooms(employee.getId(), targetId);

        if(chatroom == null){
            chatroom = chatService.createOneChatroom(employee.getId(), targetId);
        }

        ChattingVO chattingVO = chatService.getChattingDataFirst(employee.getId(), chatroom.getId());

        return ResponseEntity.ok(chattingVO);
    }

    @GetMapping("getData/many/{chatroomId}")
    public ResponseEntity<ChattingVO> getChattingDataMany(@AuthenticationPrincipal EmployeeVO employee, @PathVariable String chatroomId){
        ChattingVO chattingVO = chatService.getChattingDataFirst(employee.getId(), chatroomId);

        return ResponseEntity.ok(chattingVO);
    }

    @PostMapping("create")
    public ResponseEntity<ChattingVO> createChatroom(@AuthenticationPrincipal EmployeeVO employee, @RequestBody String[] members){
        ChattingVO chattingVO;
        try {
            ChatroomVO chatroomVO = chatService.createManyChatroom(employee.getId(), members);
            chattingVO = chatService.getChattingDataFirst(employee.getId(), chatroomVO.getId());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(chattingVO);
    }

    @GetMapping("list")
    public ResponseEntity<List<ChatroomVO>> getChatroomsList(@AuthenticationPrincipal EmployeeVO employee){

        List<ChatroomVO> list = chatService.getChatroomList(employee.getId());

        return ResponseEntity.ok(list);
    }


    @GetMapping("memberData/{chatroomId}")
    public ResponseEntity<List<EmployeeProfileVO>> getMemberData(@PathVariable String chatroomId){
        List<EmployeeProfileVO> memberData = chatService.getChatroomMemberData(chatroomId);
        return ResponseEntity.ok(memberData);
    }

    @GetMapping("chattings/{chatroomId}")
    public ResponseEntity<List<ChatMessage>> getMoreMessages(@AuthenticationPrincipal EmployeeVO employee, @PathVariable String chatroomId, String from, String direction){
        List<ChatMessage> list = chatService.getChattingDataNext(employee.getId(), chatroomId, from,direction);

        return ResponseEntity.ok(list);
    }

    @PostMapping("join/{chatroomId}")
    public ResponseEntity<List<EmployeeProfileVO>> inviteMembers(@AuthenticationPrincipal EmployeeVO employee, @RequestBody String[] members, @PathVariable String chatroomId){
        try {
            int result = chatService.joinChatroom(chatroomId, members);

            if (result == members.length) {
                chatService.insertJoinNotice(employee, chatroomId, members);

                return ResponseEntity.ok(chatService.getChatroomMemberData(chatroomId));
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("updateTitle")
    public ResponseEntity<Boolean> updateTitle(@AuthenticationPrincipal EmployeeVO employee, @RequestBody ChatroomVO chatroom){
        try {
            Boolean result = chatService.updateTitle(employee, chatroom);
            return ResponseEntity.ok(result);
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("readChatting")
    public ResponseEntity<Boolean> readChatting(@AuthenticationPrincipal EmployeeVO employee, @RequestBody ChatMessage message){
        return ResponseEntity.ok(chatService.readChatting(employee.getId(), message.getChatroomId(), message.getId()));
    }

    @DeleteMapping("out/{chatroomId}")
    public ResponseEntity outChatroom(@AuthenticationPrincipal EmployeeVO employee, @PathVariable String chatroomId){
        boolean result = chatService.outChatroom(employee.getId(), chatroomId);

        if(result){
            chatService.insertOutMessage(employee, chatroomId);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
