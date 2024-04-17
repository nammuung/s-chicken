package com.groups.schicken.Employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groups.schicken.common.vo.Pager;

@Mapper
public interface EmployeeDAO {
	
	
	public EmployeeVO getDetail(EmployeeVO employeeVO)throws Exception;
	
	public int join(EmployeeVO employeeVO)throws Exception;
	
	public int addEmployeeRole(EmployeeVO employeeVO)throws Exception;
	
	public int update(EmployeeVO employeeVO)throws Exception;
	
	public EmployeeVO userDetail(EmployeeVO employeeVO)throws Exception;
	
	public List<EmployeeVO> userList(Pager pager)throws Exception;
	
	public Long getTotalCount(Pager pager)throws Exception;
	
	//email
	public int password (EmployeeVO employeeVO)throws Exception;
	
	//소셜
	public int social (EmployeeVO employeeVO)throws Exception;
	
	//role

	public List<RoleVO> rolelist(RoleVO roleVO) throws Exception;
	public List<RoleVO> role(EmployeeVO employeeVO) throws Exception;
	
	public int roledelete(String departmentId)throws Exception;
	
	public int roleinsert(List<RoleVO> list)throws Exception;
	
	//password 변경
	

	public int passupdate(EmployeeVO employeeVO)throws Exception;
	
}
