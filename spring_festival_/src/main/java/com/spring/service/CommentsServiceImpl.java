package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.Criteria;
import com.spring.controller.board.PageMaker;
import com.spring.dao.CommentsDAO;
import com.spring.dto.CommentsVO;

public class CommentsServiceImpl implements CommentsService {
	private CommentsDAO commentsDAO;
	public void setCommentsDAO(CommentsDAO commentsDAO) {
		this.commentsDAO = commentsDAO;
	}

	@Override
	public Map<String, Object> getCommentsList(int unq_id, Criteria cri) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<CommentsVO> commentsList = commentsDAO.selectCommentsListPage(unq_id, cri);
		
		int count = commentsDAO.countComments(unq_id);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);
		
		dataMap.put("commentsList", commentsList);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}

	@Override
	public void registComments(CommentsVO comments) throws SQLException {
		commentsDAO.insertComments(comments);
	}

	@Override
	public void modifyComments(CommentsVO comments) throws SQLException {
		commentsDAO.updateComments(comments);
	}

	@Override
	public void removeComments(int cno) throws SQLException {
		commentsDAO.deleteComments(cno);
	}

}
