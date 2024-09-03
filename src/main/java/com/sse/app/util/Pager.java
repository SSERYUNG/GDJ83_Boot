package com.sse.app.util;

import lombok.Data;

@Data
public class Pager {
	
	private Long perPage=10L;
	private Long startRow;
	private Long lastRow;
	private Long page=1L;
	private String kind;
	private String search;
	
	// rownum을 처리하는 메서드
	public void makeRow() {
		// page 1 -> startRow = 0 첫번째 페이지
		// page 2 -> startRow = 10 두번째 페이지
		// page 3 -> startRow = 20 세번째 페이지
		// page 4 -> startRow = 30 네번째 페이지
		//(this.getPage() - 1) * perPage;
		this.startRow = (page-1) * perPage;
		
		
	}
	
	
	// getter
	public String getKind() {
		if(this.kind==null) {
			this.kind="k1";
		}
		return this.kind;
	}
	
	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		return this.search;
	}
	
	
	// paging을 처리하는 메서드
	public void makeNum() {
		
	}
	
}