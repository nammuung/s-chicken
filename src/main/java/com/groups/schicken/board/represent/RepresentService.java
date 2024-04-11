package com.groups.schicken.board.represent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groups.schicken.board.BoardService;
import com.groups.schicken.board.BoardVO;
import com.groups.schicken.util.FileManager;
import com.groups.schicken.util.FileVO;
import com.groups.schicken.util.Pager;
@Service
public class RepresentService implements BoardService {

	@Autowired
	private RepresentDAO representDAO;

	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		
		pager.makeIndex();
		pager.makeNum(representDAO.getTotalCount(pager));		
		
		return representDAO.getList(pager);
	}

	@Override
	public int add(BoardVO boardVO) throws Exception {
		int result=representDAO.add(boardVO);
		
		return result;
		
	}

	@Override
	public int fileAdd(FileVO fileVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		 return representDAO.getDetail(boardVO);
				
	}
	
}
