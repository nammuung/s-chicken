package com.groups.schicken.franchise.api;

import com.groups.schicken.franchise.object.QnaVO;
import com.groups.schicken.franchise.object.ResultVO;
import com.groups.schicken.franchise.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/franchise/qna/")
public class QnaApi {
    @Autowired
    private QnaService qnaService;

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteQna(@PathVariable Long id) throws Exception {
        QnaVO qnaVO = new QnaVO();
        qnaVO.setId(id);
        qnaVO.setIsDeleted(true);
        int result = qnaService.updateQna(qnaVO);
        if (result == 1) {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "삭제 성공했습니다.", qnaVO));
        } else {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.BAD_REQUEST, "삭제 실패했습니다.", null));
        }
    }
}
