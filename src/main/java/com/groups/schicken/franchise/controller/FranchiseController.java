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
import org.springframework.web.bind.annotation.PutMapping;
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
        System.out.println("franchiseVO = " + franchiseVO);
        model.addAttribute("vo", franchiseVO);
        return "franchise/detail";
    }

    @GetMapping("/franchise/join")
    public void join(FranchiseVO franchiseVO) {
    }
    @PostMapping("/franchise/join")
    public String join(Model model,FranchiseVO franchiseVO, MultipartFile[] attach) throws Exception {
        int result = franchiseService.addFranchise(franchiseVO, attach);

        if (result == 1){
            model.addAttribute("message", new MessageVO("가맹점 가입이 성공했습니다.","/franchise/detail?id="+franchiseVO.getId()));
        } else {
            model.addAttribute("message", new MessageVO("가맹점 가입에 실패했습니다.","/franchise/join"));
        }
        return "result";
    }

    @PostMapping("/franchise/update")
    public String update(Model model,FranchiseVO franchiseVO) throws Exception {
        int result = franchiseService.updateFranchise(franchiseVO);
        if (result == 1){
            model.addAttribute("message", new MessageVO("가맹점 정보 수정이 성공했습니다.","/franchise/detail?id="+franchiseVO.getId()));
        } else {
            model.addAttribute("message", new MessageVO("가맹점 정보 수정에 실패했습니다.","/franchise/detail?id="+franchiseVO.getId()));
        }
        return "result";
    }

    @PostMapping("/franchise/initPassword")
    public String initPassword(Model model,FranchiseVO franchiseVO) throws Exception {
        int result = franchiseService.initPassword(franchiseVO);
        if (result == 1){
            model.addAttribute("message", new MessageVO("초기화에 성공했습니다.","/franchise/detail?id="+franchiseVO.getId()));
        } else {
            model.addAttribute("message", new MessageVO("초기화에 실패했습니다.","/franchise/detail?id="+franchiseVO.getId()));
        }
        return "result";
    }
}
