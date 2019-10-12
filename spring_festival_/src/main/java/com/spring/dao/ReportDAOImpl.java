package com.spring.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.ReportVO;
import com.spring.dto.Report_CVO;

public class ReportDAOImpl implements ReportDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	@Override
	public List<ReportVO> selectReportList(SearchCriteria cri) throws SQLException {
		int offSet = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBound = new RowBounds(offSet,limit);
		
		List<ReportVO> reportList = session.selectList("Utils-Mapper.selectReportList",cri,rowBound);
		return reportList;
	}
	

	@Override
	public int selectReportListCount(SearchCriteria cri) throws SQLException {
		return session.selectList("Utils-Mapper.selectReportList",cri).size();
	}
	
	
	@Override
	public void doReport(String reporter, int unq_id) throws SQLException {
		Map<String, Object> reportInfo = new HashMap<String, Object>();
		
		reportInfo.put("reporter", reporter);
		reportInfo.put("unq_id", unq_id);
		
		session.update("Utils-Mapper.doReport",reportInfo);
	}
	@Override
	public void deleteReport(int unq_id) throws SQLException {
		session.update("Utils-Mapper.deleteReport",unq_id);
	}
	@Override
	public List<Report_CVO> selectReportCommentList(SearchCriteria cri) throws SQLException {
		int offSet = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBound = new RowBounds(offSet,limit);
		
		List<Report_CVO> reportList = session.selectList("Utils-Mapper.selectReportCommentList",cri,rowBound);
		return reportList;
	}
	@Override
	public int selectReportCommentListCount(SearchCriteria cri) throws SQLException {
		return session.selectList("Utils-Mapper.selectReportCommentList",cri).size();
	}
	@Override
	public void doReportComment(String reporter, int c_no) throws SQLException {
		Map<String, Object> reportInfo = new HashMap<String, Object>();
		
		reportInfo.put("reporter", reporter);
		reportInfo.put("c_no", c_no);
		
		session.update("Utils-Mapper.doReportComment",reportInfo);
		
	}
	@Override
	public void deleteReportComment(int c_no) throws SQLException {
		session.update("Utils-Mapper.deleteReport",c_no);
		
	}
}
