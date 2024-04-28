package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeProfileVO;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class ChattingVO {
    private String chatroomId;
    private String chatroomName;
    private List<EmployeeProfileVO> members;
    private List<ChatMessage> chatMessages;
    private String lastReadId;
    private Boolean end;

    public ChatMessage getLastMessage(){
        if(chatMessages.isEmpty()) return null;

        return chatMessages.get(chatMessages.size()-1);
    }

    public Boolean isLastReaded(){
        if(getLastMessage() == null) return true;

        return getLastMessage().getId().equals(lastReadId);
    }
}
