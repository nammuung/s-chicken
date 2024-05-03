package com.groups.schicken.franchise.sales;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesMapper salesMapper;

    public List<Sales> getSalesList(Sales sales) throws Exception {
        return salesMapper.getSalesList(sales);
    }

    public Sales getSales(Sales sales) throws Exception {
        return salesMapper.getSales(sales);
    }

    public List<Sales> getPerMonth(Sales sales) throws Exception {
        return salesMapper.getPerMonth(sales);
    }
    public List<Sales> getPerWeeks(Sales sales) throws Exception {
        return salesMapper.getPerWeak(sales);
    }
    public List<Sales> getPerDays(Sales sales) throws Exception {
        return salesMapper.getPerDays(sales);
    }
}
