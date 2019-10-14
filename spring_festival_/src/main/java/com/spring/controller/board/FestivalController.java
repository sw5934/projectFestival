package com.spring.controller.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.FestivalVO;
import com.spring.dto.MemberVO;
import com.spring.dto.ReviewVO;
import com.spring.dto.TogetherVO;
import com.spring.service.FestivalService;

@Controller
@RequestMapping("/festival")
public class FestivalController {
	
	@Resource(name = "uploadPath")
	private String uploadPath;

	@Autowired
	private FestivalService festivalService;

	@ModelAttribute("category")
	public String category() throws Exception {
		return "festival";
	}

	@RequestMapping("/list")
	public void listFestival(SearchCriteria cri, Model model, String listSort, String page) throws Exception {

		try {
			if(cri.getKeyword().equals(""))
				cri.setSearchType("tcw");
			if(cri.getSearchType2() == null)
				cri.setSearchType2("전국");
			if(cri.getSearchType3() == null) 
				cri.setSearchType3("전체");
			if (listSort == null)
				listSort = "fno";
			if (page == null)
				page = "1";
			cri.setListSort(listSort);
			
			Map<String, Object> dataMap = festivalService.getFestivalList(cri);
			dataMap.put("listSort",listSort);
			dataMap.put("page",page);
			dataMap.put("cri",cri);

			model.addAttribute("dataMap", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/festivalRegist", method = RequestMethod.GET)
	public void registGET(Model model) throws Exception {
		FestivalVO festival = new FestivalVO();
		festival.setUnq_Id(festivalService.getNextUnqId());
		model.addAttribute("festival",festival);
	}

	@RequestMapping(value = "/festivalRegist", method = RequestMethod.POST)
	public String registPost(FestivalVO festival, String hashTagString, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String loginUser = ((MemberVO) session.getAttribute("loginUser")).getId();
		
		festival.setF_writer(loginUser);
		
		int unq_Id = festival.getUnq_Id();
		File exist = new File(request.getServletContext().getRealPath("/resources/uploadImg/festival/")+festival.getF_writer()+"\\"+unq_Id+".jpg");
		if(!(exist.exists())) {
			File path = new File(request.getServletContext().getRealPath("resources/uploadImg/festival/")+festival.getF_writer());
			if(!(path.exists())) {
				path.mkdirs();}
			File newFile = new File(request.getServletContext().getRealPath("resources/images/defaultImg.jpg"));
			
			String thumbnailFileName = request.getServletContext().getRealPath("resources/uploadImg/festival/")+festival.getF_writer()+"\\"+unq_Id+".jpg";
		
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
		int fno = festivalService.getFestivalSeqNext();
		festival.setFno(fno);
		festivalService.setTag(festival.getFno(),hashTagString);
		festivalService.regist(festival);

		return "redirect:list";

	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void detail(int fno, Model model, String page, String listSort,SearchCriteria cri) throws Exception {

		Map<String, Object> dataMap = festivalService.read(fno, cri);
		dataMap.put("listSort",listSort);
		dataMap.put("page",page);
		model.addAttribute("dataMap", dataMap);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int fno, Model model, String page, String listSort,SearchCriteria cri) throws Exception {
		
		
		FestivalVO festival = festivalService.getFestival(fno);
		model.addAttribute("festival", festival);
		model.addAttribute("page", page);
		model.addAttribute("listSort", listSort);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(FestivalVO festival, MultipartFile[] uploadFile, int[] deleteFile, HttpServletRequest request,String listSort, String page)
			throws Exception {
		festivalService.modify(festival);

		return "redirect:detail?fno=" + festival.getFno() + "&listSort="+listSort + "&page="+page;
	}


	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String removeFestival(int fno) throws Exception {

		festivalService.remove(fno);
		return "redirect:list";
	}
	
	@RequestMapping("/click")
	public String clickVote(FestivalVO festival,int seperate,HttpServletRequest request,String listSort, String page) throws Exception{
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		festivalService.clickVote(festival, loginUser.getId(), seperate);
		return "redirect:detail?fno="+festival.getFno()+"&listSort="+listSort + "&page="+page;
	}
	
	@ResponseBody
	@RequestMapping(value="/reviewList",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> reviewList (String fno){
		ResponseEntity<Map<String,Object>> entity = null;
		
		try {
			
			List<ReviewVO> dataMap = festivalService.getReviewList(Integer.parseInt(fno));
			System.out.println("size="+dataMap.size());
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("dataMap", dataMap);
			data.put("size", dataMap.size());
			entity = new ResponseEntity<Map<String,Object>>(data,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}

	@ResponseBody
	@RequestMapping(value="/togetherList",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> togetherList (String fno){
		ResponseEntity<Map<String,Object>> entity = null;
		try {
			List<TogetherVO> dataMap = festivalService.getTogetherList(Integer.parseInt(fno));
			System.out.println("size="+dataMap.size());
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("dataMap", dataMap);
			data.put("size", dataMap.size());
			entity = new ResponseEntity<Map<String,Object>>(data,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
}
