package com.groups.schicken.erp.item;

import com.groups.schicken.common.vo.CodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<ItemVO> getItemList(ItemVO itemVO) throws Exception;

    ItemVO getItem(ItemVO itemVO) throws Exception;

    int addItem(ItemVO itemVO) throws Exception;

    int updateItem(ItemVO itemVO) throws Exception;

    List<CodeVO> getUnit() throws Exception;
}
