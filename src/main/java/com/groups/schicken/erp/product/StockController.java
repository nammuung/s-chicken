package com.groups.schicken.erp.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping("/stocks")
    public String stock(Model model, StockVO stockVO) throws Exception {
        model.addAttribute("list", stockService.getStockHistory(stockVO));
        return "stock/stock";
    }
}
