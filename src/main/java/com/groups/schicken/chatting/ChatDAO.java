package com.groups.schicken.chatting;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatDAO {
    ChatroomVO getChatroomByChatroomId(String chatroomId, String reqId);

    ChatroomVO createChatroom(ChatroomVO chatroom);

    int joinChatroom(String id, ChatroomVO chatroom);

    int createOneChatroom(ChatroomVO chatroom);

    List<ChatroomVO> getChatroomList(String id);

    int insertChat(ChatMessage message);

    String getChatroomName(String chatroomId);

    ChattingVO getChatroomData(String employeeId, String chatroomId);

    void updateLastRead(String sendDate, String chatroomId, String employeeId);

    List<ChatMessage> getChatMessageDataFirst(String chatroomId, String lastReadTime, String page);

    List<ChatMessage> getChatMessageData(String chatroomId, String from, String direction);
}
