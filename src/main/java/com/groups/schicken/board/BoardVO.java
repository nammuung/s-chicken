package com.groups.schicken.board;

import java.util.List;

import com.groups.schicken.franchise.object.FranchiseVO;
import com.groups.schicken.franchise.object.QnaCommentVO;
import com.groups.schicken.franchise.object.QnaVO;
import com.groups.schicken.util.FileVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private Boolean important;

	private List<FileVO> fileVO;
}
