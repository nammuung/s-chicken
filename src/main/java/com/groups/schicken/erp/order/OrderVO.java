package com.groups.schicken.erp.order;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import lombok.Data;

import java.util.List;

@Data
public class OrderVO {
    private Long id;
    private SupplierVO supplier;
    private String orderDate;
    private Integer status;
    private Long price;
    private String address;
    private EmployeeVO employee;
    private List<OrderItemVO> orderItems;
}
