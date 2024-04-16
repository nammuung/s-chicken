package com.groups.schicken.franchise.faq;

import com.groups.schicken.common.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/api/franchise/faq/")
@RequiredArgsConstructor
public class FaqApi {
    private final FaqService faqService;

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
    @PostMapping("sort")
    public ResponseEntity<?> sortFaq(@RequestBody List<HashMap<String, Object>> requestBody) throws Exception {
        int result = faqService.sortFaq(requestBody);
        if (result == 1) {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "정렬 성공했습니다.", null));
        } else {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.BAD_REQUEST, "정렬 실패했습니다.", null));
        }
    }
}
