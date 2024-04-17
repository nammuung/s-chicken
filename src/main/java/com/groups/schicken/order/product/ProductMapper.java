package com.groups.schicken.order.product;

import com.groups.schicken.util.Pager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface ProductMapper {
    List<Map<String, Object>> getCategory() throws Exception;
    List<ProductVO> getProductList(ProductVO productVO) throws Exception;
    ProductVO getProduct(ProductVO productVO) throws Exception;

    int addProduct(ProductVO productVO) throws Exception;

    int updateProduct(ProductVO productVO) throws Exception;

}
