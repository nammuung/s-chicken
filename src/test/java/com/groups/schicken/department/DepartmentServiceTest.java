package com.groups.schicken.department;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.include=dev")
@Transactional
class DepartmentServiceTest {

    @Autowired
    DepartmentDAO departmentDAO;

    @Test
    void addDepartment() {
        DepartmentVO departmentVO = new DepartmentVO();
        departmentVO.setName("S-chicken");
        departmentVO.setContactNumber("0000");

        int result = departmentDAO.addDepartment(departmentVO);
        System.out.println("result = " + result);
        assertEquals(1, result);
    }

    @Test
    void getDepartment() {
        DepartmentVO departmentVO = new DepartmentVO();
        departmentVO.setId(1L);
        departmentVO = departmentDAO.getDepartment(departmentVO);

        System.out.println("departmentVO = " + departmentVO);
    }

    @Test
    void getList(){
        var list = departmentDAO.getList();
        System.out.println("list = " + list);
        assertNotEquals(0, list.size());
    }

    @Test
    void getListWithHierarchy(){
        var list = departmentDAO.getListWithHierarchy();
        System.out.println("list = " + list);
        assertNotNull(list.get(0).getSort());
    }

    @Test
    void countChildren(){
        DepartmentVO department = new DepartmentVO();
        department.setId(1L);

        var count = departmentDAO.countChildren(department);
        assertNotEquals(0, count);
    }

    @Test
    void deleteDepartment(){
        DepartmentVO departmentVO = new DepartmentVO();
        departmentVO.setId(212L);

        var count = departmentDAO.deleteDepartment(departmentVO);
        assertEquals(1, count);
    }
}
