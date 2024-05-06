package com.groups.schicken.erp.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.Employee.EmployeeVO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
