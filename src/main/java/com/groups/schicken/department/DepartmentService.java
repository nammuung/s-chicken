package com.groups.schicken.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    DepartmentDAO departmentDAO;

    public DepartmentVO addDepartment(DepartmentVO department){
        int result = departmentDAO.addDepartment(department);

        if(result > 0){
            return department;
        }

        return null;
    }

    public DepartmentVO getDepartment(DepartmentVO department) {
        return departmentDAO.getDepartment(department);
    }
}
