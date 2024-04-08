package com.groups.schicken.Employee;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.services.storage.Storage.BucketAccessControls.List;

import ch.qos.logback.core.model.Model;
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

		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");

		if (obj == null) {
			return "employee/login";
		}

		SecurityContextImpl contextImpl = (SecurityContextImpl) obj;
		String user = contextImpl.getAuthentication().getPrincipal().toString();

		if (user.equals("anonymousUser")) {
			return "employee/login";
		}

		return "redirect:/";

	}

	@GetMapping("update")
	public void update(Model model) throws Exception {

	}

	@PostMapping("update")
	public void update() throws Exception {

	}
	
	
	@GetMapping("join")
	public void join(@ModelAttribute EmployeeVO employeeVO) throws Exception{
		
	}
	
	@PostMapping("join")
	public String join(EmployeeVO employeeVO, Model model, BindingResult bindingResult)throws Exception{
//		boolean check = employeeService.checkEmployee(employeeVO, bindingResult);
//		if(check) {
//			return "employee/join";
//		}

		int reuslt = employeeService.join(employeeVO);
		
		return "employee/join";
	} 
	
	
	
}
