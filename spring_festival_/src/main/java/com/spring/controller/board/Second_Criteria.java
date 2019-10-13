package com.spring.controller.board;


// 한페이지 내에 페이징 리스트가 두개 존재하는 경우 사용.
public class Second_Criteria extends SearchCriteria {
	private int first_page;			// "내가 팔로우한 사람들"에서 사용할 page
	private int second_page;			// "나를 팔로우한 사람들"에서 사용할 page
	private String str;
	private String str2;
	
	
	
/*
	public int getFirst_endPage() {
		return first_endPage;
	}
	public void setFirst_endPage(int first_endPage) {
		this.first_endPage = first_endPage;
	}
	public int getSecond_endPage() {
		return second_endPage;
	}
	public void setSecond_endPage(int second_endPage) {
		this.second_endPage = second_endPage;
	}
*/
	public int getSecond_page() {
		return second_page;
	}
	public int getFirst_page() {
		return first_page;
	}

	public void setSecond_page(int second_page) {
		this.second_page = second_page;
	}
	public void setFirst_page(int first_page) {
		System.out.println("set : first_page = " + first_page);
		this.first_page = first_page;
	}


	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Second_Criteria() {
		
		this(1, 1, 5);
		
		System.out.println("Second_Criteria.Criteria() : 생성자 호출  ");
	}
	
	
	public Second_Criteria(int first_page, int second_page, int perPageNum) {
		super();
		this.first_page = first_page;
		this.second_page = second_page;
		System.out.println("\n\nSecond_Criteria(int, int, int) - super.setPerPageNum() 호출");
		super.setPerPageNum(perPageNum);
		System.out.println("Second_Criteria(int, int, int) - super.setPerPageNum() 복귀\n\n");
	}
	
	
	public int getPerPageNum() {
//		System.out.println("Criteria.getPerPageNum(), return perPageNum : " + perPageNum);
		return super.getPerPageNum();
	}
	public void setPerPageNum(int perPageNum) {
		

		if(perPageNum > 50) {
			perPageNum=50;
		}
		super.setPerPageNum(perPageNum);
	}

	public int getPageStartRowNum(){ //각 페이지마다 시작하는 행번호	
		return (super.getPage()-1)*super.getPerPageNum();
	}
	

	public int getPageStartRowNum_First(){ //각 페이지마다 시작하는 행번호	
		return (this.first_page-1)*super.getPerPageNum();
	}

	public int getPageStartRowNum_Second(){ //각 페이지마다 시작하는 행번호	
		return (this.second_page-1)*super.getPerPageNum();
	}

	
	@Override
	public String toString() {
		return "Second_Criteria.toString : [super.page=" + super.getPage() + ", super.perPageNum=" + super.getPerPageNum() + 
						", first_page="+first_page+
						", second_page="+second_page +"]";
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	
	
}
