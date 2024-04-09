package com.groups.schicken.department;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentDAO {

    int addDepartment(DepartmentVO department);

    DepartmentVO getDepartment(DepartmentVO department);

    List<DepartmentVO> getList();

    List<DepartmentVO> getListWithHierarchy();

    Integer getLastSortByUpperId(Long upperId);

    Integer isContactNumber(String contactNumber, String except);

    Integer updateDepartment(DepartmentVO department);

    Integer updateDepartmentSort(DepartmentVO department);
}
