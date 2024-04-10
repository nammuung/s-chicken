package com.groups.schicken.franchise.object;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.util.FileVO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FranchiseVO {
    private String id;
    private String name;
    private String ownerName;
    private String postCode;
    private String address;
    private String addressDetail;
    private String contactNumber;
    private String registrationNumber;
    private String email;
    private String password;
    private String contractDate;
    private String managerId;
    private Long registerId;
    private Long contractId;
    private EmployeeVO manager = new EmployeeVO();
    private FileVO register;
    private FileVO contract;
}
