package com.groups.schicken.board.represent;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.transfer.internal.DownloadS3ObjectCallable;
import com.groups.schicken.board.BoardVO;

import com.groups.schicken.util.FileManager;
import com.groups.schicken.util.FileVO;
import com.groups.schicken.util.Pager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/represent/*")
@Slf4j
@RequiredArgsConstructor
public class RepresentController {
	
	
	@Autowired
	private RepresentService representService;	
	
	@ModelAttribute("board")
	public String board() {
		
		return "represent";
	}

	
	
	@GetMapping("detail")
	public String getDetail(BoardVO boardVO,Model model) throws Exception {
		
		int result = representService.hit(boardVO);
		
		boardVO = representService.getDetail(boardVO);
		model.addAttribute("vo", boardVO);		
		
		List<BoardVO> ar = representService.pastPage(boardVO);
		System.out.println(ar);
		model.addAttribute("move", ar);
		
		List<BoardVO> br = representService.nextPage(boardVO);
		
		model.addAttribute("next", br);

		
		return "board/detail";
	}
	
	@GetMapping("write")
	public String getWrite() {
		return "board/write";
	}
	
	@PostMapping("write")
	public String getWrite(BoardVO boardVO,@RequestParam("attach") MultipartFile attach) throws Exception {
		
		int result = representService.add(boardVO,attach);
		System.out.println(attach+"++++++++++++++++++++++++++");
		return "redirect:./impList";		
	}
	
	@GetMapping("impList")
	public String getImpList(Pager pager,Model model,BoardVO boardVO) throws Exception {

		List<BoardVO> ar = representService.getList(pager,boardVO);
		
		model.addAttribute("list",ar);
		model.addAttribute("pager", pager);
		
		System.out.println(pager.isLast());
		System.out.println(pager.isStart());

				
		return "board/impList";
	}
	
	@GetMapping("update")
	public String getUpdate(BoardVO boardVO,Model model)throws Exception{
		boardVO = representService.getDetail(boardVO);
		model.addAttribute("vo", boardVO);	
		System.out.println(boardVO.getId());
		
		return "board/update";
	}
	
	@PostMapping("update")
	public String setUpdate(BoardVO boardVO)throws Exception{
		System.out.println(boardVO.getImportant());
		int result = representService.update(boardVO);
		System.out.println("로직 들어오기");

		return "redirect:./impList";
	}
	
	@PostMapping("delete")
	public String delete(BoardVO boardVO)throws Exception{
		int result = representService.delete(boardVO);
		
		
		return "redirect:./impList";
	}
	
	

}
