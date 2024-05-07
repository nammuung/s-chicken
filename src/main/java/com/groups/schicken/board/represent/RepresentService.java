package com.groups.schicken.board.represent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.groups.schicken.notification.Noticer;
import com.groups.schicken.notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.groups.schicken.board.BoardService;
import com.groups.schicken.board.BoardVO;
import com.groups.schicken.common.util.FileManager;
import com.groups.schicken.common.vo.FileVO;
import com.groups.schicken.common.vo.Pager;
@Service
public class RepresentService implements BoardService {

	@Autowired
	private RepresentDAO representDAO;
	@Autowired
	private FileManager fileManager;
	@Autowired
	private Noticer noticer;
	@Value("app.upload.board.qna")
	private String uploadPath;
	
	public List<BoardVO> impList(BoardVO boardVO) throws Exception{
		List<BoardVO> ar = representDAO.impList(boardVO);		
		
		return ar;
	}


	@Override
	public List<BoardVO> getList(Pager pager,BoardVO boardVO) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();

		map.put("pager", pager);
		map.put("boadVO",boardVO);

		pager.makeIndex();
		pager.makeNum(representDAO.getTotalCount(map));
		System.out.println(pager.getStartIndex());
		System.out.println(pager.getPerPage());
		System.out.println(pager.getTotalPage());
		System.out.println(pager.getLastNum());

		return representDAO.getList(map);
	}

	@Override
	public List<BoardVO> cacList(Pager pager, BoardVO boardVO) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();

		map.put("pager", pager);
		map.put("boadVO",boardVO);

		pager.makeIndex();
		pager.makeNum(representDAO.cacTotalCount(map));
		System.out.println(pager.getStartIndex());
		System.out.println(pager.getPerPage());
		System.out.println(pager.getTotalPage());
		System.out.println(pager.getLastNum());

		return representDAO.cacgetList(map);

	}

	@Override
	public List<BoardVO> allgetList(Pager pager, BoardVO boardVO) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();

		map.put("pager", pager);
		map.put("boardVO", boardVO);

		pager.makeIndex();
		pager.makeNum(representDAO.allTotalCount(map));

		System.out.println(representDAO.allTotalCount(map));

		return representDAO.allList(map);
	}


	@Override
	public int add(BoardVO boardVO,MultipartFile attach) throws Exception {
		BoardVO vo2 = new BoardVO();
			int result=representDAO.add(boardVO);
			
			if(boardVO.getImportant()) {				
				vo2.setImportant(boardVO.getImportant());
				vo2.setWriterId(boardVO.getWriterId());
				List<BoardVO> ar = representDAO.impList(vo2);
				for(int i = 0 ; i<ar.size() ; i++) {
					vo2.setRank(ar.get(i).getRank()+1L);
					vo2.setId(ar.get(i).getId());
					result = representDAO.impRank(vo2);
				}
			}

			if(boardVO.getImportant()){
				noticer.sendNoticeWhole(getNoticeContent(boardVO.getTitle()), boardVO.getId() + "" , NotificationType.Notice);
			}
			if(attach.isEmpty()) {
				return result;
			}

			FileVO fileVO = new FileVO();


			fileVO.setParentId(boardVO.getId());
			fileVO.setTblId("102");

			boolean result1 = fileManager.uploadFile(attach, fileVO);


			if(result1) {
				int intresult = 1;
				result=intresult;
			}


			return result;
	}

	private String getNoticeContent(String title){
		if(title.length() < 20){
			return title;
		}

		return title.substring(0, 20) + "...";
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
	public int update(BoardVO boardVO,MultipartFile file) throws Exception {
		BoardVO vo2 = new BoardVO();
		int result=representDAO.update(boardVO);
		
		if(boardVO.getImportant()) {				
			vo2.setImportant(boardVO.getImportant());
			vo2.setWriterId(boardVO.getWriterId());
			List<BoardVO> ar = representDAO.impList(vo2);
			for(int i = 0 ; i<ar.size() ; i++) {
				vo2.setRank(ar.get(i).getRank()+1L);
				vo2.setId(ar.get(i).getId());
				result = representDAO.impRank(vo2);
			}
		}else {
			boardVO.setRank(0L);
			result = representDAO.impRank(boardVO);
		}

		if(file.isEmpty()) {
			return result;
		}

		FileVO fileVO = new FileVO();
		fileVO.setParentId(boardVO.getId());

		boolean result1 = fileManager.deleteFile(fileVO);

		fileVO.setParentId(boardVO.getId());
		fileVO.setTblId("102");
		result1= fileManager.uploadFile(file, fileVO);

		if(result1) {
			int intresult=1;
			result = intresult;
		}


		return result;
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		int result = representDAO.delete(boardVO);

		return result;
	}






}
