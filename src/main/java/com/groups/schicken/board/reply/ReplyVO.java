package com.groups.schicken.board.reply;

import com.groups.schicken.Employee.EmployeeVO;

import lombok.Data;

@Data
public class ReplyVO {
	private Long id;
	
	
	private String content;
	private String date;
	private String profile;
	private String parentId;
	private String writerId;
	
	private EmployeeVO employeeVO;
	
}
