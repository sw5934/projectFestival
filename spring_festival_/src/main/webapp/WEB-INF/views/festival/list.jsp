<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="festivalList" value="${dataMap.festivalList }" />
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="listSort" value="${dataMap.listSort }" />
<c:set var="page" value="${dataMap.page }" />
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
    
    
    <div class="box pt-3 col-10" style="left: 0px; right: 0px; margin-left:auto; margin-right:auto;">
        <a class="col-10 baminfont-Pro mt-1" style="font-size: 1.6em; padding: 0px">축제 게시판
        
        ${Date}

</a>
    </div>
    <div class="col-10 mt-3 reviewHeader" style="overflow: hidden; margin: 0 auto;"> 
        <!-- 정렬 이벤트, 글 작성 이벤트 넣기 -->
         <div class="float-sm-left col-4 mt-1 p-0">
            <button id="newBtn" class="reviewRegist ml-2" onclick="location.href='festivalRegist'">글 작성</button></div>
        <div class="float-sm-left col-2 reviewHeaderSort"><a href="?listSort=fno">최신 일자</a></div>
        <div class="float-sm-left col-2 reviewHeaderSort"><a href="?listSort=f_viewCnt">조회 수</a></div>
        <div class="float-sm-left col-2 reviewHeaderSort" style="border: 0"><a href="?listSort=vote1">가고싶어요</a></div>
        <div class="float-sm-left col-2 reviewHeaderSort" style="border: 0"><a href="?listSort=vote2">다녀왔어요</a></div>
    </div>
    <table class="mt-5 col-10" style=" margin: 0 auto;">
        <c:if test="${empty festivalList }">
            <tr>
                <td colspan="5" class="text-center">
                    <strong>해당 내용이 없습니다.</strong>
                </td>
            </tr>
        </c:if>
        <c:if test="${!empty festivalList }">
            <c:forEach items="${festivalList }" var="festival">
                <tr style="border-bottom: 0px solid black;">
                    <td style="width: 7%;  text-align: center">${festival.fno }</td>
                    <td style="width: 16%;  text-align: center"><img src="<%=request.getContextPath()%>/resources/uploadImg/festival/${festival.f_writer }/${festival.unq_Id }.jpg" style="width:93px;height:70px"></td>
                    <td style="width: 56%;"> 
                    <p>개최기간 [${festival.f_period }]</p>
                        <a href="detail?fno=${festival.fno }&listSort=${listSort}&page=${page}">${festival.f_title } <c:if test="${festival.commentCount>0}">[${festival.commentCount }]</c:if></a>
                        <p><fmt:formatDate value="${festival.f_regDate }" pattern="MM-dd HH:mm"/></p>
                         <p>${festival.f_name }</p>
                         <p>${festival.f_location1}${festival.f_location2 }</p>
                         <p>${festival.f_org}</p>
                         <p>${festival.f_type}</p>
                         <c:if test="${festival.scoreAvg==0}">
                         	<p>후기 없음</p>
                         </c:if>
                         <c:if test="${festival.scoreAvg!=0}">
                         	<p>${festival.scoreAvg}</p>
                         </c:if>
                         
                         
                         <c:if test="${festival.hashTagList!=null}">
	                         <div class="col-12">
	                         	<c:forEach items="${festival.hashTagList}" var="tag">
	                         		<a>#${tag.hashTag }</a>
	                         	</c:forEach>
	                         </div>
                         </c:if>
                         
                    </td>
                    <td style=";width: 25%">
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/like.png" style="width: 10%; float: left">
                            <p>　가고싶어요　${festival.vote1 }</p>
                        </span>
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/like.png" style="width: 10%; float: left">
                            <p>　다녀왔어요　${festival.vote2 }</p>
                        </span>
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/view.png" style="width: 10%; float: left">
                            <p>　조회수　${festival.f_viewCnt }</p>
                        </span>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <div class="mt-3 col-10" style="margin:0 auto">
        <div class="text-center">
            <ul class="pagination">   
            
                <c:if test="${pageMaker.prev }">
                	<li class="page-item">
                    	<a class="page-link" href="list${pageMaker.makeQuery(1)}">&lt;&lt;</a>
                    </li>
                </c:if>
                    
                <c:if test="${pageMaker.prev }"> 
                	<li class="page-item">
                		<a class="page-link" href="list${pageMaker.makeQuery(pageMaker.startPage-1) }">&lt;</a>
                	</li>
                </c:if>
                
                <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
                    <li class="page-item <c:out value=" ${pageMaker.cri.page==pageNum ?'active':''}" />">
                    <a class="page-link" href="list${pageMaker.makeQuery(pageNum) }">
                        ${pageNum }
                    </a>
                    </li>
                </c:forEach>
                
                <c:if test="${pageMaker.next }">
	                <li class="page-item">
	                    <a class="page-link" href="list${pageMaker.makeQuery(pageMaker.endPage+1) }">&gt;
	                    </a>
	                </li>
                </c:if>
                
                 <c:if test="${pageMaker.next }">
	                <li class="page-item">
	                    <a class="page-link" href="list${pageMaker.makeQuery(pageMaker.realEndPage) }">
	                        &gt;&gt;
	                    </a>
	                </li>
                </c:if>
            </ul>
        </div>
    </div>    
</body>

</html>