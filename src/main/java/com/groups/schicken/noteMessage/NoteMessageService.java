package com.groups.schicken.noteMessage;

import com.groups.schicken.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteMessageService {
    @Autowired
    NoteMessageDAO noteMessageDAO;

    @Transactional
    public Integer sendMessage(NoteMessageVO message, List<String> receivers) {
        Integer result = noteMessageDAO.addMessage(message);
        if(result == 0){
            return 0;
        }

        result = noteMessageDAO.addReceivers(receivers, message.getId());
        return result;
    }
}
