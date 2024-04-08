package com.groups.schicken.organization;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationDAO {
    List<OrganizationVO> getOrgChart(String opt);
}
