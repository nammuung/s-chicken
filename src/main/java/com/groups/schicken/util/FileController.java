package com.groups.schicken.util;

import com.groups.schicken.common.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.groups.schicken.common.vo.FileVO;
@Controller
public class FileController {

	@Autowired
	private FileManager fileManager;
	
	@GetMapping("/fileDown")
	public ResponseEntity<byte[]> download(FileVO fileVO) throws Exception{
		
		
		return fileManager.downFile(fileVO);
	}
	
	@PostMapping("/fileDelete")
	public ResponseEntity<Boolean> delete(@RequestBody FileVO fileVO)throws Exception{
		System.out.println("fileVO = " + fileVO);

		return ResponseEntity.ok(fileManager.deleteFile(fileVO));
	}
}
