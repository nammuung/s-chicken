package com.groups.schicken.document;

import lombok.Data;

@Data
public class SaveAppVO {
	
	private Long id;
	private String employeeId;
	private String appId;	
	private String title;
	private int rank;
	private String date;
	
}
