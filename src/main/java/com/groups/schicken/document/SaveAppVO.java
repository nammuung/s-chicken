package com.groups.schicken.document;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.CodeVO;

import lombok.Data;

@Data
public class SaveAppVO {
	
	private Long id;
	private String employeeId;
	private String appId;	
	private String title;
	private int rank;
	private String date;
	
	private EmployeeVO employee;
	private CodeVO code;
}
