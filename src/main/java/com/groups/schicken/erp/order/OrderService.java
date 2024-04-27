package com.groups.schicken.erp.order;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.erp.item.ItemMapper;
import com.groups.schicken.erp.product.StockMapper;
import com.groups.schicken.erp.product.StockVO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final StockMapper stockMapper;

    public List<OrderVO> getOrderList(OrderVO orderVO) throws Exception {
        return orderMapper.getOrderList(orderVO);
    }

    public int addOrder(List<OrderDetailVO> orderDetailVOList, EmployeeVO employeeVO) throws Exception {
        HashMap<String, OrderVO> orderListMap = new HashMap<>();
        for(OrderDetailVO orderDetailVO : orderDetailVOList){
            String supplierName = orderDetailVO.getItem().getSupplier().getName();
            Long itemPrice = orderDetailVO.getItem().getContractPrice();
            Integer itemQuantity = orderDetailVO.getQuantity();
            orderDetailVO.setSupplier(orderDetailVO.getItem().getSupplier());
            if(orderListMap.containsKey(supplierName)){
                OrderVO order = (OrderVO) orderListMap.get(supplierName);
                order.getOrderDetails().add(orderDetailVO);
                order.setPrice(order.getPrice() + itemPrice * itemQuantity);
                orderDetailVO.setPrice(itemPrice);
            } else {
                OrderVO order = new OrderVO();
                order.setEmployee(employeeVO);
                List<OrderDetailVO> list = new ArrayList<>();
                list.add(orderDetailVO);
                order.setSupplier(orderDetailVO.getItem().getSupplier());
                order.setPrice(itemPrice * itemQuantity);
                orderDetailVO.setPrice(itemPrice);
                order.setOrderDetails(list);
                order.setWriteDate(DateManager.getTodayDate());
                orderListMap.put(supplierName, order);
            }
        }
        Long orderId = orderMapper.getId();
        for (String key : orderListMap.keySet()) {
            OrderVO orderVO = (OrderVO) orderListMap.get(key);
            orderVO.setId(orderId);
            orderMapper.addOrder(orderVO);
            for(OrderDetailVO orderDetail : orderVO.getOrderDetails()){
                System.out.println("orderDetail = " + orderDetail);
                orderDetail.setOrder(orderVO);
                orderDetail.setStatus(0);
                if(orderMapper.addOrderDetail(orderDetail)!=1) throw new Exception();
            }
        }
        return 1;
    }

    public List<OrderVO> getOrderDetail(OrderVO orderVO) throws Exception {
        return orderMapper.getOrderDetail(orderVO);
    }

    public int updateOrder(OrderVO orderVO) throws Exception {
        orderVO.setOrderDate(DateManager.getTodayDate());
        return orderMapper.updateOrder(orderVO);
    }

    public List<OrderVO> getOrderSheetList(OrderVO orderVO) throws Exception {
        return orderMapper.getOrderSheetList(orderVO);
    }

    public OrderVO getOrderSheet(OrderVO orderVO) throws Exception {
        return orderMapper.getOrderSheet(orderVO);
    }

    public int updateOrderDetail(List<OrderDetailVO> orderDetailVOList) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", orderDetailVOList);
        Boolean statusTemp = null;
        for (OrderDetailVO orderDetail : orderDetailVOList) {
            OrderDetailVO prevOrderDetail = orderMapper.getOrderDetailDetail(orderDetail);
            Integer prevQuantity = prevOrderDetail.getDeliverQuantity();
            if(!Objects.equals(prevQuantity, orderDetail.getDeliverQuantity())){
                Long difQuantity = (long) (orderDetail.getDeliverQuantity() - prevQuantity);
                StockVO stockVO = new StockVO();
                stockVO.setCreateDate(DateManager.getTodayDateTime());
                stockVO.setProduct(prevOrderDetail.getItem().getProduct());
                stockVO.setQuantity(difQuantity);
                stockVO.setHistory("입고에 따른 재고 추가");
                int result = stockMapper.updateStock(stockVO);
                if(result == 0) throw new Exception("재고 추가 실패");
            }
            if(orderDetail.getStatus() == 0){
                statusTemp = true;
                break;
            }
        }
        if(statusTemp == null){
            OrderVO order = new OrderVO();
            order.setId(orderDetailVOList.get(0).getOrder().getId());
            order.setSupplier(orderDetailVOList.get(0).getSupplier());
            order.setStatus(2);
            int result = orderMapper.updateOrder(order);
            if(result == 0) throw new Exception("발주서 상태 변경 실패");
        }
        return orderMapper.updateOrderDetail(map);
    }
}
