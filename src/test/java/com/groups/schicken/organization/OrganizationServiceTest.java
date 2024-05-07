package com.groups.schicken.organization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.include=dev")
class OrganizationServiceTest {

    @Autowired
    OrganizationService organizationService;

    @Test
    void getEmployees() {
        var list = organizationService.getEmployeesByDepartmentId("7");

        System.out.println("list = " + list);
    }

    @Test
    void getChattingEmployeeList() {
        var list = organizationService.getChattingEmployeeList("22");

        System.out.println("list = " + list);
    }
}
