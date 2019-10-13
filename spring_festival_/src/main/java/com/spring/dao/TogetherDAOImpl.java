package com.spring.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.MemberVO;
import com.spring.dto.TogetherVO;

public class TogetherDAOImpl implements TogetherDAO{
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<TogetherVO> selectTogetherCriteria(SearchCriteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<TogetherVO> togetherList = session.selectList("Together-Mapper.selectSearchTogetherList", cri, rowBounds);
		return togetherList;
	}

	@Override
	public int selectTogetherCriteriaTotalCount(SearchCriteria cri) throws SQLException {
		
		List<TogetherVO> togetherList= 
				session.selectList("Together-Mapper.selectSearchTogetherList",cri);
		int count=togetherList.size();
		
		return count;
	}

	@Override
	public TogetherVO selectTogetherByTno(int tno) throws SQLException {
		TogetherVO together = session.selectOne("Together-Mapper.selectTogetherByTno", tno);
		return together;
	}

	@Override
	public void insertTogether(TogetherVO together) throws SQLException {
		session.update("Together-Mapper.insertTogether", together);
	}

	@Override
	public void updateTogether(TogetherVO together) throws SQLException {
		session.update("Together-Mapper.updateTogether", together);
	}

	@Override
	public void deleteTogether(int tno) throws SQLException {
		session.update("Together-Mapper.deleteTogether", tno);
	}

	@Override
	public void increaseViewCnt(int tno) throws SQLException {
		session.update("Together-Mapper.increaseViewCnt", tno);
	}	

	@Override
	public int getSeqNextValue() throws SQLException {
		int tno = session.selectOne("Together-Mapper.selectTogetherSeqNext");
		return tno;
	}

	@Override
	public int getUnqSeqNextValue() throws SQLException {
		int unq_Id = session.selectOne("Together-Mapper.selectUnqSeqNext");
		return unq_Id;
	}
	
	@Override
	public void deadLineTogether(String tno, String t_state) throws SQLException{
		Map<String,String> data = new HashMap<String,String>();
		data.put("tno", tno);
		data.put("t_state", t_state);
		session.update("Together-Mapper.deadLineTogether",data);
	}
	
}
