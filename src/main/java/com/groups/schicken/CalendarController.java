package com.groups.schicken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groups.schicken.annual.AnnualController;
import com.groups.schicken.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class CalendarController {

	
	
	  @Autowired private CalendarService calendarService;
	 
	
	
	/*
	 * @GetMapping("/") public String test(@RequestParam("path")String path){ return
	 * path; }
	 */

    
    @GetMapping("/")
    public String calendar ()throws Exception{
        return "home";
    }
    

    
    
	/*
	 * @PostMapping("add") public String addCalendar() {
	 * 
	 * }
	 */
    
    
    
    

}
