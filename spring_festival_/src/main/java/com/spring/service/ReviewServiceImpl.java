package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.SearchCriteria;
import com.spring.dao.AttachDAO;
import com.spring.dao.CommentsDAO;
import com.spring.dao.ReviewDAO;
import com.spring.dto.AttachVO;
import com.spring.dto.ReviewVO;

public class ReviewServiceImpl implements ReviewService{
	
	private ReviewDAO reviewDAO;
	public void setReviewDAO(ReviewDAO reviewDAO) {
		this.reviewDAO = reviewDAO;
	}
	
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attahcDAO) {
		this.attachDAO = attahcDAO;
	}
	
	private CommentsDAO commentsDAO;
	public void setCommentsDAO(CommentsDAO commentsDAO) {
		this.commentsDAO = commentsDAO;
	}

	@Override
	public Map<String, Object> getList(SearchCriteria cri) throws SQLException {
		List<ReviewVO> reviewList = reviewDAO.selectReviewCriteria(cri);
		
		for(ReviewVO review : reviewList) {
			if(commentsDAO.countComments(review.getUnq_Id())!=0) {
			review.setCommentcount(commentsDAO.countComments(review.getUnq_Id()));}
			review.setR_like(reviewDAO.getLikeCount(review.getUnq_Id()));
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(reviewDAO.selectReviewCriteriaTotalCount(cri));
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("reviewList", reviewList);
		dataMap.put("pageMaker", pageMaker);
		System.out.println(pageMaker.toString());
		return dataMap;
	}

	@Override
	public ReviewVO getReview(int rno) throws SQLException {
		ReviewVO review = reviewDAO.selectReviewByRno(rno);
		
		List<AttachVO> attachList = attachDAO.selectAttachesByUnqId(rno);
		review.setAttachList(attachList);
		return review;
	}
	//첨부파일 하나하나에 번호부여 추가할것
	@Override
	public void regist(ReviewVO review) throws SQLException {
		int rno = reviewDAO.getSeqNextValue();		
		int unqId = reviewDAO.getUnqSeqNextValue();
		
		review.setRno(rno);
		review.setUnq_Id(unqId);
		reviewDAO.insertReview(review);		
		
		List<AttachVO> attachList = review.getAttachList();
		if(attachList !=null) {
			for(AttachVO attach:attachList) {
				attach.setUnqId(unqId);			
				attachDAO.insertAttach(attach);				
			}
		}
	}

	@Override
	public void modify(ReviewVO review) throws SQLException {
		int rno = reviewDAO.getSeqNextValue();		
		int unqId = reviewDAO.getUnqSeqNextValue();
		
		reviewDAO.updateReview(review);
		
		
		List<AttachVO> attachList = review.getAttachList();
		if(attachList !=null) {
			for(AttachVO attach:attachList) {
				attach.setUnqId(unqId);			
				attachDAO.insertAttach(attach);				
			}
		}
	}

	@Override
	public void remove(int rno) throws SQLException {
		reviewDAO.deleteReview(rno);		
		
	}

	@Override
	public Map<String, Object> read(int rno, SearchCriteria cri) throws SQLException {
		ReviewVO review = reviewDAO.selectReviewByRno(rno);
		
		List<AttachVO> attachList = attachDAO.selectAttachesByUnqId(review.getUnq_Id());
		review.setAttachList(attachList);
		
		review.setCommentsList(commentsDAO.selectCommentsListPage(review.getUnq_Id(), cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(commentsDAO.countComments(review.getUnq_Id()));
		
		review.setR_like(reviewDAO.getLikeCount(review.getUnq_Id()));
		
		reviewDAO.increaseViewCnt(rno);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("review", review);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}
	
	@Override
	public ReviewVO get(int rno) throws SQLException {	
		int unqId = reviewDAO.getUnqSeqNextValue();
		ReviewVO review = reviewDAO.selectReviewByRno(rno);
		
		List<AttachVO> attachList = attachDAO.selectAttachesByUnqId(unqId);
		review.setAttachList(attachList);
		
		return review;
	}

}
