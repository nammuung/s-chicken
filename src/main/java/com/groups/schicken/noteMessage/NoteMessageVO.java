package com.groups.schicken.noteMessage;

import com.groups.schicken.common.vo.FileVO;
import lombok.Data;

@Data
public class NoteMessageVO {
    private Long id;
    private String senderId;
    private String senderName;
    private String content;
    private String date;
    private String file;
}
