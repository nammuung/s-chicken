package com.groups.schicken.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.groups.schicken.util.FileVO;

@Mapper
public interface BoardDAO {
	
	public Long getTotalCount(Map<String,Object> map)throws Exception;
	
	public List<BoardVO> getList(Map<String,Object> map)throws Exception;
	
	public Long cacTotalCount(Map<String,Object> map)throws Exception;
	
	public List<BoardVO> cacgetList(Map<String,Object> map)throws Exception;
	
	
	public Long allTotalCount(Map<String,Object> map)throws Exception;
	
	public List<BoardVO> allList(Map<String, Object> map)throws Exception;
	
	
	
	
	public int add(BoardVO boardVO)throws Exception;
	
	public int fileAdd(FileVO fileVO)throws Exception;
	
	public BoardVO getDetail(BoardVO boardVO)throws Exception;
	
	public List<BoardVO> pastPage(BoardVO boardVO)throws Exception;
	
	public List<BoardVO> nextPage(BoardVO boardVO)throws Exception;
	
	public int hit(BoardVO boardVO)throws Exception;
	
	public int update(BoardVO boardVO)throws Exception;
	
	public int delete(BoardVO boardVO)throws Exception;
	
}
