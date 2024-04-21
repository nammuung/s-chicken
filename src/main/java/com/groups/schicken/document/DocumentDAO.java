package com.groups.schicken.document;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DocumentDAO {
	int add(DocumentVO documentVO)throws Exception;
	
	int appAdd(ApprovalVO approvalVO)throws Exception;

	List<DocumentVO> allList(Map<String,Object> map)throws Exception;
	
	Long allTotalCount(Map<String, Object> map)throws Exception;
}
