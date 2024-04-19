package com.groups.schicken.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.groups.schicken.common.vo.FileVO;
import com.groups.schicken.department.DepartmentVO;
import com.groups.schicken.annual.AnnualVO;
import com.groups.schicken.common.vo.CodeVO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

//@Getter
//@Setter
//@ToString
@Slf4j
@Data
public class EmployeeVO  implements UserDetails, OAuth2User {

	
	private String id;
	private String password;
	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private String addressDetail;
	private String  postcode;
	private String dateOfEmployment;
	private String residentNumber;
	private Integer salary;
	private String accountNumber;
	private String bankName;
	private Boolean isLeaved;
	private Long departmentId;
	private String posId;
	private String posName; // 직급이름
	
	private DepartmentVO department;
	private CodeVO position;
	

	private AnnualVO annualVO;
	
	
	//소셜 Login
	private SocialVO socialVO;
	
	private RoleVO roleVO;
	
	private FileVO file;
	
	private List<RoleVO> roleVOs;
	
	//OAuth2User, Token등 정보 저장
	private Map<String, Object> attributes;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//GrantedAuthority -> 현재 사용자가 가지고 있는 권한
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(roleVOs == null){
			return authorities;
		} else {
			for(RoleVO roleVO:roleVOs) {
				GrantedAuthority g = new SimpleGrantedAuthority(roleVO.getName());

				log.warn("====== ROLE : {}", g.getAuthority());
				authorities.add(g);
			}
		}
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		 return this.password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		if (this.isLeaved == false) {
			return false;
		} 
		
		return true;
	}

}