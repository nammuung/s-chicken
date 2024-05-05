package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeProfileVO;
import lombok.Data;

import java.util.List;

@Data
public class ChattingVO {
    private String chatroomId;
    private String chatroomName;
    private ChatroomType type;
    private List<EmployeeProfileVO> members;
    private List<ChatMessage> chatMessages;
    private String lastReadTime;
    private Boolean end;

    public ChatMessage getLastMessage(){
        if(chatMessages.isEmpty()) return null;

        return chatMessages.get(chatMessages.size()-1);
    }

    public Boolean isLastReaded(){
        if(getLastMessage() == null) return true;

        return getLastMessage().getId().equals(lastReadTime);
    }
}
