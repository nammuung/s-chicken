package com.groups.schicken.notification;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.Pager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationDAO {
    int insertNotification(NotificationVO notification, List<String> receivers);

    List<NotificationVO> getNotifications(EmployeeVO employee, Pager pager ,Boolean read);

    int readNotification(EmployeeVO employee, NotificationVO notification);

    void readAll(String employeeId);
}
