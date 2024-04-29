package com.groups.schicken.franchise.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FranchiseOrderMapper {
    FranchiseOrderVO getOrder(FranchiseOrderVO franchiseOrderVO) throws Exception;

    List<FranchiseOrderVO> getOrderList(FranchiseOrderVO franchiseOrderVO) throws Exception;

    FranchiseOrderDetailVO getOrderDetail(FranchiseOrderDetailVO orderDetail) throws Exception;

    int addOrder(FranchiseOrderVO franchiseOrderVO) throws Exception;

    int addOrderDetail(FranchiseOrderDetailVO orderDetail) throws Exception;

    Long getId() throws Exception;

    int updateOrder(FranchiseOrderVO franchiseOrderVO) throws Exception;

    int updateOrderDetail(Map<String, Object> map) throws Exception;
}
