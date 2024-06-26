package com.groups.schicken.erp.item;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.CodeVO;
import com.groups.schicken.erp.product.ProductVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemVO {
    private Long id;
    private SupplierVO supplier;
    private ProductVO product;
    private Integer minQuantity;
    private Long contractPrice;
    private String createDate;
    private String modifyDate;
    private EmployeeVO writer;
    private Boolean use;
}
