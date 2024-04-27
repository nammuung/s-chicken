package com.groups.schicken.calendar;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface CalendarDAO {

	
	public int insert (CalendarVO calendarVO) throws Exception;
	public int insertuser(CalendarVO calendarVO)throws Exception;
	
	
}
