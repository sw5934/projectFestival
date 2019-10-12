package com.spring.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.Second_Criteria;
import com.spring.dao.MyPageDAO;
import com.spring.dto.AuthSettingVO;
import com.spring.dto.CommentsBoardVO;
import com.spring.dto.ReviewAndTogetherVO;

public class MyPageServiceImpl implements MyPageService {
	MyPageDAO myPageDAO;
	
	public void setMyPageDAO(MyPageDAO myPageDAO) {
		this.myPageDAO = myPageDAO;
	}
	
	@Override
	public Map<String, Object> myReviewList(Second_Criteria cri) throws SQLException {
		List<ReviewAndTogetherVO> myReviewList = new ArrayList<ReviewAndTogetherVO>();
		myReviewList = myPageDAO.myReviewList(cri);
		
		int totalCount = myPageDAO.myReviewTotalCount(cri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		// DAO에서 넘겨받은 List타입의 review목록 + review목록의 tatal을 가진 PageMaker를 Map에 저장
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("myReviewList", myReviewList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public Map<String, Object> myTogetherList(Second_Criteria cri) throws SQLException {
		List<ReviewAndTogetherVO> myTogetherList = new ArrayList<ReviewAndTogetherVO>();
		myTogetherList = myPageDAO.myTogetherList(cri);
		
		int totalCount = myPageDAO.myTogetherTotalCount(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("myTogetherList", myTogetherList);
		dataMap.put("pageMaker", pageMaker);

		
		
		return dataMap;
	}

	
	@Override
	public Map<String, Object> commentsBoard(Second_Criteria cri) throws SQLException {	
		List<CommentsBoardVO> commentsList = new ArrayList<CommentsBoardVO>();
		commentsList = myPageDAO.commentsBoard(cri);
		
		int totalCount = myPageDAO.myCommentsTotalCount(cri);
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("commentsList", commentsList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	
	@Override
	public Map<String, Object> holdingList(Second_Criteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap = myPageDAO.holdingList(cri);
		
		int totalCount = myPageDAO.holdingTotalCount(cri); // ((List<String>)dataMap.get("holdingMap")).size();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("pageMaker", pageMaker);
		
		
		return dataMap;
	}
	
	
	@Override
	public Map<String, Object> authSetting(Second_Criteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<AuthSettingVO> authSetList = new ArrayList<AuthSettingVO>();
		
		
		
		authSetList = myPageDAO.authSetting(cri);
		
		int totalCount = myPageDAO.authSetTotalCount(cri);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("authSetList", authSetList);
		dataMap.put("pageMaker", pageMaker);
		
		
		return dataMap;
	}
	
	
	
	public void authUpdate(Map<String, String> strMap) throws SQLException {
		myPageDAO.authUpdate(strMap);
	}
	
	
	public Map<String, Object> memSearch(Second_Criteria cri) throws SQLException {
		Map<String, Object> memSearchList = new HashMap<String, Object>();
		
		return memSearchList;
	}
}
