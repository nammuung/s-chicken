package com.groups.schicken.franchise.sales;

import com.groups.schicken.common.vo.Pager;
import com.groups.schicken.franchise.FranchiseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesMapper salesMapper;

    public List<Sales> getSalesList(Sales sales, Pager pager) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("sales", sales);
        map.put("pager", pager);
        pager.setPerPage(50L);
        pager.makeNum(salesMapper.getTotalSales(map));
        return salesMapper.getSalesList(map);
    }

    public Sales getSales(Sales sales) throws Exception {
        return salesMapper.getSales(sales);
    }

    public List<Sales> getPerMonth(Sales sales) throws Exception {
        return salesMapper.getPerMonth(sales);
    }
    public List<Sales> getPerWeeks(Sales sales) throws Exception {
        return salesMapper.getPerWeeks(sales);
    }
    public List<Sales> getPerDays(Sales sales) throws Exception {
        return salesMapper.getPerDays(sales);
    }
    public Map<String, Object> getSellPerDays(FranchiseVO franchiseVO) throws Exception {
        return salesMapper.getSellPerDays(franchiseVO);
    }
    public Map<String, Object> getSellPerWeeks(FranchiseVO franchiseVO) throws Exception {
        return salesMapper.getSellPerWeeks(franchiseVO);
    }
    public Map<String, Object> getSellPerMonth(FranchiseVO franchiseVO) throws Exception {
        return salesMapper.getSellPerMonth(franchiseVO);
    }
    public Map<String, Object> getSalesPerDays(FranchiseVO franchiseVO) throws Exception {
        return salesMapper.getSalesPerDays(franchiseVO);
    }
    public Map<String, Object> getSalesPerWeeks(FranchiseVO franchiseVO) throws Exception {
        return salesMapper.getSalesPerWeeks(franchiseVO);
    }
    public Map<String, Object> getSalesPerMonth(FranchiseVO franchiseVO) throws Exception {
        return salesMapper.getSalesPerMonth(franchiseVO);
    }
}
