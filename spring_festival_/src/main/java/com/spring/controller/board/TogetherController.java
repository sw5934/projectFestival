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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.MemberVO;
import com.spring.dto.TogetherVO;
import com.spring.service.TogetherService;

@Controller
@RequestMapping("/together")
public class TogetherController {
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@Autowired
	private TogetherService togetherService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@ModelAttribute("category")
	public String category() throws Exception{
		return "together";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public void listTogether(SearchCriteria cri, Model model, String listSort, String page)throws Exception{
		try {
			if(cri.getSearchType().equals(""))
				cri.setSearchType("tcw");
			if(listSort == null)
				listSort = "tno";
			if(page == null)
				page = "1";
			cri.setListSort(listSort);
			System.out.println(cri.getListSort());
			Map<String, Object> dataMap = togetherService.getList(cri);
			
			dataMap.put("listSort", listSort);
			dataMap.put("page", page);
			dataMap.put("cri", cri);
			
			model.addAttribute("dataMap", dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value= "/togetherRegist", method=RequestMethod.GET)
	public void registGET(Model model) throws Exception {
		TogetherVO together = new TogetherVO();		

		together.setUnq_Id(togetherService.getNextUnq_Id());
		model.addAttribute("together",together);
	}
	
	@RequestMapping(value="/togetherRegist", method=RequestMethod.POST)
	public String registPOST(TogetherVO together, HttpServletRequest request, String id, String unq_Id, int articleStatus) throws Exception{
		
		File origin = new File(request.getServletContext().getRealPath("/resources/uploadImg/") + together.getT_writer() + "\\" + unq_Id + ".jpg");
		if(!(origin.exists())) {
			File path = new File(request.getServletContext().getRealPath("/resources/uploadImg/") + together.getT_writer());
						
			if(!(path.exists())) {
				path.mkdirs();
			}
			File newFile = new File(request.getServletContext().getRealPath("/resources/images/defaultImg.jpg"));
			
			String thumbnailFileName = request.getServletContext().getRealPath("/resources/uploadImg/") + together.getT_writer() + "\\" + unq_Id + ".jpg";
			
			InputStream fis = null;
			OutputStream fos = null;
			try {
				fis = new FileInputStream(newFile);
				fos = new FileOutputStream(thumbnailFileName);
				
				byte[] buffer = new byte[1024];
				
				int length;
				
				while ((length = fis.read(buffer))>0) {
					fos.write(buffer, 0, length);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				fis.close();
				fos.close();
			}
		}
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		together.setT_writer(loginUser.getId());
		together.setNickname(loginUser.getNickName());
		together.setArticleStatus(articleStatus);
		
		
		System.out.println(together.toString());
		togetherService.regist(together);
		
		return "redirect:list";
	}
	
	@RequestMapping("/detail")
	public void detail(HttpServletRequest request, TogetherVO together, int tno, Model model, String page, String listSort, SearchCriteria cri) throws Exception{

		if(listSort==null)
			listSort="tno";
		if(page==null)
			page="1";
		
		Map<String, Object> dataMap = togetherService.read(tno, cri);
		dataMap.put("listSort", listSort);
		dataMap.put("page", page);
		model.addAttribute("dataMap",dataMap);
		
		
		together.getArticleStatus();
		//memberDAO.selectMemberByID(together.getT_writer());
		
		
	}
	@RequestMapping("/deadLine")
	public String deadLine(String tno, String t_state, String page, String listSort, SearchCriteria cri) throws SQLException{
		
		if(t_state.equals("1")) {
			t_state="2";}
		else {
			t_state="1";}
		
		togetherService.deadLineUpdate(tno,t_state);
		
		return "redirect:/together/detail?tno="+tno+"&page="+page+"&listSort="+listSort;
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void modifyGET(int tno, Model model, String page, String listSort, SearchCriteria cri)throws Exception{
		
		TogetherVO together = togetherService.get(tno);
		model.addAttribute("together", together);
		model.addAttribute("page", page);
		model.addAttribute("listSort", listSort);		
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modifyPOST(TogetherVO together, HttpServletRequest request, String page, String listSort, int articleStatus)throws Exception{
		
		together.setArticleStatus(articleStatus);
		togetherService.modify(together);		
		return "redirect:detail?tno=" + together.getTno() + "&listSort=" + listSort + "&page=" + page;
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.GET)
	public String removeTogether(int tno) throws Exception{
		togetherService.remove(tno);
		return "redirect:list";
	}
}
