package com.groups.schicken.organization;

import com.groups.schicken.Employee.EmployeeProfileVO;
import lombok.Data;

import java.util.List;

@Data
public class ChattingEmployeeListVO {
    private String id;
    private String name;
    private List<EmployeeProfileVO> employees;
}
