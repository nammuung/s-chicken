package com.groups.schicken.annual;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.Pager;

@Mapper
public interface AnnualDAO {

	


	


	public int annualInsert(AnnualVO annualVO)throws Exception;



	public  List<EmployeeVO> list() throws Exception;
	

	public int deleteAnnual(AnnualVO annualVO)throws Exception;


	public List<AnnualVO> annualList(Pager pager)throws Exception;
	
	public List<AnnualVO> annuals(AnnualVO annualVO)throws Exception;



	public Long getTotalCount(Pager pager)throws Exception;










}
