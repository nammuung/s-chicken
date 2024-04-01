package com.groups.schicken.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/notice/*")
public class noticeController {
	
	@GetMapping("list")
	public void getList() {
		
	}
	
	@GetMapping("detail")
	public void getDetail() {
		
	}
	
	@GetMapping("write")
	public void getWrite() {
		
	}
	
}
