package com.groups.schicken.erp.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderVO {
    private Long id;
    private SupplierVO supplier;
    private String writeDate;
    private String orderDate;
    private Integer status;
    private Long price;
    private String address;
    private EmployeeVO employee;
    private List<OrderItemVO> orderItems;
}
