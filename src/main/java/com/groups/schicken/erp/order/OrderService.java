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

    public int updateOrder(OrderVO orderVO) throws Exception {
        return orderMapper.updateOrder(orderVO);
    }

    public List<OrderVO> getOrderSheetList(OrderVO orderVO) throws Exception {
        return orderMapper.getOrderSheetList(orderVO);
    }

    public OrderVO getOrderSheet(OrderVO orderVO) throws Exception {
        return orderMapper.getOrderSheet(orderVO);
    }

    public int updateOrderItem(List<OrderItemVO> orderItemVOList) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", orderItemVOList);
        Boolean statusTemp = null;
        for (OrderItemVO orderItem : orderItemVOList) {
            if(orderItem.getStatus() == 0){
                statusTemp = true;
                break;
            }
        }
        if(statusTemp == null){
            OrderVO order = new OrderVO();
            order.setId(orderItemVOList.get(0).getOrder().getId());
            order.setSupplier(orderItemVOList.get(0).getSupplier());
            order.setStatus(2);
            int result = orderMapper.updateOrder(order);
            if(result == 0) throw new Exception();
        }
        return orderMapper.updateOrderItem(map);
    }
}
