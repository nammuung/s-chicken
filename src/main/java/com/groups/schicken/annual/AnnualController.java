package com.groups.schicken.annual;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.groups.schicken.Employee.EmployeeController;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/annual/*")
@Slf4j
public class AnnualController {

	@Autowired
	private AnnualService annualService;
	
	

	
	
	@GetMapping("annualInsert")
	public void annualInsert () throws Exception{
		
	}
	
	@PostMapping("annualInsert")
	public String annualInsert (AnnualVO annualVO, Model model)throws Exception{	
		int result = annualService.annualInsert(annualVO);
		 String msg = "연차가 등록 되었습니다.";
		    String path = "../";
		    model.addAttribute("msg", msg);
		    model.addAttribute("path", path);
		return "employee/result";
		
		
	}
	
}
