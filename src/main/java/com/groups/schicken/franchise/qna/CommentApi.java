package com.groups.schicken.franchise.qna;

import com.groups.schicken.Employee.EmployeeService;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/")
@RequiredArgsConstructor
public class CommentApi {
    private final QnaService qnaService;
    private final EmployeeService employeeService;

    @PostMapping("franchise/qna/comment/{id}")
    public ResponseEntity<?> commentQna(@AuthenticationPrincipal EmployeeVO employeeVO, @PathVariable Long id,@RequestBody QnaCommentVO commentVO) throws Exception {
        commentVO.setQnaId(id);
        commentVO.setEmployeeId(employeeVO.getId());
        int result = qnaService.commentQna(commentVO);
        if (result == 1) {
            EmployeeVO emp = new EmployeeVO();
            emp.setId(commentVO.getEmployeeId());
            commentVO.setEmployee(employeeService.userDetail(emp));
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "답변에 성공했습니다.", commentVO));
        } else {
            return ResponseEntity.badRequest().body(ResultVO.res(HttpStatus.BAD_REQUEST, "답변에 실패했습니다.", null));
        }
    }
}
