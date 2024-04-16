package com.groups.schicken.order.product;

import com.groups.schicken.util.Pager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> getProductList(ProductVO productVO) throws Exception;
    ProductVO getProduct(ProductVO productVO) throws Exception;

    int addProduct(ProductVO productVO) throws Exception;

    int updateProduct(ProductVO productVO) throws Exception;

}
