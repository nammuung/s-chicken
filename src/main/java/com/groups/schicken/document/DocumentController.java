package com.groups.schicken.document;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/document/*")
@Slf4j
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;
	
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
	
	@PostMapping("add")

	public ResponseEntity<?> add(DocumentVO documentVO, @RequestPart("attach") MultipartFile attach)throws Exception{
		System.out.println("들어옴");
		
		System.out.println(documentVO);
		return ResponseEntity.ok(documentVO);
	}
	
}
