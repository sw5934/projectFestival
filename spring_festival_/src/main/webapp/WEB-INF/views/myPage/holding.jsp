<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="${dataMap.pageMaker }" var="pageMaker"/>
<c:set value="${dataMap.holdingList }" var="holdingList"/>
<c:set value="${dataMap.page }" var="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>개최자의 게시글 페이지</title>
</head>
<body>
	<div class="col-11 mt-3" style="padding: 0px; overflow:hidden; width: 100%; height:40px;"> 
	<div class="float-sm-left" style="padding: 0px; top: 0px; width: 100%;">
	<div class="col-3 float-sm-left" style="padding: 0px; border-right:3px solid black; text-align:center; font-size:1.4em;background: #E4E4E4;">
	<a class="baminfont-Pro" href="<%=request.getContextPath()%>/myPage/holding" style="color: black;">축제</a></div>
	<div class="col-3 float-sm-left" style="padding: 0px; border-right:3px solid black; text-align:center; font-size:1.4em">
	<a class="baminfont-Pro" href="<%=request.getContextPath()%>/myPage/review" style="color: black;">후기</a></div>
	<div class="col-3 float-sm-left" style="padding: 0px; border-right:3px solid black; text-align:center; font-size:1.4em;">
	<a class="baminfont-Pro" href="<%=request.getContextPath()%>/myPage/together" style="color: black;">같이가요</a></div>
	<div class="col-3 float-sm-left" style="padding: 0px; text-align:center; font-size:1.4em">
	<a class="baminfont-Pro" href="<%=request.getContextPath()%>/myPage/comments" style="color: black;">댓글</a></div></div>
	</div>
	<div class="col-11" style="border: 1px solid #D8D8D8; height: 327px; margin:0 auto">
		<div class="" style="margin:0px 15px 5px 15px;height:290px;">
            <div class="" style="height:290px;">
				<table class="" style="width: 100%;"> <!-- border="solid 1px black" -->
                	<thead>                  
					<tr align="center" style="border-bottom:2px solid lightgrey;height:40px;">
						<th></th>
						<th>축제 제목</th>
						<th>작성일</th>
					</tr>
					</thead>
					<tbody>
					<c:if test="${empty holdingList }">
						<tr>
							<td colspan="5" class="text-center">
								<strong>회원님이 작성한 축제 글이 없습니다.</strong>
							</td>
						</tr>
					</c:if>
					<c:if test="${!empty holdingList }">
						<c:forEach items="${holdingList }" var="hList">
	                    <tr align="center" style="border-bottom:1px solid lightgrey; height: 62px;">
	                    	<td width="150px" style="text-align: center;margin: 5px;padding: 0;"><img src="<%=request.getContextPath()%>/resources/uploadImg/festival/${hList.f_writer }/${hList.unq_Id }.jpg" style="width:auto;height:40px;"></td>
	                      	<td width="400px" align="left" style="margin: 5px;padding: 0;">
	                      		<a style="cursor: pointer;" onclick="window.open('<%= request.getContextPath()%>/festival/detail/?fno=${hList.fno }')">
	                      		<u>${hList.f_title}</u><c:if test="${hList.commentCount !=0}">(${hList.commentCount })</c:if></a><br>
                   				<a>${hList.hashTagString }</a>
	                      	</td>
	                      	<td width="150px" style="margin: 5px;padding: 0;"><fmt:formatDate value="${hList.f_regDate }"/></td>
	                    </tr>
		    			</c:forEach>
		    		</c:if>
					</tbody>
				</table>
            </div>
		</div>

		<div class="clearfix">
			<div class="text-center">															
				<ul class="pagination pagination-sm m-0" style="justify-content: center; margin-left: auto; margin-right: auto;">
					<li class="page-item">
						<a class="page-link" style="border: 0px; color: black; background: none;" href="holding${pageMaker.makeQuery(1)}">&lt;&lt;</a>
					</li>
					<li class="page-item">
						<a class="page-link" style="border: 0px; color: black; background: none;" href="holding
							<c:if test="${pageMaker.prev }">
								${pageMaker.makeQuery(pageMaker.startPage-1) }
							</c:if>
						">&lt;</a>
					</li>
					<c:forEach begin="${pageMaker.startPage }" 
				           end="${pageMaker.endPage }" var="pageNum">
					<li class="page-item <c:out value="${pageMaker.cri.page == pageNum ?'active':''}" />" >
						<a class="page-link" style="border: 0px; color: black; background: none;" href="holding${pageMaker.makeQuery(pageNum) }" >
							${pageNum}
						</a>
					</li>
					</c:forEach>	
				
					<li class="page-item">
						<a class="page-link" style="border: 0px; color: black; background: none;" href="holding
							<c:if test="${pageMaker.next }">
								${pageMaker.makeQuery(pageMaker.endPage+1) }
							</c:if>
							<c:if test="${!pageMaker.next }">
								${pageMaker.makeQuery(pageMaker.cri.page) }
							</c:if>
						">&gt;</a>
					</li>
					<li class="page-item">
						<a class="page-link" style="border: 0px; color: black; background: none;" href="holding${pageMaker.makeQuery(pageMaker.realEndPage) }">
							&gt;&gt;
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>

    <div class="card-body">
	<table class="table table-condensed">
		<tr>
			<th colspan="2" class="text-center">축제 제목</th>
		</tr>
		
		
		<c:if test="${holdingList.size() <= 0}">
			반환 값이 없어 예외 처리 되었습니다.
		</c:if>
	</table>
</div>

<script>
</script>
</body>
</html>