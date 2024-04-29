package com.groups.schicken.erp.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.common.vo.OrderDetailVO;
import com.groups.schicken.erp.item.ItemVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeadOrderDetailVO extends OrderDetailVO {
    private Long id;
    private ItemVO item;
    private SupplierVO supplier;
    private HeadOrderVO order;
}
