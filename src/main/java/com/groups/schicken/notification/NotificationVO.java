package com.groups.schicken.notification;

import lombok.Data;

@Data
public class NotificationVO {
    private String id;
    private String receiverId;
    private String content;
    private String time;
    private String occurer;
}
