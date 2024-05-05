package com.groups.schicken.franchise.sales;

import com.groups.schicken.franchise.FranchiseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SalesMapper {
    int addSales(Sales sales) throws Exception;
    int addSalesDetail(Sales.Detail detail) throws Exception;
    int addListSalesDetail(List<Sales.Detail> details) throws Exception;
    int addListSales(List<Sales> sales) throws Exception;
    Sales getSales(Sales sales) throws Exception;
    List<Sales> getSalesList(Sales sales) throws Exception;

    List<Sales> getPerMonth(Sales sales) throws Exception;
    List<Sales> getPerWeeks(Sales sales) throws Exception;
    List<Sales> getPerDays(Sales sales) throws Exception;

    Map<String, Object> getSellPerDays(FranchiseVO franchiseVO) throws Exception;
    Map<String, Object> getSellPerWeeks(FranchiseVO franchiseVO) throws Exception;
    Map<String, Object> getSellPerMonth(FranchiseVO franchiseVO) throws Exception;
    Map<String, Object> getSalesPerDays(FranchiseVO franchiseVO) throws Exception;
    Map<String, Object> getSalesPerWeeks(FranchiseVO franchiseVO) throws Exception;
    Map<String, Object> getSalesPerMonth(FranchiseVO franchiseVO) throws Exception;

}
