package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeDAO;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.util.DateManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ChatDAO chatDAO;
    private final EmployeeDAO employeeDAO;

    /*

        채팅방 관련

    */

    /**
     * 1:1 채팅방의 정보를 가져온다
     *
     * @param reqId    채팅방 정보를 가져오려는 사람의 아이디
     * @param targetId 채팅하려는 대상의 아이디
     */
    public ChatroomVO getOneChatrooms(String reqId, String targetId) {
        String chatroomId = makeOneChatroomId(List.of(reqId, targetId));
        return chatDAO.getChatroomByChatroomId(chatroomId, reqId);
    }

    /**
     * 두 사람의 아이디를 받아 1:1 채팅방 생성
     * 채팅방의 기본 이름은 서로 상대방의 이름이 된다
     * 채팅방 생성후 자동으로 채팅방에 입장
     */
    @Transactional
    public ChatroomVO createOneChatroom(String id1, String id2) {
        List<String> ids = List.of(id1, id2);
        String chatroomId = makeOneChatroomId(ids);

        ChatroomVO created = new ChatroomVO();
        created.setId(chatroomId);
        created.setType(ChatroomType.One);
        int result = chatDAO.createOneChatroom(created);
        if (result != 1) {
            throw new RuntimeException("채팅방 생성 실패");
        }

        List<EmployeeVO> employees = employeeDAO.getNamesByIds(ids);
        for (EmployeeVO employee : employees) {
            for (String id : ids) {
                if (id.equals(employee.getId())) continue;
                employee.setId(id);
                break;
            }
            created.setName(employee.getName());

            if (!joinChatroom(employee.getId(), created)) {
                throw new RuntimeException("생성된 채팅방 입장 실패");
            }
        }

        return created;
    }

    /**
     * 채팅방에 입장한다
     *
     * @param id       입장하려는 사람의 아이디
     * @param chatroom 입장하려는 채팅방의 VO
     * @return DB에 insert가 성공시 true 그렇지 않으면 false
     */
    public Boolean joinChatroom(String id, ChatroomVO chatroom) {
        int result = chatDAO.joinChatroom(id, chatroom);
        return result == 1;
    }

    /**
     * 채팅방에 입장한다
     * 채팅방의 기본 이름을 생성하여 입장한다
     *
     * @param id         입장하려는 사람의 아이디
     * @param chatroomId 입장하려는 채팅방의 아이디
     * @return DB에 insert가 성공시 true 그렇지 않으면 false
     */
    public Boolean joinChatroom(String id, String chatroomId) {
        ChatroomVO chatroom = new ChatroomVO();
        chatroom.setId(chatroomId);
        chatroom.setName(getChatroomName(chatroomId));

        return joinChatroom(id, chatroom);
    }


    /**
     * employeeId로 해당 employee가 들어가 있는 채팅방 리스트를 가져온다
     */
    public List<ChatroomVO> getChatroomList(String employeeId) {
        return chatDAO.getChatroomList(employeeId);
    }

    /*

        채팅 관련

    */

    /**
     * 채팅방 아이디, 채팅 보낸 사람 아이디, 채팅 내용을 받아
     * 채팅의 아이디와 보낸시간을 생성하고 Chatmessage 객체로 리턴
     */
    public ChatMessage makeMessage(String chatroomId, String senderId, String content) {
        String sendDate = DateManager.getTodayDateTime("yyyyMMddHHmmssSSS");
        String id = chatroomId + senderId + sendDate;
        id = (new BigInteger(id)).toString(16);

        return ChatMessage.of(id, chatroomId, senderId, sendDate, content);
    }

    /**
     * 메세지를 DB에 Insert
     */
    public Boolean insertMessage(ChatMessage message) {
        int result = chatDAO.insertChat(message);
        return result == 1;
    }

    private String makeOneChatroomId(List<String> list) {
        return list.stream().sorted().collect(Collectors.joining());
    }
    private String getChatroomName(String chatroomId) {
        return chatDAO.getChatroomName(chatroomId);
    }

    public ChattingVO getChattingData(String id, String chatroomId) {
        return getChattingData(id, chatroomId, null);
    }

    @Transactional
    public ChattingVO getChattingData(String employeeId, String chatroomId, String page){
        ChattingVO chattingData = chatDAO.getChatroomData(employeeId, chatroomId);

        if(chattingData == null){
            return null;
        }


        chattingData.setChatMessages(chatDAO.getChatMessageData(chatroomId, chattingData.getLastReadId(), page));

        System.out.println("chattingData = " + chattingData);
        System.out.println("chattingData = " + chattingData);
        System.out.println("chattingData = " + chattingData);
        System.out.println("chattingData = " + chattingData);

        if(!chattingData.isLastReaded()){
            chatDAO.updateLastRead(chattingData.getLastMessage().getId(), chatroomId, employeeId);
        }

        return chattingData;
    }
}
