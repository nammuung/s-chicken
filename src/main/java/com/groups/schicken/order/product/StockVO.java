package com.groups.schicken.order.product;

import lombok.Data;

@Data
public class StockVO {
    private Long id;
    private Long quantity;
    private String expire_date;
    private Long product_id;
}
