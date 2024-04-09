package com.groups.schicken.board;

import java.util.List;

import com.groups.schicken.util.FileVO;
import com.groups.schicken.util.Pager;

public interface BoardService {
	
	
	public List<BoardVO> getList(Pager pager)throws Exception;
	
	public int add(BoardVO boardVO)throws Exception;
	
	public int fileAdd(FileVO fileVO)throws Exception;
	
	public BoardVO getDetail(BoardVO boardVO)throws Exception;
	
	public BoardVO movePage(Long id)throws Exception;
}
