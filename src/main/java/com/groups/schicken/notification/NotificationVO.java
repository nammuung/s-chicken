package com.groups.schicken.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationVO {
    private String id;
    private String receiverId;
    private String title;
    private String content;
    private String time;
    private NotificationType type;
    private String link;
    private Boolean isReaded;

    public static NotificationVO of(String content, NotificationType type, String link){
        return NotificationVO.builder()
                .content(content)
                .link(link)
                .type(type)
                .build();
    }
}
