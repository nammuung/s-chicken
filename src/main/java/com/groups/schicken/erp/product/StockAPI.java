package com.groups.schicken.erp.product;

import com.groups.schicken.common.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/stocks")
@RequiredArgsConstructor
public class StockAPI {
    private final StockService stockService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> getStocks(@PathVariable String productId) throws Exception {
        StockVO stockVO = new StockVO();
        ProductVO productVO = new ProductVO();
        productVO.setId(productId);
        stockVO.setProduct(productVO);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "재고 조회 완료", stockService.getStockHistory(stockVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @PostMapping
    public ResponseEntity<?> addStock(StockVO stockVO) throws Exception {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "재고 반영 완료", stockService.updateStock(stockVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
}
