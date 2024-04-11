package com.groups.schicken.noteMessage;

import com.groups.schicken.util.FileVO;
import lombok.Data;

@Data
public class NoteMessageVO {
    private Long id;
    private Long senderId;
    private String content;
    private String date;
    private FileVO file;
}
