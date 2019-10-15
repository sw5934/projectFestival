<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <a class="col-10 baminfont-Pro mt-1" style="font-size: 1.6em; padding: 0px">신고 게시판</a>
    </div>
    <div class="col-10 mt-3 reviewHeader" style="overflow: hidden; margin: 0 auto;">
        <!-- 정렬 이벤트, 글 작성 이벤트 넣기 -->
        <div class="float-sm-left col-8">　</div>
        <div class="float-sm-left col-2 reviewHeaderSort"><a href="<%=request.getContextPath()%>/manage/reportList">신고 게시글</a></div>
        <div class="float-sm-left col-2 reviewHeaderSort" style="border: 0"><a href="<%=request.getContextPath()%>/manage/reportCommentList">신고 댓글</a></div>
    </div>
    
    
    <form class="col-12" id="listForm" action="<%=request.getContextPath() %>/manage/reportList" method="get">
	    <select name="searchType">
	    	<option value="nickname" <c:if test='${cri.searchType =="nickname"}'>selected</c:if> >작성자 </option>
	    	<option value="reporter" <c:if test='${cri.searchType =="reporter"}'>selected</c:if> >신고자 </option>
	    	<option value="title" <c:if test='${cri.searchType =="title"}'>selected</c:if> >글 제목 </option>
	    </select>
    	<input type="text" name="keyword" value="${cri.keyword }">
    	<button type="button" onclick="$('#listForm').submit()">검색</button>
    </form>
    
    
    <table class="mt-5 col-12" style=" margin: 0 auto;">
    		<tr style="border-bottom: 0px solid black; font-size:0.9em">
                    <td style="width: 8%;  text-align: center">신고번호</td>
                    <td style="width: 8%;  text-align: center">글 위치</td>
                    <td style="width: 40%;">제목</td> 
                    <td style="width: 12%;">작성자</td>
                    <td style="width: 12%;">신고자</td>
                    <td style="width: 16%;">신고일시</td>
                </tr>
        <c:if test="${empty reportList }">
            <tr>
                <td colspan="5" class="text-center">
                    <strong>해당 내용이 없습니다.</strong>
                </td>
            </tr>
        </c:if>
        <c:if test="${!empty reportList }">
            <c:forEach items="${reportList }" var="report">
                <tr style="border-bottom: 0px solid black;">
                    <td style="width: 8%;  text-align: center">${report.rpt_no}</td>
                    <td style="width: 8%;  text-align: center">${report.type}</td>
                    <td style="width: 40%;">
	                     <c:if test="${report.type=='후기'}">
	                    <a style="text-overflow: ellipsis;overflow:hidden;display:block;white-space: nowrap;"  href="<%=request.getContextPath()%>/review/detail?rno=${report.bno } ">
	                    </c:if>
	                    <c:if test="${report.type=='같이가요'}">
	                    <a style="text-overflow: ellipsis;overflow:hidden;display:block;white-space: nowrap;"  href="<%=request.getContextPath()%>/together/detail?tno=${report.bno } ">
	                    </c:if>
	                    <c:if test="${report.type=='축제'}">
	                    <a style="text-overflow: ellipsis;overflow:hidden;display:block;white-space: nowrap;"  href="<%=request.getContextPath()%>/festival/detail?fno=${report.bno } ">
	                    </c:if>
	                    ${report.title}
	                    </a>
                    </td> 
                    <td style="width: 12%;text-overflow: ellipsis;overflow:hidden;white-space: nowrap;">${report.nickName}</td>
                    <td style="width: 12%;text-overflow: ellipsis;overflow:hidden;white-space: nowrap;">${report.reporterNick}</td>
                    <td style="width: 20%;text-overflow: ellipsis;overflow:hidden;white-space: nowrap;"><fmt:formatDate value="${report.rpt_date}"/></td>
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