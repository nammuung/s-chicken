package com.groups.schicken.document;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/document/*")
@Slf4j
public class documentController {
	
	@GetMapping("list")
	public void getList() {
		
	}
	
	@GetMapping("approvalList")
	public void getReportList() {
		
	}
	
	@GetMapping("document")
	public void documentList() {
		
	}
	@GetMapping("ref")
	public void documentRef() {
		
	}
	@GetMapping("temp")
	public void documentTemp() {
		
	}
	@GetMapping("bonus/bonus")
	public void sang() {
		
	}
	@GetMapping("pay/pay")
	public void pay() {
		
	}
	
}
