package com.groups.schicken.erp.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.OrderVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeadOrderVO extends OrderVO {
    private SupplierVO supplier;
    private String writeDate;
    private EmployeeVO employee;
    private List<HeadOrderDetailVO> orderDetails;
}
