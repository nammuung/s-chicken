package com.groups.schicken.erp.supplier;

import lombok.Data;

@Data
public class SupplierVO {
    private Long id;
    private String name;
    private String owner_name;
    private String address;
    private String address_detail;
    private String contact_number;
    private String registration_number;
    private String email;
    private String contract_date;
    private Long manager_id;
}
