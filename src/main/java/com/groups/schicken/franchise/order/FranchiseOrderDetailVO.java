package com.groups.schicken.franchise.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.common.vo.OrderDetailVO;
import com.groups.schicken.erp.item.ItemVO;
import com.groups.schicken.erp.product.ProductVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FranchiseOrderDetailVO extends OrderDetailVO {
    private ProductVO product;
    private FranchiseOrderVO order;
}
