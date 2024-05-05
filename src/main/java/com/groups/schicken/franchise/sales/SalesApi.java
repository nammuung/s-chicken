package com.groups.schicken.franchise.sales;

import com.groups.schicken.common.vo.Pager;
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
    public ResponseEntity<?> getSalesList(@AuthenticationPrincipal FranchiseVO franchiseVO, Pager pager) {
        Sales sales = new Sales();
        if(franchiseVO == null) {
            sales.setFranchise(franchise);
        } else {
            sales.setFranchise(franchiseVO);
        }
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSalesList(sales, pager)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
    @GetMapping("sales/{id}")
    public ResponseEntity<?> getSalesList(@PathVariable String id, Pager pager) {
        Sales sales = new Sales();
        FranchiseVO franchiseVO = new FranchiseVO();
        franchiseVO.setId(id);
        sales.setFranchise(franchiseVO);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSalesList(sales, pager)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }


    @GetMapping("sales/month")
    public ResponseEntity<?> getPerMonth(@AuthenticationPrincipal FranchiseVO franchiseVO) {
        Sales sales = new Sales();
        if(franchiseVO == null) {
            sales.setFranchise(franchise);
        } else {
            sales.setFranchise(franchiseVO);
        }
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getPerMonth(sales)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("sales/weeks")
    public ResponseEntity<?> getPerWeeks(@AuthenticationPrincipal FranchiseVO franchiseVO) {
        Sales sales = new Sales();
        if(franchiseVO == null) {
            sales.setFranchise(franchise);
        } else {
            sales.setFranchise(franchiseVO);
        }
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getPerWeeks(sales)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("sales/days")
    public ResponseEntity<?> getPerDays(@AuthenticationPrincipal FranchiseVO franchiseVO) {
        Sales sales = new Sales();
        if(franchiseVO == null) {
            sales.setFranchise(franchise);
        } else {
            sales.setFranchise(franchiseVO);
        }
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getPerDays(sales)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
    @GetMapping("sales/days/one/{id}")
    public ResponseEntity<?> getPerDays(@PathVariable String id) {
        FranchiseVO franchiseVO = new FranchiseVO();
        franchiseVO.setId(id);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSalesPerDays(franchiseVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("sales/month/{id}")
    public ResponseEntity<?> getInquiryPerMonth(@PathVariable String id) {
        Sales sales = new Sales();
        FranchiseVO franchiseVO = new FranchiseVO();
        franchiseVO.setId(id);
        sales.setFranchise(franchiseVO);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getPerMonth(sales)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("sales/weeks/{id}")
    public ResponseEntity<?> getInquiryPerWeeks(@PathVariable String id) {
        Sales sales = new Sales();
        FranchiseVO franchiseVO = new FranchiseVO();
        franchiseVO.setId(id);
        sales.setFranchise(franchiseVO);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getPerWeeks(sales)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("sales/days/{id}")
    public ResponseEntity<?> getInquiryPerDays(@PathVariable String id) {
        Sales sales = new Sales();
        FranchiseVO franchiseVO = new FranchiseVO();
        franchiseVO.setId(id);
        sales.setFranchise(franchiseVO);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getPerDays(sales)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("sales/days/one")
    public ResponseEntity<?> getSalesDays(@AuthenticationPrincipal FranchiseVO franchiseVO) {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSalesPerDays(franchise)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("sales/month/one")
    public ResponseEntity<?> getMonth(@AuthenticationPrincipal FranchiseVO franchiseVO) {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSalesPerMonth(franchise)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("sales/weeks/one")
    public ResponseEntity<?> getWeeks(@AuthenticationPrincipal FranchiseVO franchiseVO) {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSalesPerWeeks(franchise)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }


    @GetMapping("sell/days")
    public ResponseEntity<?> getSellPerDays(@AuthenticationPrincipal FranchiseVO franchiseVO) {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSellPerDays(franchise)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
    @GetMapping("sell/days/{id}")
    public ResponseEntity<?> getSellPerDays(@PathVariable String id) {
        try {
            FranchiseVO franchise = new FranchiseVO();
            franchise.setId(id);
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSellPerDays(franchise)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
    @GetMapping("sell/weeks")
    public ResponseEntity<?> getSellPerWeeks(@AuthenticationPrincipal FranchiseVO franchiseVO) {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSellPerWeeks(franchise)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
    @GetMapping("sell/month")
    public ResponseEntity<?> getSellPerMonth(@AuthenticationPrincipal FranchiseVO franchiseVO) {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), salesService.getSellPerMonth(franchise)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
}
