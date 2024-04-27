package com.groups.schicken.file;

import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.common.util.FileManager;
import com.groups.schicken.common.vo.FileVO;
import com.groups.schicken.erp.product.ProductService;
import com.groups.schicken.erp.product.ProductVO;
import com.groups.schicken.erp.product.StockService;
import com.groups.schicken.erp.product.StockVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(properties = "spring.profiles.include=dev")
public class FileTest {
    @Autowired
    private FileManager fileManager;
    @Autowired
    private StockService stockService;
    @Autowired
    private ProductService productService;

    @Test
    public void test() throws Exception {
        List<ProductVO> list = productService.getProductList(new ProductVO());
        for (ProductVO productVO : list) {
            StockVO stockVO = new StockVO();
            stockVO.setProduct(productVO);
            stockVO.setCreateDate(DateManager.getTodayDateTime());
            stockVO.setQuantity(0L);
            stockVO.setHistory("제품 생성에 따른 재고 초기화");
            stockService.updateStock(stockVO);
        }
    }
}
