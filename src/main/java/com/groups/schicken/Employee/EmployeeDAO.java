package com.groups.schicken.Employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groups.schicken.annual.AnnualVO;
import com.groups.schicken.common.vo.Pager;

@Mapper
public interface EmployeeDAO {

	// 직원 관련 ///////////////////////////////////////////////////
	// 직원 상세정보
	public EmployeeVO getDetail(EmployeeVO employeeVO)throws Exception;
	// 로그인
	public int join(EmployeeVO employeeVO)throws Exception;
	// 직원 정보 수정
	public int updateEmployee(EmployeeVO employeeVO)throws Exception;
	// 로그인 할때 id 조회
	public EmployeeVO userDetail(EmployeeVO employeeVO)throws Exception;
	// 직원 목록
	public List<EmployeeVO> userList(Pager pager)throws Exception;
	// 퇴사자 목록
	public List<EmployeeVO> isuserList(Pager pager)throws Exception;
	// 직원 인원 구하는 용도
	public Long getTotalCount(Pager pager)throws Exception;
	// 퇴사자 인원 구하는 용도
	public Long getTotalCount2(Pager pager)throws Exception;




	//소셜 /////////////////////////////////////////////////
	public int social (EmployeeVO employeeVO)throws Exception;

	//role ////////////////////////////////////////////////
	public List<RoleVO> rolelist(RoleVO roleVO) throws Exception;
	public List<RoleVO> role(EmployeeVO employeeVO) throws Exception;
	public int roledelete(String departmentId)throws Exception;
	public int roleinsert(List<RoleVO> list)throws Exception;
	public int addEmployeeRole(EmployeeVO employeeVO)throws Exception;


	// password ////////////////////////////////////////////

	// 패스워드 조회
	public EmployeeVO passwordinfo (EmployeeVO employeeVO)throws Exception;
	//email
	public int password (EmployeeVO employeeVO)throws Exception;
	//password 변경
	public int passupdate(EmployeeVO employeeVO)throws Exception;


    EmployeeProfileVO getProfile(String id);
}
