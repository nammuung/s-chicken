package com.groups.schicken.franchise.faq;

import com.groups.schicken.common.vo.MessageVO;
import com.groups.schicken.util.Pager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FaqController {
    private final FaqService faqService;

    @GetMapping("/franchise/faq/detail")
    public String getFaqDetail(Model model, FaqVO faqVO) throws Exception {
        faqVO = faqService.getFaq(faqVO);
        model.addAttribute("vo", faqVO);
        return "franchise/faq/detailForFranchise";
    }
    @GetMapping("/franchise/faq/list")
    public String getFaqList(Model model, Pager pager) throws Exception {
        List<FaqVO> list = faqService.getFaqList(pager);
        if (pager.getPage() == 1){
            List<FaqVO> importantList = faqService.getImportantFaqList();
            model.addAttribute("importantList", importantList);
        }
        model.addAttribute("commonList", list);
        return "franchise/faq/list";
    }
    @GetMapping("/franchise/faq/add")
    public String addFaq(Model model) throws Exception {
        return "franchise/faq/add";
    }
    @PostMapping("/franchise/faq/add")
    public String addFaq(Model model, FaqVO faqVO) throws Exception {
        int result = faqService.addFaq(faqVO);
        if (result == 1){
            model.addAttribute("message", new MessageVO("작성 완료했습니다.", "/franchise/faq/detail?id="+faqVO.getId()));
        } else {
            model.addAttribute("message", new MessageVO("작성 실패했습니다.", "/franchise/faq/list"));
        }
        return "result";
    }

    @GetMapping("/franchise/faq/update")
    public String updateFaqRoute(Model model, FaqVO faqVO) throws Exception {
        faqVO = faqService.getFaq(faqVO);
        model.addAttribute("vo", faqVO);
        return "franchise/faq/update";
    }
    @PostMapping("/franchise/faq/update")
    public String updateFaq(Model model, FaqVO faqVO) throws Exception {
        int result = faqService.updateFaq(faqVO);
        if (result == 1){
            model.addAttribute("message", new MessageVO("수정 완료했습니다.", "/franchise/faq/detail?id="+faqVO.getId()));
        } else {
            model.addAttribute("message", new MessageVO("수정 실패했습니다.", "/franchise/faq/list"));
        }
        return "result";
    }
    @PostMapping("delete")
    public String deleteFaq(Model model, FaqVO faqVO) throws Exception {
        int result = faqService.deleteFaq(faqVO);
        if (result == 1) {
            model.addAttribute("message", new MessageVO("FAQ 삭제가 완료되었습니다.", "/franchise/faq/list"));
        } else {
            model.addAttribute("message", new MessageVO("FAQ 삭제에 실패했습니다.", "/franchise/faq/detail?id=" + faqVO.getId()));
        }
        return "result";
    }
}
