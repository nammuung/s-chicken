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
import org.springframework.web.bind.annotation.ResponseBody;

import com.groups.schicken.Employee.EmployeeController;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/annual/*")
@Slf4j
public class AnnualController {

	@Autowired
	private AnnualService annualService;
	
	
	

	
	
	

	@GetMapping("annualInsert")
	public String annualInsert() throws Exception {
	    return "annual/annualInsert";
	}

	
	@PostMapping("annualInsert")
	public String annualInsert (AnnualVO annualVO, Model model)throws Exception{   
	     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     String id = auth.getName(); 
	     annualVO.setWriterId(id);
	    int result = annualService.annualInsert(annualVO);
	     String msg = "연차가 등록 되었습니다.";
	        String path = "../";
	        model.addAttribute("msg", msg);
	        model.addAttribute("path", path);
	    return "redirect:/employee/result"; // 연차 등록 후에 리다이렉트되는 페이지로의 경로를 설정합니다.
	}

	
}
