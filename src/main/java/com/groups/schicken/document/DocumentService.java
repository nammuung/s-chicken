package com.groups.schicken.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
	@Autowired
	private DocumentDAO documentDAO;
	
	public int add(DocumentVO documentVO)throws Exception{
		System.out.println("들어옴");
		int result =documentDAO.add(documentVO);
		return result;
	}
	
}
