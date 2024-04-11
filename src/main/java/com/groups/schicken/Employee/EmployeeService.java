package com.groups.schicken.Employee;

import java.util.List;
import java.util.Map;

import com.groups.schicken.franchise.mapper.FranchiseMapper;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.groups.schicken.franchise.mapper.FranchiseMapper;
import com.groups.schicken.franchise.object.FranchiseVO;
import com.groups.schicken.util.Pager;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)  //error 났을때 rollbac설정
public class EmployeeService extends DefaultOAuth2UserService implements UserDetailsService {

	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private FranchiseMapper franchiseMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;  //비밀번호를 저장할때 사용 암호화 하는 역할



	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
	    System.out.println("입력 아이디: "+id);
	    EmployeeVO employeeVO = new EmployeeVO();

	    // emp로 시작하는 경우 그룹웨어 사용자
	    if (id.startsWith("emp")) {
	        // "emp"를 제거한 실제 id 파싱
	        String realId = id.substring(3);
	        employeeVO.setId(realId);
	        try {
	        	employeeVO= employeeDAO.getDetail(employeeVO);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    // fra로 시작하는 경우 여기 수정해야댐 =============================================
	    else if (id.startsWith("fra")) {
			FranchiseVO franchiseVO = new FranchiseVO();
	        // "fra"를 제거한 실제 id 파싱
	        String realId = id.substring(3);
	        franchiseVO.setId(realId);
	        try {
	            franchiseVO = franchiseMapper.getFranchise(franchiseVO);
				System.out.println("franchiseVO = " + franchiseVO);
				return franchiseVO;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
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
		return employeeDAO.userDetail(employeeVO);
	}


	public List<EmployeeVO> userList(Pager pager)throws Exception{
		pager.makeIndex();
		pager.makeNum(employeeDAO.getTotalCount(pager));
		return employeeDAO.userList(pager);
	}


	// 소셜 로그인
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		ClientRegistration clientRegistration = userRequest.getClientRegistration();  // 인가 서버에서 클라이언트의 정보를 가져와 매핑시킴
		
		log.info("Client ID == > {}", clientRegistration.getClientId());
		log.info("Client Name == > {}", clientRegistration.getClientName());
		
		OAuth2User user = super.loadUser(userRequest); //loadUser메서드 호출하여 userRequest요청에 대한 정보를  OAuth2User 객체에 담음
		
		if(clientRegistration.getClientName().equals("Kakao")) {
			
			user = this.kakao(user);
		}
		
		SocialVO socialVO = new SocialVO();
		socialVO.setKind(clientRegistration.getClientName());  
		((EmployeeVO)user).setSocialVO(socialVO);              

		return user;
	}
	
	
	private OAuth2User kakao(OAuth2User oAuth2User) {
		Map<String, Object> map = oAuth2User.getAttribute("properties");
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setName(oAuth2User.getName()); 
		employeeVO.setAttributes(oAuth2User.getAttributes());
		
		return employeeVO;
		
	}
	
	
	
	
	
	
	

	

}
