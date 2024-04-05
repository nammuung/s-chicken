package com.groups.schicken.department;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentDAO {

    int addDepartment(DepartmentVO department);
}
