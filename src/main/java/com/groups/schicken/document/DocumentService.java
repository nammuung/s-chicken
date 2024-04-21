package com.groups.schicken.document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groups.schicken.common.vo.Pager;

@Service
public class DocumentService {
	@Autowired
	private DocumentDAO documentDAO;
	
	public int add(DocumentVO documentVO)throws Exception{
		System.out.println("들어옴");
		int result =documentDAO.add(documentVO);
				
		return result;
	}
	
	public int appAdd(ApprovalVO approvalVO)throws Exception{
			
			int result = documentDAO.appAdd(approvalVO);
			
			return result;
		
	}
	
	
	public List<DocumentVO> list(DocumentVO documentVO,TemplateVO templateVO,Pager pager)throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();
		
		
		map.put("documentVO", documentVO);
		map.put("pager", pager);
		map.put("templateVO", templateVO);
		pager.makeIndex();
		pager.makeNum(documentDAO.allTotalCount(map));
		
		
		
		return documentDAO.allList(map);
	}
}
