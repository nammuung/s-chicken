package com.groups.schicken.notification;

public enum NotificationType {
    NoteMessage, Document, DocumentAccept, DocumentReject, Notice, Chat, Calendar;


    public static String getTitleByType(NotificationType type){
        return switch (type){
            case NoteMessage -> "쪽지가 왔습니다";
            case Document -> "결재할 문서가 있습니다";
            case DocumentAccept -> "문서가 승인 되었습니다";
            case DocumentReject -> "문서가 반려 되었습니다";
            case Notice -> "중요 공지사항이 있습니다";
            case Chat -> null;
            case Calendar -> "일정이 등록 되었습니다";
        };
    }
}
