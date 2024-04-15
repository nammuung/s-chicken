package com.groups.schicken.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.groups.schicken.common.vo.FileVO;
@Controller
public class FileController {

	@Autowired
	private FileManager fileManager;
	
	@GetMapping("/fileDown")
	public ResponseEntity<byte[]> download(FileVO fileVO) throws Exception{
		
		
		return fileManager.downFile(fileVO);
	}
}
