package com.spring.controller.board;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dto.MemberVO;
import com.spring.service.VoteService;



@Controller
@RequestMapping("/vote")
public class VoteController {
	@Resource(name="voteService")
	private VoteService voteService;
	
	private static final Logger logger = LoggerFactory.getLogger(VoteController.class);
	
	@ModelAttribute("category")
	public String category() throws Exception{
		return "vote";		
	}

	@ModelAttribute("view")
	public String view() throws Exception{
		return "찜 목록";		
	}
	
	

	@RequestMapping("/voteList")
	public String voteList(HttpServletRequest request, HttpServletResponse response,
			Second_Criteria cri,Model model) throws SQLException {
		
		
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		cri.setStr(loginUser.getId());
		
		
		Map<String, Object> voteListMap = voteService.selectVoteList(cri);		
		
		model.addAttribute("voteListMap", voteListMap);
		
		
		
		
		String url="/vote/voteList";
		
		
		
		
		return url;
	}
	
	
	
	
	@RequestMapping("/deleteVote")
	public ResponseEntity<Map<String, Object>> deleteVote(@RequestBody Map<String, Object> strMap)
																throws SQLException {
		System.out.println("VoteController.deleteVote() 시작");
		
		
		System.out.println("VoteController.deleteVote(),     strMap.id = " + strMap.get("id"));
		System.out.println("VoteController.deleteVote(),     strMap.u_id = " + strMap.get("u_id"));
		
		
		voteService.deleteVote(strMap);
		
		
		ResponseEntity<Map<String, Object>> responseEntity = null; 
		
		try {
//			responseEntity = new ResponseEntity(menuList, HttpStatus.OK);			
		} catch (Exception e) {
//			responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		} 
		
		return responseEntity;
	}
}
