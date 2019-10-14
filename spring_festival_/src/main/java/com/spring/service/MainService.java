package com.spring.service;

import java.util.Map;

import com.spring.controller.board.SearchCriteria;

public interface MainService {

	public Map<String, Object> searchThreeBoard(SearchCriteria cri) throws Exception;
}
