package com.groups.schicken.annual;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnualDAO {

	

	public List<AnnualVO> list(AnnualVO annualVO)throws Exception;
	


	public int annualInsert(AnnualVO annualVO)throws Exception;
	
	
}
