package com.spring.controller.board;

public class PageMaker {

	private int totalCount; // 전체 board 개수
	private int startPage; // 시작 페이지 번호
	private int endPage; // 마지막 페이지 번호
	private int realEndPage; // 끝 페이지 번호
	private boolean prev; // 이전페이지 버튼 유무
	private boolean next; // 다음페이지 버튼 유무

	private int displayPageNum = 10; // 한 페이지에 보여줄 페이지번호 개수

	private SearchCriteria cri; // 현재 페이지 정보
	
	// 인성
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;

		calcData();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getRealEndPage() {
		return realEndPage;
	}

	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(SearchCriteria cri) {
		this.cri = cri;
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", realEndPage=" + realEndPage + ", prev=" + prev + ", next=" + next + ", displayPageNum="
				+ displayPageNum + ", cri=" + cri + "]";
	}

	// startPage, endPage, prev, next설정, by totalCount
	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		// 페이지번호/페이지넘버링 * 페이지 넘버링= 몫을 올림

		startPage = (endPage - displayPageNum) + 1;

		realEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));

		if (endPage > realEndPage) {
			endPage = realEndPage;
		}

		prev = startPage == 1 ? false : true;
		// 시작페이지 번호1이 아니면 true

		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}
	
	public String makeNonSearchQuery() {
		String query = "?page=" + cri.getPage() + "&perPageNum=" + cri.getPerPageNum();
		return query;
	}

	public String makeNonSearchQuery(int page) {
		String query = "?page=" + cri.getPage() + "&perPageNum=" + cri.getPerPageNum();
		return query;
	}
	
	private void calcData(String seperator) {
		
		if (seperator.equals("follow") || seperator.equals("threeBoard") || seperator.equals("VwantGo") ) {
			endPage = (int) (Math.ceil(((Second_Criteria) cri).getFirst_page() / (double) displayPageNum) * displayPageNum);
		} else if (seperator.equals("followed") || seperator.equals("FwantGo") || seperator.equals("goAndBack") ) {
			endPage = (int) (Math.ceil(((Second_Criteria) cri).getSecond_page() / (double) displayPageNum) * displayPageNum);
			// cri.getSecond_page() / (double) displayPageNum)     
			// =>>  1~10		    /      10.0				= 0.1~1     
			// =>>  11~20		    /      10.0    			= 1.1~2     
			// =>>  21~30		    /      10.0    			= 2.1~3

			// Math.ceil(cri.getSecond_page() / (double) displayPageNum))     
			// =>>  1~10		    /      10.0				= Math.ceil(0.1~1) = 1     
			// =>>  11~20		    /      10.0    			= Math.ceil(1.1~2) = 2     
			// =>>  21~30		    /      10.0    			= Math.ceil(2.1~3) = 3
						
			// Math.ceil(cri.getSecond_page() / (double) displayPageNum)) * displayPageNum     
			// =>>  1~10		    /      10.0				= Math.ceil(0.1~1) = 1	* 10 = 10 
			// =>>  11~20		    /      10.0    			= Math.ceil(1.1~2) = 2	* 10 = 20 
			// =>>  21~30		    /      10.0    			= Math.ceil(2.1~3) = 3	* 10 = 30
		}


		// (10,20,30, 40, 50 - 10) + 1 = 01, 11, 21, 31, 41
		startPage = (endPage - displayPageNum) + 1;

		realEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));

		if (endPage > realEndPage) {
			endPage = realEndPage;
		}

		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;		
	}


	public String makeQuery() {
		String query = "?page=" + cri.getPage() + "&perPageNum=" + cri.getPerPageNum()
						+ "&listSort="+((SearchCriteria)cri).getListSort() 
						+ "&searchType="+((SearchCriteria)cri).getSearchType() 
						+ "&keyword="+((SearchCriteria)cri).getKeyword();
		return query;
	}

	public String makeQuery(int page) {
		String query = "?page=" + page + "&perPageNum=" + cri.getPerPageNum()
						+ "&listSort="+((SearchCriteria)cri).getListSort() 
						+"&searchType="+((SearchCriteria)cri).getSearchType()  
						+ "&keyword="+((SearchCriteria)cri).getKeyword();;
		return query;
	}


	// 인성
	public void setTotalCount(int totalCount, String seperator) {	
		this.totalCount = totalCount;
		calcData(seperator);
	}

}
