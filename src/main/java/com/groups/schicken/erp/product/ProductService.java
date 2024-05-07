package com.groups.schicken.erp.product;

import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.common.vo.CodeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final StockMapper stockMapper;

    public List<ProductVO> getProductList(ProductVO productVO) throws Exception {
        return productMapper.getProductList(productVO);
    }

    public ProductVO getProduct(ProductVO productVO) throws Exception {
        return productMapper.getProduct(productVO);
    }

    public int addProduct(ProductVO productVO) throws Exception {
        int result = productMapper.addProduct(productVO);
        if (result == 0) throw new Exception("제품 추가 실패");
        StockVO stockVO = new StockVO();
        stockVO.setProduct(productVO);
        stockVO.setQuantity(0L);
        stockVO.setHistory("제품 생성에 따른 재고 초기화");
        stockVO.setCreateDate(DateManager.getTodayDateTime());
        result = stockMapper.updateStock(stockVO);
        if (result == 0) throw new Exception("재고 추가 실패");
        return result;
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
