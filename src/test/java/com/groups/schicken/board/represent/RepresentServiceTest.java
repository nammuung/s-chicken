package com.groups.schicken.board.represent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.groups.schicken.board.BoardVO;

@SpringBootTest(properties = "spring.profiles.include=dev")
@Transactional
public class RepresentServiceTest {
	@Autowired
	private RepresentDAO representDAO;
	
	void add() throws Exception {
		for(int i = 0 ; i < 10 ; i++) {
			BoardVO boardVO = new BoardVO();
			
//			boardVO.setId(123L+i);
			boardVO.setWriteDate("1-1");
			boardVO.setContent("test"+i);
			boardVO.setHit(12L);
			int result = representDAO.add(boardVO);
			assertEquals(1, result);
		}
		System.out.println("finish");
	}
}
