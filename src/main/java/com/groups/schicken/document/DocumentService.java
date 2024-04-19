package com.groups.schicken.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groups.schicken.common.util.DateManager;

@Service
public class DocumentService {
	@Autowired
	private DocumentDAO documentDAO;
	
	public int add(DocumentVO documentVO)throws Exception{
		documentVO.setWriteDate(DateManager.getTodayDate());
		return documentDAO.add(documentVO);
	}
}
