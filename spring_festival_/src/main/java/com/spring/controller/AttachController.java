package com.spring.controller;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dao.AttachDAO;
import com.spring.dto.AttachVO;
import com.spring.utils.DownloadFileUtils;

@Controller
@RequestMapping("/attach")
public class AttachController {
	
	@Autowired
	private AttachDAO attachDAO;
	
	@Resource(name = "uploadPath")
	private String a_uploadPath;
	
	@RequestMapping("/get")
	public ResponseEntity<byte[]> getAttach(int a_no) throws Exception{
		
		ResponseEntity<byte[]> entity = null;
		
		AttachVO attach = attachDAO.selectAttachByA_no(a_no);
		String fileName = attach.getA_uuid()+ "$$" + attach.getA_filename();
		String filePath = attach.getA_uploadPath();
		
		filePath = a_uploadPath + filePath + File.separator + fileName;
		
		return DownloadFileUtils.download(filePath);
	}
	
	@RequestMapping("/thumb")
	public ResponseEntity<byte[]> getThumbnail(int a_no) throws Exception{
		
		ResponseEntity<Byte[]> entity = null;
		
		AttachVO attach = attachDAO.selectAttachByA_no(a_no);
		String fileName = "s_" + attach.getA_uuid() + "$$" + attach.getA_filename();
		String filePath = attach.getA_uploadPath();
		
		filePath = a_uploadPath + filePath + File.separator + fileName;
		
		return DownloadFileUtils.download(filePath);
	}
	

}
