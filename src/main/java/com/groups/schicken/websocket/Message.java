package com.groups.schicken.websocket;

import lombok.Data;

@Data
public class Message {
    private String from;
    private String to;
    private String date;
    private Object content;
}
