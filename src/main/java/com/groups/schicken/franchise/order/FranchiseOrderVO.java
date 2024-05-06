package com.groups.schicken.franchise.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.OrderVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import com.groups.schicken.franchise.FranchiseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FranchiseOrderVO extends OrderVO {
    private FranchiseVO franchise;
    private List<FranchiseOrderDetailVO> orderDetails;
}
