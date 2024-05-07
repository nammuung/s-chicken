package com.groups.schicken.board.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.Pager;
import com.groups.schicken.department.DepartmentVO;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("list")
	public ResponseEntity<List<ReplyVO>> list(@AuthenticationPrincipal EmployeeVO employeVo,ReplyVO replyVO,Pager pager)throws Exception{
		replyVO.setWriterId(employeVo.getId());
		List<ReplyVO> ar = replyService.list(replyVO, pager);
		System.out.println("들어오니?");
		System.out.println(ar);
		
		return ResponseEntity.ok(ar);
	}
	
	@PostMapping("add")
	public ResponseEntity<Integer> add(@AuthenticationPrincipal EmployeeVO employeVo,@RequestBody ReplyVO replyVO) throws Exception{
		replyVO.setWriterId(employeVo.getId());
		System.out.println(replyVO.getContent());
		int result = replyService.add(replyVO);
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("delete")
	public ResponseEntity<Integer> delete(@RequestBody ReplyVO replyVO) throws Exception{
		
		int result = replyService.delete(replyVO);
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("update")
	public ResponseEntity<Integer> update(@RequestBody ReplyVO replyVO)throws Exception{
		
		int result = replyService.update(replyVO);
		
		return ResponseEntity.ok(result);
	}
	
}
