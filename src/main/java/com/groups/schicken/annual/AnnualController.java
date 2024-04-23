package com.groups.schicken.annual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groups.schicken.Employee.EmployeeController;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/annual/*")
@Slf4j
public class AnnualController {

	@Autowired
	private AnnualService annualService;
	
	
	

	
	@GetMapping("list")
	public String annualList( AnnualVO annualVO,Model model,Pager pager,  @RequestParam("id") String id)throws Exception{
	

		annualVO.setEmployeeId(id);
		pager.setEmployeeId(id);
		
		List<AnnualVO> ar = annualService.annualList(annualVO,pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		return "annual/list";
	}
	
	

	@GetMapping("annualInsert")
	public String annualInsert() throws Exception {
	    return "annual/annualInsert";
	}

	
	@PostMapping("annualInsert")
	public String annualInsert (AnnualVO annualVO, Model model)throws Exception{
		System.out.println("test");
		System.out.println(annualVO);
	     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     String id = auth.getName(); 
	     annualVO.setWriterId(id);
	    int result = annualService.annualInsert(annualVO);
	     String msg = "연차가 등록 되었습니다.";
	        String path = "/";
	        model.addAttribute("msg", msg);
	        model.addAttribute("path", path);
	    return "annual/result"; // 연차 등록 후에 리다이렉트되는 페이지로의 경로를 설정합니다.
	}

	
}
