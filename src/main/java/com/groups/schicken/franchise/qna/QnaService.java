package com.groups.schicken.franchise.qna;

import com.groups.schicken.Employee.EmployeeService;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.franchise.FranchiseVO;
import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.common.vo.Pager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QnaService {
    private final QnaMapper qnaMapper;
    private final EmployeeService employeeService;

    public int addQna(QnaVO qnaVO) throws Exception {
        qnaVO.setWriteDate(DateManager.getTodayDate());
        return qnaMapper.addQna(qnaVO);
    }

    public int updateQna(QnaVO qnaVO) throws Exception {
        qnaVO.setModifyDate(DateManager.getTodayDate());
        return qnaMapper.updateQna(qnaVO);
    }

    public int deleteQna(QnaVO qnaVO) throws Exception {
        return qnaMapper.deleteQna(qnaVO);
    }

    public List<QnaVO> getAllFranchiseQnaList(Pager pager) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pager", pager);
        pager.makeNum(qnaMapper.getTotal(map));
        return qnaMapper.getAllFranchiseQnaList(map);
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
        System.out.println("qnaVO = " + qnaVO);
        List<QnaVO> anotherList = qnaMapper.getAnotherQna(qnaVO);
        for (QnaVO  vo: anotherList){
            if(vo.getId() > qnaVO.getId()) qnaVO.setNextQna(vo);
            if(vo.getId() < qnaVO.getId()) qnaVO.setPreQna(vo);
        }
        if(qnaVO.getComment() != null){
            EmployeeVO employeeVO = new EmployeeVO();
            employeeVO.setId(qnaVO.getComment().getEmployee().getId());
            employeeVO = employeeService.userDetail(employeeVO);
            QnaCommentVO comment = qnaVO.getComment();
            comment.setEmployee(employeeVO);
        }

        return qnaVO;
    }

    public int commentQna(QnaCommentVO commentVO) throws Exception {
        commentVO.setWriteDate(DateManager.getTodayDate());
        return qnaMapper.commentQna(commentVO);
    }

    public QnaVO getSequenceQna() throws Exception {
        List<QnaVO> list = qnaMapper.getFirstSequenceQna();
        QnaVO vo = new QnaVO();
        if(list.get(0) != null) {
            vo = list.get(0);
        }
        if(list.get(1) != null) {
            vo.setPreQna(list.get(1));
        }
        return vo;
    }
    public QnaVO getSequenceQna(QnaVO vo) throws Exception {
        vo = qnaMapper.getQna(vo);
        List<QnaVO> list = qnaMapper.getSequenceQna(vo);
        for(QnaVO vo1 : list){
            if(vo1.getId() > vo.getId()) vo.setNextQna(vo1);
            if(vo1.getId() < vo.getId()) vo.setPreQna(vo1);
        }
        return vo;
    }

}
