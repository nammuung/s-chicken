package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatWebsocketController {
    private final ChatService chatService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/{chatroomId}")
    public void sendMessage(@DestinationVariable("chatroomId") String chatroomId, @AuthenticationPrincipal EmployeeVO loginVO, ChatMessage content) {
        log.info("sendMessage : from - {} , to - {} , message - {}", loginVO.getName(), chatroomId, content.getContent());

        ChatMessage message = chatService.makeMessage(chatroomId, loginVO.getId(), content.getContent());
        template.convertAndSend("/sub/chat/" + chatroomId, message);

        message.setChatroomId(chatroomId);

        Boolean isInserted = chatService.insertMessage(message);
        if (!isInserted) {
            template.convertAndSend("/sub/chat/" + chatroomId, ChatMessage.getCancelMessage(message.getId()));
        }
    }
}
