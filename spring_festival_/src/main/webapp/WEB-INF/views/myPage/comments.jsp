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
<div id="wrapper" style="margin-left: 15px; margin-right: 15px; position:relative;">
	<div class="col-11 mt-3" style="padding: 0px; overflow:hidden; width: 100%; height:40px;"> 
	<div class="float-sm-left" style="padding: 0px; top: 0px; width: 100%;">
	<div class="col-3 float-sm-left" style="padding: 0px; border-right:3px solid black; text-align:center; font-size:1.4em">
	<a class="baminfont-Pro" href="<%=request.getContextPath()%>/myPage/holding" style="color: black;">축제</a></div>
	<div class="col-3 float-sm-left" style="padding: 0px; border-right:3px solid black; text-align:center; font-size:1.4em">
	<a class="baminfont-Pro" href="<%=request.getContextPath()%>/myPage/review" style="color: black;">후기</a></div>
	<div class="col-3 float-sm-left" style="padding: 0px; border-right:3px solid black; text-align:center; font-size:1.4em">
	<a class="baminfont-Pro" href="<%=request.getContextPath()%>/myPage/together" style="color: black;">같이가요</a></div>
	<div class="col-3 float-sm-left" style="padding: 0px; text-align:center; font-size:1.4em;background: #E4E4E4;">
	<a class="baminfont-Pro" href="<%=request.getContextPath()%>/myPage/comments" style="color: black;">댓글</a></div></div>
	</div>
	<div class="" style="border: 1px solid #D8D8D8; height: 327px;">
		<div class="" style="margin:0px 15px 5px 15px;height:290px;">
			<div class="" >
				<table class="" style="width: 100%;">
					<thead>	
					<tr align="center" style="border-bottom:2px solid lightgrey;height:40px;">
						<th>카테고리</th>
						<th>제  목</th>
						<th>댓  글</th>
						<th>작성일</th>
					</tr>
					</thead>
					<tbody>
					<c:if test="${empty commentsList }">
						<tr>
							<td colspan="5" class="text-center">
								<strong>회원님이 작성한 댓글이 없습니다.</strong>
							</td>
						</tr>
					</c:if>
					<c:if test="${!empty commentsList }">
						<c:forEach items="${commentsList }" var="tuple">
							<tr align="center" style="border-bottom:1px solid lightgrey;">
								<td width="20%" style="margin: 5px;padding: 0;">${tuple.category }</td>
								<td width="30%" style="margin: 5px;padding: 0;">
									<a style="cursor: pointer;" onclick="window.open('
										<c:if test="${tuple.category == '후기' }">
											<%= request.getContextPath()%>/review/detail/?rno=${tuple.bno }
										</c:if>
										<c:if test="${tuple.category != '후기' }">#</c:if>')">
										<u>${tuple.title }</u>
									</a>
								</td>
								<td width="30%" style="margin: 5px;padding: 0;">${tuple.comment }</td>
								<td width="20%" style="margin: 5px;padding: 0;">${tuple.regDate }</td>
							</tr>
						</c:forEach>
					</c:if>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="clearfix">
			<div id="text-center">
				<!-- ul태그의 클래스 : 오른쪽 정렬 및 float:left설정 -->
				<ul class="pagination pagination-sm" style="justify-content: center; margin-left: auto; margin-right: auto;">
					<li class="page-item">
						<a class="page-link" style="border: 0px; color: black; background: none;" href="#">&lt;&lt;</a>
					</li>
					<li class="page-item">
						<a class="page-link" style="border: 0px; color: black; background: none;" href="#">&lt;</a>
					</li>
					<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
					<li class="page-item <c:out value="${pageMaker.cri.page == pageNum ? 'active' : '' }"/>">
						<a class="page-link" style="border: 0px; color: black; background: none;" href="comments${pageMaker.makeQuery(pageNum) }">${pageNum }</a>
					</li>
					</c:forEach>
					<li class="page-item">
						<a class="page-link" style="border: 0px; color: black; background: none;" href="#">&gt;</a>
					</li>
					<li class="page-item">
						<a class="page-link" style="border: 0px; color: black; background: none;" href="#">&gt;&gt;</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
</body>
</html>