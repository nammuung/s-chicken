package com.groups.schicken.board.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.groups.schicken.board.BoardVO;
import com.groups.schicken.util.Pager;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("list")
	public ResponseEntity<List<ReplyVO>> list(ReplyVO replyVO,Pager pager)throws Exception{
		List<ReplyVO> ar = replyService.list(replyVO, pager);
		System.out.println("들어오니?");
		return ResponseEntity.ok(ar);
	}
	
	@PostMapping("add")
	public ResponseEntity<Integer> add(@RequestBody ReplyVO replyVO) throws Exception{
		
		System.out.println(replyVO.getContent());
		int result = replyService.add(replyVO);
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("delete")
	public ResponseEntity<Integer> update(@RequestBody ReplyVO replyVO) throws Exception{
		
		int result = replyService.delete(replyVO);
		
		return ResponseEntity.ok(result);
	}
	
}
