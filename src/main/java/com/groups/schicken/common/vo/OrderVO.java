package com.groups.schicken.common.vo;

import lombok.Data;

@Data
public class OrderVO {
    private Long id;
    private Integer status;
    private String address;
    private String orderDate;
    private Long price;
    private String comment;
}
