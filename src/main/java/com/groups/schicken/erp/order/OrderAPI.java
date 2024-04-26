package com.groups.schicken.erp.order;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.ResultVO;
import com.groups.schicken.erp.item.ItemVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/")
public class OrderAPI {
    private final OrderService orderService;

    @GetMapping("orders")
    public ResponseEntity<?> getOrderList(OrderVO orderVO) throws Exception {
        try {
            System.out.println(orderService.getOrderList(orderVO));
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), orderService.getOrderList(orderVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
    @GetMapping("orders/{id}")
    public ResponseEntity<?> getOrderList(@PathVariable Long id) throws Exception {
        OrderVO orderVO = new OrderVO();
        orderVO.setId(id);
        try {
            System.out.println(orderService.getOrderList(orderVO));
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), orderService.getOrderDetail(orderVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @PostMapping("orders")
    @Transactional
    public ResponseEntity<?> addOrder(@AuthenticationPrincipal EmployeeVO employeeVO, @RequestBody List<OrderItemVO> orderItemList) throws Exception {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "발주서 작성 완료", orderService.addOrder(orderItemList, employeeVO)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @PutMapping("orders")
    public ResponseEntity<?> updateOrder(OrderVO orderVO) throws Exception {
        try {
            int result = orderService.updateOrder(orderVO);
            if (result == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ResultVO.res(HttpStatus.OK, "발주 실패", null));
            } else {
                return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "발주 완료", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("orderSheets")
    public ResponseEntity<?> getOrderSheetList(OrderVO orderVO, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) throws Exception {
        System.out.println("startDate = " + startDate);
        System.out.println("endDate = " + endDate);
        try {
            List<OrderVO> list = orderService.getOrderSheetList(orderVO);
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
        OrderVO orderVO = new OrderVO();
        SupplierVO supplierVO = new SupplierVO();
        String[] ids = id.split("-");
        orderVO.setId(Long.parseLong(ids[0]));
        supplierVO.setId(Long.parseLong(ids[1]));
        orderVO.setSupplier(supplierVO);
        try {
            System.out.println("orderService.getOrderSheet(orderVO) = " + orderService.getOrderSheet(orderVO));
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), orderService.getOrderSheet(orderVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @PutMapping("orderItems")
    @Transactional
    public ResponseEntity<?> updateOrderItem(@RequestBody List<OrderItemVO> orderItemVOList) throws Exception {
        System.out.println("orderItemVOList = " + orderItemVOList);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "저장 되었습니다.", orderService.updateOrderItem(orderItemVOList)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
}
