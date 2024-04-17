package com.groups.schicken.franchise.qna;

import com.groups.schicken.franchise.FranchiseVO;
import com.groups.schicken.common.vo.MessageVO;
import com.groups.schicken.util.Pager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/franchise/qna/")
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;

    @GetMapping("add")
    public void addQna(Model model) throws Exception {
    }
    @PostMapping("add")
    public String addQna(@AuthenticationPrincipal FranchiseVO franchiseVO, Model model, QnaVO qnaVO) throws Exception {
        qnaVO.setWriterId(franchiseVO.getId());
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
            model.addAttribute("message", new MessageVO("문의 수정이 완료되었습니다.", "/franchise/qna/detail?id=" + qnaVO.getId()));
        } else {
            model.addAttribute("message", new MessageVO("문의 수정에 실패했습니다.", "/franchise/qna/detail?id=" + qnaVO.getId()));
        }
        return "result";
    }
    
    @PostMapping("delete")
    public String deleteQna(Model model,QnaVO qnaVO) throws Exception {
        int result = qnaService.deleteQna(qnaVO);
        if (result == 1) {
            model.addAttribute("message", new MessageVO("문의 삭제가 완료되었습니다.", "/franchise/qna/list"));
        } else {
            model.addAttribute("message", new MessageVO("문의 삭제에 실패했습니다.", "/franchise/qna/detail?id=" + qnaVO.getId()));
        }
        return "result";
    }

    @GetMapping("list")
    public String getFranchiseQnaList(@AuthenticationPrincipal FranchiseVO franchiseVO, Model model, Pager pager) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<QnaVO> list = null;
        if (authentication != null) {
            boolean hasFranchiseAuthority = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_FRANCHISE"));
            if (hasFranchiseAuthority) {
                list = qnaService.getFranchiseQnaList(franchiseVO, pager); //가맹점 로그인 시
            } else {
                list = qnaService.getAllFranchiseQnaList(pager); //매니저 로그인 시
            }
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

    @GetMapping("sequenceAnswer")
    public String getQnaSequence(Model model, QnaVO qnaVO) throws Exception {
        System.out.println(qnaVO);
        if(qnaVO.getId() == null){
            qnaVO = qnaService.getSequenceQna();
        } else {
            qnaVO = qnaService.getSequenceQna(qnaVO);
        }
        model.addAttribute("vo", qnaVO);
        return "franchise/qna/sequenceAnswer";
    }

}
