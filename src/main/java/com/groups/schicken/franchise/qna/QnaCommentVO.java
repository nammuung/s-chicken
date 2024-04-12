package com.groups.schicken.franchise.qna;

import com.groups.schicken.Employee.EmployeeVO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class QnaCommentVO {
    private Long id;
    private Long qnaId;
    private String writeDate;
    private String content;
    private String employeeId;
    private EmployeeVO employee;
}
