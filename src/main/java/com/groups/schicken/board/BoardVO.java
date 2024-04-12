package com.groups.schicken.board;

import java.util.List;

import com.groups.schicken.common.vo.FileVO;

import lombok.Data;

@Data
public class BoardVO {
	private Long id;
	private String writeDate;
	private String title;
	private String content;
	private Boolean isDelete;
	private Long hit;
	private int sort;
	private Long writerId;
	private Boolean important;

	private List<FileVO> fileVOs;
}
