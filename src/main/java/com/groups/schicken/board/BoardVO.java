package com.groups.schicken.board;

import com.groups.schicken.common.vo.FileVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	private Long id;
	private String writeDate;
	private String title;
	private String content;
	private Boolean isDeleted;
	private Long hit;
	private int sort;
	private Long writerId;
	private Boolean important;

	private List<FileVO> fileVO;
}
