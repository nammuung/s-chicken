package com.groups.schicken.franchise.order;

import com.groups.schicken.erp.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/franchise")
public class FranchiseOrderController {
    private final FranchiseOrderService franchiseOrderService;
    private final ProductService productService;
    @GetMapping("/order")
    public String order(Model model) throws Exception {
        model.addAttribute("category", productService.getCategory());
        return "franchise/order/order";
    }
    @GetMapping("/getOrderSheet")
    public String getOrderSheet(Model model, FranchiseOrderVO franchiseOrderVO) throws Exception {
        System.out.println("franchiseOrderService.getOrder(franchiseOrderVO) = " + franchiseOrderService.getOrder(franchiseOrderVO));
        model.addAttribute("order", franchiseOrderService.getOrder(franchiseOrderVO));
        return "franchise/order/orderSheet";
    }

    @GetMapping("/order/list")
    public String orderList(Model model) {
        return "franchise/order/list";
    }
}
