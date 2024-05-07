package com.groups.schicken.franchise.qna;

import com.groups.schicken.franchise.FranchiseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnaVO {
    private Long id;
    private String title;
    private String content;
    private String writeDate;
    private String modifyDate;
    private String writerId;
    private Boolean isDeleted;
    private FranchiseVO writer;
    private QnaCommentVO comment;
    private QnaVO preQna;
    private QnaVO nextQna;
}
