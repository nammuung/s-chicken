package com.groups.schicken.franchise.service;

import com.groups.schicken.franchise.mapper.QnaMapper;
import com.groups.schicken.franchise.object.FranchiseVO;
import com.groups.schicken.franchise.object.QnaCommentVO;
import com.groups.schicken.franchise.object.QnaVO;
import com.groups.schicken.util.DateManager;
import com.groups.schicken.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QnaService {
    @Autowired
    private QnaMapper qnaMapper;

    public int addQna(QnaVO qnaVO) throws Exception {
        qnaVO.setWriteDate(DateManager.getTodayDate());
        return qnaMapper.addQna(qnaVO);
    }

    public int updateQna(QnaVO qnaVO) throws Exception {
        return qnaMapper.updateQna(qnaVO);
    }

    public int deleteQna(QnaVO qnaVO) throws Exception {
        return qnaMapper.deleteQna(qnaVO);
    }

    public List<QnaVO> getAllFranchiseQnaList(Pager pager) throws Exception {
        return qnaMapper.getAllFranchiseQnaList(pager);
    }

    public List<QnaVO> getFranchiseQnaList(FranchiseVO franchiseVO, Pager pager) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("franchise", franchiseVO);
        map.put("pager", pager);
        pager.makeNum(qnaMapper.getTotal(map));
        return qnaMapper.getFranchiseQnaList(map);
    }

    public QnaVO getQna(QnaVO qnaVO) throws Exception {
        qnaVO = qnaMapper.getQna(qnaVO);
        List<QnaVO> anotherList = qnaMapper.getAnotherQna(qnaVO);
        System.out.println("anotherList = " + anotherList);
        for (QnaVO  vo: anotherList){
            if(vo.getId() > qnaVO.getId()) qnaVO.setNextQna(vo);
            if(vo.getId() < qnaVO.getId()) qnaVO.setPreQna(vo);
        }
        return qnaVO;
    }

    public int commentQna(QnaCommentVO commentVO) throws Exception {
        commentVO.setWriteDate(DateManager.getTodayDate());
        return qnaMapper.commentQna(commentVO);
    }


}
