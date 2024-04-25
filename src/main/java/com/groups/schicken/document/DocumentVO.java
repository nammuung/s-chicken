package com.groups.schicken.document;

import java.util.List;

import org.apache.poi.ss.formula.functions.Code;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.FileVO;
import com.groups.schicken.department.DepartmentVO;

import lombok.Data;

@Data
public class DocumentVO {
	private Long id;
	private String title;
	private String content;
	private String start;
	private String end;
	private String writeDate;
	private String status;
	private Long templateId;
	private String writerId;
	private String des;
	private int count;
	
	private TemplateVO templateVO;
	private List<ApprovalVO> approvalVOs;
	private String level;
	private EmployeeVO employeeVO;
	private DepartmentVO department;
	
	
	private FileVO fileVO;

}
