package com.groups.schicken.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groups.schicken.util.FileVO;
import com.groups.schicken.util.Pager;

@Mapper
public interface BoardDAO {
	
	public Long getTotalCount(Pager pager)throws Exception;
	
	public List<BoardVO> getList(Pager pager)throws Exception;
	
	public int add(BoardVO boardVO)throws Exception;
	
	public int fileAdd(FileVO fileVO)throws Exception;
	
	public BoardVO getDetail(BoardVO boardVO)throws Exception;
	
	
}
