package com.groups.schicken.board;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.FileVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
public class BoardVO {
	private Long id;
	private String writeDate;
	private String title;
	private String content;
	private Boolean isDeleted;
	private Long hit;
	private int sort;
	private String writerId;
	private Boolean important;
	private Long rank;


	private List<FileVO> fileVO;
	private EmployeeVO employeeVO;

	public boolean getImportant(){
		if(important == null)
			return false;

		return important;
	}
}
