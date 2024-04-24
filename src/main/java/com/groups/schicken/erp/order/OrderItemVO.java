package com.groups.schicken.erp.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.erp.item.ItemVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemVO {
    private Long id;
    private ItemVO item;
    private SupplierVO supplier;
    private OrderVO order;
    private Integer quantity;
    private Integer deliver_quantity;
    private Integer status;
    private Long price;
    private String comment;
}
