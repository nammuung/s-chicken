package com.groups.schicken.erp.order;

import com.groups.schicken.erp.item.ItemVO;
import lombok.Data;

@Data
public class OrderItemVO {
    private Long id;
    private ItemVO item;
    private OrderVO order;
    private Integer quantity;
    private Integer deliver_quantity;
    private Integer status;
    private Long price;
    private String comment;
}
