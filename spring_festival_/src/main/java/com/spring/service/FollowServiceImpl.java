package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.spring.controller.board.Criteria;
import com.spring.controller.board.PageMaker;
import com.spring.controller.board.Second_Criteria;
import com.spring.dao.FollowDAO;

public class FollowServiceImpl implements FollowService {
	private FollowDAO followDAO;

	public void setFollowDAO(FollowDAO followDAO) {
		System.out.println("FollowServiceImpl.setFollowDAO()");
		this.followDAO = followDAO;
	}
	
	
	
	
	public Map<String,Object> followList(Second_Criteria cri) throws SQLException {

		Map<String, Object> followList = new HashMap<String, Object>();
		followList = followDAO.followList(cri); // followList <- dataMap { follow, followed }


		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		int totalCount = followDAO.followTotalCount(cri);
		pageMaker.setTotalCount(totalCount, "follow");
		

		
		PageMaker pageMakered = new PageMaker();
		pageMakered.setCri(cri);
		totalCount = followDAO.followedTotalCount(cri);
		pageMakered.setTotalCount(totalCount, "followed");
		


		followList.put("pageMaker", pageMaker);
		followList.put("pageMakered", pageMakered);
		followList.put("myId", cri.getStr());
		// followList <- follow, followed, pageMaker, pageMakered
		

		System.out.println("FollowService.selectFollow() 종료\n" + cri.getStr());
		return followList;
	}
	
	
	
	
	

	public Map<String, Object> f_write(Second_Criteria cri) throws SQLException {
		Map<String, Object> followBoardList = followDAO.f_write(cri); // "팔로워가 작성한 게시판 3개 글 목록", "가고싶어요" 글 목록
															// Map = { "three_board_list" , " wantGoList" }
		
		
		PageMaker threeBoardPM = new PageMaker();
		threeBoardPM.setCri(cri);
		int totalCount = followDAO.f_WriteTotalCount(cri);
		System.out.println("게시판3개의 모든 글 갯수 = " + totalCount);
		threeBoardPM.setTotalCount(totalCount, "threeBoard"); // First_page
		// 두번째 인자는 "follow", "followed", "threeBaord", "wnatGo"를 구분하기 위함이다.
		

		PageMaker wantGoPM = new PageMaker();
		wantGoPM.setCri(cri);
		totalCount = followDAO.f_WantGoTotalCount(cri);
		System.out.println("가고싶어요 게시판의 모든 글 갯수 = " + totalCount);
		wantGoPM.setTotalCount(totalCount, "wantGo"); // Second_page

		followBoardList.put("wantGoPM", wantGoPM);
		followBoardList.put("threeBoardPM", threeBoardPM);
		// Map = { "three_board_list" , " wantGoList", "wantGoPM", "threeBoardPM" }
		
		System.out.println("FollowService - Map.followBoardList.size() = " + followBoardList.size());
		
		return followBoardList;
	}
	
	
	
	public void unFollow(String nickName, String myId) throws SQLException {
		System.out.println("FollowService.unFollow() - followDAO.unFollow() 호출");
		followDAO.unFollow(nickName, myId);
	}
}
