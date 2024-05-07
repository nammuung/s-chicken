package com.groups.schicken.erp.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HeadOrderController {
    private final HeadOrderService headOrderService;

    @GetMapping("/order")
    public String order(Model model) {
        return "erp/order/order";
    }
    @GetMapping("/order/write")
    public String write(Model model) {
        return "erp/order/write";
    }

    @GetMapping("/getOrderSheet")
    public String getOrderSheet(Model model, HeadOrderVO headOrderVO) throws Exception {
        model.addAttribute("order", headOrderService.getOrder(headOrderVO));
        return "template/orderSheet";
    }
    @GetMapping("/order/list")
    public String orderList(Model model) {
        return "erp/order/list";
    }
    @GetMapping("/order/sell")
    public String sellList(Model model) {
        return "erp/order/sell";
    }

    @GetMapping("/order/release")
    public String release(Model model) {
        return "erp/order/release";
    }
}
