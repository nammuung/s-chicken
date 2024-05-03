package com.groups.schicken.calendar;

import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;

import org.apache.poi.ss.formula.functions.Code;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.calendar.CalendarVO;
import com.groups.schicken.common.vo.CodeVO;
import com.groups.schicken.department.DepartmentVO;

import lombok.Data;
@Data
public class CalendarVO {

	public Long id;
    public String start;
    public String end;
	public String title;
	public Boolean userYn;
	public String content;
	public String share;
	public Long calendarId;
	public String employeeId;
	
	// 파라미터 보내는 용도 부서, 직급
	public String depname;
	public String cname;
	public String name;
	
	
	public EmployeeVO employeeVO;
	
	public Object isAllDay() {
		// TODO Auto-generated method stub
		return null;
	}

}
