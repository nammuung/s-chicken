package com.groups.schicken.notification;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.common.vo.Pager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationDAO notificationDAO;

    public List<NotificationVO> getNotifications(EmployeeVO employeeVO, Boolean read) {
        Pager pager = new Pager();
        pager.setPage(1L);
        pager.makeIndex();

        List<NotificationVO> list = notificationDAO.getNotifications(employeeVO, pager, read);

        for (NotificationVO notification : list) {
            notification.setTitle(getTitleByType(notification.getType()));
            notification.setTime(DateManager.dateParsing(notification.getTime(),"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm"));
        }

        return list;
    }

    public List<NotificationVO> getNotifications(EmployeeVO employeeVO, Pager pager) {
        pager.makeIndex();

        List<NotificationVO> list = notificationDAO.getNotifications(employeeVO, pager, null);
        for (NotificationVO notification : list) {
            notification.setTitle(getTitleByType(notification.getType()));
            notification.setTime(DateManager.dateParsing(notification.getTime(),"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm"));
        }

        return list;
    }

    @Transactional
    public void readNotification(EmployeeVO employeeVO, NotificationVO notification) {
        int result = notificationDAO.readNotification(employeeVO, notification);
        if(result != 1){
            throw new RuntimeException("알림 업데이트 오류");
        }
    }

    public static String getTitleByType(NotificationType type){
        return switch (type){
            case NoteMessage -> "쪽지가 왔습니다";
            case Document -> "결재할 문서가 있습니다";
            case Notice -> "새로운 공지사항이 있습니다";
            case Chat -> null;
        };
    }

}
