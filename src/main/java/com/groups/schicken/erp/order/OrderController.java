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

    @GetMapping("/order")
    public String order(Model model) {
        return "erp/order/order";
    }
    @GetMapping("/order/write")
    public String write(Model model) {
        return "erp/order/write";
    }

    @GetMapping("/getOrderSheet")
    public String getOrderSheet(Model model, OrderVO orderVO) throws Exception {
        model.addAttribute("order", orderService.getOrderSheet(orderVO));
        return "template/orderSheet";
    }

    @GetMapping("/order/list")
    public String orderList(Model model) {
        return "erp/order/list";
    }
}
