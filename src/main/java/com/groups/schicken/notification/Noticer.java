package com.groups.schicken.notification;


import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.util.DateManager;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Noticer {
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationDAO notificationDAO;

    public void sendNotice(String content, String link, NotificationType type ,List<String> receivers) {
        System.out.println("content = " + content);
        sendNotice(NotificationVO.of(content, type, link), receivers);
    }

    public void sendNotice(NotificationVO notification) {
        List<String> receivers = List.of(notification.getReceiverId());
        sendNotice(notification, receivers);
    }

    public void sendNoticeByEmployeeVO(NotificationVO notification, EmployeeVO receiver) {
        notification.setId(receiver.getId());
        sendNotice(notification);
    }

    public void sendNoticeByEmployeeVO(NotificationVO notification, List<EmployeeVO> receivers) {
        sendNotice(notification, receivers.stream().map(EmployeeVO::getId).toList());
    }

    public void sendNotice(NotificationVO notification, List<String> receivers) {
        notification.setTime(DateManager.getTodayDateTime("yyyyMMddHHmmss"));
        int result = notificationDAO.insertNotification(notification, receivers);
        System.out.println("notification = " + notification);

        notification.setTitle(NotificationType.getTitleByType(notification.getType()));
        notification.setTime(DateManager.dateParsing(notification.getTime(),"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm"));

        for (String receiver : receivers) {
            System.out.println("받는 주소 : /sub/noti/" + receiver);
            messagingTemplate.convertAndSend("/sub/noti/" + receiver, notification);
        }
    }
}
