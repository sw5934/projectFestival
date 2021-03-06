<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="review" value="${dataMap.review }" />
<c:set var="festival" value="${dataMap.festival }" />
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<!DOCTYPE html>
<html>

<head>
   
    <meta charset="UTF-8">
    <title>축제 게시판</title>
</head>

<body>

 <style>
        .reviewHeaderSort {
            font-family: bamin-hanna-Pro;
            font-size: 1.4em;
            text-align: center;
            border-right: 3px solid darkslategrey;

            padding: 0px
        }


        a:link {
            color: darkslategrey;
            text-decoration: none;
        }

        a:visited {
            color: darkslategrey;
            text-decoration: none;
        }

        a:hover {
            color: darkorange;
            text-decoration: none;
        }

        .reviewRegist {
            font-family: bamin-hanna-Pro;
            font-size: 1.2em;
            line-height: inherit;
            padding: 5px 15px;
            border: 1px solid darkslategrey;
            border-radius: 3%;
        }

        table {
            border-collapse: separate;
            border-spacing: 0 10px;
        }
    </style>
    
    
    
    
   
    <div class="mt-3 col-10" style="margin:0 auto">
    <div class="col-12" style="border-bottom: 3px solid black; font-family: bamin-hanna-Pro; font-size: 1.2em;">
	    <div class="float-sm-left col-3">${review.rno}</div>
	    <div class="float-sm-left col-6">${review.r_title }</div>
	    <div class="float-sm-left col-3" style="">
	    	<a class="float-sm-left" style="width:30%;">작성자 :</a>
	        <div class="baminfont-Air float-sm-left" style="font-size:1em; position:relative;width:70%;">
		    	<a class="user_Box" onclick="userBox($(this))">${review.nickname }</a>
		    </div>
        </div>
	     
	    	
    </div>
    <div class="col-12" style="border-bottom: 3px solid black; overflow:hidden; font-family: bamin-hanna-Pro; font-size: 1.1em;">
	    <div class="float-sm-left col-9"> 축제명 : <a href="<%=request.getContextPath()%>/festival/detail?fno=${festival.fno }">${festival.f_name }</a></div>
	    <div class="float-sm-left col-3"> 
	    <fmt:formatDate value="${review.r_regDate }" pattern="MM-dd HH:mm"/></div>
    </div>
    
    <div class="col-12" style="overflow:hidden;">
	    <a class="float-sm-right" id="score">평점 : </a>
    </div>
    
    
    <div class="col-12 pl-2 pr-2" style="">
	    <div style="font-size:1.2em; padding: 10px;min-height: 200px; margin-top: 15px;">${review.r_content }</div>
    </div>
    
    <div class="col-12" align="center" style="overflow:hidden">
    	<div class="col-6" style="float: left; text-align: center; cursor:pointer; margin:0 auto;"> 		
<div class="float-sm-right">
    <c:if test="${loginUser.id != null }">
   				<a class="baminfont-Air float-sm-right m-1" id="r_LikeCnt" onclick="like_update()" style="font-size:1.2em">${review.r_like }</a>
   				<a class="baminfont-Air float-sm-right m-1" style="font-size:1.2em" onclick="like_update()"> 좋아요 </a>
   				<c:if test="${dataMap.history==0}">
   				<img class="float-sm-right m-1" onclick="like_update()" src="/festival/resources/bootstrap/plugins/cm/unlike.png" style="width: 20px; float: left" id="like_update">
   				</c:if>
   				<c:if test="${dataMap.history!=0}">
   				<img class="float-sm-right m-1" onclick="like_update()" src="/festival/resources/bootstrap/plugins/cm/like.png" style="width: 20px; float: left" id="like_update">
   				</c:if>
   				
   			</c:if> 
   		</div>	</div>
    </div>
    
    
    <div class="col-12 mb-2" style="height: 40px; border-bottom: 3px solid black; overflow:hidden; justify-content: space-around;">
	    <a class="" style="width: 25%; float: left; text-align: center; cursor: pointer;" href="list?page=${dataMap.page}&listSort=${dataMap.listSort}"><img src="<%=request.getContextPath()%>/resources/images/document.png" style="width:20px;">&nbsp;목록</a>	    
   	   		
  		<div class="" style="width: 25%; float: left; text-align: center;"><a href="<%=request.getContextPath()%>/manage/doReport?unq_id=${review.unq_Id}&no=${review.rno}&page=${dataMap.page}&listSort=${dataMap.listSort}&originCategory=${category}"><img src="<%=request.getContextPath()%>/resources/images/reportIcon.png" style="width:25px;">신고</a></div>	     	  		
   	 	<div class="" id="modifyContentBtn" style="width: 25%; float: left; text-align: center; cursor: pointer;" onclick="onModify();"><img src="<%=request.getContextPath()%>/resources/images/modify.png" style="width:30px;">수정</div> 
   		<div class="" id="deleteContentBtn" style="width: 25%; float: left; text-align: center; cursor: pointer;" onclick="onRemove();"><img src="<%=request.getContextPath()%>/resources/images/delete.png" style="width:20px;">삭제</div>
    	
    </div>
    
    </div>
    <!-- 댓글달기 -->
<div class="card col-10" style="margin:0 auto">
					<div class="card-header">
						<div class="box box-success">
							<div class="box-header">
								<h4 class="box-title">댓글 작성</h4>
							</div>
							<div class="box-body">
								<input class="form-control" type="hidden"
										id="newC_Writer" value="${loginUser.id }">
								<input class="form-control" type="hidden"
										id="newC_Id" value="${loginUser.nickName}">
								<input class="form-control" type="text" placeholder="댓글을 입력하세요."
										id="newC_Content">
							</div>
							<div class="box-footer">
								<br/>
								<button type="button" class="btn btn-primary"
										id="commentsAddBtn">댓글 작성</button>
							</div>
						</div>
					</div>
					<div class="card-body">
						<!-- The time line -->
						<ul class="timeline color-palette-set">
							<!-- timeline time label -->
							<li class="bg-green color-palette row" id="commentsDiv">
								<span class="col-sm-12">댓글 목록</span>
							</li>

						</ul>
					</div>
					<div class="card-footer">
						<div class="text-center">
							<ul id="pagination" class="pagination pagination-sm no-margin">

							</ul>
						</div>
					</div>	
				</div>				
			<!-- end col-md-12 -->
	


<script>
function onModify(){ 
	if("${loginUser.id eq review.id }"){
	self.location.href="<%=request.getContextPath()%>/review/modify?rno=${review.rno}&listSort=${dataMap.listSort}&page=${dataMap.page}";
	return;}
	alert("수정할 수 없습니다.")
}
function onRemove(){
	if("${loginUser.id eq review.id }"){
	self.location.href="<%=request.getContextPath()%>/review/remove?rno=${review.rno}";	
	return;}
	alert("삭제할 수 없습니다.")
}
</script>

<%@ include file="./detail_js.jsp" %>		
</body>

</html>