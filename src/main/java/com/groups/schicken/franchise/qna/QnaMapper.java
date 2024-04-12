package com.groups.schicken.franchise.qna;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QnaMapper {
    int addQna(QnaVO qnaVO) throws Exception;
    int updateQna(QnaVO qnaVO) throws Exception;
    int deleteQna(QnaVO qnaVO) throws Exception;
    List<QnaVO> getAllFranchiseQnaList(Map<String, Object> map) throws Exception;
    List<QnaVO> getFranchiseQnaList(Map<String, Object> map) throws Exception;
    QnaVO getQna(QnaVO qnaVO) throws Exception;

    int commentQna(QnaCommentVO commentVO) throws Exception;

    Long getTotal(Map<String, Object> map) throws Exception;

    List<QnaVO> getAnotherQna(QnaVO qnaVO) throws Exception;
    List<QnaVO>  getFirstSequenceQna() throws Exception;
    List<QnaVO>  getSequenceQna(QnaVO qnaVO) throws Exception;
}
