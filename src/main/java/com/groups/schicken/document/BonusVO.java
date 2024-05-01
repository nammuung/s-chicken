package com.groups.schicken.document;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.CodeVO;
import com.groups.schicken.department.DepartmentVO;

import lombok.Data;

@Data
public class BonusVO {
	private Long employeeId;
	private Long documentId;
	private Long bonus;
	private Long date;
	
	private EmployeeVO employeeVO;
	private CodeVO codeVO;
}
