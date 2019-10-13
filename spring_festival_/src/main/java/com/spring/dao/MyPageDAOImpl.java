package com.spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.Criteria;
import com.spring.controller.board.Second_Criteria;
import com.spring.dto.AuthSettingVO;
import com.spring.dto.CommentsBoardVO;
import com.spring.dto.HoldingVO;
import com.spring.dto.MemSearchVO;
import com.spring.dto.ReviewAndTogetherVO;

public class MyPageDAOImpl implements MyPageDAO {
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<ReviewAndTogetherVO> myReviewList(Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		
		List<ReviewAndTogetherVO> myReviewList = new ArrayList<ReviewAndTogetherVO>();
		myReviewList = session.selectList("MyPage-Mapper.reviewBoard", cri, rowBounds);

		// 각 리뷰글에 대한 댓글 총 갯수를 구한다.		
		for(ReviewAndTogetherVO review : myReviewList) {
			review.setComments((Integer)session.selectOne("MyPage-Mapper.reviewComments", review)) ;	
			System.out.println("댓글갯수(" + review.getUnq_id() +") : " + review.getComments());
		}


		
		
		return myReviewList;
	}

	@Override
	public List<ReviewAndTogetherVO> myTogetherList(Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<ReviewAndTogetherVO> myTogetherList = new ArrayList<ReviewAndTogetherVO>();
		myTogetherList = session.selectList("MyPage-Mapper.togetherBoard", cri, rowBounds);
		
		
		
		// 각 같이가요 글에 대한 댓글 총 갯수를 구한다.
		for(ReviewAndTogetherVO review : myTogetherList) {
			review.setComments((Integer)session.selectOne("MyPage-Mapper.togetherComments", review)) ;	
			System.out.println("댓글갯수(" + review.getUnq_id() +") : " + review.getComments());
		}
		

		
		
		
		return myTogetherList;
	}

	@Override
	public int myReviewTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.myReviewTotalCount", cri);
		return totalCount;
	}

	@Override
	public int myTogetherTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.myTogetherTotalCount", cri);
		return totalCount;
	}
	
	
	
	
	
	
	@Override
	public List<CommentsBoardVO> commentsBoard(Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<CommentsBoardVO> commentsList = new ArrayList<CommentsBoardVO>();
		commentsList = session.selectList("MyPage-Mapper.commentsBoard", cri, rowBounds);
		
		return commentsList;
	}
	
	
	@Override
	public int myCommentsTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.commentsTotalCount", cri);
		
		return totalCount;
	}
	
	
	
	@Override
	public Map<String, Object> holdingList(Second_Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);

		Map<String, List<String>> tagMap = new HashMap<String, List<String>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> cmtMap = new HashMap<String, Object>();
		List<HoldingVO> holdingList = new ArrayList<HoldingVO>();	// 개최글 모든 컬럼을 저장하는 List
		List<String> holdingTitle = new ArrayList<String>();	// 개최글 제목만 저장하는 List
		holdingList = session.selectList("MyPage-Mapper.holdingNoTagList", cri, rowBounds);

		
		int temp = cri.getPerPageNum();
		cri.setPerPageNum(100); // 만약 2인경우에는 쿼리 결과로 해시태그가 2개밖에 조회가 안되서 임시 변경
		int num;
		List<String> tagList;
		for(int i=0; i<holdingList.size(); i++) {
			tagList = new ArrayList<String>();
			cri.setStr2(holdingList.get(i).getTitle());
			tagList.add(session.selectList("MyPage-Mapper.holdingTagList", cri) + "");
			holdingList.get(i).setHashTag(tagList);
		}
		cri.setPerPageNum(temp);
		
		
		
/*
		List<String> tmp = new ArrayList<String>();
		String title = "";
		for(HoldingVO tuple : holdingList) {
			System.out.println("요기요 tuple : " + tuple);
			tuple.setComments((Integer)session.selectOne("MyPage-Mapper.holdingCommentsCount", tuple)); // tuple이 가진 unq_id를 쿼리에 사용한다.
			if( !title.equals(tuple.getTitle()) ) {
				tmp = new ArrayList<String>();
				title = tuple.getTitle();
				tmp.clear();
			}
			tmp.addAll(tuple.getHashTag());
			holdingTitle.add(tuple.getTitle());
			tagMap.put(title, tmp); // 하나의 제목에 대한 여러개의 태그들(List<String>)
			cmtMap.put(tuple.getTitle(), tuple.getComments());
		}
		
		


		// List 0부터 n까지 비교하면서 동일한 스트링이 있으면 삭제.
		// 각 스트링은 고유해진다.
		for(int i=0; i<holdingTitle.size(); i++) {
			for(int x=0; x<holdingTitle.size(); x++) {
				if(holdingTitle.get(i).equals(holdingTitle.get(x))) {
					holdingTitle.remove(x);
				}
			}
		}
		
		

		
		resultMap.put("cmtMap", cmtMap); // Map
		resultMap.put("holdingMap", holdingTitle); // List<String>
		resultMap.put("tagMap", tagMap); // Map
*/		
		
		
		
		resultMap.put("holdingList", holdingList); // List<String>
		return resultMap;
	}
	
	
	
	@Override
	public int holdingTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.holdingTotalCount", cri);
		return totalCount;
	}
	
	
	
	@Override
	public List<AuthSettingVO> authSetting(Second_Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		
		
		List<AuthSettingVO> authSetList = new ArrayList<AuthSettingVO>();
		
		authSetList = session.selectList("MyPage-Mapper.memAuthSet", cri, rowBounds);
		
		int num;
		for(int i=0; i<authSetList.size(); i++) {
			if(authSetList.get(i).getAuth_no()==1) {
				cri.setStr2(authSetList.get(i).getId());
				num = session.selectOne("MyPage-Mapper.blackListPeriod", cri);
				authSetList.get(i).setPeriod(num);
				System.out.println("authSetList(" + i + ")");
			}
		}

		return authSetList;
	}
	
	@Override
	public int authSetTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.authSetTotalCount", cri);
		
		System.out.println("auth : " + totalCount);
		return totalCount;
	}
	
	
	
	
	public void authUpdate(Map<String, String> strMap) throws SQLException {
		session.update("MyPage-Mapper.authUpdate", strMap);
		if(strMap.get("auNo").equals("1")) {
			session.update("MyPage-Mapper.authUpdate2", strMap);
			session.update("MyPage-Mapper.authUpdate3", strMap);
			System.out.println("제재회원 이므로 블랙리스트 추가.");
		} else if(!strMap.get("auNo").equals("1")) {
			session.update("MyPage-Mapper.authUpdate2", strMap);
			System.out.println("제재회원이 아니므로 블랙리스트 기록 제거.");
		}
		
	}
	
	

	@Override
	public List<MemSearchVO> memSearch(Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<MemSearchVO> memSearchList = session.selectList("MyPage-Mapper.MemSearch", cri, rowBounds);
		System.out.println("DAO - searchList.size = " + memSearchList.size());
		return memSearchList;
	}
	
	@Override
	public int memSearchTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("MyPage-Mapper.MemSearchTotalCount", cri);
		return totalCount;
	}
}








