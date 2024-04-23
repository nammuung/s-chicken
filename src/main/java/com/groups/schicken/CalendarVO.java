package com.groups.schicken;

import java.sql.Date;

import lombok.Data;

@Data
public class CalendarVO {

	public Long id;
	public Date start;
	public Date end;
	public String title;
	public Boolean userYn;
	public String content;
}
