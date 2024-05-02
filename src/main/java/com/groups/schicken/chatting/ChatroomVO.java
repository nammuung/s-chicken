package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeProfileVO;
import lombok.Data;

import java.util.List;

@Data
public class ChatroomVO {
    private String id;
    private String name;
    private ChatroomType type;
    private String profileURL;
    private List<EmployeeProfileVO> members;
    private String notice;
    private String lastChatting;
    private String lastConnect;
    private String joinDate;
    private ChatMessage lastMessage;

    public String getProfileURL(){
        if(type.equals(ChatroomType.Many)) {
            return "/img/단체.png";
        }

        return members.get(0).getProfileImg();
    }
}
