package com.groups.schicken.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.groups.schicken.util.Pager;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)  //error 났을때 rollbac설정
public class EmployeeService implements UserDetailsService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;  //비밀번호를 저장할때 사용 암호화 하는 역할



	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		System.out.println(id);
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setId(id);

		
		try {
			employeeVO= employeeDAO.getDetail(employeeVO);

		} catch (Exception e) {

			e.printStackTrace();  //에외처리 했을때 정보를 출력하는 메서드 호출
		}
		

		return employeeVO;
	}	
	

	// 회원 가입
	public int join(EmployeeVO employeeVO)throws Exception{
	    // 생년월일에서 하이픈 제거
	    String residentNumbers = employeeVO.getResidentNumber().replaceAll("-", "");
	    // 제거된 생년월일을 다시 설정
	    employeeVO.setResidentNumber(residentNumbers);
	    
	    // 입사일에서 하이픈 제거
	    String dateOfEmploymens = employeeVO.getDateOfEmployment().replaceAll("-", "");
	    // 제거된 입사일을 다시 설정
	    employeeVO.setDateOfEmployment(dateOfEmploymens);
	    
	    // 나머지 코드는 그대로 유지
	    employeeVO.setPassword(passwordEncoder.encode(employeeVO.getPassword()));
	    int result = employeeDAO.join(employeeVO);
		    
	    
	    return result;
	}
	

	public EmployeeVO userDetail (EmployeeVO employeeVO)throws Exception{
		log.info("===={}================서비스임", employeeVO);
		return employeeDAO.userDetail(employeeVO);
	}


	public List<EmployeeVO> userList(Pager pager)throws Exception{
		pager.makeIndex();
		pager.makeNum(employeeDAO.getTotalCount(pager));
		return employeeDAO.userList(pager);
	}
	
	
	

}
