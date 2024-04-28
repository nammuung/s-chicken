package com.groups.schicken;

import com.groups.schicken.franchise.FranchiseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groups.schicken.annual.AnnualController;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class CalendarController {

	/*
	 * @Autowired private CalendarService calendarService;
	 */
	
	
	/*
	 * @GetMapping("/") public String test(@RequestParam("path")String path){ return
	 * path; }
	 */

    
    @GetMapping("/")
    public String calendar ()throws Exception{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("authentication = " + authentication);
		if (authentication != null) {
			boolean hasFranchiseAuthority = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_FRANCHISE"));
			if (hasFranchiseAuthority) {
				return "franchise/home";
			}
		}
        return "home";// 본사 직원 화면
    }
    
	/*
	 * @PostMapping("add") public String addCalendar() {
	 * 
	 * }
	 */
    
    

}
