package com.groups.schicken.erp.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HeadOrderMapper {
    HeadOrderVO getOrder(HeadOrderVO headOrderVO) throws Exception;

    List<HeadOrderVO> getOrderList(HeadOrderVO headOrderVO) throws Exception;

    HeadOrderDetailVO getOrderDetail(HeadOrderDetailVO orderDetail) throws Exception;

    int addOrder(HeadOrderVO headOrderVO) throws Exception;

    int addOrderDetail(HeadOrderDetailVO orderDetail) throws Exception;

    Long getId() throws Exception;

    int updateOrder(HeadOrderVO headOrderVO) throws Exception;

    int updateOrderDetail(Map<String, Object> map) throws Exception;
}
