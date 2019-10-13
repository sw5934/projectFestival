package com.spring.controller.board;

import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.MemberVO;
import com.spring.service.FollowService;

@Controller
@RequestMapping("/follow")
public class FollowController {
	@Resource(name="followService")
	private FollowService followService;
	

	@ModelAttribute("category")
	public String category() throws Exception{
		return "follow";		
	}

	@ModelAttribute("view")
	public String view() throws Exception{
		return "팔로우 현황";		
	}
	
	@RequestMapping(value="/followList")
	public ModelAndView myFollow(ModelAndView model,
							Second_Criteria cri,
							HttpServletRequest request,
							HttpServletResponse response) throws SQLException {
		cri = new Second_Criteria(1,1,5);
		
		
		
		
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> dataMap = followService.followList(cri);
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		cri.setStr(loginUser.getId());
		
		
		if( (null != request.getParameter("first_page")) && (null != request.getParameter("second_page"))) {			
			cri.setFirst_page(Integer.parseInt(request.getParameter("first_page")));
			cri.setSecond_page(Integer.parseInt(request.getParameter("second_page")));
		}
		
		cri.setPerPageNum(5);
		model.addObject("dataMap",dataMap);		
		model.setViewName("/follow/followList");

		return model;
	}
	
	
	
	
	
	
	@RequestMapping(value="/f_write")
	public String f_write(Model model, Second_Criteria cri,
							HttpServletRequest request,
							HttpServletResponse response) throws SQLException {
		System.out.println("FollowController.f_write() 시작");
		cri = new Second_Criteria(1,1,3);
		response.setContentType("text/html;charset=utf-8");
		
		
		if( (null != request.getParameter("first_page")) && (null != request.getParameter("second_page"))) {				
			cri.setFirst_page(Integer.parseInt(request.getParameter("first_page")));
			cri.setSecond_page(Integer.parseInt(request.getParameter("second_page")));
		}
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		cri.setStr(loginUser.getId());
		System.out.println("로그인 아이디 : "+cri.getStr());

		// 팔로워가 3개의 게시판에 작성한 글들의 리스트 + 가고싶어요 리스트를 반환한다.
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap = followService.f_write(cri);
		// Map = { "three_board_list" , " wantGoList", "wantGoPM", "threeBoardPM" }
		
		System.out.println("!!!!!dataMap : "+dataMap.get("three_board_list").toString());
		
		
		dataMap.put("dataMap", dataMap);
		model.addAttribute("dataMap", dataMap);
		
		
		
		System.out.println("FollowController.f_write() 종료");
		return "/follow/f_write";
	}
	
	
	
	/*
	[ data:JSON.stringify(nName),
	  contentType:'application/json', ]
	 위의 구조를 포함하는 ajax로부터의 요청을 처리하려면 "최대" 아래 조건을 만족해야 한다.
	   1) 리턴타입은 "ResponsEntity"타입으로 작성한다.           -> 최소조건
	   1) "@ResponseBody"를 메서드에 매핑.     -> 최대조건
	   2) "@RequestBody"타입의 매개변수를 작성("data:JSON.stringify(nName)" 데이터를 받아내는 변수).         -> 최소조건
	   3) 요청 측의 각종 데이터를 받아낼 HttpServletRequest request 작성.            -> 최대조건
	*/
	
	@RequestMapping(value="follow",method=RequestMethod.GET)
	public String follow(String follow,String me,String nick) throws Exception{
		
		String nickName= URLEncoder.encode(nick, "UTF-8");
		
		if(followService.checkFollow(me, follow)==0) {
			followService.doFollow(me, follow);
		}else {
			followService.unFollow(follow, me);}
		return "redirect:/member/memInfo?nick="+nickName;
	}
	
	
	
	@RequestMapping("/unFollow")
	public ResponseEntity<String> unFollow(@RequestBody Map<String, String> strMap) throws SQLException {		
		String follower_Nick = (String)strMap.get("nName");
		String myId = (String)strMap.get("myId");
		
		followService.unFollow(follower_Nick, myId);
		
		ResponseEntity<String> responseEntity = null; 
		
		try {		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return responseEntity;
	}
	
}
