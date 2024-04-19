package com.groups.schicken.notification;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationVO {
    private String id;
    private String receiverId;
    private String content;
    private String time;
    private NotificationType type;
    private Object occurer;

    public static NotificationVO of(String content, Object occurer, NotificationType type){
        return NotificationVO.builder()
                .content(content)
                .occurer(occurer)
                .type(type)
                .build();
    }
}
