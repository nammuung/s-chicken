package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeProfileVO;
import lombok.Data;

import java.util.List;

@Data
public class ChatroomVO {
    private String id;
    private String name;
    private ChatroomType type;
    private List<EmployeeProfileVO> members;
    private String notice;
    private Long noReadCount;
    private String joinDate;
    private ChatMessage lastMessage;
}
