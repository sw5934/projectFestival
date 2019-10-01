package com.spring.controller.board;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource(name="boardService")
	private BoardService bs;
	
	
	@RequestMapping("/review")
	public ModelAndView myReviewList(ModelAndView modelnView, Second_Criteria cri,
			HttpServletRequest request,	HttpServletResponse response) throws SQLException {
		System.out.println("BoardController.reviewBoard() 시작");
		
		
		String id = "id260@festival.com";
		cri.setStr(id);
		

		
		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap = bs.myReviewList(cri);   // dataMap { myReviewList, 
										  //           pageMaker { cri.getStr() } 
										  //		 }
		
		
		

		modelnView.addObject("dataMap",dataMap);		
		modelnView.setViewName("/board/review");
		
		System.out.println("BoardController.reviewBoard() 종료");
		return modelnView;
	}
	
}
