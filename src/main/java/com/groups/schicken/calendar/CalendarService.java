package com.groups.schicken.calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.groups.schicken.annual.AnnualService;

import io.micrometer.observation.Observation.Event;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CalendarService {
	
	
	  @Autowired 
	  private CalendarDAO calendarDAO;
	
	  


	  
	 public int insert (CalendarVO calendarVO)throws Exception{
		 calendarDAO.insert(calendarVO);
		 calendarVO.setCalendarId(calendarVO.getId());
		 if(calendarVO.getEmployeeId() == null) {
		 calendarVO.setEmployeeId(calendarVO.getShare());
		 }
		 System.out.println(calendarVO.getCalendarId());
		
		 int result = calendarDAO.insertuser(calendarVO);
		 return result;
		 
	 }
	  
	 public List<CalendarVO> getList(CalendarVO calendarVO)throws Exception{
		 return calendarDAO.calList(calendarVO);
	 }
	 
	
	
}
