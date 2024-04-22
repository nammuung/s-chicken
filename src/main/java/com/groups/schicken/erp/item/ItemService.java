package com.groups.schicken.erp.item;

import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.common.vo.CodeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;
    public List<ItemVO> getItemList(ItemVO itemVO) throws Exception{
        return itemMapper.getItemList(itemVO);
    }

    public ItemVO getItem(ItemVO itemVO) throws Exception{
        return itemMapper.getItem(itemVO);
    }

    public int addItem(ItemVO itemVO) throws Exception{
        itemVO.setCreateDate(DateManager.getTodayDate());
        return itemMapper.addItem(itemVO);
    }

    public int updateItem(ItemVO itemVO) throws Exception{
        itemVO.setModifyDate(DateManager.getTodayDate());
        return itemMapper.updateItem(itemVO);
    }


}
