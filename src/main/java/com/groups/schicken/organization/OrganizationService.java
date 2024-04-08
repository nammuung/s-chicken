package com.groups.schicken.organization;

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
}
