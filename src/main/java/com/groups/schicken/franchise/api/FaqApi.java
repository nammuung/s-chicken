package com.groups.schicken.franchise.api;

import com.groups.schicken.franchise.object.FaqVO;
import com.groups.schicken.franchise.object.QnaVO;
import com.groups.schicken.franchise.object.ResultVO;
import com.groups.schicken.franchise.service.FaqService;
import com.groups.schicken.franchise.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/franchise/faq/")
public class FaqApi {
    @Autowired
    private FaqService faqService;

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFaq(@PathVariable Long id) throws Exception {
        FaqVO faqVO = new FaqVO();
        faqVO.setId(id);
        faqVO.setIsDeleted(true);
        int result = faqService.updateFaq(faqVO);
        if (result == 1) {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "삭제 성공했습니다.", faqVO));
        } else {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.BAD_REQUEST, "삭제 실패했습니다.", null));
        }
    }
}
