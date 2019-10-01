package com.spring.controller.board;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dao.AttachDAO;
import com.spring.dto.MemberVO;
import com.spring.dto.ReviewVO;
import com.spring.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Resource(name = "uploadPath")
	private String uploadPath;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private AttachDAO attachDAO;

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	// ???
	@ModelAttribute("category")
	public String category() throws Exception {
		return "review";
	}

	@RequestMapping("/list")
	public void listReview(SearchCriteria cri, Model model, String listSort) throws Exception {

		try {
			if (listSort == null)
				listSort = "rno";
			cri.setListSort(listSort);
			System.out.println(cri.getListSort());
			Map<String, Object> dataMap = reviewService.getList(cri);

			model.addAttribute("dataMap", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/reviewRegist", method = RequestMethod.GET)
	public void registGET(Model model) throws Exception {
		ReviewVO review = new ReviewVO();
		
		review.setUnq_Id(reviewService.getNextUnq_Id());
		model.addAttribute("review",review);
	}

	@RequestMapping(value = "/reviewRegist", method = RequestMethod.POST)
	public String registPost(ReviewVO review, HttpServletRequest request) throws Exception {

		logger.info(review.toString());

		HttpSession session = request.getSession();
		String loginUser = ((MemberVO) session.getAttribute("loginUser")).getId();

		review.setId(loginUser);
		System.out.println(review.toString());
		reviewService.regist(review);

		return "redirect:list";

	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void detail(int rno, Model model, SearchCriteria cri) throws Exception {

		Map<String, Object> dataMap = reviewService.read(rno, cri);

		model.addAttribute("dataMap", dataMap);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int rno, Model model, SearchCriteria cri) throws Exception {

		ReviewVO review = reviewService.get(rno);
		model.addAttribute("review", review);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(ReviewVO review, MultipartFile[] uploadFile, int[] deleteFile, HttpServletRequest request)
			throws Exception {

		// if(deleteFile != null) {
		// //파일하나, db하나 for문으로 반복
		// for(int a_no:deleteFile) {
		//
		// AttachVO attach = attachDAO.selectAttachByA_no(a_no);
		//
		// //파일지우기
		// DeleteFileUtils.delete(uploadPath, attach);
		//
		// attachDAO.deleteAttach(a_no);
		// }
		// }
		//
		// List<AttachVO> attachList = new ArrayList<AttachVO>();
		// if(uploadFile != null) {
		// for(MultipartFile file : uploadFile) {
		//
		// AttachVO attach = UploadFileUtils.uploadFile(uploadPath,
		// file.getOriginalFilename(), review.getUnq_Id(), file.getBytes());
		//
		// attachList.add(attach);
		// }
		// review.setAttachList(attachList);
		// }

		reviewService.modify(review);

		System.out.println(review.toString());

		return "redirect:detail?rno=" + review.getRno();
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String removeReview(int rno) throws Exception {

		/*
		 * ReviewVO review = reviewService.getReview(rno);
		 * 
		 * List<AttachVO> attachList = review.getAttachList(); if(attachList != null) {
		 * //파일하나, db하나 for문으로 반복 for(AttachVO attach : attachList) {
		 * 
		 * //파일지우기 DeleteFileUtils.delete(uploadPath, attach);
		 * 
		 * //DB지우기 attachDAO.deleteAttach(attach.getA_no()); } }
		 */
		reviewService.remove(rno);
		return "redirect:list";
	}
}
