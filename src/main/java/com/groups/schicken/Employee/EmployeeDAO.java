package com.groups.schicken.Employee;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeDAO {
	
	
	public EmployeeVO getDetail(EmployeeVO employeeVO)throws Exception;
	
	public int add(EmployeeVO employeeVO)throws Exception;
	
	public int addEmployeeRole(EmployeeVO employeeVO)throws Exception;
	
	public int update(EmployeeVO employeeVO)throws Exception;
	
	
	
	
	

}
