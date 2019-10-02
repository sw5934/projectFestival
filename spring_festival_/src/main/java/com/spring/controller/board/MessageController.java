package com.spring.controller.board;

import java.sql.SQLException;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dto.MemberVO;
import com.spring.dto.MessageVO;
import com.spring.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
 
	@ModelAttribute("category")
	public String category() throws Exception{
		return "message";		
	}
	@ModelAttribute("categoryMain")
	public String categoryMain() throws Exception{
		return "receiveList";		
	}

	@ModelAttribute("view")
	public String view() throws Exception{
		return "쪽지";		
	}
	@Autowired
	private MessageService messageService;
	
	private static final Logger logger=	LoggerFactory.getLogger(MessageController.class);
	
	@RequestMapping("/sendList")
	public void sendList(SearchCriteria cri, Model model, String page,HttpServletRequest request)throws Exception{
		cri.setPerPageNum(10);
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		Map<String,Object> data = messageService.MessageListFromSender(cri, loginUser.getId());
		model.addAttribute("data",data);

		if(page==null) {
			page="1";
		}
		model.addAttribute("page",page);
	}

	@RequestMapping("/receiveList")
	public void receiveList(SearchCriteria cri, Model model , String page,HttpServletRequest request)throws Exception{
		cri.setPerPageNum(10);
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		Map<String,Object> data = messageService.MessageListFromReceiver(cri, loginUser.getId());
		model.addAttribute("data",data);
		
		if(page==null) {
			page="1";
		}
		model.addAttribute("page",page);
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String readMessage(Model model ,int m_no,int pageNum,HttpServletRequest request)throws Exception{
		HttpSession session = request.getSession();
		String loginUser = ((MemberVO)session.getAttribute("loginUser")).getId();

		MessageVO message = messageService.MessageDetail(m_no);

		if(message.getM_receiver_Id().equals(loginUser)||message.getM_sender_Id().equals(loginUser)) {
			if(message.getM_receiver_Id().equals(loginUser)) {
				messageService.updateCheck(m_no);}
	
			model.addAttribute("message",message);
			model.addAttribute("pageNum",pageNum);
			return "message/detail";}
		else {
			return "redirect:/message/sendList";}
	}
	
	@RequestMapping("/senderDelete")
	public String senderDelete(int m_no)throws Exception{
		messageService.deleteFromSender(m_no);
		return "redirect:/message/sendList";
	}
	@RequestMapping("/receiverDelete")
	public String receiverDelete(int m_no)throws Exception{
		messageService.deleteFromReceiver(m_no);
		return "redirect:/message/receiveList";
	}

	@RequestMapping(value="/sendMessage", method=RequestMethod.GET)
	public void sendMessageGET(Model model, String m_sender_Nick)throws Exception{
		model.addAttribute("sender", m_sender_Nick);
	}
	
	@RequestMapping(value="/sendMessage", method=RequestMethod.POST)
	public String sendMessagePOST(MessageVO message)throws Exception{
		System.out.println(message.toString());
		message.setM_no(messageService.getNextMno());
		messageService.sendMessage(message);
		return "redirect:/message/detail?m_no="+message.getM_no()+"&pageNum=1";
	}
	
	@ResponseBody
	@RequestMapping("/checkNick")
	public ResponseEntity<String> checkNick(String nickname) throws SQLException {
		int nick = messageService.searchNickname(nickname);
		System.out.println("nick="+nick);
		ResponseEntity<String> entity = null;
		
		if(nick>0) {
			entity = new ResponseEntity<String>("",HttpStatus.OK);
		}
		else{
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
}
