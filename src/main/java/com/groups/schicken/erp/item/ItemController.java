package com.groups.schicken.erp.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items")
    public String getItemList(Model model) throws Exception {
        model.addAttribute("unit", itemService.getUnit());
        return "erp/item/item";
    }
}
