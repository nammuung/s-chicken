package com.groups.schicken.noteMessage;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.util.Pager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteMessageDAO {
    Long getTotalCount(EmployeeVO employee, NoteMessageBoxType type);

    Integer addMessage(NoteMessageVO message);

    Integer addReceivers(List<String> receivers, Long id);

    List<NoteMessageVO> getList(EmployeeVO employee, Pager pager, NoteMessageBoxType type);

    NoteMessageVO getMessage(NoteMessageVO noteMessage);

    Integer moveBox(String id, String[] messages, NoteMessageBoxType to);

    NoteMessageVO getMessageWithReceivers(NoteMessageVO noteMessage);

    List<NoteMessageVO> getSendList(EmployeeVO employee, Pager pager);
}
