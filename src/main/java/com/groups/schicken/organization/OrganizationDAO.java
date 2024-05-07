package com.groups.schicken.organization;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationDAO {
    List<OrganizationVO> getEmployeesByDepartmentId(String deptId);

    List<OrganizationVO> getOrgChart(String opt);

    OrganizationVO getEmployeeByEmployeeId(String empId);

    List<ChattingEmployeeListVO> getChattingEmployeeList(String id);
}
