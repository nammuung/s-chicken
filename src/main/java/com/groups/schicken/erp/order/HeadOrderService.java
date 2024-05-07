package com.groups.schicken.erp.order;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.erp.item.ItemMapper;
import com.groups.schicken.erp.order.history.HistoryMapper;
import com.groups.schicken.erp.order.history.HistoryVO;
import com.groups.schicken.erp.product.StockMapper;
import com.groups.schicken.erp.product.StockVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HeadOrderService {
    private final HeadOrderMapper headOrderMapper;
    private final StockMapper stockMapper;
    private final HistoryMapper historyMapper;

    public List<HeadOrderVO> getOrderList(HeadOrderVO headOrderVO) throws Exception {
        return headOrderMapper.getOrderList(headOrderVO);
    }
    public List<HeadOrderVO> getOrderSupList(HeadOrderVO headOrderVO) throws Exception {
        return headOrderMapper.getOrderSupList(headOrderVO);
    }

    public HeadOrderVO getOrder(HeadOrderVO headOrderVO) throws Exception {
        return headOrderMapper.getOrder(headOrderVO);
    }

    public int addOrder(HeadOrderVO headOrderVO) throws Exception {
        HashMap<String, HeadOrderVO> orderListMap = new HashMap<>();
        for(HeadOrderDetailVO headOrderDetailVO : headOrderVO.getOrderDetails()){
            String supplierName = headOrderDetailVO.getItem().getSupplier().getName();
            Long itemPrice = headOrderDetailVO.getItem().getContractPrice();
            Integer itemQuantity = headOrderDetailVO.getQuantity();
            headOrderDetailVO.setSupplier(headOrderDetailVO.getItem().getSupplier());
            if(orderListMap.containsKey(supplierName)){
                HeadOrderVO order = (HeadOrderVO) orderListMap.get(supplierName);
                order.getOrderDetails().add(headOrderDetailVO);
                order.setPrice(order.getPrice() + itemPrice * itemQuantity);
                headOrderDetailVO.setPrice(itemPrice);
            } else {
                HeadOrderVO order = new HeadOrderVO();
                order.setEmployee(headOrderVO.getEmployee());
                List<HeadOrderDetailVO> list = new ArrayList<>();
                list.add(headOrderDetailVO);
                order.setSupplier(headOrderDetailVO.getItem().getSupplier());
                order.setPrice(itemPrice * itemQuantity);
                headOrderDetailVO.setPrice(itemPrice);
                order.setOrderDetails(list);
                order.setWriteDate(DateManager.getTodayDate());
                orderListMap.put(supplierName, order);
            }
        }
        Long orderId = headOrderMapper.getId();
        for (String key : orderListMap.keySet()) {
            HeadOrderVO headOrderVO2 = (HeadOrderVO) orderListMap.get(key);
            headOrderVO2.setId(orderId);
            headOrderVO2.setComment(headOrderVO.getComment());
            headOrderMapper.addOrder(headOrderVO2);
            for(HeadOrderDetailVO orderDetail : headOrderVO2.getOrderDetails()){
                orderDetail.setOrder(headOrderVO2);
                orderDetail.setStatus(0);
                if(headOrderMapper.addOrderDetail(orderDetail)!=1) throw new Exception();
            }
        }
        return 1;
    }

    public int updateOrder(HeadOrderVO headOrderVO) throws Exception {
        headOrderVO.setOrderDate(DateManager.getTodayDate());
        return headOrderMapper.updateOrder(headOrderVO);
    }

    public int updateOrderDetail(List<HeadOrderDetailVO> headOrderDetailVOList) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", headOrderDetailVOList);
        Boolean statusTemp = null;
        for (HeadOrderDetailVO orderDetail : headOrderDetailVOList) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@first = " + orderDetail);
            HeadOrderDetailVO prevOrderDetail = headOrderMapper.getOrderDetail(orderDetail);
            Integer prevQuantity = prevOrderDetail.getDeliverQuantity();
            if(!Objects.equals(prevQuantity, orderDetail.getDeliverQuantity())){
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@second = " + orderDetail);
                Long difQuantity = (long) (orderDetail.getDeliverQuantity() - prevQuantity);
                StockVO stockVO = new StockVO();
                stockVO.setCreateDate(DateManager.getTodayDateTime());
                stockVO.setProduct(prevOrderDetail.getItem().getProduct());
                stockVO.setQuantity(difQuantity);
                stockVO.setHistory("입고에 따른 재고 추가");
                int result = stockMapper.updateStock(stockVO);
                if(result == 0) throw new Exception("재고 추가 실패");
                HistoryVO historyVO = new HistoryVO();
                historyVO.setOrder(orderDetail.getOrder());
                historyVO.setSupplier(orderDetail.getSupplier());
                historyVO.setWriteDate(DateManager.getTodayDateTime());
                historyVO.setContent(prevOrderDetail.getItem().getProduct().getName()+" "+difQuantity+"개 입고");
                result = historyMapper.addReceiveHistory(historyVO);
                if(result == 0) throw new Exception("내역 추가 실패");
            }
            if(orderDetail.getStatus() == 0){
                statusTemp = true;
            }
        }
        if(statusTemp == null){
            HeadOrderVO order = new HeadOrderVO();
            order.setId(headOrderDetailVOList.get(0).getOrder().getId());
            order.setSupplier(headOrderDetailVOList.get(0).getSupplier());
            order.setStatus(2);
            int result = headOrderMapper.updateOrder(order);
            if(result == 0) throw new Exception("발주서 상태 변경 실패");
        }
        return headOrderMapper.updateOrderDetail(map);
    }
}
