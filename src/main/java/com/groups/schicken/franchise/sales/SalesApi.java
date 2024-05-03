package com.groups.schicken.franchise.sales;

import com.groups.schicken.common.vo.ResultVO;
import com.groups.schicken.erp.order.HeadOrderVO;
import com.groups.schicken.franchise.FranchiseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/")
public class SalesApi {
    private final SalesService salesService;
    private FranchiseVO franchise;
    {
        franchise = new FranchiseVO();
        franchise.setId("1098");
    }

    @GetMapping("sales")
    public ResponseEntity<?> getSalesList(@AuthenticationPrincipal FranchiseVO franchiseVO) {
        Sales sales = new Sales();
        if(franchiseVO == null) {
            sales.setFranchise(franchise);
        } else {
            sales.setFranchise(franchiseVO);
        }
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSalesList(sales)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
    @GetMapping("sales/{id}")
    public ResponseEntity<?> getSales(@AuthenticationPrincipal FranchiseVO franchiseVO, @PathVariable Long id) {
        Sales sales = new Sales();
        if(franchiseVO == null) {
            sales.setFranchise(franchise);
        } else {
            sales.setFranchise(franchiseVO);
        }
        sales.setId(id);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSales(sales)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
}
