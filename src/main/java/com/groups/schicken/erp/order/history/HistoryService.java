package com.groups.schicken.erp.order.history;

import com.groups.schicken.common.vo.OrderVO;
import com.groups.schicken.erp.order.HeadOrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryMapper historyMapper;

    public List<HistoryVO> getReceiveHistoryList(HeadOrderVO headOrderVO) throws Exception {
        return historyMapper.getReceiveHistoryList(headOrderVO);
    }

    public List<HistoryVO> getReleaseHistoryList(OrderVO orderVO) throws Exception {
        return historyMapper.getReleaseHistoryList(orderVO);
    }

    public int addReceiveHistory(HistoryVO historyVO) throws Exception {
        return historyMapper.addReceiveHistory(historyVO);
    }

    public int addReleaseHistory(HistoryVO historyVO) throws Exception {
        return historyMapper.addReleaseHistory(historyVO);
    }
}
