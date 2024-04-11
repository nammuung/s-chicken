package com.groups.schicken.franchise.controller;

import com.groups.schicken.franchise.object.QnaVO;
import com.groups.schicken.franchise.service.QnaService;
import com.groups.schicken.util.Pager;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    private QnaService qnaService;
    @GetMapping("/manager/qna/list")
    public String getQnaList(Model model,Pager pager) throws Exception {
        List<QnaVO> list = qnaService.getAllFranchiseQnaList(pager);
        model.addAttribute("list", list);
        return "manager/qna/list";
    }
}
