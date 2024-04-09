package com.groups.schicken.Employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groups.schicken.util.Pager;

@Mapper
public interface EmployeeDAO {
	
	
	public EmployeeVO getDetail(EmployeeVO employeeVO)throws Exception;
	
	public int join(EmployeeVO employeeVO)throws Exception;
	
	public int addEmployeeRole(EmployeeVO employeeVO)throws Exception;
	
	public int update(EmployeeVO employeeVO)throws Exception;
	
	public EmployeeVO userDetail(EmployeeVO employeeVO)throws Exception;
	
	public List<EmployeeVO> userList(Pager pager)throws Exception;
	
	public Long getTotalCount(Pager pager)throws Exception;
	
	
	

}
