package com.groups.schicken.order.product;

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

    public List<Map<String, Object>> getCategory() throws Exception {
        return productMapper.getCategory();
    }
}
