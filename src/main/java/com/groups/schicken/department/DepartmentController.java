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
    public ResponseEntity<List<DepartmentVO>> addDepartment(@RequestBody DepartmentVO department){
        System.out.println("department = " + department);
        Integer result = departmentService.addDepartment(department);

        if(result < 1){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(departmentService.getList());
    }

    @PostMapping("updateDepartment")
    public ResponseEntity<List<DepartmentVO>> updateDepartment(@RequestBody DepartmentVO department){
        System.out.println("department = " + department);
        Integer result = departmentService.updateDepartment(department);

        if(result < 1){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(departmentService.getList());
    }

    @GetMapping("checkContactNumber")
    public ResponseEntity<Boolean> checkContactNumber(String contactNumber, String except){
        return ResponseEntity.ok(departmentService.checkContactNumber(contactNumber, except));
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
