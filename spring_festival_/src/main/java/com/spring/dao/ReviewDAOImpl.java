package com.spring.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.LikeVO;
import com.spring.dto.ReviewVO;

public class ReviewDAOImpl implements ReviewDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<ReviewVO> selectReviewCriteria(SearchCriteria cri) throws SQLException {
		int offset=cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		List<ReviewVO> reviewList;
		
		if(!(cri.getListSort().equals("r_like")||cri.getListSort().equals("r_viewcnt"))) {
			cri.setListSort("rno");
		}
		
		if(cri.getListSort().equals("r_like")) {
			reviewList=
					session.selectList("Review-Mapper.selectSearchReviewListByLike",cri,rowBounds);
		}else {
			reviewList=
					session.selectList("Review-Mapper.selectSearchReviewList",cri,rowBounds);
		}	
		return reviewList;
	}

	@Override
	public int selectReviewCriteriaTotalCount(SearchCriteria cri) throws SQLException {
		
	List<ReviewVO> reviewList;
			
			if(!(cri.getListSort().equals("r_like")||cri.getListSort().equals("r_viewcnt"))) {
				cri.setListSort("rno");
			}
			
			if(cri.getListSort().equals("r_like")) {
				reviewList=
						session.selectList("Review-Mapper.selectSearchReviewListByLike",cri);
			}else {
				reviewList=
						session.selectList("Review-Mapper.selectSearchReviewList",cri);
			}	
		int count=reviewList.size();
		
		return count;
	}	

	@Override
	public ReviewVO selectReviewByRno(int rno) throws SQLException {
		ReviewVO review=session.selectOne("Review-Mapper.selectReviewByRno", rno);
		return review;
	}

	@Override
	public void insertReview(ReviewVO review) throws SQLException {
		session.update("Review-Mapper.insertReview", review);
	}

	@Override
	public void updateReview(ReviewVO review) throws SQLException {
		session.update("Review-Mapper.updateReview", review);
	}

	@Override
	public void deleteReview(int rno) throws SQLException {
		session.update("Review-Mapper.deleteReview", rno);
	}

	@Override
	public void increaseViewCnt(int rno) throws SQLException {
		session.update("Review-Mapper.increaseViewCnt", rno);
	}

	@Override
	public int getSeqNextValue() throws SQLException {
		int rno = session.selectOne("Review-Mapper.selectReviewSeqNext");
		return rno;
	}

	@Override
	public int getUnqSeqNextValue() throws SQLException {
		int uniqid = session.selectOne("Review-Mapper.selectUnqSeqNext");
		return uniqid;
	}

	@Override
	public int getLikeCount(int unq_Id) throws SQLException {
		int count = session.selectOne("Utils-Mapper.selectLikeCount",unq_Id);
		return count;
	}

	@Override
	public int getLikeHistory(String id, int unq_Id) throws SQLException {
		Map<String,Object> sqlData = new HashMap<String,Object>();
		sqlData.put("id", id);
		sqlData.put("unq_Id", unq_Id);
		int history = session.selectOne("Utils-Mapper.selectLikeHistory",sqlData);
		return history;
	}

	@Override
	public void addLike(String id, int unq_Id) throws SQLException {
		LikeVO like = new LikeVO();
		like.setId(id);
		like.setUnq_Id(unq_Id);
		session.update("Utils-Mapper.addLike", like);
	}

	@Override
	public void deleteLike(String id, int unq_Id) throws SQLException {
		LikeVO like = new LikeVO();
		like.setId(id);
		like.setUnq_Id(unq_Id);
		session.update("Utils-Mapper.deleteLike", like);
	}



}
