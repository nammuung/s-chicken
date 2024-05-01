package com.groups.schicken.erp.order.history;

import com.groups.schicken.common.vo.OrderVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import lombok.Data;

@Data
public class HistoryVO {
    private OrderVO order;
    private SupplierVO supplier;
    private String writeDate;
    private String content;
}
