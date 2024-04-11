package com.groups.schicken.noteMessage;

import com.groups.schicken.util.Pager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteMessageDAO {
    Integer addMessage(NoteMessageVO message);

    Integer addReceivers(List<String> receivers, Long id);
}
