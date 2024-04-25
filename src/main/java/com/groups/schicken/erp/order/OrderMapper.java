package com.groups.schicken.erp.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    List<OrderVO> getOrderList(OrderVO orderVO) throws Exception;

    int addOrder(OrderVO orderVO) throws Exception;
    int addOrderItem(OrderItemVO orderItem) throws Exception;
    Long getId() throws Exception;

    List<OrderVO> getOrderDetail(OrderVO orderVO) throws Exception;

    int updateOrder(OrderVO orderVO) throws Exception;

    List<OrderVO> getOrderSheetList(OrderVO orderVO) throws Exception;

    OrderVO getOrderSheet(OrderVO orderVO) throws Exception;

    int updateOrderItem(Map<String, Object> map) throws Exception;
}
