package com.groups.schicken.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/organization/*")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("orgChart")
    @ResponseBody
    public ResponseEntity<List<OrgElementVO>> getOrgChart(String opt){
        List<OrgElementVO> list  = organizationService.getOrgChart(opt);

        return ResponseEntity.ok(list);
    }

    @PostMapping("orgChart")
    public String addOrgChart(){
        return "aaa";
    }

}
