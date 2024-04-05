package com.groups.schicken.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeVO {

	
	private Long id;
	private String password;
	private String name;
	private String phone_number;
	private String email;
	private String address;
	private String address_detail;
	private Integer  postcode;
	private String date_of_employment;
	private String resident_number;
	private Integer salary;
	private String account_number;
	private String bank_name;
	private Boolean is_leaved;
	private Long department_id;
	private String pos_id;
	
}
