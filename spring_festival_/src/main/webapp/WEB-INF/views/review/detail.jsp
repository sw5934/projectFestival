<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="review" value="${dataMap.review }" />
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
    <div class="col-12" style="border-bottom: 3px solid black; overflow:hidden;">
	    <div class="float-sm-left col-3">${review.rno}</div>
	    <div class="float-sm-left col-6">${review.r_title }</div>
	    <div class="float-sm-left col-3">${review.id }</div>
    </div>
    <div class="col-12" style="border-bottom: 3px solid black; overflow:hidden;">
	    <div class="float-sm-left col-9">${review.r_title }</div>
	    <div class="float-sm-left col-3">${review.r_regDate }</div>
    </div>
    
    <div class="col-12" style="overflow:hidden;">    
	    <a class="float-sm-right" id="score">평점 : </a>
    </div>
    
    
    <div class="col-12 pl-5 pr-5">
	    <div>${review.r_content }</div>
    </div>
    
    
    <div class="col-12 mb-5" style="border-bottom: 3px solid black; overflow:hidden;">
	    <a class="float-sm-left col-4" href="list?page=${dataMap.page}&listSort=${dataMap.listSort}" style="cursor: pointer;">목록</a>	    
	   		<div class="float-sm-left col-1">
	   			<c:if test="${review.id != null }">
	   				<c:if test="${dataMap.history==0}">
	   				<img src="/festival/resources/bootstrap/plugins/cm/unlike1.png" style="width: 20px; float: left" id="like_update">
	   				</c:if>
	   				<c:if test="${dataMap.history!=0}">
	   				<img src="/festival/resources/bootstrap/plugins/cm/like1.png" style="width: 20px; float: left" id="like_update">
	   				</c:if>
	   				<button id="r_LikeCnt" onclick="like_update()">${review.r_like }</button>
	   			</c:if>
	   		</div>
	  		<div class="float-sm-left col-1">신고</div>	    	  		
	   	 	<div class="float-sm-left col-3" id="modifyContentBtn" style="cursor: pointer;" onclick="onModify();">수정</div> 
	   		<div class="float-sm-left col-1" id="deleteContentBtn" style="cursor: pointer;" onclick="onRemove();">삭제</div>
    	
    </div>
    
    </div>
    <!-- 댓글달기 -->
<div class="card">
					<div class="card-header">
						<div class="box box-success">
							<div class="box-header">
								<h3 class="box-title">ADD NEW REPLY</h3>
							</div>
							<div class="box-body">
								<input class="form-control" type="hidden"
										id="newC_Writer" value="chun@naver.com">
								<label for="newC_Content">Comments Text</label>
								<input class="form-control" type="text" placeholder="COMMENTS TEXT"
										id="newC_Content">
							</div>
							<div class="box-footer">
								<br/>
								<button type="button" class="btn btn-primary"
										id="commentsAddBtn">ADD REPLY</button>
							</div>
						</div>
					</div>
					<div class="card-body">
						<!-- The time line -->
						<ul class="timeline color-palette-set">
							<!-- timeline time label -->
							<li class="bg-green color-palette row" id="commentsDiv">
								<span class="col-sm-12">Comments List</span>
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

</script>
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

