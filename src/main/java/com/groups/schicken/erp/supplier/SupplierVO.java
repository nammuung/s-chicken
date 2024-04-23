package com.groups.schicken.erp.supplier;

import com.groups.schicken.Employee.EmployeeVO;
import lombok.Data;

@Data
public class SupplierVO {
    private Long id;
    private String name;
    private String ownerName;
    private String address;
    private String addressDetail;
    private String contactNumber;
    private String registrationNumber;
    private String email;
    private String contractDate;
    private EmployeeVO manager;
}
