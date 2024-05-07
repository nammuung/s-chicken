package com.groups.schicken.noteMessage;

import com.groups.schicken.Employee.EmployeeVO;
import lombok.Data;

import java.util.List;

@Data
public class NoteMessageVO {
    private Long id;
    private String senderId;
    private String senderName;
    private String content;
    private String date;
    private String file;
    private String filename;
    private List<EmployeeVO> receiversVO;
}
