package com.groups.schicken.document;

import com.groups.schicken.common.vo.FileVO;

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
	private Long templateId;
	private Long employeeId;
	private String des;
	
	private FileVO fileVO;

}
