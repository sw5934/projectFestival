<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="together" value="${dataMap.together }" />
<c:set var="festival" value="${dataMap.festival }" />
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<!DOCTYPE html>
<html>

<head>
   
    <meta charset="UTF-8">
    <title>같이가요</title>
</head>

<body>

<style>
        .togetherHeaderSort {
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

        .togetherRegist {
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
	    <div class="float-sm-left col-3">${together.tno}</div>
	    <div class="float-sm-left col-6">${together.t_title }</div>
	    <div class="float-sm-left col-3">작성자 : ${together.nickname }</div>
    </div>
    <div class="col-12" style="border-bottom: 3px solid black; overflow:hidden;">
	     <div class="float-sm-left col-9"> 축제명 : ${festival.f_name }</div>
	    <div class="float-sm-left col-3">
	    <fmt:formatDate value="${together.t_regDate }" pattern="MM-dd HH:mm"/></div>
    </div>   
    
    <div class="col-12 pl-5 pr-5">
   	 <c:if test="${together.t_writer == loginUser.id }">
    	<c:if test="${together.articleStatus == 1 }">
    		<div>생년월일 : ${loginUser.birth }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 성별 : ${loginUser.sex }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 지역 : ${loginUser.location1 }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 성향 : ${loginUser.prtPattern }</div>
		</c:if>	 
		<c:if test="${together.articleStatus != 1 }">
			<div></div>
		</c:if>
	 </c:if>  
	  
	    <div class="col-12 pl-5 pr-5" style="min-height: 200px; margin-top: 15px;">${together.t_content }</div>
    </div>
    
    
    <div class="col-12 mb-5" style="border-bottom: 3px solid black; overflow:hidden;">
	    <a class="float-sm-left col-4" href="list?page=${dataMap.page}&listSort=${dataMap.listSort}" style="cursor: pointer;">목록</a>	    
	   		<c:if test="${loginUser.id eq together.t_writer }">
	   			<a class="float-sm-left col-1" id="deadlineBtn" style="cursor: pointer;" onclick="onDeadline()"
	   			 href="<%=request.getContextPath()%>/together/deadLine?t_state=${together.t_state}&tno=${together.tno}&listSort=${dataMap.listSort}&page=${dataMap.page}"
	   			><img src="<%=request.getContextPath()%>/resources/images/deadLine${together.t_state}.png"></a>
	   		</c:if>
	   		
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
								<h3 class="box-title">댓글목록</h3>
							</div>
							<div class="box-body">
								<input class="form-control" type="hidden"
										id="newC_Writer" value="chun@naver.com">
								<label for="newC_Content">댓글 달기</label>
								<input class="form-control" type="text" placeholder="댓글을 입력하세요."
										id="newC_Content">
							</div>
							<div class="box-footer">
								<br/>
								<button type="button" class="btn btn-primary"
										id="commentsAddBtn">댓글 추가</button>
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
	if("${loginUser.id eq together.t_writer }"){
	self.location.href="<%=request.getContextPath()%>/together/modify?tno=${together.tno}&listSort=${dataMap.listSort}&page=${dataMap.page}";
	return;
	}
	alert("수정할 수 없습니다.")
}
function onRemove(){
	if("${loginUser.id eq together.t_writer }"){
	self.location.href="<%=request.getContextPath()%>/together/remove?tno=${together.tno}";	
	return;
	}
	alert("삭제할 수 없습니다.")
}
</script>

<%@ include file="./detail_js.jsp" %>		
</body>

</html>