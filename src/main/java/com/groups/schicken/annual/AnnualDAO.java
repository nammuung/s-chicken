package com.groups.schicken.annual;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groups.schicken.Employee.EmployeeVO;

@Mapper
public interface AnnualDAO {

	


	


	public int annualInsert(AnnualVO annualVO)throws Exception;



	public  List<EmployeeVO> list() throws Exception;
	
	
}
