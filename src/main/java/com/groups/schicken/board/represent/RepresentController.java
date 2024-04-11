package com.groups.schicken.board.represent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groups.schicken.board.BoardVO;
import com.groups.schicken.util.Pager;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/represent/*")
@Slf4j
public class RepresentController {
	
	
	@Autowired
	private RepresentService representService;
	
	@ModelAttribute("board")
	public String board() {
		
		return "represent";
	}
	
	
	
	@GetMapping("list")
	public String getList(Pager pager, Model model) throws Exception {


		
		return "notice/list";
		
	}
	
	@GetMapping("detail")
	public String getDetail(BoardVO boardVO,Model model) throws Exception {
		boardVO = representService.getDetail(boardVO);
		model.addAttribute("vo", boardVO);
		System.out.println(boardVO.getId());
		
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
	public String getWrite(BoardVO boardVO) throws Exception {
		int result = representService.add(boardVO);
		return "redirect:./impList";		
	}
	
	@GetMapping("impList")
	public String getImpList(Pager pager,Model model) throws Exception {

		List<BoardVO> ar = representService.getList(pager);
		
		model.addAttribute("list",ar);
		model.addAttribute("pager", pager);		
				
		return "board/impList";
	}
	

}
