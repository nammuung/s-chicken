package com.groups.schicken.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.groups.schicken.common.util.DateManager;
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
	
	@PostMapping("document/saveDel")
	@ResponseBody
	public ResponseEntity<Integer> saveDel(@RequestBody SaveAppVO saveAppVO)throws Exception{
		System.out.println(saveAppVO);
		int result = documentService.appDel(saveAppVO);		
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("document/saveApp")
	@ResponseBody
	public ResponseEntity<?> saveApp(@RequestBody List<SaveAppVO> ar)throws Exception{
		SaveAppVO saveAppVO = new SaveAppVO();
		EmployeeVO employeeVO = new EmployeeVO();
		for(int i = 0 ; i < ar.size() ; i++) {
			saveAppVO = ar.get(i);
			documentService.appSave(saveAppVO);
		}
		
		employeeVO.setId(ar.get(0).getEmployeeId());
		
		List<SaveAppVO> br = documentService.getTitle(employeeVO);		
		System.out.println(br);
		
		return ResponseEntity.ok(br);
	}
	
	@PostMapping("document/approvalUpdate")
	public ResponseEntity<Integer> approvalUpdate(ApprovalVO approvalVO)throws Exception{
		int result = documentService.resultUpdate(approvalVO);
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("document/refuseUpdate")
	public ResponseEntity<Integer> refuseUpdate(ApprovalVO approvalVO)throws Exception{
		int result = documentService.refuseUpdate(approvalVO);
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("approvalList")
	public void approval(@AuthenticationPrincipal EmployeeVO employeeVO,Model model,Pager pager)throws Exception{
		
		List<DocumentVO> ar = documentService.approvalList(employeeVO,pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		System.out.println(pager);
	}

	
	
	@GetMapping("document")
	public void documentList(String cate,@AuthenticationPrincipal EmployeeVO employeeVO,@RequestParam Map<String, Object> map,Pager pager,DocumentVO documentVO,TemplateVO templateVO,Model model) throws Exception {
		System.out.println(map);
		System.out.println(cate);
		System.out.println(employeeVO);
		cate=(String)map.get("category");		
		
		documentVO.setWriterId(employeeVO.getId());
		
		List<DocumentVO> ar = documentService.list(employeeVO,documentVO, templateVO, pager,cate);
		
		System.out.println(ar);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		System.out.println(pager);
	}
	
	@GetMapping("callList")
	public void callList(String cate,@AuthenticationPrincipal EmployeeVO employeeVO,@RequestParam Map<String, Object> map,Pager pager,DocumentVO documentVO,TemplateVO templateVO,Model model) throws Exception {
		System.out.println(map);
		System.out.println(cate);
		System.out.println(employeeVO);
		cate=(String)map.get("category");		
		
		documentVO.setWriterId(employeeVO.getId());
		
		List<DocumentVO> ar = documentService.list(employeeVO,documentVO, templateVO, pager,cate);
		
		System.out.println(ar);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		System.out.println(pager);
	}
	
	@GetMapping("ref")
	public void documentRef() {
		
	}
	@GetMapping("tempList")
	public void documentTemp(@AuthenticationPrincipal EmployeeVO employeeVO,Pager pager,DocumentVO documentVO,TemplateVO templateVO,Model model) throws Exception {
		
		documentVO.setWriterId(employeeVO.getId());
		
		List<DocumentVO> ar = documentService.tempList(employeeVO,documentVO, templateVO, pager);
		
		System.out.println(ar);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);		
	}
	
	
	
	@GetMapping("exList/bonus")
	public void sang(@AuthenticationPrincipal EmployeeVO employeeVO,Model model,SaveAppVO saveAppVO) throws Exception {
		
		employeeVO = documentService.getEx(employeeVO);
				
		model.addAttribute("list", employeeVO);
		
		List<SaveAppVO> ar = documentService.getTitle(employeeVO);
		
		model.addAttribute("title", ar);		
		
	}
	
	
	@GetMapping("temp/temp")
	public void tempBonus(DocumentVO documentVO,Model model)throws Exception{
		List<DocumentVO> ar=documentService.getDetail(documentVO);
		
		
		model.addAttribute("list", ar);
		System.out.println(ar);
	}
	
	@GetMapping("call")
	public String  call(DocumentVO documentVO,Model model)throws Exception{
		ApprovalVO approvalVO = new ApprovalVO();
		List<DocumentVO> ar = documentService.getDetail(documentVO);
		System.out.println(ar);	
		
//			documentVO.setId(null);
//			documentVO.setTitle(ar.get(0).getTitle()); 
//			documentVO.setContent(ar.get(0).getContent());
//			documentVO.setTemplateId(ar.get(0).getTemplateId());
//			documentVO.setEmployeeVO(ar.get(0).getEmployeeVO());
//			documentVO.setWriterId(ar.get(0).getWriterId());
//			documentVO.setWriteDate(DateManager.getTodayDate());
//			documentVO.setStatus("0");
//			documentVO.setTemp(2);
//			documentService.add(documentVO);
//			approvalVO.setDate(null);
//			approvalVO.setDocumentId(documentVO.getId());
//			System.out.println(approvalVO.getEmployeeId());
		for(int i =0 ; i <ar.size() ; i++ ) {
			ar.get(i).setId(null);
			for(int j =0 ; j <ar.get(i).getApprovalVOs().size();j++) {			
				
			ar.get(i).getApprovalVOs().get(j).setDocumentId(null);
			ar.get(i).getApprovalVOs().get(j).setResult(0);			
			ar.get(i).getApprovalVOs().get(j).setDate(null);			
			ar.get(i).getApprovalVOs().get(j).setComment(null);			
			
			}			
		}
		
		System.out.println(ar);
		
		model.addAttribute("list", ar);
		
		return "document/temp/temp";
		
	}
	
	@GetMapping("writenList/writenBonus")
	public void writenBonus(DocumentVO documentVO,Model model)throws Exception{
		List<DocumentVO> ar=documentService.getDetail(documentVO);
		
		int result = 0;
		for(int i =0 ; i<ar.size(); i++) {
			
			for(int j = 0 ; j < ar.get(i).getApprovalVOs().size() ; j++) {
				if(ar.get(i).getApprovalVOs().get(j).getResult() !=2) {
					
			result +=ar.get(i).getApprovalVOs().get(j).getResult();
				}else {
				result +=ar.get(i).getApprovalVOs().get(j).getResult()-1;
				}
			}
		}
		System.out.println(result);
		System.out.println(ar.get(0));
		System.out.println(ar.get(1));
		model.addAttribute("nowCount", result);
		model.addAttribute("list", ar);
	}
	


	
	@GetMapping("exList/pay")
	public void pay() {
		
	}
	

	
	@PostMapping("temp")
	public ResponseEntity<?> addTemp(DocumentVO documentVO,@RequestParam HashMap<String,Object> map)throws Exception{
		ApprovalVO approvalVO = new ApprovalVO();
		BonusVO bonusVO =new BonusVO();
		
		String ranks = (String) map.get("rank");
		String ids = (String)map.get("employeeId");
		String results = (String)map.get("result");
		String[] rankArray = ranks.split(",");
		String[] idsArray = ids.split(",");
		String[] resultArray= results.split(",");
		String[] date = documentVO.getWriteDate().split(" ");
		
		documentVO.setId(null);
		documentVO.setWriteDate(date[0]);
		documentVO.setCount(rankArray.length);
		documentVO.setTemp(1);
		
		int result = documentService.add(documentVO);
		String bonus = (String)map.get("bonus");
		String bonusEmployeeId = (String)map.get("bunusEmployeeId");
		
		if(!bonus.isEmpty()) {
			bonusVO.setBonus(Long.parseLong(bonus));
		}
		if(!bonusEmployeeId.isEmpty()) {			
			bonusVO.setEmployeeId(Long.parseLong(bonusEmployeeId));
		}
		
		bonusVO.setDocumentId(documentVO.getId());
		result = documentService.bonusAdd(bonusVO);
		
		approvalVO.setDocumentId(documentVO.getId());
				
		
		Long[] longRankArray = new Long[rankArray.length];
		Long[] longIdsArray = new Long[idsArray.length]; 
		int[] longResultArray = new int[resultArray.length];

		// 문자열 배열의 각 요소를 롱타입으로 변환하여 새롭게 생성한 롱타입 배열에 저장합니다.
		for (int i = 0; i < rankArray.length; i++) {
		    longRankArray[i] = Long.parseLong(rankArray[i]);
		    longIdsArray[i] = Long.parseLong(idsArray[i]);
		    longResultArray[i] = Integer.parseInt(resultArray[i]);
		    
		    approvalVO.setRank(longRankArray[i]);
		    approvalVO.setEmployeeId(longIdsArray[i]);
		    approvalVO.setResult(longResultArray[i]);		    
		    
		    result = documentService.appAdd(approvalVO);
		}
		

		return ResponseEntity.ok(documentVO);
	}
	

	
	
	@PostMapping("tempTotemp")
	public ResponseEntity<?> tempTotemp(DocumentVO documentVO,@RequestParam HashMap<String,Object> map,TemplateVO templateVO)throws Exception{
		ApprovalVO approvalVO = new ApprovalVO();
		BonusVO bonusVO = new BonusVO();
		
		System.out.println(map);
		String ranks = (String) map.get("rank");
		String ids = (String)map.get("employeeId");
		String results = (String)map.get("result");
		String[] rankArray = ranks.split(",");
		String[] idsArray = ids.split(",");
		String[] resultArray= results.split(",");
		String[] date = documentVO.getWriteDate().split(" ");
		
		documentVO.setTemp(1);
		documentVO.setCount(rankArray.length);
		
		approvalVO.setDocumentId(documentVO.getId());
		int result = documentService.tempToSang(documentVO, approvalVO);
		bonusVO.setDocumentId(documentVO.getId());
		
		String bonus = (String)map.get("bonus");
		String bonusEmployeeId = (String)map.get("bunusEmployeeId");
		
		if(!bonus.isEmpty()) {
			bonusVO.setBonus(Long.parseLong(bonus));
			result = documentService.tempBonus(bonusVO);
		}
		if(!bonusEmployeeId.isEmpty()) {
			bonusVO.setEmployeeId(Long.parseLong(bonusEmployeeId));
			result = documentService.tempBonus(bonusVO);
		}				
		
		Long[] longRankArray = new Long[rankArray.length];
		Long[] longIdsArray = new Long[idsArray.length]; 
		int[] longResultArray = new int[resultArray.length];

		// 문자열 배열의 각 요소를 롱타입으로 변환하여 새롭게 생성한 롱타입 배열에 저장합니다.
		for (int i = 0; i < rankArray.length; i++) {
		    longRankArray[i] = Long.parseLong(rankArray[i]);
		    longIdsArray[i] = Long.parseLong(idsArray[i]);
		    longResultArray[i] = Integer.parseInt(resultArray[i]);
		    
		    approvalVO.setRank(longRankArray[i]);
		    approvalVO.setEmployeeId(longIdsArray[i]);
		    approvalVO.setResult(longResultArray[i]);		    
		    
		    result = documentService.tempToSangApp(approvalVO);
		}
		return ResponseEntity.ok(documentVO);
	}
	
 
	@PostMapping("tempToSang")
	public ResponseEntity<?> tempToSang(DocumentVO documentVO,@RequestParam HashMap<String,Object> map,TemplateVO templateVO)throws Exception{
		ApprovalVO approvalVO = new ApprovalVO();
		BonusVO bonusVO = new BonusVO();
		
		String ranks = (String) map.get("rank");
		String ids = (String)map.get("employeeId");
		String results = (String)map.get("result");
		String[] rankArray = ranks.split(",");
		String[] idsArray = ids.split(",");
		String[] resultArray= results.split(",");
		String[] date = documentVO.getWriteDate().split(" ");
		
		documentVO.setTemp(0);
		documentVO.setCount(rankArray.length);
		
		approvalVO.setDocumentId(documentVO.getId());
		int result = documentService.tempToSang(documentVO,approvalVO);
		bonusVO.setDocumentId(documentVO.getId());
		
		String bonus = (String)map.get("bonus");
		String bonusEmployeeId = (String)map.get("bunusEmployeeId");
		
		bonusVO.setBonus(Long.parseLong(bonus));
		bonusVO.setEmployeeId(Long.parseLong(bonusEmployeeId));
		
		result = documentService.tempBonus(bonusVO);
		
				
		
		Long[] longRankArray = new Long[rankArray.length];
		Long[] longIdsArray = new Long[idsArray.length]; 
		int[] longResultArray = new int[resultArray.length];

		// 문자열 배열의 각 요소를 롱타입으로 변환하여 새롭게 생성한 롱타입 배열에 저장합니다.
		for (int i = 0; i < rankArray.length; i++) {
		    longRankArray[i] = Long.parseLong(rankArray[i]);
		    longIdsArray[i] = Long.parseLong(idsArray[i]);
		    longResultArray[i] = Integer.parseInt(resultArray[i]);
		    
		    approvalVO.setRank(longRankArray[i]);
		    approvalVO.setEmployeeId(longIdsArray[i]);
		    approvalVO.setResult(longResultArray[i]);		    
		    
		    result = documentService.tempToSangApp(approvalVO);
		}
		return ResponseEntity.ok(documentVO);
	}
	
	@PostMapping("add")

	public ResponseEntity<?> add(DocumentVO documentVO,@RequestParam HashMap<String,Object> map,TemplateVO templateVO)throws Exception{
		ApprovalVO approvalVO = new ApprovalVO();
		BonusVO bonusVO = new BonusVO();
		
		
		String ranks = (String) map.get("rank");
		String ids = (String)map.get("employeeId");
		String results = (String)map.get("result");
		String[] rankArray = ranks.split(",");
		String[] idsArray = ids.split(",");
		String[] resultArray= results.split(",");
		String[] date = documentVO.getWriteDate().split(" ");
		
		
		documentVO.setCount(rankArray.length);
		
		int result = documentService.add(documentVO);
		String bonus = (String)map.get("bonus");
		String bonusEmployeeId = (String)map.get("bunusEmployeeId");
				
		bonusVO.setBonus(Long.parseLong(bonus));
		bonusVO.setEmployeeId(Long.parseLong(bonusEmployeeId));
		bonusVO.setDocumentId(documentVO.getId());
		result = documentService.bonusAdd(bonusVO);
		
		
		
		
		approvalVO.setDocumentId(documentVO.getId());
				
		
		Long[] longRankArray = new Long[rankArray.length];
		Long[] longIdsArray = new Long[idsArray.length]; 
		int[] longResultArray = new int[resultArray.length];

		// 문자열 배열의 각 요소를 롱타입으로 변환하여 새롭게 생성한 롱타입 배열에 저장합니다.
		for (int i = 0; i < rankArray.length; i++) {
		    longRankArray[i] = Long.parseLong(rankArray[i]);
		    longIdsArray[i] = Long.parseLong(idsArray[i]);
		    longResultArray[i] = Integer.parseInt(resultArray[i]);
		    
		    approvalVO.setRank(longRankArray[i]);
		    approvalVO.setEmployeeId(longIdsArray[i]);
		    approvalVO.setResult(longResultArray[i]);		    
		    
		    result = documentService.appAdd(approvalVO);
		}
		

		return ResponseEntity.ok(documentVO);
	}
	
	@PostMapping("document/tansferSave")
	@ResponseBody
	public ResponseEntity<?> tansferSave(@RequestBody SaveAppVO saveAppVO)throws Exception{
		
		List<SaveAppVO> ar=documentService.getApp(saveAppVO);
		
		System.out.println(ar);
		
		return ResponseEntity.ok(ar);
	}
	
}
	
	
	
