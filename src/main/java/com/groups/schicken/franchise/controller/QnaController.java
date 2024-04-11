package com.groups.schicken.franchise.controller;

import com.groups.schicken.franchise.object.FranchiseVO;
import com.groups.schicken.franchise.object.MessageVO;
import com.groups.schicken.franchise.object.QnaVO;
import com.groups.schicken.franchise.service.QnaService;
import com.groups.schicken.util.Pager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/franchise/qna/")
public class QnaController {
    @Autowired
    private QnaService qnaService;

    @GetMapping("add")
    public void addQna(Model model) throws Exception {
    }
    @PostMapping("add")
    public String addQna(@AuthenticationPrincipal FranchiseVO franchiseVO, Model model, QnaVO qnaVO) throws Exception {
        qnaVO.setWriterId(franchiseVO.getId());
        System.out.println("qnaVO = " + qnaVO);
        int result = qnaService.addQna(qnaVO);
        if (result == 1) {
            model.addAttribute("message", new MessageVO("문의 추가가 완료되었습니다.", "/franchise/qna/detail?id=" + qnaVO.getId()));
        } else {
            model.addAttribute("message", new MessageVO("문의 추가에 실패했습니다.", "/franchise/qna/detail?id=" + qnaVO.getId()));
        }
        return "result";
    }

    @GetMapping("update")
    public String updateQnaRoute(Model model, QnaVO qnaVO) throws Exception {
        qnaVO = qnaService.getQna(qnaVO);
        model.addAttribute("vo", qnaVO);
        return "franchise/qna/update";
    }
    @PostMapping("update")
    public String updateQna(Model model,QnaVO qnaVO) throws Exception {
        int result = qnaService.updateQna(qnaVO);
        if (result == 1) {
            model.addAttribute("message", new MessageVO("문의 추가가 완료되었습니다.", "/franchise/qna/detail?id=" + qnaVO.getId()));
        } else {
            model.addAttribute("message", new MessageVO("문의 추가에 실패했습니다.", "/franchise/qna/detail?id=" + qnaVO.getId()));
        }
        return "result";
    }
    
    @PostMapping("delete")
    public String deleteQna(Model model,QnaVO qnaVO) throws Exception {
        int result = qnaService.deleteQna(qnaVO);
        if (result == 1) {
            model.addAttribute("message", new MessageVO("문의 삭제가 완료되었습니다.", "/franchise/qna/detail?id=" + qnaVO.getId()));
        } else {
            model.addAttribute("message", new MessageVO("문의 삭제에 실패했습니다.", "/franchise/qna/detail?id=" + qnaVO.getId()));
        }
        return "result";
    }

    @GetMapping("list")
    public String getFranchiseQnaList(Model model, Pager pager) throws Exception {
//        System.out.println("franchiseVO = " + franchiseVO);
        FranchiseVO franchiseVO = new FranchiseVO();
        franchiseVO.setId("210");
        List<QnaVO> list = qnaService.getFranchiseQnaList(franchiseVO, pager);
        System.out.println("pager = " + pager);
        for (QnaVO vo : list) {
            System.out.println("vo = " + vo);
        }
        model.addAttribute("list", list);
        model.addAttribute("pager", pager);
        return "franchise/qna/list";
    }

    @GetMapping("detail")
    public String getQna(Model model, QnaVO qnaVO) throws Exception {
        qnaVO = qnaService.getQna(qnaVO);
        model.addAttribute("vo", qnaVO);
        return "franchise/qna/detail";
    }
}
