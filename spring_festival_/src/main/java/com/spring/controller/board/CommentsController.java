package com.spring.controller.board;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.CommentsVO;
import com.spring.service.CommentsService;

@RestController
@RequestMapping("/comments")
public class CommentsController {
	
	@Autowired
	private CommentsService service;
	
	@RequestMapping(value = "/{unq_Id}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("unq_Id") int unq_Id, @PathVariable("page") int page) throws Exception{
		ResponseEntity<Map<String, Object>> entity = null;
		
		Criteria cri = new Criteria();
		cri.setPage(page);
		
		try {
			Map<String, Object> dataMap = service.getCommentsList(unq_Id, cri);
			
			entity = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody CommentsVO comments, String unq_Id) throws Exception{
		
		ResponseEntity<String> entity = null;
		try {
			service.registComments(comments);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping(value = "/{c_no}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("c_no") int c_no,
										 @RequestBody CommentsVO comments) throws Exception{
		ResponseEntity<String> entity = null;
		
		comments.setC_no(c_no);
		try {
			service.modifyComments(comments);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping(value = "/{c_no}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("c_no") int c_no) throws Exception{
		
		ResponseEntity<String> entity = null;
		try {
			service.removeComments(c_no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return entity;
	}

}
