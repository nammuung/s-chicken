package com.groups.schicken.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/organization/*")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("orgChart")
    @ResponseBody
    public ResponseEntity<List<OrganizationVO>> getOrgChart(String opt){
        List<OrganizationVO> list  = organizationService.getOrgChart(opt);

        return ResponseEntity.ok(list);
    }

    @GetMapping("employees")
    public ResponseEntity<List<OrganizationVO>> getEmployees(String deptId, String empId){
        List<OrganizationVO> list;
        if(deptId != null){
            list = organizationService.getEmployeesByDepartmentId(deptId);
        } else if (empId != null) {
            list = List.of(organizationService.getEmployeeByEmployeeId(empId));
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("orgChartPage")
    public String orgChartPage(){
        return "organization/orgChart";
    }

}
