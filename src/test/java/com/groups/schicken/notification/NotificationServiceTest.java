package com.groups.schicken.notification;

import com.groups.schicken.Employee.EmployeeVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.include=dev")
class NotificationServiceTest {

    @Autowired
    NotificationService service;

    @Test
    void getNotifications() {
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setId("19990806228");

        var list = service.getNotifications(employeeVO, null);
        System.out.println("list = " + list);
    }
}
