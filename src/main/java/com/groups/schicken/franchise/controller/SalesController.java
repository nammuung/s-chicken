package com.groups.schicken.franchise.controller;

import com.groups.schicken.franchise.object.FranchiseVO;
import com.groups.schicken.franchise.service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalesController {
    @Autowired
    private FranchiseService franchiseService;

    @GetMapping("/sales/detail")
    public String getFranchise(Model model, FranchiseVO franchiseVO) throws Exception {
        franchiseVO = franchiseService.getFranchise(franchiseVO);
        model.addAttribute("vo", franchiseVO);
        return "sales/franchiseDetail";
    }
}
