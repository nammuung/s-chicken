package com.groups.schicken.franchise.sales;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalesMapper {
    int addSales(Sales sales) throws Exception;
    int addSalesDetail(Sales.Detail detail) throws Exception;
    int addListSalesDetail(List<Sales.Detail> details) throws Exception;
    int addListSales(List<Sales> sales) throws Exception;
    Sales getSales(Sales sales) throws Exception;
    List<Sales> getSalesList(Sales sales) throws Exception;

    List<Sales> getPerMonth(Sales sales) throws Exception;
    List<Sales> getPerWeak(Sales sales) throws Exception;
    List<Sales> getPerDays(Sales sales) throws Exception;
}
