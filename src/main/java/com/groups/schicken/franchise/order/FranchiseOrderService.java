package com.groups.schicken.franchise.order;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.erp.item.ItemMapper;
import com.groups.schicken.erp.product.StockMapper;
import com.groups.schicken.erp.product.StockVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FranchiseOrderService {
    private final FranchiseOrderMapper franchiseOrderMapper;
    private final ItemMapper itemMapper;
    private final StockMapper stockMapper;

    public List<FranchiseOrderVO> getOrderList(FranchiseOrderVO franchiseOrderVO) throws Exception {
        return franchiseOrderMapper.getOrderList(franchiseOrderVO);
    }

    public FranchiseOrderVO getOrder(FranchiseOrderVO franchiseOrderVO) throws Exception {
        return franchiseOrderMapper.getOrder(franchiseOrderVO);
    }

    public int addOrder(List<FranchiseOrderDetailVO> franchiseOrderDetailVOList, EmployeeVO employeeVO) throws Exception {
        return 1;
    }

    public int updateOrder(FranchiseOrderVO franchiseOrderVO) throws Exception {
        franchiseOrderVO.setOrderDate(DateManager.getTodayDate());
        return franchiseOrderMapper.updateOrder(franchiseOrderVO);
    }

    public int updateOrderDetail(List<FranchiseOrderDetailVO> franchiseOrderDetailVOList) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", franchiseOrderDetailVOList);
        Boolean statusTemp = null;
        for (FranchiseOrderDetailVO orderDetail : franchiseOrderDetailVOList) {
            FranchiseOrderDetailVO prevOrderDetail = franchiseOrderMapper.getOrderDetail(orderDetail);
            Integer prevQuantity = prevOrderDetail.getDeliverQuantity();
            if(!Objects.equals(prevQuantity, orderDetail.getDeliverQuantity())){
                Long difQuantity = (long) (orderDetail.getDeliverQuantity() - prevQuantity);
                StockVO stockVO = new StockVO();
                stockVO.setCreateDate(DateManager.getTodayDateTime());
                stockVO.setProduct(prevOrderDetail.getProduct());
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
            FranchiseOrderVO order = new FranchiseOrderVO();
            order.setId(franchiseOrderDetailVOList.get(0).getOrder().getId());
            order.setStatus(2);
            int result = franchiseOrderMapper.updateOrder(order);
            if(result == 0) throw new Exception("발주서 상태 변경 실패");
        }
        return franchiseOrderMapper.updateOrderDetail(map);
    }
}
