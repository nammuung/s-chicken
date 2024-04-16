package com.groups.schicken.franchise.faq;

import com.groups.schicken.util.Pager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FaqMapper {
    List<FaqVO> getImportantFaqList() throws Exception;
    List<FaqVO> getFaqList (Pager pager) throws Exception;
    int addFaq(FaqVO faqVO) throws Exception;
    FaqVO getFaq(FaqVO faqVO) throws Exception;
    int updateFaq(FaqVO faqVO) throws Exception;
    Long getTotalCount(Pager pager) throws Exception;
    List<FaqVO> getAnotherFaq(FaqVO faqVO) throws Exception;
    int setImportantFaq(FaqVO faqVO) throws Exception;
    int deleteImportantFaq(FaqVO faqVO) throws Exception;
    int sortImportantFaq(Map<String, Object> map) throws Exception;
}
