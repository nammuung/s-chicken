package com.groups.schicken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.auth.policy.Principal;
import com.groups.schicken.Employee.EmployeeProfileVO;
import com.groups.schicken.Employee.EmployeeService;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.annual.AnnualController;
import com.groups.schicken.board.BoardVO;
import com.groups.schicken.board.represent.RepresentService;
import com.groups.schicken.common.vo.Pager;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class CalendarController {

	
	
	  @Autowired private CalendarService calendarService;
	  @Autowired private RepresentService representService;
	  @Autowired private EmployeeService employeeService;
	
	/*
	 * @GetMapping("/") public String test(@RequestParam("path")String path){ return
	 * path; }
	 */

    
    @GetMapping("/")
    public String bordarList (Model model, String id)throws Exception{
    	
    	EmployeeVO employeeVO = new EmployeeVO();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		id = authentication.getName();
		EmployeeProfileVO employeeProfileVO = new EmployeeProfileVO();
		employeeProfileVO.setId(id);
		System.out.println(id);
    	
    	EmployeeProfileVO profile = employeeService.getProfile(id);
    	System.out.println("==========================================================="+profile);
    	BoardVO boardVO =new BoardVO();
    	Pager pager = new Pager();
    	boardVO.setWriterId(employeeVO.getId());
		List<BoardVO> ar = representService.allgetList(pager, boardVO);
		model.addAttribute("profile", profile);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
        return "home";
    }

    
	/*
	 * @PostMapping("add") public String addCalendar() {
	 * 
	 * }
	 */
    
    
    
    

}
