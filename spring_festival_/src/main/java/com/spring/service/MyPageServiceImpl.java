package com.spring.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.Second_Criteria;
import com.spring.dao.MyPageDAO;
import com.spring.dto.ReviewAndTogetherVO;

public class MyPageServiceImpl implements MyPageService {
	private MyPageDAO myPageDAO;
	
	public void setMyPageDAO(MyPageDAO myPageDAO) {
		this.myPageDAO = myPageDAO;
	}
	
	
	
	
	public Map<String, Object> myReviewList(Second_Criteria cri) throws SQLException {
		
		List<ReviewAndTogetherVO> myReviewList = new ArrayList<ReviewAndTogetherVO>();
		myReviewList = myPageDAO.myReviewList(cri);
		
		int totalCount = myPageDAO.myReviewTotalCount(cri);

		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		Map<String,Object> dataMap=new HashMap<String,Object>();
		
		dataMap.put("myReviewList", myReviewList);
		dataMap.put("pageMaker",pageMaker);
		

		return dataMap;
	}
	
	
	
}
