package com.groups.schicken.notification;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.Pager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/notificationPage")
    public String getNotificationPage(@AuthenticationPrincipal EmployeeVO employeeVO, Pager pager, Model model) {
        List<NotificationVO> list = notificationService.getNotifications(employeeVO);
        model.addAttribute("notificationList", list);
        return "notification/notification";
    }

    @MessageMapping("/noti")
    public void sendNotification(NotificationVO notificationVO){
        System.out.println("notificationVO = " + notificationVO);
        messagingTemplate.convertAndSend("/sub/noti", notificationVO);
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationVO>> getNotifications(@AuthenticationPrincipal EmployeeVO employeeVO, Pager pager, Boolean read){
        System.out.println("read = " + read);

        List<NotificationVO> list;
        if(pager == null){
            list = notificationService.getNotifications(employeeVO, read);
        } else {
            list = notificationService.getNotifications(employeeVO, pager, read);
        }

        return ResponseEntity.ok(list);
    }

    @PostMapping("/notifications/read")
    public ResponseEntity<String> getNotifications(@AuthenticationPrincipal EmployeeVO employeeVO, @RequestBody NotificationVO notification){
        notificationService.readNotification(employeeVO, notification);
        return ResponseEntity.ok("read");
    }
}
