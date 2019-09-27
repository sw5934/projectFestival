package com.spring.controller.board;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dao.AttachDAO;
import com.spring.dto.AttachVO;
import com.spring.dto.CommentsVO;
import com.spring.dto.ReviewVO;
import com.spring.service.ReviewService;
import com.spring.utils.DeleteFileUtils;
import com.spring.utils.UploadFileUtils;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private AttachDAO attachDAO;
	
	private static final Logger logger=	LoggerFactory.getLogger(ReviewController.class);
	
	//???
	@ModelAttribute("category")
	public String category() throws Exception{
		return "review";		
	}
	
	@RequestMapping("/list")
	public void listReview(SearchCriteria cri, Model model ,String listSort)throws Exception{
		
		try {
			if(listSort==null)
				listSort  = "rno";
			cri.setListSort(listSort);
			System.out.println(cri.getListSort());
			Map<String, Object> dataMap = reviewService.getList(cri);

			model.addAttribute("dataMap",dataMap);
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public void registGET()throws Exception{}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public void registPost(ReviewVO review, MultipartFile[] uploadFile, HttpServletResponse response) throws Exception{
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		System.out.println(review);
		for(MultipartFile file : uploadFile) {
			AttachVO attach = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), review.getUnqId(), file.getBytes());
			
			attachList.add(attach);
		}
		
		review.setAttachList(attachList);
		
		reviewService.regist(review);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.reload();window.close();");
		out.println("</script>");
	}
	
	@RequestMapping(value = "/detail", method=RequestMethod.GET)
	public void detail(int rno, Model model, SearchCriteria cri) throws Exception{
		
		Map<String, Object> dataMap = reviewService.read(rno, cri);
		
		model.addAttribute("dataMap", dataMap);
	}
	
	@RequestMapping(value = "/modify", method=RequestMethod.GET)
	public void modify(int rno, Model model,SearchCriteria cri) throws Exception{
		
		ReviewVO review = reviewService.get(rno);
		model.addAttribute("review", review);
	}
	@RequestMapping(value = "/modify", method=RequestMethod.POST)
	public void modify(ReviewVO review, MultipartFile[] uploadFile, int[] deleteFile, HttpServletResponse response) throws Exception{
		if(deleteFile != null) {
			//파일하나, db하나 for문으로 반복
			for(int ano:deleteFile) {
				
				AttachVO attach = attachDAO.selectAttachByAno(ano);
				
				//파일지우기
				DeleteFileUtils.delete(uploadPath, attach);
				
				attachDAO.deleteAttach(ano);
			}
		}
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();
			if(uploadFile != null) {
				for(MultipartFile file : uploadFile) {
					
					AttachVO attach = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), review.getUnqId(), file.getBytes());
					
					attachList.add(attach);
				}
				review.setAttachList(attachList);
			}
			
			//제목과 내용만 수정할 수 있어 빼놓음
			reviewService.modify(review);
			
			response.setContentType("text/html;charset=utf-8");		
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.opener.location.reload();");
			out.println("loaction.href='detail?rno=" + review.getRno() + "';");
			out.println("</script>");
	}
	@RequestMapping("/remove")
	public void removeReview(int rno, HttpServletResponse response) throws Exception{
		
		ReviewVO review = reviewService.getReview(rno);
		
		List<AttachVO> attachList = review.getAttachList();
		if(attachList != null) {
			//파일하나, db하나 for문으로 반복
			for(AttachVO attach : attachList) {
				
				//파일지우기
				DeleteFileUtils.delete(uploadPath, attach);
				
				//DB지우기
				attachDAO.deleteAttach(attach.getAno());
			}
		}
		reviewService.remove(rno);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.reload();window.close();");
		out.println("</script>");
	}
}
