package com.groups.schicken.erp.order;

import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.erp.item.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;

    public List<OrderVO> getOrderList(OrderVO orderVO) throws Exception {
        return orderMapper.getOrderList(orderVO);
    }

    public int addOrder(OrderVO orderVO) throws Exception {
        orderVO.setOrderDate(DateManager.getTodayDate());
        orderMapper.addOrder(orderVO);
//        Map<String, List<OrderVO>> orderList = new HashMap<>();
        for(OrderItemVO orderItem : orderVO.getOrderItems()){
            orderItem.setOrder(orderVO);
            orderItem.setStatus(0);
            if(orderMapper.addOrderItem(orderItem)!=1) throw new Exception();
        }
        return 1;
    }
}
