package com.groups.schicken.erp.item;

import com.groups.schicken.erp.product.ProductService;
import com.groups.schicken.erp.product.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ProductService productService;

    @GetMapping("/items")
    public String getItemList(Model model) throws Exception {
        model.addAttribute("category", productService.getCategory());
        return "erp/item/item";
    }
}
