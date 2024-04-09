package com.groups.schicken.Employee;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class EmployeeVO  implements UserDetails {

	
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
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}