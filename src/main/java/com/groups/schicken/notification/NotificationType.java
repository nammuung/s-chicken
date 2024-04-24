package com.groups.schicken.notification;

public enum NotificationType {
    NoteMessage, Document, Notice, Chat;


    public static String getTitleByType(NotificationType type){
        return switch (type){
            case NoteMessage -> "쪽지가 왔습니다";
            case Document -> "결재할 문서가 있습니다";
            case Notice -> "새로운 공지사항이 있습니다";
            case Chat -> null;
        };
    }
}
