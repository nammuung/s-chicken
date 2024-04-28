package com.groups.schicken.erp.product;

import com.groups.schicken.common.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/products")
public class ProductApi {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProductList(ProductVO productVO) throws Exception {
        System.out.println("productVO = " + productVO);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), productService.getProductList(productVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getProductList(@PathVariable String id) throws Exception {
        ProductVO productVO = new ProductVO();
        productVO.setId(id);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), productService.getProduct(productVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
    @PostMapping
    @Transactional
    public ResponseEntity<?> addProduct(ProductVO productVO) throws Exception {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "제품 추가 완료", productService.addProduct(productVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(ProductVO productVO) throws Exception {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "제품 수정 완료", productService.updateProduct(productVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(ProductVO productVO) throws Exception {
        try {
            return ResponseEntity.ok(productService.deleteProduct(productVO));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
}
