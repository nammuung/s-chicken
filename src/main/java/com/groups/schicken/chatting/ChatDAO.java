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
}
