package com.groups.schicken.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pager {
	private Long page;//페이지 번호
	private Long perPage; //한페이지당 출력 갯수
	
	private Long startIndex;
	
	
	private Long totalPage;
	private Long startNum;
	private Long lastNum;
	
	//이전 블럭이 없으면 true;
	private boolean start;
	//다음 블럭이 없으면 true;
	private boolean last;
	
	
	
	//검색 관련
	private String search;
	private String kind;
	
	
	public void makeIndex() {
		//1 0
		//2 10
		//3 20
		this.startIndex= (this.getPage()-1)*this.getPage();
		
	}
	
	public void makeNum(Long totalCount) {
		
		if(totalCount<1) {
			totalCount=1L;
		}
		
		totalPage = totalCount/this.getPerPage();
		
		
		if(totalCount%this.getPerPage() != 0) {
			//totalPage=totalPage+1;
			totalPage++;
		}
		
		this.setTotalPage(totalPage);
		
		//2. 총블럭의 수 구하기
		Long perBlock=5L;//블럭당 번호의 갯수
		Long totalBlock=0L;
		totalBlock=totalPage/perBlock;
		if(totalPage%perBlock != 0) {
			totalBlock++;
		}
		//3. Page 값으로 현재 블럭 번호 구하기
		Long curBlock=0L;//블럭 번호
		curBlock=this.getPage()/perBlock;
		if(this.getPage()%perBlock != 0) {
			curBlock++;
		}
		
		//4. 현재 블럭 번호로 시작 번호와 끝번호 구하기
		Long startNum=0L;
		Long lastNum=curBlock*perBlock;
		startNum=lastNum-perBlock+1;
		
		this.setStartNum(startNum);
		this.setLastNum(lastNum);
		
		//이전, 다음 블럭 유무
		if(curBlock==1) {
			this.setStart(true);
		}
		
		if(curBlock==totalBlock) {
			this.setLastNum(totalPage);
			this.setLast(true);
		}
	}

	
	//Getter
	//public 리턴타입 get멤버변수명(){}
	public Long getPage() {
		if(this.page==null || this.page<1) {
			this.page=1L;
		}
		return this.page;
	}
	
	public Long getPerPage() {
		if(this.perPage==null || this.perPage<1) {
			this.perPage=10L;
		}
		return this.perPage;
	}
	
	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		
		return this.search;
	}
	
	
	public void setTotalCount(Long page){
		
		
		
	}
	
}
