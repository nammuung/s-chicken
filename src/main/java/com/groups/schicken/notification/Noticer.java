package com.groups.schicken.notification;


import com.groups.schicken.common.util.DateManager;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Noticer {
    private final SimpMessagingTemplate messagingTemplate;
    private final NoticeDAO noticeDAO;

    public void sendNotice(String content, String ouccrer, NotificationType type ,List<String> receivers){
        System.out.println("content = " + content);
        sendNotice(NotificationVO.of(content, ouccrer, type), receivers);
    }

    public void sendNotice(NotificationVO notification){
        List<String> receivers = List.of(notification.getReceiverId());
        sendNotice(notification, receivers);
    }

    public void sendNotice(NotificationVO notification, List<String> receivers){
        notification.setTime(DateManager.getTodayDateTime("yyyyMMddHHmmss"));
        int result = noticeDAO.insertNotice(notification, receivers);
        System.out.println("insert result = " + result);
        for (String receiver : receivers) {
            messagingTemplate.convertAndSend("/sub/noti/" + receiver, notification);
        }
    }
}
