package com.groups.schicken.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.annual.AnnualService;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;

import io.micrometer.observation.Observation.Event;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CalendarService {
	
	
	  private static final int result = 0;
	@Autowired 
	  private CalendarDAO calendarDAO;
	
	  


	  
	public int insert(CalendarVO calendarVO) throws Exception {
		System.out.println(calendarVO.getStart());
		System.out.println(calendarVO.getEnd());

	    // CalendarDAO를 사용하여 캘린더 이벤트를 삽입
	    calendarDAO.insert(calendarVO);

	    // 삽입된 캘린더 이벤트의 ID를 CalendarVO에 설정
	    calendarVO.setCalendarId(calendarVO.getId());

	    // Gson을 사용하여 JSON 문자열을 배열로 변환
	    Gson gson = new Gson();
	    String employeeIdJson = calendarVO.getEmployeeId();
	    List<Map<String, String>> employeeIdList = gson.fromJson(employeeIdJson, new TypeToken<List<Map<String, String>>>() {}.getType());

	    // 배열의 각 요소를 추출하여 인서트
	    for (Map<String, String> employeeIdMap : employeeIdList) {
	        String employeeIdValue = employeeIdMap.get("value");
	        calendarVO.setEmployeeId(employeeIdValue);
	        System.out.println(calendarVO.getCalendarId());
	        int result = calendarDAO.insertuser(calendarVO);
	    }
	    
		
		  // 부서 리스트를 조회하여 CalendarVO에 설정하고 인서트 
	    List<CalendarVO> a = calendarDAO.departmentList(calendarVO);
	    if(a.isEmpty()) {
	        return result;
	    } else {
	        calendarVO.setCalendarId(calendarVO.getId());
	        calendarDAO.insertAllUser(a);   
	        calendarDAO.depList(calendarVO);
	        calendarDAO.depDelte(calendarVO);
	    }
	   // calendarDAO.depDelte(calendarVO);
	    return result;
	}




	  
	 public List<CalendarVO> getList(CalendarVO calendarVO)throws Exception{
		 return calendarDAO.calList(calendarVO);
	 }
	 
	
	
}
