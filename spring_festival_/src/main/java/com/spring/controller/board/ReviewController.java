package com.spring.controller.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	// ???
	@ModelAttribute("category")
	public String category() throws Exception {
		return "review";
	}

	@RequestMapping("/list")
	public void listReview(SearchCriteria cri, Model model, String listSort, String page) throws Exception {

				
		try {
			if (listSort == null)
				listSort = "rno";
			if (page == null)
				page = "1";
			cri.setListSort(listSort);
			System.out.println(cri.getListSort());
			Map<String, Object> dataMap = reviewService.getList(cri);
			dataMap.put("listSort",listSort);
			dataMap.put("page",page);

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
	public String registPost(ReviewVO review, HttpServletRequest request, String unq_Id, int starInput) throws Exception {
		
		
				
		File exist = new File(request.getServletContext().getRealPath("/resources/uploadImg/")+review.getId()+"\\"+unq_Id+".jpg");
		if(!(exist.exists())) {
			File path = new File(request.getServletContext().getRealPath("resources/uploadImg/")+review.getId());
			
			if(!(path.exists())) {
				path.mkdirs();}
			
			File newFile = new File(request.getServletContext().getRealPath("resources/images/defaultImg.jpg"));
			
			String thumbnailFileName = request.getServletContext().getRealPath("resources/uploadImg/")+review.getId() +  "\\"+unq_Id+".jpg";
		
			InputStream fis = null;
			OutputStream fos = null;
			try {
				fis = new FileInputStream(newFile);
				fos = new FileOutputStream(thumbnailFileName);
				
				byte[] buffer = new byte[1024];
				
				int length;
				
				while ((length = fis.read(buffer))>0) {
					fos.write(buffer,0,length);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				fis.close();
				fos.close();
			}			
		}
		
		review.setR_score(starInput);
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		review.setId(loginUser.getId());
		review.setNickname(loginUser.getNickName());
		System.out.println(review.toString());
		reviewService.regist(review);
		
		return "redirect:list";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void detail(HttpServletRequest request,ReviewVO review, int rno, Model model, String page, String listSort,SearchCriteria cri) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		Map<String, Object> dataMap = reviewService.read(rno, cri);
		dataMap.put("listSort",listSort);
		dataMap.put("page",page);
		
		int history = reviewService.getLikeHistory(loginUser.getId(), ((ReviewVO)dataMap.get("review")).getUnq_Id());
		dataMap.put("history",history);		
		model.addAttribute("dataMap", dataMap);
		
		ReviewVO reviewV = (ReviewVO) dataMap.get("review");
		Date registDate = reviewV.getR_regDate();
		String rdate = registDate.toString();
		String subrdate = rdate.substring(0, 10);
		
		review.getR_score();
	}
	
	@ResponseBody
	@RequestMapping(value = "clickLike", method = RequestMethod.GET)	
	public ResponseEntity<Integer> like(String id, String unq_Id)throws Exception{	
		ResponseEntity<Integer> entity = null;
		try {
			int parseUnq_Id = Integer.parseInt(unq_Id);
			int history = reviewService.getLikeHistory(id, parseUnq_Id);
			reviewService.updateLike(id, parseUnq_Id, history);
			
			entity = new ResponseEntity<Integer>(history,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int rno, Model model, String page, String listSort,SearchCriteria cri) throws Exception {
		
		
		ReviewVO review = reviewService.get(rno);
		model.addAttribute("review", review);
		model.addAttribute("page", page);
		model.addAttribute("listSort", listSort);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(ReviewVO review, HttpServletRequest request,String listSort, String page, int starInput)
			throws Exception {

		review.setR_score(starInput);
		reviewService.modify(review);
		
		
		
		System.out.println(review.toString());

		return "redirect:detail?rno=" + review.getRno() + "&listSort="+listSort + "&page="+page;
	}


	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String removeReview(int rno) throws Exception {

		
		reviewService.remove(rno);
		return "redirect:list";
	}
}
