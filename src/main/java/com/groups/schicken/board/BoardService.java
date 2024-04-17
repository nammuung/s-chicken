package com.groups.schicken.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.groups.schicken.common.vo.Pager;

public interface BoardService {
	
	
	public List<BoardVO> getList(Pager pager,BoardVO boardVO)throws Exception;
	
	public List<BoardVO> cacList(Pager pager,BoardVO boardVO)throws Exception;
	
	public List<BoardVO> allgetList(Pager pager,BoardVO boardVO)throws Exception;
	
	
	public int add(BoardVO boardVO, MultipartFile attach)throws Exception;
	
	public BoardVO getDetail(BoardVO boardVO)throws Exception;
	
	public List<BoardVO> pastPage(BoardVO boardVO)throws Exception;
	public List<BoardVO> nextPage(BoardVO boardVO)throws Exception;
	
	public int hit(BoardVO boardVO)throws Exception;
	
	public int update(BoardVO boardVO,MultipartFile file)throws Exception;
	
	public int delete(BoardVO boardVO)throws Exception;


}
