package com.groups.schicken.calendar;

import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.calendar.CalendarVO;

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
	
	public EmployeeVO employeeVO;

	public Object isAllDay() {
		// TODO Auto-generated method stub
		return null;
	}

}
