package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatWebsocketController {
    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/{chatroomId}")
    public void sendMessage(@DestinationVariable("chatroomId") String chatroomId, @AuthenticationPrincipal EmployeeVO loginVO, ChatMessage message){

    }
}
