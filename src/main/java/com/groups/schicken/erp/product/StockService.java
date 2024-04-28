package com.groups.schicken.erp.product;

import com.groups.schicken.common.util.DateManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockMapper stockMapper;

    public int updateStock(StockVO stockVO) throws Exception {
        stockVO.setCreateDate(DateManager.getTodayDateTime());
        return stockMapper.updateStock(stockVO);
    }

    public List<StockVO> getStockHistory(StockVO stockVO) throws Exception {
        return stockMapper.getStockHistory(stockVO);
    }

    public Long getTotalStock(StockVO stockVO) throws Exception {
        return stockMapper.getTotalStock(stockVO);
    }
}
