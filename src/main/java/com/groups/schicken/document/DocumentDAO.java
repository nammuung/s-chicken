package com.groups.schicken.document;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.groups.schicken.Employee.EmployeeVO;


@Mapper
public interface DocumentDAO {
	//상신하기 사용자 정보가져오기
	EmployeeVO getEx(EmployeeVO employeeVO)throws Exception;
	//결재선 라인 저장 제목가져오기
	List<SaveAppVO> getTitle(EmployeeVO employeeVO)throws Exception;
	//결재선 라인 제목 조회해서 결재자 정보가져오기
	List<SaveAppVO> getApp(SaveAppVO saveAppVO)throws Exception;
	//결재선 저장 삭제
	int appDel(SaveAppVO saveAppVO)throws Exception;
	
	
	List<DocumentVO> getDetail(DocumentVO documentVO)throws Exception;
	
	
	//상신
	int add(DocumentVO documentVO)throws Exception;	
	int appAdd(ApprovalVO approvalVO)throws Exception;
	int bonusAdd(BonusVO bonusVO)throws Exception;
	
	
	int tempTosang(DocumentVO documentVO)throws Exception;
	
	int tempToappAdd(ApprovalVO approvalVO)throws Exception;
	int tempBonus(BonusVO bonusVO)throws Exception;
	
	int tempToappDel(ApprovalVO approvalVO)throws Exception;

	int appSave(SaveAppVO appVO)throws Exception;
	
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
