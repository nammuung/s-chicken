package com.groups.schicken.annual;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groups.schicken.Employee.EmployeeService;
import com.groups.schicken.Employee.EmployeeVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)  //error 났을때 rollbac설정
public class AnnualService {

	@Autowired
	private AnnualDAO annualDAO;
	
	
	public List<AnnualVO> list (AnnualVO annualVO)throws Exception{
		
		List<AnnualVO> ar = annualDAO.list(annualVO);		
		return ar;
	}
	
	public int annualInsert (AnnualVO annualVO)throws Exception{
        LocalDate today = LocalDate.now();
        String date = String.format("%04d%02d%02d", today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        annualVO.setAnnualDate(date);
		int result = annualDAO.annualInsert(annualVO);
			
		return result;
	}
	
	
}
