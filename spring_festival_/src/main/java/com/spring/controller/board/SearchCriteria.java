package com.spring.controller.board;

//Criteria의 기능을 확장
public class SearchCriteria extends Criteria {
	private String searchType;			//검색 구분
	private String searchType2;
	private String searchType3;
	private String keyword;				//검색어
	private String listSort;
	
	
	public SearchCriteria() {
		this("","","new");
	}
	
	public SearchCriteria(String searchType, String keyword, String listSort) {
		super();
		this.searchType = searchType;
		this.keyword = keyword;
	}
	public SearchCriteria(String searchType,String searchType2,String searchType3, String keyword, String listSort) {
		super();
		this.searchType = searchType;
		this.searchType2 = searchType2;
		this.searchType3 = searchType3;
		this.keyword = keyword;
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getListSort() {
		return listSort;
	}

	public void setListSort(String listSort) {
		this.listSort = listSort;
	}

	public String getSearchType2() {
		return searchType2;
	}

	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}

	public String getSearchType3() {
		return searchType3;
	}

	public void setSearchType3(String searchType3) {
		this.searchType3 = searchType3;
	}

	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + ", listSort=" + listSort + "]";
	}
	
	

}
