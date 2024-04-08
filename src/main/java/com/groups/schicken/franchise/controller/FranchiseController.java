package com.groups.schicken.franchise.controller;

import com.groups.schicken.franchise.object.FranchiseVO;
import com.groups.schicken.franchise.object.MessageVO;
import com.groups.schicken.franchise.service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FranchiseController {
    @Autowired
    private FranchiseService franchiseService;
    @GetMapping("/franchise/inquiry")
    public String getFranchiseList(Model model) throws Exception {
        List<FranchiseVO> franchiseVOList = franchiseService.getFranchiseList();
        model.addAttribute("list", franchiseVOList);
        return "franchise/inquiry";
    }

    @GetMapping("/franchise/detail")
    public String getFranchise(Model model,FranchiseVO franchiseVO) throws Exception {
        franchiseVO = franchiseService.getFranchise(franchiseVO);
        model.addAttribute("vo", franchiseVO);
        return "franchise/detail";
    }

    @GetMapping("/franchise/join")
    public void join(FranchiseVO franchiseVO) {
    }
    @PostMapping("/franchise/join")
    @ResponseBody
    public ResponseEntity<?> join(FranchiseVO franchiseVO, MultipartFile[] attach) throws Exception {
        int result = franchiseService.addFranchise(franchiseVO);
        if (result == 1){
            return ResponseEntity.ok(franchiseVO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
