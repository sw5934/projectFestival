<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set value="${dataMap.pageMaker }" var="pageMaker" />
<c:set value="${dataMap.commentsList }" var="commentsList" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>!!내가 작성한 댓글</title>
</head>
<body>
<div id="wrap">
	<div id="table">	
		<table>
			<tr>
				<td>카테고리</td>
				<td>제  목</td>
				<td>댓  글</td>
				<td>작성일시</td>
			</tr>
			<c:forEach items="${commentsList }" var="tuple">
				<tr>
					<td>${tuple.category }</td>
					<td>
						<a href="
							<c:if test="${tuple.category == '후기' }">
								<%= request.getContextPath()%>/review/detail/?rno=${tuple.bno }
							</c:if>
							<c:if test="${tuple.category != '후기' }">
								#
							</c:if>
						">
							${tuple.title }
						</a>
					</td>
					<td>${tuple.comment }</td>
					<td>${tuple.regDate }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div id="pageing">
		<!-- ul태그의 클래스 : 오른쪽 정렬 및 float:left설정 -->
		<ul class="pagination pagination-sm m-0 float-right">
			<li class="page-item">
				<a class="page-link">&lt;&lt;</a>
			</li>
			<li class="page-item">
				<a class="page-link">&lt;</a>
			</li>
			
			<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
				<li class="page-item <c:out value="${pageMaker.cri.page == pageNum ? 'active' : '' }"/>"'>
					<a class="page-link" href="comments${pageMaker.makeQuery(pageNum) }">${pageNum }</a>
				</li>
			</c:forEach>
			
			
			
			<li class="page-item">
				<a class="page-link">&gt;</a>
			</li>
			<li class="page-item">
				<a class="page-link">&gt;&gt;</a>
			</li>
		</ul>
	</div>	
</div>
</body>
</html>