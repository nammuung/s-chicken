package com.groups.schicken.util;

import lombok.Data;

@Data
public class Pager {
	
	private Long page;	//페이지 번호
	private Long perPage;	//한페이지당 출력 갯수
	
	private Long startIndex;
	
	private Long totalPage;
	private Long startNum;
	private Long lastNum;
   
	//이전 블럭이 없으면 true;
	private boolean start; //이전값있냐없냐 true,false
	//다음 블럭이 없으면 true;
	private boolean last; //다음값
   
	//검색 관련
	private String search;
	private String kind;
	
	public void makeIndex() {
		
		this.startIndex = (this.getPage()-1)*this.getPage();
		
	}
	
	public void makeNum(Long totalCount) {
		
		if(totalCount < 1) {
			
			totalCount = 1L;
			
		}
		
		totalPage = totalCount/perPage;
		if(totalCount%perPage != 0) {
			totalPage++;
		}
		
		Long perBlock = 5L;
		Long totalBlock = 0L;
		
		totalBlock = totalPage/perBlock;
		if(totalPage%perBlock != 0) {
			
			totalBlock++;
			
		}
		
		Long curBlock = 0L;
		curBlock = this.getPage()/perBlock;
		if(this.getPage()%perBlock != 0) {
			
			curBlock++;
			
		}
		
		Long startNum = 0L;
		Long lastNum = curBlock*perBlock;
		startNum = lastNum-perBlock+1;
		
		if(curBlock == 1) {
			
			this.setStart(true);
			
		}
		
		if(curBlock == lastNum) {
			this.setLastNum(totalPage);
			this.setLast(true);
			
		}
	}
		
		public Long getPage() {
			if(this.page == null || this.page < 1) {
				this.page = 1L;
			}
			return this.page;
		}
		
		public Long getPerPage() {
			if(this.perPage ==null || this.perPage < 1) {
				this.perPage = 10L;
			}
			return this.perPage;
		}
		
		public String getSeartch() {
			if(this.search == null) {
				this.search = "";
			}
			return this.search;
		}
		
	}
	

