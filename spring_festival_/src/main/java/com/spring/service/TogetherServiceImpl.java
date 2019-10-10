package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.SearchCriteria;
import com.spring.dao.AttachDAO;
import com.spring.dao.CommentsDAO;
import com.spring.dao.TogetherDAO;
import com.spring.dto.AttachVO;
import com.spring.dto.ReviewVO;
import com.spring.dto.TogetherVO;

public class TogetherServiceImpl implements TogetherService{
	
	private TogetherDAO togetherDAO;
	public void setTogetherDAO(TogetherDAO togetherDAO) {
		this.togetherDAO = togetherDAO;
	}
	
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}
	
	private CommentsDAO commentsDAO;
	public void setCommentsDAO(CommentsDAO commentsDAO) {
		this.commentsDAO = commentsDAO;
	}

	@Override
	public Map<String, Object> getList(SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object> ();
		List<TogetherVO> togetherList = togetherDAO.selectTogetherCriteria(cri);
		
		for(TogetherVO together : togetherList) {
			if(commentsDAO.countComments(together.getUnq_Id()) != 0) {
				together.setCommentcount(commentsDAO.countComments(together.getUnq_Id()));					
			}
		}
			
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(togetherDAO.selectTogetherCriteriaTotalCount(cri));
		dataMap.put("togetherlist", togetherList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public TogetherVO getTogether(int tno) throws SQLException {
		TogetherVO together = togetherDAO.selectTogetherByTno(tno);
		
		List<AttachVO> attachList = attachDAO.selectAttachesByUnq_Id(tno);
		together.setAttachList(attachList);
		return together;
	}

	@Override
	public void regist(TogetherVO together) throws SQLException {
		int tno = togetherDAO.getSeqNextValue();
		together.setTno(tno);
		togetherDAO.insertTogether(together);
	}

	@Override
	public void modify(TogetherVO together) throws SQLException {
		togetherDAO.updateTogether(together);
		
		List<AttachVO> attachList = together.getAttachList();
		if(attachList !=null) {
			for(AttachVO attach:attachList) {
				attach.setUnq_Id(together.getUnq_Id());
				attachDAO.insertAttach(attach);
			}
		}
	}

	@Override
	public void remove(int tno) throws SQLException {
		togetherDAO.deleteTogether(tno);
	}

	@Override
	public Map<String, Object> read(int tno, SearchCriteria cri) throws SQLException {
		TogetherVO together = togetherDAO.selectTogetherByTno(tno);
		
		List<AttachVO> attachList = attachDAO.selectAttachesByUnq_Id(together.getUnq_Id());
		together.setAttachList(attachList);
		together.setCommentsList(commentsDAO.selectCommentsListPage(together.getUnq_Id(), cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(commentsDAO.countComments(together.getUnq_Id()));
		
		togetherDAO.increaseViewCnt(tno);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("together", together);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}

	@Override
	public TogetherVO get(int tno) throws SQLException {	
		TogetherVO together = togetherDAO.selectTogetherByTno(tno);
		int unq_Id = together.getUnq_Id();	
		
		List<AttachVO> attachList = attachDAO.selectAttachesByUnq_Id(unq_Id);
		together.setAttachList(attachList);
		
		return together;
	}
	
	public int getNextUnq_Id() throws SQLException{
		return togetherDAO.getUnqSeqNextValue();
	}
	
	@Override
	public void deadLineUpdate(String tno, String t_state) throws SQLException{
		togetherDAO.deadLineTogether(tno, t_state);
	}

}
