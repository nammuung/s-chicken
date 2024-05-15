package com.groups.schicken.common.util;

import com.groups.schicken.common.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class FileController {
	private final FileManager fileManager;
	private final FileMapper mapper;

	@GetMapping("/fileDown")
	public ResponseEntity<byte[]> download(FileVO fileVO) throws Exception{


		return fileManager.downFile(fileVO);
	}

	@PostMapping("/fileDelete")
	public ResponseEntity<Boolean> delete(@RequestBody FileVO fileVO)throws Exception{
		System.out.println("fileVO = " + fileVO);

		return ResponseEntity.ok(fileManager.deleteFile(fileVO));
	}

	@GetMapping("/files/{fileId}")
	public ResponseEntity<FileVO> getfiles(@PathVariable Long fileId){
		FileVO file =  mapper.getFileById(fileId);
		return ResponseEntity.ok(file);
	}
}
