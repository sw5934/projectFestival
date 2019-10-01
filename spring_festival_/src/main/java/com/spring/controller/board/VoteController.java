package com.spring.controller.board;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dto.VoteVO;
import com.spring.service.VoteService;



@Controller
@RequestMapping("/vote")
public class VoteController {
	@Resource(name="voteService")
	private VoteService voteService;
	
	private static final Logger logger = LoggerFactory.getLogger(VoteController.class);
	

	@RequestMapping("/voteList")
	public String voteList(Second_Criteria cri,Model model) throws SQLException {
		String id="id473@festival.com";
		cri.setStr(id);
		
		
		Map<String, Object> voteListMap = voteService.selectVoteList(cri);
		// Map = { "wantGoList", "goAndBackList", "goAndBackPM", "wantGoPM" }
		
		
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
