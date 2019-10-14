package com.spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.Criteria;
import com.spring.controller.board.Second_Criteria;
import com.spring.dto.AuthSettingVO;
import com.spring.dto.CommentsBoardVO;
import com.spring.dto.FestivalVO;
import com.spring.dto.HashTagVO;
import com.spring.dto.HoldingVO;
import com.spring.dto.MemSearchVO;
import com.spring.dto.ReviewAndTogetherVO;

public class MyPageDAOImpl implements MyPageDAO {
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<ReviewAndTogetherVO> myReviewList(Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		
		List<ReviewAndTogetherVO> myReviewList = new ArrayList<ReviewAndTogetherVO>();
		myReviewList = session.selectList("MyPage-Mapper.reviewBoard", cri, rowBounds);

		// 각 리뷰글에 대한 댓글 총 갯수를 구한다.		
		for(ReviewAndTogetherVO review : myReviewList) {
			review.setComments((Integer)session.selectOne("MyPage-Mapper.reviewComments", review)) ;	
			System.out.println("댓글갯수(" + review.getUnq_id() +") : " + review.getComments());
		}


		
		
		return myReviewList;
	}

	@Override
	public List<ReviewAndTogetherVO> myTogetherList(Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<ReviewAndTogetherVO> myTogetherList = new ArrayList<ReviewAndTogetherVO>();
		myTogetherList = session.selectList("MyPage-Mapper.togetherBoard", cri, rowBounds);
		
		
		
		// 각 같이가요 글에 대한 댓글 총 갯수를 구한다.
		for(ReviewAndTogetherVO review : myTogetherList) {
			review.setComments((Integer)session.selectOne("MyPage-Mapper.togetherComments", review)) ;	
			System.out.println("댓글갯수(" + review.getUnq_id() +") : " + review.getComments());
		}
		

		
		
		
		return myTogetherList;
	}

	@Override
	public int myReviewTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.myReviewTotalCount", cri);
		return totalCount;
	}

	@Override
	public int myTogetherTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.myTogetherTotalCount", cri);
		return totalCount;
	}
		
	@Override
	public List<CommentsBoardVO> commentsBoard(Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<CommentsBoardVO> commentsList = new ArrayList<CommentsBoardVO>();
		commentsList = session.selectList("MyPage-Mapper.commentsBoard", cri, rowBounds);
		
		return commentsList;
	}
	
	
	@Override
	public int myCommentsTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.commentsTotalCount", cri);
		
		return totalCount;
	}
	
	@Override
	public int holdingCommentsCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.holdingCommentsCount", cri);
		return totalCount;
	}
	
	@Override
	public Map<String, Object> holdingList(Second_Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<FestivalVO> holdingList = session.selectList("MyPage-Mapper.holdingList", cri, rowBounds);

		for(FestivalVO holding : holdingList) {
			List<HashTagVO> tagList = session.selectList("MyPage-Mapper.holdingTagList", holding);
			holding.setHashTagList(tagList);
			String tagString = "";
			if(holding.getHashTagList()!=null) {
				for(HashTagVO tag:holding.getHashTagList())
					tagString = tagString + " #" + tag.getHashTag();
			}
			holding.setHashTagString(tagString);
			
			int count = session.selectOne("Comments-Mapper.countComments",holding.getUnq_Id());
			if(count!=0) {
				holding.setCommentCount(count);}
		}
		
		resultMap.put("holdingList", holdingList);
		return resultMap;
	}
	
	
	@Override
	public int holdingTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.holdingTotalCount", cri);
		return totalCount;
	}
	
	
	
	@Override
	public List<AuthSettingVO> authSetting(Second_Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		
		
		List<AuthSettingVO> authSetList = new ArrayList<AuthSettingVO>();
		
		authSetList = session.selectList("MyPage-Mapper.memAuthSet", cri, rowBounds);
		
		int num;
		for(AuthSettingVO Auth :authSetList) {
			String id = Auth.getId();
			if(((Integer)(session.selectOne("MyPage-Mapper.searchBlackListRecord",id)))!=0) {
				cri.setStr2(id);
				num = session.selectOne("MyPage-Mapper.blackListPeriod", id);
				Auth.setPeriod(num);
			}
		}

		return authSetList;
	}
	
	@Override
	public int authSetTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.authSetTotalCount", cri);
		
		System.out.println("auth : " + totalCount);
		return totalCount;
	}
	
	
	
	
	public void authUpdate(Map<String, String> strMap) throws SQLException {
		int auno=Integer.parseInt(strMap.get("auNo"));
		
		if(strMap.get("auNo").equals("1")) {
			session.update("MyPage-Mapper.deleteBlackList", strMap);
			session.update("MyPage-Mapper.insertBlackList", strMap);
			System.out.println("제재회원 이므로 블랙리스트 추가.");
		} else if(!strMap.get("auNo").equals("1")) {
			session.update("MyPage-Mapper.deleteBlackList", strMap);
			System.out.println("제재회원이 아니므로 블랙리스트 기록 제거.");
		}
		session.update("MyPage-Mapper.deleteAuth", strMap);

		for(int i=0;i<=auno;i++) {
		Map<String,Object> sqlData = new HashMap<String,Object>();
		sqlData.put("authNo", i);
		sqlData.put("mem", strMap.get("mem"));
		session.update("MyPage-Mapper.authUpdate", sqlData);
		}		
	}

	@Override
	public List<MemSearchVO> memSearch(Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<MemSearchVO> memSearchList = session.selectList("MyPage-Mapper.MemSearch", cri, rowBounds);
		System.out.println("DAO - searchList.size = " + memSearchList.size());
		return memSearchList;
	}
	
	@Override
	public int memSearchTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.MemSearchTotalCount", cri);
		return totalCount;
	}
}








