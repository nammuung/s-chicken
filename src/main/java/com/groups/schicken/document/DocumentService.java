package com.groups.schicken.document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.Pager;

@Service
public class DocumentService {
	@Autowired
	private DocumentDAO documentDAO;
	
	public EmployeeVO getEx(EmployeeVO employeeVO)throws Exception{
		
		return documentDAO.getEx(employeeVO);
	}
	
	public List<DocumentVO> getDetail(DocumentVO documentVO)throws Exception{
		
		return documentDAO.getDetail(documentVO);
	}
	
	
	public List<DocumentVO> approvalDetail(DocumentVO documentVO)throws Exception{
		
		return documentDAO.approvalDetail(documentVO);
	}
	
	
	public List<DocumentVO> approvalList(EmployeeVO employeeVO,Pager pager)throws Exception{
		
		Map<String, Object> map = new HashMap<String,Object>();		
				
		map.put("employeeVO", employeeVO);
		map.put("pager", pager);
		
		pager.makeIndex();
		pager.makeNum(documentDAO.allTotalCount(map)); 
		
		List<DocumentVO> ar = documentDAO.approvalList(map);	
		
		System.out.println(ar);
		
		return ar;
		
	}
	public List<DocumentVO> list(DocumentVO documentVO,TemplateVO templateVO,Pager pager,String cate)throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();

		map.put("documentVO", documentVO);
		map.put("pager", pager);
		map.put("category", cate);
		
		pager.makeIndex();
		pager.makeNum(documentDAO.allTotalCount(map));
		System.out.println("123"+map);
		
		
		return documentDAO.allList(map);
	}
	
	public List<DocumentVO> tempList(DocumentVO documentVO,TemplateVO templateVO,Pager pager)throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();

		map.put("documentVO", documentVO);
		map.put("pager", pager);
		
		pager.makeIndex();
		pager.makeNum(documentDAO.allTotalCount(map));

		
		return documentDAO.tempList(map);
	}
	
	public int add(DocumentVO documentVO)throws Exception{
		System.out.println("들어옴");
		int result =documentDAO.add(documentVO);
				
		return result;
	}
	
	public int appAdd(ApprovalVO approvalVO)throws Exception{
			
			int result = documentDAO.appAdd(approvalVO);
			
			return result;
		
	}
	
	public int tempToSang(DocumentVO documentVO,ApprovalVO approvalVO)throws Exception{
			
			int result = documentDAO.tempToappDel(approvalVO);
			result = documentDAO.tempTosang(documentVO);
			
		
		return result;
	}
	
	public int tempToSangApp(ApprovalVO approvalVO)throws Exception{
		int result = documentDAO.tempToappAdd(approvalVO);
		
		return result ;
	}
	
	

	public int resultUpdate(ApprovalVO approvalVO)throws Exception{
		int result = documentDAO.resultUpdate(approvalVO);		
		result = documentDAO.statusUpdate(approvalVO);
		
		return result;
	}
	
	public int refuseUpdate(ApprovalVO approvalVO)throws Exception{
		int result = documentDAO.refuseUpdate(approvalVO);
			result = documentDAO.statusRefuse(approvalVO);
		return result;
	}
}
