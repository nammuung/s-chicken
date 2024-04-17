package com.groups.schicken.board.reply;

import lombok.Data;

@Data
public class ReplyVO {
	private Long id;
	
	
	private String content;
	private String date;
	private Long ref;
	private Long step;
	private Long depth;
	private String profile;
	private String parentId;
	
	private Long writerId;
	
}
