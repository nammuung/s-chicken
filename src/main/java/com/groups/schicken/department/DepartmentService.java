package com.groups.schicken.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    DepartmentDAO departmentDAO;

    public int  addDepartment(DepartmentVO department){
        int result = departmentDAO.addDepartment(department);
        return result;
    }
}
