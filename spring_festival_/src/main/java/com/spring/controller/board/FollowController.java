package com.spring.controller.board;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.MemberVO;
import com.spring.service.FollowService;

@Controller
@RequestMapping("/follow")
public class FollowController {
	@Resource(name="followService")
	private FollowService followService;
	
	
	
	// page, perPageNum을 받아냄.
	// 
	@RequestMapping(value="/followList")
	public ModelAndView myFollow(ModelAndView model,
							Second_Criteria cri,
							HttpServletRequest request,
							HttpServletResponse response) throws SQLException {
		System.out.println("FollowController.myFollow() 시작");
		
		System.out.println("FollowController.myFollow,  Criteria(1,1,5) 호출");
		cri = new Second_Criteria(1,1,5);
		System.out.println("FollowController.myFollow,  Criteria(1,1,5) 복귀\n");
		
		
		
		
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		cri.setStr(loginUser.getId());
		System.out.println("로그인 아이디 : "+cri.getStr());
		
		
		if( (null != request.getParameter("first_page")) && (null != request.getParameter("second_page"))) {			
			cri.setFirst_page(Integer.parseInt(request.getParameter("first_page")));
			cri.setSecond_page(Integer.parseInt(request.getParameter("second_page")));
		}
		

		
		
		cri.setPerPageNum(5);

		System.out.println("FollowController -  followService.followList(cri) 호출");
		dataMap = followService.followList(cri);
		System.out.println("FollowController -  followService.followList(cri) 복귀\n");
	
		
		
		// dataMap = followList <- follow, followed, pageMaker, pageMakered
		
		System.out.println("-------- Result ---------");
		System.out.println("follow = " + dataMap.get("follow"));
		System.out.println("followed = " + dataMap.get("followed"));
		System.out.println("pageMaker = " + dataMap.get("pageMaker"));
		System.out.println("pageMakered = " + dataMap.get("pageMakered"));

		
		
		

		System.out.println("FollowController - dataMap = " + dataMap);
	
		
		model.addObject("dataMap",dataMap);		
		model.setViewName("/follow/followList");

		System.out.println("FollowController.myFollow() 종료");
		return model;
	}
	
	
	
	
	
	
	@RequestMapping(value="/f_write")
	public String f_write(Model model, Second_Criteria cri,
							HttpServletRequest request,
							HttpServletResponse response) throws SQLException {
		System.out.println("FollowController.f_write() 시작");
		cri = new Second_Criteria(1,1,5);
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
	@RequestMapping("/unFollow")
	public ResponseEntity<String> unFollow(@RequestBody Map<String, String> strMap
											) throws SQLException {
		System.out.println("FollowController.unFollow() 시작");
		System.out.println("FollowController.unFollow(),   넘어온 데이터 nickName = " + (String)strMap.get("nName"));
		System.out.println("FollowController.unFollow(),   넘어온 데이터 nickName = " + (String)strMap.get("myId"));
		
		String follower_Nick = (String)strMap.get("nName");
		String myId = (String)strMap.get("myId");
		
		
		
		
		followService.unFollow(follower_Nick, myId);
		
		
		
		
		ResponseEntity<String> responseEntity = null; 
		
		try {
//			responseEntity = new ResponseEntity(menuList, HttpStatus.OK);			
		} catch (Exception e) {
//			responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		} 
		
		return responseEntity;
	}
	
}
