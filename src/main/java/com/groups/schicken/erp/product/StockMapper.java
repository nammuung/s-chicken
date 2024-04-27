package com.groups.schicken.erp.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper {
    int updateStock(StockVO stockVO) throws Exception;
    List<StockVO> getStockHistory(StockVO stockVO) throws Exception;
    Long getTotalStock(StockVO stockVO) throws Exception;
}
