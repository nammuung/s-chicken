package com.groups.schicken.erp.supplier;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupplierMapper {

    List<SupplierVO> getSupplierList(SupplierVO supplierVO) throws Exception;
    SupplierVO getSupplier(SupplierVO supplierVO) throws Exception;
    int addSupplier(SupplierVO supplierVO) throws Exception;
    int updateSupplier(SupplierVO supplierVO) throws Exception;
    int deleteSupplier(SupplierVO supplierVO) throws Exception;
}
