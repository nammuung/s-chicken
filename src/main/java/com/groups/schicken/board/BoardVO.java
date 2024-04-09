package com.groups.schicken.board;

import java.util.List;

import com.groups.schicken.util.FileVO;

import lombok.Data;

@Data
public class BoardVO {
	private Long id;
	private String writeDate;
	private String title;
	private String content;
	private Boolean isDeleted;
	private Long hit;
	private int sort;
	private Long writerId;
	private Long next;
	private Long last;
	private String nextTitle;
	private String lastTitle;
	
	private List<FileVO> fileVOs;
}
