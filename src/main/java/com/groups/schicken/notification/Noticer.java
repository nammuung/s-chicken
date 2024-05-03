package com.groups.schicken.notification;


import com.groups.schicken.Employee.EmployeeDAO;
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
    private final EmployeeDAO employeeDAO;

    /**
     * 알림을 보냄
     * @param content 알림창에 보여줄 말
     * @param link 알림창 클릭시 연결될 객체
     * @param type 알림의 타입
     * @param receivers 알림을 받는 사람의 리스트
     */
    public void sendNotice(String content, String link, NotificationType type ,List<String> receivers) {
        System.out.println("content = " + content);
        sendNotice(NotificationVO.of(content, type, link), receivers);
    }

    /**
     * notificationVO로 알림을 보냄
     * @param notification content, link, type, receiver가 있어야함
     */
    public void sendNotice(NotificationVO notification) {
        List<String> receivers = List.of(notification.getReceiverId());
        sendNotice(notification, receivers);
    }

    /**
     * NotificationVO와 EmployeeVO로 알림을 보냄
     * @param notification content, link, type가 있어야함
     * @param receiver id가 있어야함
     */
    public void sendNoticeByEmployeeVO(NotificationVO notification, EmployeeVO receiver) {
        notification.setId(receiver.getId());
        sendNotice(notification);
    }

    /**
     * NotificationVO와 EmployeeVO의 리스트로 알림을 보냄
     * @param notification content, link, type가 있어야함
     * @param receivers id가 있는 EmployeeVO의 리스트
     */
    public void sendNoticeByEmployeeVO(NotificationVO notification, List<EmployeeVO> receivers) {
        sendNotice(notification, receivers.stream().map(EmployeeVO::getId).toList());
    }

    /**
     * NotificationVO와 받는 사람의 id를 리스트로 알림을 보냄
     * @param notification content, link, type가 있어야함
     * @param receivers id(String)의 리스트
     */
    public void sendNotice(NotificationVO notification, List<String> receivers) {
        insertNotification(notification, receivers);

        for (String receiver : receivers) {
            System.out.println("받는 주소 : /sub/noti/" + receiver);
            messagingTemplate.convertAndSend("/sub/noti/" + receiver, notification);
        }
    }

    public void sendNoticeWhole(String content, String link, NotificationType type){
        sendNoticeWhole(NotificationVO.of(content, type, link));
    }

    public void sendNoticeWhole(NotificationVO notification){
        List<String> wholeEmployeeIds = employeeDAO.getWholeIds();
        insertNotification(notification, wholeEmployeeIds);

        messagingTemplate.convertAndSend("/sub/noti/whole", notification);
    }

    private void insertNotification(NotificationVO notification, List<String> receivers){
        notification.setTime(DateManager.getTodayDateTime("yyyyMMddHHmmss"));
        int result = notificationDAO.insertNotification(notification, receivers);
        System.out.println("notification = " + notification);

        notification.setTitle(NotificationType.getTitleByType(notification.getType()));
        notification.setTime(DateManager.dateParsing(notification.getTime(),"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm"));
    }
}
