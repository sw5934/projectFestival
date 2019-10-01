package com.spring.service;

import java.sql.SQLException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import com.spring.dao.MemberDAO;
import com.spring.dto.MemberVO;

/*@Service("mailService")*/
public class MailService {
	
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Autowired
	 private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
 
    
    public int getMemberByEmail(String email) throws SQLException {
    	
    	int chkemail = memberDAO.selectMemberByEmail(email);
    	
    	return chkemail;
    	
    }
    
    @Async
	public void sendMail(String to, String subject, String body) {
      MimeMessage message = mailSender.createMimeMessage();
      try {
		MimeMessageHelper messageHelper = 
		new MimeMessageHelper(message, true, "UTF-8");
		//messageHelper.setCc("zzzzzz@naver.com");
		messageHelper.setFrom("cmin.festival@gmail.com", "축제의민족");
		messageHelper.setSubject(subject);
		messageHelper.setTo(to); 
		messageHelper.setText(body);
		mailSender.send(message);  
      }catch(Exception e){
		e.printStackTrace();
	  }
	}
 
	@Async
	public void sendPreConfiguredMail(String message) {
	  SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
	  mailMessage.setText(message);
	  mailSender.send(mailMessage);
	}
}

