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

import jakarta.servlet.http.HttpServletRequest;
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
	public String login(@ModelAttribute EmployeeVO employeeVO, HttpSession session, HttpServletRequest request) throws Exception {
	    Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
	    log.info("{}", obj);
	    System.out.println(employeeVO.getId());

	    // 세션에 저장된 "아이디 저장하기" 값 가져오기
	    Boolean rememberMe = (Boolean) session.getAttribute("rememberMe");

	    if (obj == null) {
	        log.info("============오브젝트 Null=================================");
	        return "employee/login";
	    }

	    // "아이디 저장하기"가 체크되어 있고, 사용자 정보가 있다면 다시 로그인하지 않고 바로 페이지로 이동
	    if (rememberMe != null && rememberMe && employeeVO.getId() != null) {
	        // 여기에서 로그인 로직을 생략하고, 바로 목적 페이지로 이동하도록 설정
	        return "redirect:/employee/join"; // 예시로 'join' 페이지로 이동하도록 설정
	    }

	    return "employee/login";
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
