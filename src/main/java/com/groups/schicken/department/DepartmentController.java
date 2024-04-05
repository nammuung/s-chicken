package com.groups.schicken.department;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department/*")
public class DepartmentController {

    @PostMapping("department")
    public void addDepartment(DepartmentVO department){

    }
}
