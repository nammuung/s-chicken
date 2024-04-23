package com.groups.schicken.erp.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public String order(Model model) {
        return "erp/order/order";
    }


    @GetMapping("/getOrderSheet")
    public String getOrderSheet(Model model) {
        return "template/orderSheet";
    }

    @GetMapping("/order/list")
    public String getOrderList(Model model) {
        return "erp/order/list";
    }
}
