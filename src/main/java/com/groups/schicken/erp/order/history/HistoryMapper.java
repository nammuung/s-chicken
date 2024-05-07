package com.groups.schicken.erp.order.history;

import com.groups.schicken.common.vo.OrderVO;
import com.groups.schicken.erp.order.HeadOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface HistoryMapper {
    List<HistoryVO> getReceiveHistoryList (HeadOrderVO headOrderVO) throws Exception;
    List<HistoryVO> getReleaseHistoryList (OrderVO orderVO) throws Exception;
    int addReceiveHistory(HistoryVO historyVO) throws Exception;
    int addReleaseHistory(HistoryVO historyVO) throws Exception;
}
