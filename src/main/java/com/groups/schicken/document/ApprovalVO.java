package com.groups.schicken.document;

import lombok.Data;

@Data
public class ApprovalVO {
	private Long documentId;
	private Long employeeId;
	private Long rank;
	private int result;
	private String date;
	private String comment;
}
