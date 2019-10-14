<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="hashTagList" value="${dataMap.festival.hashTagList }" />
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

        .regist {
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
    
    
    <div class="col-12" style="overflow:hidden;">
    	<div class="col-8 float-sm-left">
    		<div class="col-12 baminfont-Pro" style="font-size:1.5em;">기본 정보</div>
    		<div class="col-12 " style="font-size:1.3em;">${festival.f_title }</div>
    		<div class="col-12 " style="font-size:1.3em;">${festival.f_content }</div> 
                         <c:if test="${hashTagList!=null}">
	                         <div class="col-12">
	                         	<c:forEach items="${hashTagList}" var="tag">
	                         		<a>#${tag.hashTag }</a>
	                         	</c:forEach>
	                         </div>
                         </c:if>
    		
    	
    	</div>
    	<div class="col-3 float-sm-right">
    		<div class="col-12 baminfont-Pro" style="font-size:1.5em;background-color:#65ddda;color:white">상세 정보</div>
    		<div class="col-12 baminfont-Air" style="font-size:1.3em">축제명</div>
    		<div class="col-12" style="font-size:0.8em">${festival.f_name }</div>
    		<div class="col-12 baminfont-Air" style="font-size:1.3em">개최지</div>
    		<div class="col-12" style="font-size:0.8em">${festival.f_location1 } ${festival.f_location2}</div>
    		<div class="col-12 baminfont-Air" style="font-size:1.3em">개최기간</div>
    		<div class="col-12" style="font-size:0.8em">${festival.f_period }</div>
            <c:if test="${festival.scoreAvg!=0}">
	    		<div class="col-12 baminfont-Air" style="font-size:1.3em">후기평점</div>
	    		<div class="col-12" style="font-size:0.8em">${festival.scoreAvg }</div>
    		</c:if>
    		<c:if test="${festival.f_ref!=null }">
    		<div class="col-12 baminfont-Air" style="font-size:1.3em">웹사이트 보기</div>
    		<div class="col-12" style="font-size:0.8em"><a href="${festival.f_ref }" target="_black" style="color:blue;">축제 사이트 링크로 이동</a>${festival.f_ref }</div>
    		</c:if>
    	</div>
    
    </div> 
    <div class="col-12 mb-5" style="border-bottom: 3px solid black; overflow:hidden;">
    	<div class="float-sm-left col-3" style="cursor: pointer;">
	   	 	<a href="<%=request.getContextPath() %>/festival/click?fno=${festival.fno}&seperate=1&unq_Id=${festival.unq_Id}&page=${dataMap.page}&listSort=${dataMap.listSort}">가고싶어요 ${festival.vote1}</a></div> 
	   		<div class="float-sm-left col-1" style="cursor: pointer;">
	   	 	<a href="<%=request.getContextPath() %>/festival/click?fno=${festival.fno}&seperate=2&unq_Id=${festival.unq_Id}&page=${dataMap.page}&listSort=${dataMap.listSort}">다녀왔어요 ${festival.vote2}</a></div>
    </div>
    <div class="col-12 mb-5" style="border-bottom: 3px solid black; overflow:hidden;">
	    <a class="float-sm-left col-4" href="list?page=${dataMap.page}&listSort=${dataMap.listSort}" style="cursor: pointer;">목록</a>	    
	  		<div class="float-sm-left col-1"><a href="<%=request.getContextPath()%>/manage/doReport?unq_id=${festival.unq_Id}&no=${festival.fno}&page=${dataMap.page}&listSort=${dataMap.listSort}&originCategory=${category}">신고</a></div>	    	  		
	   	 	<div class="float-sm-left col-3" id="modifyContentBtn" style="cursor: pointer;" onclick="onModify();">수정</div>  
	   		<div class="float-sm-left col-1" id="deleteContentBtn" style="cursor: pointer;" onclick="onRemove();">삭제</div>
    	
    </div>
    <div class="col-11">
    <div class="col-10 mb-3 pb-3" style="margin:0 auto;overflow:hidden">
	    <div class="col-6 p-0 float-left" align="center">
	    	<label id="reviewLabel" class="baminfont-Pro" style="width:85%; font-size:1.4em;" onclick="loadReview()">후기</label>
	    	<button id="reviewBtn" hidden="true" class="regist ml-2" onclick="location.href='<%=request.getContextPath()%>/review/reviewRegist?fno=${festival.fno}&f_name=${festival.f_name}'">글 작성</button>
	    </div>
	    <div class="col-6 p-0 float-left" align="center">
	    	<label id="togetherLabel" class="baminfont-Pro" style="width:85%; font-size:1.4em;" onclick="loadTogether()">같이 가요</label>
	    	<button id="togetherBtn" hidden="true" class="regist ml-2" onclick="location.href='<%=request.getContextPath()%>/together/togetherRegist?fno=${festival.fno}&f_name=${festival.f_name}'">글 작성</button>
	    </div>
    </div>
    	<div id="relateBrd" style="margin-bottom: 20px;">
    	
    	 
    	
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
										id="newC_Writer" value="${loginUser.id}">
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
function onModify(){ 
	if("${loginUser.id eq festival.f_writer }"){
	self.location.href="<%=request.getContextPath()%>/festival/modify?fno=${festival.fno}&listSort=${dataMap.listSort}&page=${dataMap.page}";
	return;}
	alert("수정할 수 없습니다.")
}
function onRemove(){
	if("${loginUser.id eq festival.f_writer }"){
	self.location.href="<%=request.getContextPath()%>/festival/remove?fno=${festival.fno}";	
	return;}
	alert("삭제할 수 없습니다.")
}
</script>

<%@ include file="./detail_js.jsp" %>		
</body>

</html>