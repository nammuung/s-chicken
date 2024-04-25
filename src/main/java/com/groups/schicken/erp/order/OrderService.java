package com.groups.schicken.erp.order;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.erp.item.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;

    public List<OrderVO> getOrderList(OrderVO orderVO) throws Exception {
        return orderMapper.getOrderList(orderVO);
    }

    public int addOrder(List<OrderItemVO> orderItemVOList, EmployeeVO employeeVO) throws Exception {
        HashMap<String, OrderVO> orderListMap = new HashMap<>();
        for(OrderItemVO orderItemVO : orderItemVOList){
            String supplierName = orderItemVO.getItem().getSupplier().getName();
            Long itemPrice = orderItemVO.getItem().getContractPrice();
            Integer itemQuantity = orderItemVO.getQuantity();
            orderItemVO.setSupplier(orderItemVO.getItem().getSupplier());
            if(orderListMap.containsKey(supplierName)){
                OrderVO order = (OrderVO) orderListMap.get(supplierName);
                order.getOrderItems().add(orderItemVO);
                order.setPrice(order.getPrice() + itemPrice * itemQuantity);
                orderItemVO.setPrice(itemPrice);
            } else {
                OrderVO order = new OrderVO();
                order.setEmployee(employeeVO);
                List<OrderItemVO> list = new ArrayList<>();
                list.add(orderItemVO);
                order.setSupplier(orderItemVO.getItem().getSupplier());
                order.setPrice(itemPrice * itemQuantity);
                orderItemVO.setPrice(itemPrice);
                order.setOrderItems(list);
                order.setOrderDate(DateManager.getTodayDate());
                orderListMap.put(supplierName, order);
            }
        }
        Long orderId = orderMapper.getId();
        for (String key : orderListMap.keySet()) {
            OrderVO orderVO = (OrderVO) orderListMap.get(key);
            orderVO.setId(orderId);
            orderMapper.addOrder(orderVO);
            for(OrderItemVO orderItem : orderVO.getOrderItems()){
                System.out.println("orderItem = " + orderItem);
                orderItem.setOrder(orderVO);
                orderItem.setStatus(0);
                if(orderMapper.addOrderItem(orderItem)!=1) throw new Exception();
            }
        }
        return 1;
    }

    public List<OrderVO> getOrderDetail(OrderVO orderVO) throws Exception {
        return orderMapper.getOrderDetail(orderVO);
    }

    public OrderVO getOrderSheet(OrderVO orderVO) throws Exception {
        return orderMapper.getOrderSheet(orderVO);
    }
}
