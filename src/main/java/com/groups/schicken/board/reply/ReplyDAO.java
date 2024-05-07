package com.groups.schicken.board.reply;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ReplyDAO {
	
	//list
	Long totalCount(Map<String,Object> map)throws Exception;
	
	List<ReplyVO> list(Map<String, Object> map) throws Exception;
	
	//update
	int update(ReplyVO replyVO)throws Exception;
	
	//delete
	int delete(ReplyVO replyVO)throws Exception; 
	
	//add
	int add(ReplyVO replyVO)throws Exception;
	
}
