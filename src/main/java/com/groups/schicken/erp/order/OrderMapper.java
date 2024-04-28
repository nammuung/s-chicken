package com.groups.schicken.erp.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    OrderVO getOrder(OrderVO orderVO) throws Exception;

    List<OrderVO> getOrderList(OrderVO orderVO) throws Exception;

    OrderDetailVO getOrderDetail(OrderDetailVO orderDetail) throws Exception;

    int addOrder(OrderVO orderVO) throws Exception;

    int addOrderDetail(OrderDetailVO orderDetail) throws Exception;

    Long getId() throws Exception;

    int updateOrder(OrderVO orderVO) throws Exception;

    int updateOrderDetail(Map<String, Object> map) throws Exception;
}
