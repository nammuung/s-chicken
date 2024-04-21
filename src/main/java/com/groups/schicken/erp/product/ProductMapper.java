package com.groups.schicken.erp.product;

import com.groups.schicken.common.vo.CodeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    List<CodeVO> getCategory() throws Exception;
    List<ProductVO> getProductList(ProductVO productVO) throws Exception;
    ProductVO getProduct(ProductVO productVO) throws Exception;

    int addProduct(ProductVO productVO) throws Exception;

    int updateProduct(ProductVO productVO) throws Exception;

}
