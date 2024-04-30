package com.groups.schicken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.policy.Principal;
import com.groups.schicken.Employee.EmployeeProfileVO;
import com.groups.schicken.Employee.EmployeeService;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.annual.AnnualController;
import com.groups.schicken.board.BoardVO;
import com.groups.schicken.board.represent.RepresentService;
import com.groups.schicken.calendar.CalendarService;
import com.groups.schicken.calendar.CalendarVO;
import com.groups.schicken.common.vo.Pager;
import com.groups.schicken.notification.Noticer;
import com.groups.schicken.notification.NotificationType;
import com.groups.schicken.organization.ChattingEmployeeListVO;
import com.groups.schicken.organization.OrganizationService;
import com.nimbusds.jose.shaded.gson.JsonObject;

import io.micrometer.observation.Observation.Event;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Notification;

@Controller
@RequestMapping("/")
@Slf4j
public class MainController {

	
	
	  @Autowired private CalendarService calendarService;
	  @Autowired private RepresentService representService;
	  @Autowired private EmployeeService employeeService;
	  @Autowired private OrganizationService organizationService;
	  @Autowired private Noticer noticer;
	  
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

    	BoardVO boardVO =new BoardVO();
    	Pager pager = new Pager();
    	boardVO.setWriterId(employeeVO.getId());
		List<BoardVO> ar = representService.allgetList(pager, boardVO);
		model.addAttribute("profile", profile);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
        return "home";
    }

    
    @GetMapping("userlist")
    @ResponseBody
    public List<ChattingEmployeeListVO> main ()throws Exception{
    	// 0사번은 없으니까 다가져옴
    	List<ChattingEmployeeListVO> a = organizationService.getChattingEmployeeList("0");
    	System.out.println(a+"f로그임");
		return a;
    }
    
    
    @PostMapping("insert")
    @ResponseBody
    public String insert(@RequestBody CalendarVO calendarVO) throws Exception  {
            log.info("{}", calendarVO);
            calendarVO.setUserYn(true);
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		String id = authentication.getName();
    		calendarVO.setShare(id);
            int result = calendarService.insert(calendarVO);
            //noticer.sendNotice(id+"일정이 등록되었습니다.", /, NotificationType.Calendar, List<String>);
                return "일정이 성공적으로 추가되었습니다.";

    }
    @PostMapping("update")
    @ResponseBody
    public String update(@RequestBody CalendarVO calendarVO)throws Exception{
    	calendarService.update(calendarVO);
    	return "업데이트 성공";
    } 


 
    
    @GetMapping("list")
    @ResponseBody
    public List<CalendarVO> list (CalendarVO calendarVO)throws Exception{
    	List<CalendarVO> ar = calendarService.getList(calendarVO);
    	return ar;
    }
    
    
    


    
    
    
    

}