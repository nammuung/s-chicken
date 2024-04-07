package com.groups.schicken.franchise;

import lombok.Data;

@Data
public class FranchiseVO {
    private Long id;
    private String name;
    private String owner_name;
    private String address;
    private String address_detail;
    private String contact_number;
    private String registration_number;
    private String email;
    private String password;
    private String contract_date;
    private Long manager_id;
}
