package com.groups.schicken.board.represent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.groups.schicken.board.BoardService;
import com.groups.schicken.board.BoardVO;
import com.groups.schicken.util.FileManager;
import com.groups.schicken.common.vo.FileVO;
import com.groups.schicken.util.Pager;
@Service
public class RepresentService implements BoardService {

	@Autowired
	private RepresentDAO representDAO;
	@Autowired
	private FileManager fileManager;
	@Value("app.upload.board.qna")
	private String uploadPath;

	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		
		pager.makeIndex();
		pager.makeNum(representDAO.getTotalCount(pager));		
		
		return representDAO.getList(pager);
	}

	@Override
	public int add(BoardVO boardVO,MultipartFile attach) throws Exception {
			int result=representDAO.add(boardVO);
		
			if(attach.isEmpty()) {
				return result;
			}
			
			FileVO fileVO = new FileVO();
			
			
			fileVO.setParentId(boardVO.getId());
			fileVO.setTblId("100");
			
			boolean result1 = fileManager.uploadFile(attach, fileVO);
			
			
			if(result1) {
				int intresult = 1;
				result=intresult;
			}
			return result;	
	}



	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		
		 return representDAO.getDetail(boardVO);
				
	}


	@Override
	public List<BoardVO> pastPage(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return representDAO.pastPage(boardVO);
	}
	
	@Override
	public List<BoardVO> nextPage(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return representDAO.nextPage(boardVO);
	}

	@Override
	public int hit(BoardVO boardVO) throws Exception {
		
		return representDAO.hit(boardVO);
	}

	@Override
	public int update(BoardVO boardVO) throws Exception {
		int result = representDAO.update(boardVO);
		
		
		return result;
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		int result = representDAO.delete(boardVO);
		
		return result;
	}
	
}
