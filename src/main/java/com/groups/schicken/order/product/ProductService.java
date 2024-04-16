package com.groups.schicken.order.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public List<ProductVO> getProductList(ProductVO productVO) throws Exception {
        return productMapper.getProductList(productVO);
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
}
