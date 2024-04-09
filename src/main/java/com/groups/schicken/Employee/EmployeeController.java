package com.groups.schicken.Employee;

import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groups.schicken.util.Pager;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/employee/*")
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// Login
	@GetMapping("login")
	public String login(@ModelAttribute EmployeeVO employeeVO, HttpSession session) throws Exception {

		Object obj=(session.getAttribute("SPRING_SECURITY_CONTEXT"));
		System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));

		if (obj == null) {
			log.info("여기탐=================================");
			return "employee/login";
		}


		return "employee/join";

	}
	
	

	@GetMapping("update")
	public void update(Model model) throws Exception {

	}

	@PostMapping("update")
	public void update() throws Exception {

	}
	
	//회원가입 페이지 이동
	@GetMapping("join")
	public void join(@ModelAttribute EmployeeVO employeeVO) throws Exception{
	}
	
	// 회원가입 요청
	@PostMapping("join")
	public String join(EmployeeVO employeeVO, Model model) throws Exception {
		
		int result = employeeService.join(employeeVO);
	    
	    String msg = "가입 실패";
	    String path = "./join";

	    if (result > 0) {
	        msg = "가입 성공";
	        path = "../";
	    }

	    model.addAttribute("msg", msg);
	    model.addAttribute("path", path);
	    
	    return "employee/result";
	}

	
	@GetMapping("profile")
	public String userDetail(EmployeeVO employeeVO, Model model)throws Exception{
		employeeVO = employeeService.userDetail(employeeVO);
		model.addAttribute("detail", employeeVO);
		log.info("===={}===컨트롤ㄹ러임", employeeVO);
		return "employee/profile";
		
	}

	
	@GetMapping("list")
	public String userList(Pager pager, Model model)throws Exception{
		List<EmployeeVO> ar = employeeService.userList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager",pager);
		return "employee/list";
		
		
	}

	
	
	
	
}
