<%@page import="com.spring.service.TogetherServiceImpl"%>
<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="togetherList" value="${dataMap.togetherlist }" />
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="listSort" value="${dataMap.listSort }" />
<c:set var="page" value="${dataMap.page }" />
<c:set var="newArticle" value="${dataMap.newArticle }" />

<!DOCTYPE html>
<html>

<head>
   
    <meta charset="UTF-8">
    <title>같이가요 게시판</title>
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
    
    
    <div class="box pt-3 col-10" style="left: 0px; right: 0px; margin-left:auto; margin-right:auto;">
        <a class="col-10 baminfont-Pro mt-1" style="font-size: 1.6em; margin-left: 20px; padding: 0px">같이가요 게시판</a>
    </div>
    <div class="col-10 mt-3 togetherHeader" style="overflow: hidden; margin: 0 auto;">
        <!-- 정렬 이벤트, 글 작성 이벤트 넣기 -->
         <div class="float-sm-left col-8 mt-1 p-0"></div>
        <div class="float-sm-left col-2 togetherHeaderSort"><a href="?listSort=tno">최신 일자</a></div>
        <div class="float-sm-left col-2 togetherHeaderSort" style="border: 0"><a href="?listSort=t_viewcnt">조회 수</a></div>
    </div>
    
    <div style="width: 100%; margin-top: 10px;">
    <form class="col-12" style="width: 400px;float: right; margin-right: 80px;" id="listForm" action="<%=request.getContextPath() %>/together/list" method="get">
	    <select name="searchType">
	    	<option value="tcw" <c:if test='${cri.searchType =="tcw"}'>selected</c:if> >전체 </option>
	    	<option value="title" <c:if test='${cri.searchType =="title"}'>selected</c:if> >제목 </option>
	    	<option value="contents" <c:if test='${cri.searchType =="contents"}'>selected</c:if> >내용 </option>
	    	<option value="tc" <c:if test='${cri.searchType =="tc"}'>selected</c:if> >제목+내용 </option>
	    	<option value="festival" <c:if test='${cri.searchType =="festival"}'>selected</c:if> >축제명 </option>
	    	<option value="writer" <c:if test='${cri.searchType =="writer"}'>selected</c:if> >작성자 </option>
	    </select>
    	<input type="text" name="keyword" value="${cri.keyword }">
    	<button type="button" onclick="$('#listForm').submit()">검색</button>
    </form>
    </div>
    
    <table class="mt-5 col-10" style=" margin: 0 auto;">
        <c:if test="${empty togetherList }">
            <tr>
                <td colspan="5" class="text-center">
                    <strong>해당 내용이 없습니다.</strong>
                </td>
            </tr>
        </c:if>
        
        <c:if test="${!empty togetherList }">
            <c:forEach items="${togetherList }" var="together">
                <tr style="border-bottom: 0px solid black;">
                    <td style="width: 7%;  text-align: center">${together.tno }</td>
                    
                   <c:if test="${together.t_state == 1 }">
                    	<td style="width: 16%;  text-align: center; font-weight: bold">[모집중]</td>
                   </c:if>
                   <c:if test="${together.t_state == 2 }">
                   	 <td style="width: 16%;  text-align: center; font-weight: bold; color: red;">[마감]</td>
                    </c:if>
                    <td style="width: 56%;">
                     <c:set var="t_regDate" value="${together.t_regDate}" />                     
                        <a href="detail?tno=${together.tno }&listSort=${listSort}&page=${page}">
                        	<c:if test="${together.newCount == 2}"><span style="font-weight: bold;" >${together.t_title }&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/images/newArticle.png" style="width:30px;height:auto;"></span></c:if>
                    		<c:if test="${together.newCount != 2}"><span>${together.t_title }</span></c:if>
                    	
                        <c:if test="${together.commentcount>0}">[${together.commentcount }]</c:if></a>
                        <p><fmt:formatDate value="${together.t_regDate }" pattern="MM-dd HH:mm"/>&emsp;
                        <a class="user_Box" onclick="userBox($(this))">${together.nickname }</a></p>
                        <div style="position:relative;">
                         
                         </div>
                    </td>
                    <td style=";width: 25%">
                        <%-- <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/like.png" style="width: 10%; float: left">
                            <p>　좋아요　${together.r_like }</p>
                        </span> --%>
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/view.png" style="width: 10%; float: left">
                            <p>　조회수　${together.t_viewcnt }</p>
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