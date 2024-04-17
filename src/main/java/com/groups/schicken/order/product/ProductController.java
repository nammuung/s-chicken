package com.groups.schicken.order.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public String getProductList(ProductVO productVO, Model model) throws Exception {
        System.out.println("productService.getCategory() = " + productService.getCategory());
        model.addAttribute("category", productService.getCategory());
        return "order/product/product";
    }
}
