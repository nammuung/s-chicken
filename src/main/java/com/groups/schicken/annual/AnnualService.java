package com.groups.schicken.annual;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
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
//@Transactional(rollbackFor = Exception.class) // error 났을때 rollbac설정
public class AnnualService {

	@Autowired
	private AnnualDAO annualDAO;

	//작년 연차내역 안보이게 설정
	//@Scheduled(cron = "0 50 23 L 12 ?") //매년 12월 마지막날 23시 50분 동작
	//@Scheduled(cron = "0 * * * * ?") // 1분
	public Integer deleteAnnual() throws Exception {
	    LocalDate today = LocalDate.now();
	    AnnualVO annualVO = new AnnualVO();
	    List<AnnualVO> annualList = annualDAO.annuals(annualVO);
	    
	    for (AnnualVO annual : annualList) {
	        String annualDate = annual.getAnnualDate();
	        
	        LocalDate annualLocalDate = LocalDate.parse(annualDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
	        
	       
	        if (annualLocalDate.getYear() != today.getYear()) {
	            annual.setIsAnnual(false); 
	            annualDAO.deleteAnnual(annualVO); 
	        }
	    }
	    
	    return annualList.size(); 
	}

	
	
	
	//연차 추가 로직
	//@Scheduled(cron = "0 * * * * ?") // 1분
	@Scheduled(cron = "0 0 0 1 * *") // 매월 1일 0시 0분 0초에 실행
	public void updateAnnualLeaves() throws Exception {
	    LocalDate today = LocalDate.now(); // 오늘 날짜
	    List<EmployeeVO> employeeList = annualDAO.list(); // 모든 직원 목록 가져오기
	    String msg = "연차 제공";
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	    for (EmployeeVO employeeVO : employeeList) {
	        LocalDate dateOfEmployment = LocalDate.parse(employeeVO.getDateOfEmployment(), formatter);
	        long yearsOfService = dateOfEmployment.until(today).getYears(); // 근속 연수 계산
	        int annualLeavesToGrant = (yearsOfService >= 3) ? 2 : 1; // 3년 이상이면 2개, 그렇지 않으면 1개

	        if (dateOfEmployment.plusMonths(1).isBefore(today)) { // 입사 후 최소 1달이 지난 경우에만 지급
	            AnnualVO annualVO = new AnnualVO();
	            annualVO.setEmployeeId(employeeVO.getId());
	            annualVO.setRemainderAnnual(annualLeavesToGrant);
	            annualVO.setHistory(msg);
	            String date = String.format("%04d%02d%02d", today.getYear(), today.getMonthValue(), 1);
	            annualVO.setAnnualDate(date);
	            annualVO.setIsAnnual(true);

	            // 연차 정보 DB에 저장
	            int result = annualDAO.annualInsert(annualVO);
	        }
	    }
	}


	public int annualInsert(AnnualVO annualVO) throws Exception {
		LocalDate today = LocalDate.now();
		String date = String.format("%04d%02d%02d", today.getYear(), today.getMonthValue(), today.getDayOfMonth());
		annualVO.setAnnualDate(date);
		Integer s = annualVO.getRemainderAnnual();
		if (s <= 0) {
			annualVO.setAnnual(s);
		}
		if(annualVO.getIsAnnual()== null) {
			annualVO.setAnnual(0);
			Integer	totla = annualVO.getRemainderAnnual() - annualVO.getAnnual();	
		}
		Integer totla = annualVO.getRemainderAnnual() - annualVO.getAnnual();
		annualVO.setAnnualTotal(totla);
		int result = annualDAO.annualInsert(annualVO);

		return result;
	}

	
	public List<AnnualVO> annualList(AnnualVO annualVO) throws Exception {
	    List<AnnualVO> annualList = annualDAO.annualList(annualVO); // 배열

	    List<AnnualVO> resultList = new ArrayList<>(); // 새로운 배열
	    int remainderAnnual = 0;
	    String currentYear = ""; // 현재 년도
	    
	    for (AnnualVO annual : annualList) {
	        String annualDate = annual.getAnnualDate(); // 작성날짜
	        String yearOfAnnual = annualDate.substring(0, 4); // 날짜에서 연도 추출

	        // 새로운 연도인지 확인
	        if (!yearOfAnnual.equals(currentYear)) {
	            if (!currentYear.isEmpty()) {
	                remainderAnnual = 0;
	            }
	            currentYear = yearOfAnnual; // currentYear를 새로운 연도로 업데이트
	        }
	        	
	        int receivedAnnual = annual.getRemainderAnnual(); // 받은 연차
	        int usedAnnual = annual.getAnnual(); // 사용한 연차

	        
	        remainderAnnual += (receivedAnnual - usedAnnual); // 남은 연차 계산
	        
	        annual.setAnnualTotal(remainderAnnual);
	        resultList.add(annual);
	    }
	    System.out.println(resultList);
	    // resultList를 annualVO에 담아서 다시 선택

	    return resultList;
	}


	
	 




}
