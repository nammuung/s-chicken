package com.groups.schicken.erp.order;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/orders")
public class OrderAPI {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getOrderList(OrderVO orderVO) throws Exception {
        System.out.println("orderVO = " + orderVO);
        try {
            System.out.println(orderService.getOrderList(orderVO));
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), orderService.getOrderList(orderVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> addOrder(@AuthenticationPrincipal EmployeeVO employeeVO, OrderVO orderVO) throws Exception {
        try {
            orderVO.setEmployee(employeeVO);
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "주문 추가 완료", orderService.addOrder(orderVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
}
