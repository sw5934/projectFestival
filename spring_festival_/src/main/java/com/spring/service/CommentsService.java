package com.spring.service;

import java.sql.SQLException;
import java.util.Map;

import com.spring.controller.board.Criteria;
import com.spring.controller.board.SearchCriteria;
import com.spring.dto.CommentsVO;

public interface CommentsService {

	public Map<String, Object> getCommentsList(int unq_Id, SearchCriteria cri) throws Exception;

	public void registComments(CommentsVO comments) throws SQLException;

	public void modifyComments(CommentsVO comments) throws SQLException;

	public void removeComments(int c_no) throws SQLException;

}
