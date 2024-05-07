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

    public List<NotificationVO> getNotifications(EmployeeVO employeeVO){
        return getNotifications(employeeVO,null);
    }

    public List<NotificationVO> getNotifications(EmployeeVO employeeVO, Boolean read){
        Pager pager = new Pager();
        pager.setPage(1L);

        return getNotifications(employeeVO, pager, read);
    }

    public List<NotificationVO> getNotifications(EmployeeVO employeeVO, Pager pager, Boolean read) {
        pager.makeIndex();

        List<NotificationVO> list = notificationDAO.getNotifications(employeeVO, pager, read);
        for (NotificationVO notification : list) {
            notification.setTitle(NotificationType.getTitleByType(notification.getType()));
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


    public void readAll(String employeeId) {
        notificationDAO.readAll(employeeId);
    }
}
