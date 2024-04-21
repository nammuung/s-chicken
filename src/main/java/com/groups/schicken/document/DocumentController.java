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

	public ResponseEntity<?> add(DocumentVO documentVO,@RequestParam HashMap<String,Object> map, @RequestPart("attach") MultipartFile attach)throws Exception{
		ApprovalVO approvalVO = new ApprovalVO();
		
		int result = documentService.add(documentVO);
		approvalVO.setDocumentId(documentVO.getId());
		approvalVO.setResult(0);
				
		String ranks = (String) map.get("rank");
		String ids = (String)map.get("employeeId");
		String[] rankArray = ranks.split(",");
		String[] idsArray = ids.split(",");
		
		Long[] longRankArray = new Long[rankArray.length]; 
		Long[] longIdsArray = new Long[idsArray.length]; 

		// 문자열 배열의 각 요소를 롱타입으로 변환하여 새롭게 생성한 롱타입 배열에 저장합니다.
		for (int i = 0; i < rankArray.length; i++) {
		    longRankArray[i] = Long.parseLong(rankArray[i]);
		    longIdsArray[i] = Long.parseLong(idsArray[i]);
		    approvalVO.setRank(longRankArray[i]);
		    approvalVO.setEmployeeId(longIdsArray[i]);
		    
		    result = documentService.appAdd(approvalVO);
		}

		return ResponseEntity.ok(documentVO);
	}
	
}
