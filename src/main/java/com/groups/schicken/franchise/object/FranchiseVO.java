package com.groups.schicken.franchise.object;

import lombok.Data;

@Data
public class FranchiseVO {
    private Long id;
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
    private Long managerId;
    private Long registerId;
    private Long contractId;
}
