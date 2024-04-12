package com.groups.schicken.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groups.schicken.common.vo.FileVO;
import com.groups.schicken.util.Pager;

@Mapper
public interface BoardDAO {
	
	public Long getTotalCount(Pager pager)throws Exception;
	
	public List<BoardVO> getList(Pager pager)throws Exception;
	
	public int add(BoardVO boardVO)throws Exception;
	
	public int fileAdd(FileVO fileVO)throws Exception;
	
	public BoardVO getDetail(BoardVO boardVO)throws Exception;
	
	public List<BoardVO> pastPage(BoardVO boardVO)throws Exception;
	
	public List<BoardVO> nextPage(BoardVO boardVO)throws Exception;
	
	public int hit(BoardVO boardVO)throws Exception;
	
	public int update(BoardVO boardVO)throws Exception;
	
	public int delete(BoardVO boardVO)throws Exception;
	
}
