package com.spring.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.Second_Criteria;
import com.spring.dao.BoardDAO;
import com.spring.dto.SecondReviewVO;

public class BoardServiceImpl implements BoardService {
	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	
	
	
	public Map<String, Object> myReviewList(Second_Criteria cri) throws SQLException {
		System.out.println("BoardServiceImpl.myReviewList() 시작");
		
		List<SecondReviewVO> myReviewList = new ArrayList<SecondReviewVO>();
		myReviewList = boardDAO.myReviewList(cri);
		
		int totalCount = boardDAO.myReviewTotalCount(cri);

		System.out.println("BoardServiceImpl.myReviewList() ,   totalCount = " + totalCount);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		Map<String,Object> dataMap=new HashMap<String,Object>();
		
		dataMap.put("myReviewList", myReviewList);
		dataMap.put("pageMaker",pageMaker);
		

		System.out.println("BoardServiceImpl.myReviewList() 종료");
		return dataMap;
	}
	
	
	
}
