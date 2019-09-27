package com.spring.controller.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.MemberVO;
import com.spring.dto.MessageVO;
import com.spring.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	private static final Logger logger=	LoggerFactory.getLogger(MessageController.class);
	
	@RequestMapping("/sendList")
	public void sendList(SearchCriteria cri, Model model ,HttpServletRequest request)throws Exception{
		cri.setPerPageNum(15);
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		Map<String,Object> data = messageService.MessageListFromSender(cri, loginUser.getId());
		model.addAttribute("data",data);
	}

	@RequestMapping("/receiveList")
	public void receiveList(SearchCriteria cri, Model model ,HttpServletRequest request)throws Exception{
		cri.setPerPageNum(15);
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		Map<String,Object> data = messageService.MessageListFromReceiver(cri, loginUser.getId());
		model.addAttribute("data",data);
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public void readMessage(Model model ,int m_no)throws Exception{
		
		MessageVO message = messageService.MessageDetail(m_no);
		messageService.updateCheck(m_no);
		model.addAttribute("message",message);
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
		return "redirect:/message/detail?m_no="+message.getM_no();
	}
}
