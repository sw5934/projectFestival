package com.spring.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.SearchCriteria;
import com.spring.dto.FestivalVO;
import com.spring.dto.HashTagVO;
import com.spring.dto.ReviewVO;
import com.spring.dto.TogetherVO;
import com.spring.dto.VoteVO;

public class FestivalDAOImpl implements FestivalDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	@Override
	public List<FestivalVO> selectSearchFestivalList(SearchCriteria cri) throws SQLException { 
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<FestivalVO> festivalList = session.selectList("Festival-Mapper.selectSearchFestivalList", cri, rowBounds);
		return festivalList;
	}
	
	@Override
	public int selectSearchFestivalListCount(SearchCriteria cri) throws SQLException{
		return session.selectOne("Festival-Mapper.selectSearchFestivalListCount",cri);
	}
	
	@Override
	public FestivalVO selectFestival(int fno) throws SQLException {
		return session.selectOne("Festival-Mapper.selectFestival",fno);
	}
	@Override
	public int selectFestivalSeqNext() throws SQLException {
		return session.selectOne("Festival-Mapper.selectFestivalSeqNext");
	}
	@Override
	public int selectUnqSeqNext() throws SQLException {
				return session.selectOne("Festival-Mapper.selectUnqSeqNext");
	}
	@Override
	public void insertFestival(FestivalVO festival) throws SQLException {
		session.update("Festival-Mapper.insertFestival",festival);
		
	}
	@Override
	public void updateFestival(FestivalVO festival) throws SQLException {
		session.update("Festival-Mapper.updateFestival",festival);
		
	}
	@Override
	public void deleteFestival(int fno) throws SQLException {
		session.update("Festival-Mapper.deleteFestival",fno);
		
	}
	@Override
	public void increaseViewCnt(int fno) throws SQLException {
		session.update("Festival-Mapper.increaseViewCnt",fno);
		
	}
	@Override
	public void increaseVote1(int fno) throws SQLException {
		session.update("Festival-Mapper.increaseVote1",fno);
		
	}
	@Override
	public void increaseVote2(int fno) throws SQLException {
		session.update("Festival-Mapper.increaseVote2",fno);
		
	}
	@Override
	public void decreaseVote1(int fno) throws SQLException {
		session.update("Festival-Mapper.decreaseVote1",fno);
		
	}
	@Override
	public void decreaseVote2(int fno) throws SQLException {
		session.update("Festival-Mapper.decreaseVote2",fno);
		
	}
	@Override
	public VoteVO selectVoteRecord(int fno, String id, int seperate) throws SQLException {
		Map<String,Object> sqlData = new HashMap<String,Object>();
		sqlData.put("id",id);
		sqlData.put("fno",fno);
		sqlData.put("seperate",seperate);
		return session.selectOne("Festival-Mapper.selectVoteRecord",sqlData);
	}
	@Override
	public void createVoteRecord(String id, int unq_Id, int seperate) throws SQLException {
		Map<String,Object> sqlData = new HashMap<String,Object>();
		sqlData.put("id",id);
		sqlData.put("unq_Id",unq_Id);
		sqlData.put("seperate",seperate);
		session.update("Festival-Mapper.createVoteRecord",sqlData);
		
	}
	@Override
	public void deleteVoteRecord(String id, int unq_Id, int seperate) throws SQLException {
		Map<String,Object> sqlData = new HashMap<String,Object>();
		sqlData.put("id",id);
		sqlData.put("unq_Id",unq_Id);
		sqlData.put("seperate",seperate);
		session.update("Festival-Mapper.deleteVoteRecord",sqlData);
		
	}
	public void insertTag(int fno,String tag) {
		Map<String,Object> sqlData = new HashMap<String,Object>();
		sqlData.put("fno", fno);
		sqlData.put("tag", tag);
		session.update("Festival-Mapper.insertTag",sqlData);
	}
	
	public void deleteTag(int fno) {
		session.update("Festival-Mapper.deleteTag",fno);
	}
	
	public List<HashTagVO> selectTag(int fno) {
		List<HashTagVO> hashTagList = session.selectList("Festival-Mapper.selectTag",fno);
		return hashTagList;
	}
	

	public List<ReviewVO> selectReviewListByfno(int fno){
		return session.selectList("Festival-Mapper.selectReviewListByfno",fno);
	}
	
	public List<TogetherVO> selectTogetherListByfno(int fno){
		return session.selectList("Festival-Mapper.selectTogetherListByfno",fno);
	}
	
	public float selectScoreAvg(int fno) throws SQLException{
		return session.selectOne("Festival-Mapper.selectScoreAvg",fno);
	};
}
