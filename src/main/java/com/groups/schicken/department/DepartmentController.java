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
    public ResponseEntity<DepartmentVO> addDepartment(@RequestBody DepartmentVO department){
        System.out.println("department = " + department);
        department = departmentService.addDepartment(department);

        if(department == null){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(department);
    }

    @GetMapping("checkContactNumber")
    public ResponseEntity<Boolean> checkContactNumber(String contactNumber){
        return ResponseEntity.ok(departmentService.checkContactNumber(contactNumber));
    }

    @GetMapping("getDepartment")
    public ResponseEntity<DepartmentVO> getDepartment(DepartmentVO department){
        department = departmentService.getDepartment(department);

        return ResponseEntity.ok(department);
    }

    @GetMapping("list")
    public ResponseEntity<List<DepartmentVO>> getList(){
        List<DepartmentVO> list = departmentService.getList();

        return ResponseEntity.ok(list);
    }
}
