package com.groups.schicken.chatting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String id;
    private String chatroomId;
    private String senderId;
    private ChattingType type;
    private String sendDate;
    private String content;

    public static ChatMessage getCancelMessage(String id){
        return ChatMessage.builder()
                .id(id)
                .type(ChattingType.Cancel)
                .build();
    }

    public static ChatMessage of(String id, String chatroomId, String senderId, String sendDate, String content) {
        return ChatMessage.builder()
                .id(id)
                .chatroomId(chatroomId)
                .senderId(senderId)
                .type(ChattingType.Message)
                .sendDate(sendDate)
                .content(content)
                .build();
    }
}
