package com.groups.schicken.board.represent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.groups.schicken.board.BoardVO;
import com.groups.schicken.common.vo.Pager;

@Controller
@RequestMapping("/cac/*")
public class CacController {
	@Autowired
	private RepresentService representService;
	
	@ModelAttribute("board")
	public String board() {
		
		return "cac";
	}
	
	@GetMapping("cacList")
	public String cacList(BoardVO boardVO,Pager pager,Model model) throws Exception{
		List<BoardVO> ar = representService.cacList(pager, boardVO);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		
		System.out.println(ar);
		
		return "board/list";
	}
	
	@GetMapping("write")
	public String write() throws Exception{
		
		return "board/write";
	}
	
	@GetMapping("detail")
	public String cacdetail(BoardVO boardVO,Model model)throws Exception{
		int result = representService.hit(boardVO);
		
		boardVO = representService.getDetail(boardVO);
		model.addAttribute("vo", boardVO);
		
		List<BoardVO> ar = representService.pastPage(boardVO);
		
		model.addAttribute("move", ar);
		
		List<BoardVO> br = representService.nextPage(boardVO);
		
		model.addAttribute("next", br);
		
		return "board/detail";
		
	}
	
	@GetMapping("update")
	public String update(BoardVO boardVO,Model model)throws Exception{
		boardVO = representService.getDetail(boardVO);
		model.addAttribute("vo", boardVO);
		return "board/update";
	}
	
	@PostMapping("update")
	public String update(BoardVO boardVO,@RequestParam(value="attach") MultipartFile attach)throws Exception{
		int result = representService.update(boardVO, attach);
		
		return "redirect:./cacList";		
		
	}
	@PostMapping("delete")
	public String delete(BoardVO boardVO)throws Exception{
		int result = representService.delete(boardVO);
		
		return "redirect:./cacList";
	}
	
	
	
}
