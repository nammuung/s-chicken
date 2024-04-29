package com.groups.schicken.common.vo;

import lombok.Data;

@Data
public class OrderDetailVO {
    private OrderVO order;
    private Integer quantity;
    private Integer deliverQuantity;
    private Integer status;
    private Long price;
    private String comment;
}
