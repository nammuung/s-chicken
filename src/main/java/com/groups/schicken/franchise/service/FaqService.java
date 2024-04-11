package com.groups.schicken.franchise.service;

import com.groups.schicken.franchise.mapper.FaqMapper;
import com.groups.schicken.franchise.object.FaqVO;
import com.groups.schicken.franchise.object.QnaVO;
import com.groups.schicken.util.DateManager;
import com.groups.schicken.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {
    @Autowired
    private FaqMapper faqMapper;

    public List<FaqVO> getImportantFaqList() throws Exception {
        return faqMapper.getImportantFaqList();
    }
    public List<FaqVO> getFaqList(Pager pager) throws Exception {
        pager.makeNum(faqMapper.getTotalCount(pager));
        return faqMapper.getFaqList(pager);
    }

    public FaqVO getFaq(FaqVO faqVO) throws Exception {
        faqVO = faqMapper.getFaq(faqVO);
        List<FaqVO> anotherList = faqMapper.getAnotherFaq(faqVO);
        for (FaqVO  vo: anotherList){
            System.out.println("vo = " + vo);
            if(vo.getId() > faqVO.getId()) faqVO.setNextFaq(vo);
            if(vo.getId() < faqVO.getId()) faqVO.setPreFaq(vo);
        }
        return faqVO;
    }

    public int addFaq(FaqVO faqVO) throws Exception {
        faqVO.setWriteDate(DateManager.getTodayDate());
        return faqMapper.addFaq(faqVO);
    }

    public int updateFaq(FaqVO faqVO) throws Exception {
        return faqMapper.updateFaq(faqVO);
    }

    public int deleteFaq(FaqVO faqVO) throws Exception {
        faqVO.setIsDeleted(true);
        return faqMapper.updateFaq(faqVO);
    }

}
