package com.groups.schicken.annual;

import java.util.List;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;

import com.groups.schicken.Employee.EmployeeVO;

import lombok.Data;

@Data
public class AnnualVO {
//직원 연차
	
	
	private Long id;  // 연차 id
	private String employeeId; // 직원 id
	private Integer remainderAnnual;  // 잔여 연차
	private Integer annual;  // 사용연차
	private String annualDate;  // 년도
	private String history; // 내역
	private Long documentId;  // 문서번호
	private String writerId; // 작성자
	private Integer annualTotal; // 갯수 구하는용도
	private Boolean isAnnual;
	private EmployeeVO emp;
	private List<AnnualVO> resultList;
	 
	 
}
