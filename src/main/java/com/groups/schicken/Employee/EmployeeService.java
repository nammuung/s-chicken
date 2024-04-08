package com.groups.schicken.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)  //error 났을때 rollbac설정
public class EmployeeService implements UserDetailsService {

	@Autowired
	private EmployeeDAO employeeDAO;

	//@Autowired
	//private PasswordEncoder passwordEncoder;  //비밀번호를 저장할때 사용 암호화 하는 역할



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setId((username));

		try {
			employeeVO= employeeDAO.getDetail(employeeVO);

		} catch (Exception e) {
			e.printStackTrace();  //에외처리 했을때 정보를 출력하는 메서드 호출
		}

		return employeeVO;
	}


	//평문 password, Role정보 저장
	public int join(EmployeeVO employeeVO)throws Exception{
		//employeeVO.setPassword(passwordEncoder.encode(employeeVO.getPassword()));
		int result = employeeDAO.join(employeeVO);

//		result = employeeDAO.addEmployeeRole(employeeVO);
		return result;
	}




//	//비밀번호, 사번 중복 검사
//	public boolean checkEmployee(EmployeeVO employeeVO, BindingResult bindingResult)throws Exception{
//		boolean check=false;
//		// true = error , flase = sucess
//
//		check=bindingResult.hasErrors(); //객체에 에러가 없는지 검증
//
//
////		if(!employeeVO.getPassword().equals(employeeVO.getPassword())) {
////			check=true;
////			bindingResult.rejectValue("passwordCheck", "employeeVO.password.equals");
////		}
//
//
//		EmployeeVO result = employeeDAO.getDetail(employeeVO);
//
//		if(result != null) {
//			check=true;
//			bindingResult.rejectValue("id", "employeeVO.id.equals");
//		}
//		return check;
//	}



}
