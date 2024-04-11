package com.groups.schicken.franchise.mapper;

import com.groups.schicken.franchise.object.FranchiseVO;
import com.groups.schicken.franchise.object.QnaCommentVO;
import com.groups.schicken.franchise.object.QnaVO;
import com.groups.schicken.util.Pager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QnaMapper {
    int addQna(QnaVO qnaVO) throws Exception;
    int updateQna(QnaVO qnaVO) throws Exception;
    int deleteQna(QnaVO qnaVO) throws Exception;
    List<QnaVO> getAllFranchiseQnaList(Pager pager) throws Exception;
    List<QnaVO> getFranchiseQnaList(Map<String, Object> map) throws Exception;
    QnaVO getQna(QnaVO qnaVO) throws Exception;

    int commentQna(QnaCommentVO commentVO) throws Exception;

    Long getTotal(Map<String, Object> map) throws Exception;

    List<QnaVO> getAnotherQna(QnaVO qnaVO) throws Exception;
}
