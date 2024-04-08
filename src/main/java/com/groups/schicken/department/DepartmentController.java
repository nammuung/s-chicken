package com.groups.schicken.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/department/*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("addDepartment")
    @ResponseBody
    public ResponseEntity<DepartmentVO> addDepartment(@RequestBody DepartmentVO department){
        System.out.println("department = " + department);
        department = departmentService.addDepartment(department);

        if(department == null){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(department);
    }

    @GetMapping("getDepartment")
    @ResponseBody
    public ResponseEntity<DepartmentVO> getDepartment(DepartmentVO department){
        department = departmentService.getDepartment(department);

        return ResponseEntity.ok(department);
    }

    @GetMapping("list")
    @ResponseBody
    public ResponseEntity<List<DepartmentVO>> getList(){
        List<DepartmentVO> list = departmentService.getList();

        return ResponseEntity.ok(list);
    }
}
