package com.spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.Criteria;
import com.spring.dto.CommentsBoardVO;
import com.spring.dto.HoldingVO;
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

		/*
		int comments[] = new int[myReviewList.size()];
		
		for(int i=0; i<myReviewList.size(); i++) {
			comments[i] = (Integer)session.selectOne("MyPage-Mapper.reviewComments", myReviewList.get(i));
			System.out.println("짜잔 : " + comments[i]);
		}
		*/		

		
		
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
		
		
		/*
		int comments[] = new int[myReviewList.size()];
		
		for(int i=0; i<myReviewList.size(); i++) {
			comments[i] = (Integer)session.selectOne("MyPage-Mapper.reviewComments", myReviewList.get(i));
			System.out.println("짜잔 : " + comments[i]);
		}
		*/
		
		
		
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
	public Map<String, Object> holdingList(Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);

		Map<String, Object> tagMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> cmtMap = new HashMap<String, Object>();
		List<HoldingVO> holdingList = new ArrayList<HoldingVO>();
		List<String> holdingTitle = new ArrayList<String>();
		holdingList = session.selectList("MyPage-Mapper.holdingList", cri, rowBounds);
		
		
		System.out.println("holdingList.size() = " + holdingList.size());
		
		
		
		List<String> tmp = new ArrayList<String>();
		String title = "";
		for(HoldingVO tuple : holdingList) {
			tuple.setComments((Integer)session.selectOne("MyPage-Mapper.holdingCommentsCount", tuple)); // tuple이 가진 unq_id를 쿼리에 사용한다.
			if( !title.equals(tuple.getTitle()) ) {
				title = tuple.getTitle();
				tmp.clear();
			}
			tmp.add(tuple.getHashTag());
			holdingTitle.add(tuple.getTitle());
			tagMap.put(title, tmp); // 하나의 제목에 대한 여러개의 태그들(List<String>)
			System.out.println("이고오옷 : " + tagMap + " , " + tuple.getTitle());
			cmtMap.put(tuple.getTitle(), tuple.getComments());
		}
		
		
		for(int i=0; i<holdingTitle.size(); i++) {
			for(int x=0; x<holdingTitle.size(); x++) {
				if(holdingTitle.get(i).equals(holdingTitle.get(x))) {
					holdingTitle.remove(x);
				}
			}
		}
		
		resultMap.put("cmtMap", cmtMap); // Map
		resultMap.put("holdingMap", holdingTitle); // List<String>
		resultMap.put("tagMap", tagMap); // Map
		
		return resultMap;
	}
	
	@Override
	public int holdingTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.holdingTotalCount", cri);
		return totalCount;
	}
}








