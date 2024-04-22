package com.groups.schicken.erp.product;

import com.groups.schicken.common.vo.CodeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public List<ProductVO> getProductList(ProductVO productVO) throws Exception {
        return productMapper.getProductList(productVO);
    }

    public ProductVO getProduct(ProductVO productVO) throws Exception {
        return productMapper.getProduct(productVO);
    }

    public int addProduct(ProductVO productVO) throws Exception {
        return productMapper.addProduct(productVO);
    }

    public int updateProduct(ProductVO productVO) throws Exception {
        return productMapper.updateProduct(productVO);
    }

    public int deleteProduct(ProductVO productVO) throws Exception {
        return productMapper.updateProduct(productVO);
    }

    public List<CodeVO> getCategory() throws Exception {
        return productMapper.getCategory();
    }

    public List<CodeVO> getUnit() throws Exception{
        return productMapper.getUnit();
    }
}
