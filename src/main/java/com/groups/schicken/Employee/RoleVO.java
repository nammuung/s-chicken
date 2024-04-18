package com.groups.schicken.Employee;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleVO {

	private String id;	
	private String name;
	private String rolId;	
	private String roleName;
		
	public static RoleVO of(String id, String name) {
		return RoleVO.builder()
				.id(id)
				.name(name)
				.build();
	} 


	public static RoleVO ofs(String id, String rolId) {
		return RoleVO.builder()
				.id(id)
				.rolId(rolId)
				.build();
	} 
}
