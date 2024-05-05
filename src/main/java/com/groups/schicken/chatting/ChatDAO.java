package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeProfileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChatDAO {
    ChatroomVO getChatroomByChatroomId(String chatroomId, String reqId);

    int createChatroom(ChatroomVO chatroom);

    int joinChatroom(String id, ChatroomVO chatroom);

    int createOneChatroom(ChatroomVO chatroom);

    List<ChatroomVO> getChatroomList(String id);

    int insertChat(ChatMessage message);

    String getChatroomName(String chatroomId);

    ChattingVO getChatroomData(String employeeId, String chatroomId);

    int updateLastRead(String sendDate, String chatroomId, String employeeId);

    List<ChatMessage> getChatMessageDataFirst(String chatroomId, String lastReadTime, String page);

    List<ChatMessage> getChatMessageData(String chatroomId, String from, String direction);

    List<ChatMessage> getLastChatData(List<String> list);

    int updateLastReadById(String employeeId, String chatroomId, String chatId);

    int updateTitle(String empId, String chatroomId, String name);

    int insertMember(String chatroomId,String name, String joinDate, String[] members);

    List<EmployeeProfileVO> getMembersByChatroomId(String chatroomId);

    int outChatroom(String empId, String chatroomId);
}
