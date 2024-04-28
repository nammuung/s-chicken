package com.groups.schicken.document;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.groups.schicken.Employee.EmployeeVO;


@Mapper
public interface DocumentDAO {
	
	EmployeeVO getEx(EmployeeVO employeeVO)throws Exception;
	
	List<DocumentVO> getDetail(DocumentVO documentVO)throws Exception;
	
	int add(DocumentVO documentVO)throws Exception;
	
	int appAdd(ApprovalVO approvalVO)throws Exception;
	
	int tempTosang(DocumentVO documentVO)throws Exception;
	
	int tempToappAdd(ApprovalVO approvalVO)throws Exception;
	
	int tempToappDel(ApprovalVO approvalVO)throws Exception;

	
	Long allTotalCount(Map<String, Object> map)throws Exception;
	

	
	List<DocumentVO> approvalList(Map<String,Object> map)throws Exception;
	
	
	
	List<DocumentVO> allList(Map<String,Object> map)throws Exception;
	List<DocumentVO> tempList(Map<String,Object> map)throws Exception;
	
	List<DocumentVO> approvalDetail(DocumentVO documentVO)throws Exception;
	
	int resultUpdate(ApprovalVO approvalVO)throws Exception;
	int statusUpdate(ApprovalVO approvalVO)throws Exception;
	int refuseUpdate(ApprovalVO approvalVO)throws Exception;
	int statusRefuse(ApprovalVO approvalVO)throws Exception;
	
}
