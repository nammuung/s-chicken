package com.groups.schicken.calendar;

import java.sql.Date;

import com.groups.schicken.calendar.CalendarVO;

import lombok.Data;
@Data
public class CalendarVO {

	public Long id;
	public Date start;
	public Date end;
	public String title;
	public Boolean userYn;
	public String content;
	public String share;
	public Long calendarId;
	public String employeeId;

    public Long getCalendareId() {
        return calendarId;
    }
}
