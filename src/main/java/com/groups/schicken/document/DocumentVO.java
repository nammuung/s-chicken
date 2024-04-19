package com.groups.schicken.document;

import lombok.Data;

@Data
public class DocumentVO {
	private Long id;
	private String title;
	private String content;
	private String start;
	private String end;
	private String writeDate;
	private String status;
	private Long templeteId;
	private Long employeeId;
	private String des;
}
