package com.groups.schicken.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.Pager;



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
	public void approval(@AuthenticationPrincipal EmployeeVO employeeVO ,DocumentVO documentVO,Model model)throws Exception{
	
		List<DocumentVO> ar = documentService.approval(employeeVO);
		model.addAttribute("list", ar);		
	}

	
	
	@GetMapping("document")
	public void documentList(Pager pager,DocumentVO documentVO,TemplateVO templateVO,Model model) throws Exception {
		List<DocumentVO> ar = documentService.list(documentVO, templateVO, pager);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);		

	}
	@GetMapping("ref")
	public void documentRef() {
		
	}
	@GetMapping("temp")
	public void documentTemp() {
		
	}
	@GetMapping("exList/bonus")
	public void sang() {
		
	}
	@GetMapping("exList/pay")
	public void pay() {
		
	}
	
	@PostMapping("add")

	public ResponseEntity<?> add(DocumentVO documentVO,@RequestParam HashMap<String,Object> map, @RequestPart("attach") MultipartFile attach,TemplateVO templateVO)throws Exception{
		ApprovalVO approvalVO = new ApprovalVO();
		
		int result = documentService.add(documentVO);
		approvalVO.setDocumentId(documentVO.getId());
		approvalVO.setResult(0);
		
				
		String ranks = (String) map.get("rank");
		String ids = (String)map.get("employeeId");
		String[] rankArray = ranks.split(",");
		String[] idsArray = ids.split(",");
		String[] date = documentVO.getWriteDate().split(" ");
		

		
		documentVO.setWriteDate(date[0]);
		
		
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
	
	@GetMapping("writenList/bonus")
	public void writenBonus(DocumentVO documentVO,Model model)throws Exception{
		List<DocumentVO> ar=documentService.getDetail(documentVO);
		System.out.println(ar.get(0));
		model.addAttribute("list", ar);
	}
	
	@GetMapping("approvalList/bonus")
	public void approvalDetail(@AuthenticationPrincipal EmployeeVO employeeVO,Model model,DocumentVO documentVO)throws Exception{
				
		List<DocumentVO> ar = documentService.approvalDetail(employeeVO);
		System.out.println(ar.get(0).getApprovalVOs());
		System.out.println(ar.get(1));
		model.addAttribute("list", ar);
		
	}

	
}
