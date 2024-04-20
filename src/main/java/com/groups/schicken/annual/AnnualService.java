package com.groups.schicken.annual;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
	
	//@Scheduled(cron = "0 * * * * ?") (1분)
	@Scheduled(cron = "0 0 0 1 * *") // 매월 1일 0시 0분  0초  초-분-시-일-월-년
	public void list ()throws Exception{
		LocalDate today = LocalDate.now(); // 오늘 날짜 가지고옴
		List<EmployeeVO> employeeList = annualDAO.list();
		String msg = "연차 제공";
		AnnualVO annualVO = new AnnualVO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		for (EmployeeVO employeeVO : employeeList) {
			
			String dateOfEmploymentString = employeeVO.getDateOfEmployment();
	        LocalDate dateOfEmployment = LocalDate.parse(dateOfEmploymentString, formatter);
			
			if (dateOfEmployment.plusMonths(1).isBefore(today)) {
                // 1달이 지났으면 remainderAnnual 값을 설정
                annualVO.setRemainderAnnual(1);
                annualVO.setEmployeeId(employeeVO.getId());
                annualVO.setHistory(msg);
                String date = String.format("%04d%02d%02d", today.getYear(), today.getMonthValue(), today.getDayOfMonth());
                annualVO.setAnnualDate(date);
                
            }
			if (annualVO.getRemainderAnnual() != null) {
                String date = String.format("%04d%02d%02d", today.getYear(), today.getMonthValue(), today.getDayOfMonth());
                annualVO.setAnnualDate(date);
                // 나머지 연차 관련 정보 설정 후 DB에 저장
                int result = annualDAO.annualInsert(annualVO);
            }
		}	
		
	}

	
	public int annualInsert (AnnualVO annualVO)throws Exception{
        LocalDate today = LocalDate.now();
        String date = String.format("%04d%02d%02d", today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        annualVO.setAnnualDate(date);
		int result = annualDAO.annualInsert(annualVO);
			
		return result;
	}
	
	
}
