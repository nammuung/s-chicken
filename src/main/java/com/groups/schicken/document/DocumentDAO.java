package com.groups.schicken.document;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DocumentDAO {
	int add(DocumentVO documentVO)throws Exception;
	
	int appAdd(ApprovalVO approvalVO)throws Exception;
}
