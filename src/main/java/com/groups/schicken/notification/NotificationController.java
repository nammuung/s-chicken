package com.groups.schicken.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/noti")
    public void sendNotification(NotificationVO notificationVO){
        System.out.println("notificationVO = " + notificationVO);
        messagingTemplate.convertAndSend("/sub/noti", notificationVO);
    }
}
