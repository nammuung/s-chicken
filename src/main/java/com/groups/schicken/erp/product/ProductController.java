package com.groups.schicken.erp.product;

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
        model.addAttribute("category", productService.getCategory());
        return "erp/product/product";
    }
}
