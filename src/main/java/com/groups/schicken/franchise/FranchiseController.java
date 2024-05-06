package com.groups.schicken.franchise;

import com.groups.schicken.common.vo.MessageVO;
import com.groups.schicken.common.vo.Pager;
import com.groups.schicken.erp.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FranchiseController {
    private final FranchiseService franchiseService;
    private final ProductService productService;

    @GetMapping("/franchise/home")
    public String getFranchiseHome(Model model, Pager pager) throws Exception {
        model.addAttribute("pager", pager);
        return "franchise/home";
    }
    @GetMapping("/franchise/inquiry")
    public String getFranchiseList(Model model, Pager pager) throws Exception {
        List<FranchiseVO> franchiseVOList = franchiseService.getFranchiseList(pager);
        model.addAttribute("list", franchiseVOList);
        model.addAttribute("pager", pager);
        return "franchise/inquiry";
    }

    @GetMapping("/franchise/detail")
    public String getFranchise(Model model,FranchiseVO franchiseVO) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        franchiseVO = franchiseService.getFranchise(franchiseVO);
        model.addAttribute("vo", franchiseVO);
        if (authentication != null) {
            boolean hasFranchiseAuthority = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_FRANCHISE"));
            if (!hasFranchiseAuthority) {
                return "franchise/detailForEmployee";// 본사 직원 화면
            }
        }
        return "franchise/detailForFranchise"; // 가맹점 화면
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
        franchiseVO = franchiseService.getFranchise(franchiseVO);
        int result = franchiseService.initPassword(franchiseVO);
        if (result == 1){
            model.addAttribute("message", new MessageVO("초기화에 성공했습니다.","/franchise/detail?id="+franchiseVO.getId()));
        } else {
            model.addAttribute("message", new MessageVO("초기화에 실패했습니다.","/franchise/detail?id="+franchiseVO.getId()));
        }
        return "result";
    }


}
