<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="reviewList" value="${dataMap.reviewList }" />
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
    
    
    <div class="box pt-3 col-10" style="left: 0px; right: 0px; margin-left:auto; margin-right:auto;">
        <a class="col-10 baminfont-Pro mt-1" style="font-size: 1.6em; padding: 0px">후기 게시판</a>
    </div>
    <div class="col-10 mt-3 reviewHeader" style="overflow: hidden; margin: 0 auto;">
        <!-- 정렬 이벤트, 글 작성 이벤트 넣기 -->
        <div class="float-sm-left col-6 mt-1 p-0">
            <button id="newBtn" class="reviewRegist ml-2" onclick="location.href='reviewRegist'">글 작성</button></div>
        <div class="float-sm-left col-2 reviewHeaderSort"><a href="?listSort=rno">최신 일자</a></div>
        <div class="float-sm-left col-2 reviewHeaderSort"><a href="?listSort=r_viewcnt">조회 수</a></div>
        <div class="float-sm-left col-2 reviewHeaderSort" style="border: 0"><a href="?listSort=r_like">좋아요</a></div>
    </div>
    <table class="mt-5 col-10" style=" margin: 0 auto;">
        <c:if test="${empty reviewList }">
            <tr>
                <td colspan="5" class="text-center">
                    <strong>해당 내용이 없습니다.</strong>
                </td>
            </tr>
        </c:if>
        <c:if test="${!empty reviewList }">
            <c:forEach items="${reviewList }" var="review">
                <tr style="border-bottom: 0px solid black;">
                    <td style="width: 7%;  text-align: center">${review.rno }</td>
                    <td style="width: 16%;  text-align: center"><img src="<%=request.getContextPath()%>/resources/bootstrap/dist/img/credit/mastercard.png"></td>
                    <td style="width: 56%;">
                        <a href="detail?rno=${review.rno }">${review.r_title } <c:if test="${review.commentcount>0}">[${review.commentcount }]</c:if></a>
                        <p>${review.r_regDate } ${review.id }</p>
                    </td>
                    <td style=";width: 25%">
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/like.png" style="width: 10%; float: left">
                            <p>　좋아요　${review.r_like }</p>
                        </span>
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/view.png" style="width: 10%; float: left">
                            <p>　조회수　${review.r_viewcnt }</p>
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
                    	<a class="page-link" href="${pageMaker.makeQuery(1)}">&lt;&lt;</a>
                    </li>
                </c:if>
                    
                <c:if test="${pageMaker.prev }"> 
                	<li class="page-item">
                		<a class="page-link" href="${pageMaker.makeQuery(pageMaker.startPage-1) }">&lt;</a>
                	</li>
                </c:if>
                
                <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
                    <li class="page-item <c:out value=" ${pageMaker.cri.page==pageNum ?'active':''}" />">
                    <a class="page-link" href="${pageMaker.makeQuery(pageNum) }">
                        ${pageNum }
                    </a>
                    </li>
                </c:forEach>
                
                <c:if test="${pageMaker.next }">
	                <li class="page-item">
	                    <a class="page-link" href="${pageMaker.makeQuery(pageMaker.endPage+1) }">&gt;
	                    </a>
	                </li>
                </c:if>
                
                 <c:if test="${pageMaker.next }">
	                <li class="page-item">
	                    <a class="page-link" href="${pageMaker.makeQuery(pageMaker.realEndPage) }">
	                        &gt;&gt;
	                    </a>
	                </li>
                </c:if>
            </ul>
        </div>
    </div>
</body>

</html>