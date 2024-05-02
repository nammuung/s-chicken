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
import com.groups.schicken.notification.Noticer;
import com.groups.schicken.notification.NotificationType;



@Controller
@RequestMapping("/document/*")
@Slf4j
public class DocumentController {
	@Autowired
	private Noticer noticer;
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
	
	//최종결재 확인후 상태 업데이트
	@PostMapping("document/approvalUpdate")
	public ResponseEntity<Integer> approvalUpdate(ApprovalVO approvalVO)throws Exception{
		BonusVO bonusVO = new BonusVO();
		DocumentVO documentVO = new DocumentVO();
		int result = documentService.resultUpdate(approvalVO,bonusVO,documentVO);
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("document/refuseUpdate")
	public ResponseEntity<Integer> refuseUpdate(ApprovalVO approvalVO)throws Exception{
		DocumentVO documentVO = new DocumentVO();
		int result = documentService.refuseUpdate(approvalVO,documentVO);
		
		return ResponseEntity.ok(result);
	}
	//결재함 리스트
	@GetMapping("approvalList")
	public void approval(@AuthenticationPrincipal EmployeeVO employeeVO,Model model,Pager pager)throws Exception{
		
		List<DocumentVO> ar = documentService.approvalList(employeeVO,pager);
		System.out.println(ar.get(0)+"123");
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		System.out.println(pager);
	}

	
	//상신함 열람하기
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
	//불러올 목록 리스트
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
	
	
	//상여금 신청서
	@GetMapping("exList/bonus")
	public void sang(@AuthenticationPrincipal EmployeeVO employeeVO,Model model,SaveAppVO saveAppVO) throws Exception {
		
		employeeVO = documentService.getEx(employeeVO);
				
		model.addAttribute("list", employeeVO);
		
		List<SaveAppVO> ar = documentService.getTitle(employeeVO);
		
		model.addAttribute("title", ar);		
		
	}
	//휴가계획서
	@GetMapping("exList/vacation")
	public void vacation(@AuthenticationPrincipal EmployeeVO employeeVO,Model model,SaveAppVO saveAppVO)throws Exception{
		employeeVO = documentService.getEx(employeeVO);
		
		model.addAttribute("list", employeeVO);
		
		List<SaveAppVO> ar = documentService.getTitle(employeeVO);
		
		model.addAttribute("title", ar);
		
	}
	
	
	//임시저장 문서 열기
	@GetMapping("temp/temp")
	public void tempBonus(@AuthenticationPrincipal EmployeeVO employeeVO,DocumentVO documentVO,Model model)throws Exception{
		List<DocumentVO> ar=documentService.getDetail(documentVO);
		
		
		model.addAttribute("list", ar);
		System.out.println(ar);
		
		List<SaveAppVO> br = documentService.getTitle(employeeVO);
		
		model.addAttribute("title", br);	
	}
	
	//불러오기
	@GetMapping("call")
	public String  call(@AuthenticationPrincipal EmployeeVO employeeVO,DocumentVO documentVO,Model model)throws Exception{
		ApprovalVO approvalVO = new ApprovalVO();
		List<DocumentVO> ar = documentService.getDetail(documentVO);
		System.out.println(ar);	
		
		for(int i =0 ; i <ar.size() ; i++ ) {
			ar.get(i).setId(null);
			for(int j =0 ; j <ar.get(i).getApprovalVOs().size();j++) {			
				
			ar.get(i).getApprovalVOs().get(j).setDocumentId(null);
			ar.get(i).getApprovalVOs().get(j).setResult(0);			
			ar.get(i).getApprovalVOs().get(j).setDate(null);			
			ar.get(i).getApprovalVOs().get(j).setComment(null);			
			
			}			
		}
		
		System.out.println("123"+ar);
		
		model.addAttribute("list", ar);
		
		List<SaveAppVO> br = documentService.getTitle(employeeVO);
		
		model.addAttribute("title", br);	
		
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
		if(ar.get(result) != null) {
			noticer.sendNotice("결재바람", ar.get(0).getId()+"", NotificationType.Document, List.of(ar.get(result).getApprovalVOs().get(0).getEmployeeId()+""));
		}
		System.out.println(ar.get(0));
		System.out.println(ar.get(1));
		model.addAttribute("nowCount", result);
		model.addAttribute("list", ar);
	}
	
	@GetMapping("writenList/writenVacation")
	public void writenVacation(DocumentVO documentVO,Model model)throws Exception{
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
	//상여금 신청서 문서 오픈
	@GetMapping("exList/pay")
	public void pay() {
		
	}
	

	//첫 임시저장하기
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
	

	
	//임시저장 된 문서 다시 임시저장하기
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
	
	//임시저장 상신하기
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
	//상여금신청서 첫 상신하기
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
		
		if(bonus != null) {
		bonusVO.setBonus(Long.parseLong(bonus));
		bonusVO.setEmployeeId(Long.parseLong(bonusEmployeeId));
		bonusVO.setDocumentId(documentVO.getId());
		result = documentService.bonusAdd(bonusVO);		
		}
		
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
		noticer.sendNotice("빨리해 임마",approvalVO.getDocumentId()+"",NotificationType.Document,List.of(idsArray[1]));

		return ResponseEntity.ok(documentVO);
	}
	//나의 결재선 라인 저장하기
	@PostMapping("document/tansferSave")
	@ResponseBody
	public ResponseEntity<?> tansferSave(@RequestBody SaveAppVO saveAppVO)throws Exception{
		
		List<SaveAppVO> ar=documentService.getApp(saveAppVO);
		
		System.out.println(ar);
		
		return ResponseEntity.ok(ar);
	}
	
}
	
	
	
