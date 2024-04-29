package com.groups.schicken.franchise.order;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.ResultVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import com.groups.schicken.franchise.FranchiseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/franchise/")
public class FranchiseOrderAPI {
    private final FranchiseOrderService franchiseOrderService;
    private FranchiseVO franchiseVO = new FranchiseVO();
    {
        franchiseVO.setId("1098");
    };
    @GetMapping("orders")
    public ResponseEntity<?> getOrderList(@AuthenticationPrincipal FranchiseVO franchise ,FranchiseOrderVO franchiseOrderVO) throws Exception {
        try {
//            if(franchiseVO == null) return ResponseEntity.badRequest().build();
            franchiseOrderVO.setFranchise(franchiseVO);
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), franchiseOrderService.getOrderList(franchiseOrderVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
    @GetMapping("orders/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) throws Exception {
        FranchiseOrderVO franchiseOrderVO = new FranchiseOrderVO();
        franchiseOrderVO.setId(id);
        try {
            System.out.println("오더 디테일 : "+ franchiseOrderService.getOrder(franchiseOrderVO));
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), franchiseOrderService.getOrder(franchiseOrderVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
    @GetMapping("orders/{orderId}/{supplierId}")
    public ResponseEntity<?> getOrderList(@PathVariable Long orderId,@PathVariable Long supplierId) throws Exception {
        FranchiseOrderVO franchiseOrderVO = new FranchiseOrderVO();
        franchiseOrderVO.setId(orderId);
        try {
            System.out.println("오더 디테일 : "+ franchiseOrderService.getOrder(franchiseOrderVO));
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), franchiseOrderService.getOrder(franchiseOrderVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @PostMapping("orders")
    @Transactional
    public ResponseEntity<?> addOrder(@AuthenticationPrincipal FranchiseVO franchise,@RequestBody FranchiseOrderVO franchiseOrderVO) throws Exception {
//        if (franchiseVO == null) return ResponseEntity.badRequest().build();
        try {
            franchiseOrderVO.setFranchise(franchiseVO);
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "발주 완료", franchiseOrderService.addOrder(franchiseOrderVO)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @PutMapping("orders")
    public ResponseEntity<?> updateOrder(FranchiseOrderVO franchiseOrderVO) throws Exception {
        try {
            int result = franchiseOrderService.updateOrder(franchiseOrderVO);
            if (result == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ResultVO.res(HttpStatus.OK, "발주 실패", null));
            } else {
                if (franchiseOrderVO.getStatus() == 1){
                    return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "발주 완료", null));
                } else  {
                    return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "반려 완료", null));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("orderSheets")
    public ResponseEntity<?> getOrderSheetList(FranchiseOrderVO franchiseOrderVO, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) throws Exception {
        System.out.println("startDate = " + startDate);
        System.out.println("endDate = " + endDate);
        try {
            List<FranchiseOrderVO> list = franchiseOrderService.getOrderList(franchiseOrderVO);
            if(startDate!=null && endDate != null){
                list.removeIf(order -> order.getOrderDate().compareTo(startDate) < 0 || order.getOrderDate().compareTo(endDate) > 0);
            }
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), list));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("orderSheets/{id}")
    public ResponseEntity<?> getOrderSheet(@PathVariable String id) throws Exception {
        FranchiseOrderVO franchiseOrderVO = new FranchiseOrderVO();
        String[] ids = id.split("-");
        franchiseOrderVO.setId(Long.parseLong(ids[0]));
        try {
            System.out.println("orderService.getOrderSheet(orderVO) = " + franchiseOrderService.getOrder(franchiseOrderVO));
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), franchiseOrderService.getOrder(franchiseOrderVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @PutMapping("orderDetails")
    @Transactional
    public ResponseEntity<?> updateOrderDetail(@RequestBody List<FranchiseOrderDetailVO> franchiseOrderDetailVOList) throws Exception {
        System.out.println("orderDetailVOList = " + franchiseOrderDetailVOList);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "저장 되었습니다.", franchiseOrderService.updateOrderDetail(franchiseOrderDetailVOList)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
}
