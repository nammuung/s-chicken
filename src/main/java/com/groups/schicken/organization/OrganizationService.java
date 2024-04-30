package com.groups.schicken.organization;

import com.groups.schicken.common.util.PhoneNumberHyphenInserter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationDAO organizationDAO;


    public List<OrganizationVO> getOrgChart(String opt) {
        List<OrganizationVO> list = organizationDAO.getOrgChart(opt);

        return list;
    }

    public List<OrganizationVO> getEmployeesByDepartmentId(String deptId) {
        return organizationDAO.getEmployeesByDepartmentId(deptId);
    }

    public OrganizationVO getEmployeeByEmployeeId(String empId) {
        return organizationDAO.getEmployeeByEmployeeId(empId);
    }

    public List<ChattingEmployeeListVO> getChattingEmployeeList(String id) {
        List<ChattingEmployeeListVO> list = organizationDAO.getChattingEmployeeList(id);

        return list;
    }
}
