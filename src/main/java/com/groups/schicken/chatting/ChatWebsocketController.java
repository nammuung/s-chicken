package com.groups.schicken.chatting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatWebsocketController {
    private final ChatService chatService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/many/{chatroomId}")
    public void sendMessage(@DestinationVariable("chatroomId") String chatroomId, ChatMessage content) {
        log.info("sendMessage : from - {} , to - {} , message - {}", content.getSenderId(), chatroomId, content.getContent());

        ChatMessage message = chatService.makeMessage(chatroomId, content.getSenderId(), content.getContent());
        template.convertAndSend("/sub/chat/" + chatroomId, message);

        message.setChatroomId(chatroomId);

        Boolean isInserted = chatService.insertMessage(message);
        if (!isInserted) {
            template.convertAndSend("/sub/chat/" + chatroomId, ChatMessage.getCancelMessage(message.getId()));
        }
    }

    @MessageMapping("/chat/one/{targetId}")
    public void sendMessageToOne(@DestinationVariable("targetId") String targetId, ChatMessage content) {
        log.info("sendMessage : from - {} , to - {} , message - {}", content.getSenderId(), targetId, content.getContent());

        String chatroomId = Stream.of(targetId, content.getSenderId()).sorted().collect(Collectors.joining(""));

        ChatMessage message = chatService.makeMessage(chatroomId, content.getSenderId(), content.getContent());
        template.convertAndSend("/sub/chat/" + targetId, message);
        template.convertAndSend("/sub/chat/" + content.getSenderId(), message);

        message.setChatroomId(chatroomId);

        Boolean isInserted = chatService.insertMessage(message);
        if (!isInserted) {
            template.convertAndSend("/sub/chat/" + targetId, ChatMessage.getCancelMessage(message.getId()));
        }
    }
}
