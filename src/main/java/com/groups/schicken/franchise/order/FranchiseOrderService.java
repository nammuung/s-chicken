package com.groups.schicken.franchise.order;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.common.vo.OrderDetailVO;
import com.groups.schicken.erp.item.ItemMapper;
import com.groups.schicken.erp.product.ProductMapper;
import com.groups.schicken.erp.product.ProductVO;
import com.groups.schicken.erp.product.StockMapper;
import com.groups.schicken.erp.product.StockVO;
import com.groups.schicken.franchise.FranchiseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FranchiseOrderService {
    private final FranchiseOrderMapper franchiseOrderMapper;
    private final StockMapper stockMapper;
    private final ProductMapper productMapper;

    public List<FranchiseOrderVO> getOrderList(FranchiseOrderVO franchiseOrderVO) throws Exception {
        return franchiseOrderMapper.getOrderList(franchiseOrderVO);
    }

    public FranchiseOrderVO getOrder(FranchiseOrderVO franchiseOrderVO) throws Exception {
        return franchiseOrderMapper.getOrder(franchiseOrderVO);
    }

    public int addOrder(FranchiseOrderVO franchiseOrderVO) throws Exception {
        franchiseOrderVO.setWriteDate(DateManager.getTodayDate());
        List<Long> detailPrice = new ArrayList<Long>();
        Long totalPrice = 0L;
        for(FranchiseOrderDetailVO detail : franchiseOrderVO.getOrderDetails()) {
            ProductVO productVO = productMapper.getProduct(detail.getProduct());
            Long price = productVO.getSellPrice() * detail.getQuantity();
            detailPrice.add(productVO.getSellPrice());
            totalPrice += price;
        }
        franchiseOrderVO.setPrice(totalPrice);
        int result = franchiseOrderMapper.addOrder(franchiseOrderVO);
        if(result == 0) throw new Exception("주문 추가 실패");
        List<FranchiseOrderDetailVO> orderDetails = franchiseOrderVO.getOrderDetails();
        for (int i = 0, orderDetailsSize = orderDetails.size(); i < orderDetailsSize; i++) {
            FranchiseOrderDetailVO detail = orderDetails.get(i);
            detail.setOrder(franchiseOrderVO);
            detail.setPrice(detailPrice.get(i));
            result = franchiseOrderMapper.addOrderDetail(detail);
            if (result == 0) throw new Exception("주문 상세 추가 실패");
        }
        return 1;
    }

    public int updateOrder(FranchiseOrderVO franchiseOrderVO) throws Exception {
        franchiseOrderVO.setOrderDate(DateManager.getTodayDate());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", franchiseOrderVO.getStatus());
        map.put("orderId", franchiseOrderVO.getId());
        int result = franchiseOrderMapper.submitOrderDetail(map);
        if (result == 0) throw new Exception();
        result = franchiseOrderMapper.updateOrder(franchiseOrderVO);
        if (result == 0) throw new Exception();
        return 1;
    }

    public int updateOrderDetail(List<FranchiseOrderDetailVO> franchiseOrderDetailVOList) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", franchiseOrderDetailVOList);
        Boolean statusTemp = null;
        for (FranchiseOrderDetailVO orderDetail : franchiseOrderDetailVOList) {
            FranchiseOrderDetailVO prevOrderDetail = franchiseOrderMapper.getOrderDetail(orderDetail);
            Integer prevQuantity = prevOrderDetail.getDeliverQuantity();
            if(!Objects.equals(prevQuantity, orderDetail.getDeliverQuantity())){
                Long difQuantity = (long) (prevQuantity - orderDetail.getDeliverQuantity() );
                StockVO stockVO = new StockVO();
                stockVO.setCreateDate(DateManager.getTodayDateTime());
                stockVO.setProduct(prevOrderDetail.getProduct());
                stockVO.setQuantity(difQuantity);
                stockVO.setHistory("출고에 따른 재고 삭감");
                int result = stockMapper.updateStock(stockVO);
                if(result == 0) throw new Exception("재고 삭감 실패");
            }
            if(orderDetail.getStatus() == 2){
                statusTemp = true;
                break;
            }
        }
        if(statusTemp == null){
            FranchiseOrderVO order = new FranchiseOrderVO();
            order.setId(franchiseOrderDetailVOList.get(0).getOrder().getId());
            order.setStatus(3);
            int result = franchiseOrderMapper.updateOrder(order);
            if(result == 0) throw new Exception("발주서 상태 변경 실패");
        }
        return franchiseOrderMapper.updateOrderDetail(map);
    }
}
