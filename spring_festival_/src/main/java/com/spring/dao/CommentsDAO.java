package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.Criteria;
import com.spring.dto.CommentsVO;

public interface CommentsDAO {
	public List<CommentsVO> selectCommentsListPage(int unq_id, Criteria cri) throws SQLException;

	public int countComments(int unq_id) throws SQLException;

	public void insertComments(CommentsVO comments) throws SQLException;

	public void updateComments(CommentsVO comments) throws SQLException;

	public void deleteComments(int cno) throws SQLException;

}
