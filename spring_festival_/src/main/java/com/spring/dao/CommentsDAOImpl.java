package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.test.context.jdbc.Sql;

import com.spring.controller.board.Criteria;
import com.spring.dto.CommentsVO;

public class CommentsDAOImpl implements CommentsDAO {
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<CommentsVO> selectCommentsListPage(int unq_id, Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<CommentsVO> commentsList = session.selectList("Comments-Mapper.selectCommentsList", unq_id, rowBounds);
		
		return commentsList;
	}

	@Override
	public int countComments(int unq_id) throws SQLException {
		int count = session.selectOne("Comments-Mapper.countComments",unq_id);
		return count;
	}

	@Override
	public void insertComments(CommentsVO comments) throws SQLException {
		session.update("Comments-Mapper.insertComments",comments);		
	}

	@Override
	public void updateComments(CommentsVO comments) throws SQLException {
		session.update("Comments-Mapper.updateComments", comments);		
	}

	@Override
	public void deleteComments(int cno) throws SQLException {
		session.update("Comments-Mapper.deletComments",cno);		
	}

}
