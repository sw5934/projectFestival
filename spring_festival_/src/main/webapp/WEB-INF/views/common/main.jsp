<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="festivalRegist" value="${festivalFromRegist }" />
<c:set var="festivalVote" value="${festivalFromVote }" />
<c:set var="reviewRegist" value="${reviewFromRegist }" />
<c:set var="reviewLike" value="${reviewFromLike }" />

<body>
	<div><img src="<%=request.getContextPath()%>/resources/images/festivalMain.png" style="width:100%"></div>




	<div class="col-11 p-0 mt-2" style="margin:0 auto; overflow:hidden;"> 
		<div class="col-6 p-3 float-sm-left" style="width:48%; border: 1px solid #BBBBBB;">
			<div class="col-12 p-1 m-0" style="border-bottom:1px solid #BBBBBB">
			<a class="baminfont-Air" style="font-size:1.4em" href="<%=request.getContextPath()%>/festival/list?listSort=fno">최근 축제 미리보기</a>
			</div>
			<div class="col-11 " style="overflow:hidden;">
				<c:forEach items="${festivalRegist }" var="regist" begin="1" end="5">
					<div class="col-9 m-0 p-2 float-sm-left" style="font-size:0.8em; text-decoration:none;text-overflow: ellipsis;overflow:hidden;display:block;white-space: nowrap;">
						<a href="<%=request.getContextPath()%>/festival/detail?fno=${regist.fno }" style="color:#444444;">${regist.f_title }</a>
					</div>
					<div class="col-3 m-0 p-2 float-sm-left"><fmt:formatDate value="${regist.f_regDate }"/></div> 
				</c:forEach>
			</div>
		</div>
		
		
		<div class="col-6 p-3 float-sm-right" style="width:48%; border: 1px solid #BBBBBB;">
			<div class="col-12 p-1 m-0" style="border-bottom:1px solid #BBBBBB">
			<a class="baminfont-Air" style="font-size:1.4em" href="<%=request.getContextPath()%>/festival/list?listSort=vote1">사람들이 많이 찾는 축제</a>
			</div>
			<div class="col-11 " style="overflow:hidden;">
				<c:forEach items="${festivalVote }" var="vote" begin="1" end="5">
					<div class="col-9 m-0 p-2 float-sm-left" style="font-size:0.8em; text-decoration:none; text-overflow: ellipsis;overflow:hidden;display:block;white-space: nowrap;">
						<a href="<%=request.getContextPath()%>/festival/detail?fno=${vote.fno }" style="color:#444444;">${vote.f_title }</a>
					</div>
					<div class="col-3 m-0 p-2 float-sm-left"><fmt:formatDate value="${vote.f_regDate }"/></div>
				</c:forEach>
			</div>
		</div>
		 
</div> 



	<div class="col-11 p-0 mt-2" style="margin:0 auto; overflow:hidden;">
		<div class="col-6 p-3 float-sm-left" style="width:48%; border: 1px solid #BBBBBB;">
			<div class="col-12 p-1 m-0" style="border-bottom:1px solid #BBBBBB">
			<a class="baminfont-Air" style="font-size:1.4em" href="<%=request.getContextPath()%>/review/list?listSort=rno">최신 후기</a>
			</div>
			<div class="col-11 " style="overflow:hidden;">
				<c:forEach items="${reviewRegist }" var="regist" begin="1" end="5">
					<div class="col-9 m-0 p-2 float-sm-left" style="font-size:0.8em; text-decoration:none;  text-overflow: ellipsis;overflow:hidden;display:block;white-space: nowrap;">
						<a href="<%=request.getContextPath()%>/review/detail?rno=${regist.rno }" style="color:#444444;">${regist.r_title }</a>
					</div>
					<div class="col-3 m-0 p-2 float-sm-left"><fmt:formatDate value="${regist.r_regDate }"/></div>
				</c:forEach>
			</div>
		</div>
		 
		 
		<div class="col-6 p-3 float-sm-right" style="width:48%; border: 1px solid #BBBBBB;">
			<div class="col-12 p-1 m-0" style="border-bottom:1px solid #BBBBBB">
			<a class="baminfont-Air" style="font-size:1.4em" href="<%=request.getContextPath()%>/review/list?listSort=r_like">인기 후기</a>
			</div>
			<div class="col-11 " style="overflow:hidden;">
				<c:forEach items="${reviewLike }" var="like" begin="1" end="5">
					<div class="col-9 m-0 p-2 float-sm-left" style="font-size:0.8em; text-decoration:none; ellipsis;overflow:hidden;display:block;white-space: nowrap;">
						<a href="<%=request.getContextPath()%>/review/detail?rno=${like.rno }" style="color:#444444;">${like.r_title }</a>
					</div>
					<div class="col-3 m-0 p-2 float-sm-left"><fmt:formatDate value="${like.r_regDate }"/></div>
				</c:forEach>
			</div>
		</div>
		
</div>



</body>	