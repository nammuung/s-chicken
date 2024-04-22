package com.groups.schicken.erp.supplier;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    @GetMapping("/supplier")
    public String getSupplierList(SupplierVO supplierVO, Model model) throws Exception {
//        model.addAttribute("supplier", supplierService.getSupplier());
        return "erp/supplier/supplier";
    }
}
