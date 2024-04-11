package com.groups.schicken.franchise.api;

import com.groups.schicken.Employee.EmployeeService;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.franchise.object.QnaCommentVO;
import com.groups.schicken.franchise.object.ResultVO;
import com.groups.schicken.franchise.service.QnaService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/")
public class CommentApi {
    @Autowired
    private QnaService qnaService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("franchise/qna/comment/{id}")
    public ResponseEntity<?> commentQna(@AuthenticationPrincipal EmployeeVO employeeVO, @PathVariable Long id,@RequestBody QnaCommentVO commentVO) throws Exception {
        commentVO.setQnaId(id);
        commentVO.setEmployeeId("19990806228");
        System.out.println("commentVO = " + commentVO);
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
