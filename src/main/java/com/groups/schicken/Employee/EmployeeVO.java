package com.groups.schicken.Employee;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.groups.schicken.department.DepartmentVO;
import com.groups.schicken.util.CodeVO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
	
	//소셜 Login
	private SocialVO socialVO;
	
	
	//OAuth2User, Token등 정보 저장
	private Map<String, Object> attributes;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return true;
	}

}