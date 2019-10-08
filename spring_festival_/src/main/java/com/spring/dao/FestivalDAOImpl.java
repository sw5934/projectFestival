package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.AttachVO;
import com.spring.dto.FestivalVO;

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

}
