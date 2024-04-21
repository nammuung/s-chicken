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
    private String link;

    public static NotificationVO of(String content, NotificationType type, String link){
        return NotificationVO.builder()
                .content(content)
                .link(link)
                .type(type)
                .build();
    }
}
