package com.groups.schicken.department;

import com.groups.schicken.Employee.RoleVO;

import lombok.Data;

@Data
public class DepartmentVO {
    private Long id;
    private String name;
    private String contactNumber;
    private Integer sort;
    private Long upperId;
    private String upperName;
    private Integer depth;
    private RoleVO roleVO;
}
