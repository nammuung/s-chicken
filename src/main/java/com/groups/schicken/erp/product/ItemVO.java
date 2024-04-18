package com.groups.schicken.erp.product;

import lombok.Data;

@Data
public class ItemVO {
    private Long id;
    private Long supplier_id;
    private Long product_id;
    private Integer min_quantity;
    private Long contract_price;
    private Long sell_price;
    private String create_date;
    private String modify_date;
    private String uni_id;
}
