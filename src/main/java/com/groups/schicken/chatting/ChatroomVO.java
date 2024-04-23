package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeVO;
import lombok.Data;

import java.util.List;

@Data
public class ChatroomVO {
    private String id;
    private String name;
    private ChatroomType type;
    private List<EmployeeVO> members;
    private String notice;
    private String lastChatting;
    private String lastConnect;
    private String joinDate;
}
