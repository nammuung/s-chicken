package com.groups.schicken.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationDAO organizationDAO;


    public List<OrgElementVO> getOrgChart(String opt) {
        List<OrgElementVO> list = organizationDAO.getOrgChart(opt);

        return list;
    }
}
