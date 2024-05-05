package com.groups.schicken.franchise.sales;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SalesController {
    private static SalesService salesService;

    @GetMapping("/franchise/sales")
    public String sales(Model model) {
        return "franchise/sales/list";
    }
}
