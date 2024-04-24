package com.groups.schicken.erp.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OrderVO> getOrderList(OrderVO orderVO) throws Exception;

    int addOrder(OrderVO orderVO) throws Exception;
    int addOrderItem(OrderItemVO orderItem) throws Exception;
    Long getId() throws Exception;

    List<OrderVO> getOrderDetail(OrderVO orderVO) throws Exception;
}
