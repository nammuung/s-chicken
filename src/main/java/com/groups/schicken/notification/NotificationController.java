package com.groups.schicken.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/notification")
    public String getNotificationPage(){
        return "notification/notification";
    }

    @MessageMapping("/noti")
    public void sendNotification(NotificationVO notificationVO){
        System.out.println("notificationVO = " + notificationVO);
        messagingTemplate.convertAndSend("/sub/noti", notificationVO);
    }
}
