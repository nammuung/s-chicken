package com.groups.schicken.chatting;

import com.groups.schicken.Employee.EmployeeDAO;
import com.groups.schicken.Employee.EmployeeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatDAO chatDAO;
    private final EmployeeDAO employeeDAO;

    public ChatroomVO getOneChatrooms(String reqId, String targetId) {
        String chatroomId = makeOneChatroomId(List.of(reqId, targetId));
        return chatDAO.getChatroomByChatroomId(chatroomId, reqId);
    }

    @Transactional
    public ChatroomVO createOneChatroom(String id1, String id2) {
        List<String> ids = List.of(id1, id2);
        String chatroomId = makeOneChatroomId(ids);

        ChatroomVO created = new ChatroomVO();
        created.setId(chatroomId);
        created.setType(ChatroomType.One);
        int result = chatDAO.createOneChatroom(created);
        if(result != 1){
            throw new RuntimeException("채팅방 생성 실패");
        }

        /* 상대방의 이름으로 기본 채팅방 이름 지정 */
        List<EmployeeVO> employees = employeeDAO.getNamesByIds(ids);
        for (EmployeeVO employee : employees) {
            for (String id : ids) {
                if (id.equals(employee.getId())) continue;
                employee.setId(id);
                break;
            }
            created.setName(employee.getName());

            if(!joinChatroom(employee.getId(), created)){
                throw new RuntimeException("생성된 채팅방 입장 실패");
            }
        }

        return created;
    }

    public Boolean joinChatroom(String id, ChatroomVO chatroom){
        int result = chatDAO.joinChatroom(id, chatroom);
        return result == 1;
    }

    private String makeOneChatroomId(List<String> list){
        return list.stream().sorted().collect(Collectors.joining());
    }

    public List<ChatroomVO> getChatroomList(String id) {
        return chatDAO.getChatroomList(id);
    }
}
