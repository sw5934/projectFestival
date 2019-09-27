<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="list" value="${data.list }" />
<c:set var="pageMaker" value="${data.pageMaker }" />

<body>

<a class="" href="<%=request.getContextPath()%>/message/receiveList?page=1">수신함으로</a>
<a class="" href="<%=request.getContextPath()%>/message/sendMessage">작성</a>

<table class="mt-5 col-10" style=" margin: 0 auto;">
        <c:if test="${empty list }">
            <tr>
                <td colspan="5" class="text-center">
                    <strong>해당 내용이 없습니다.</strong>
                </td>
            </tr>
        </c:if>
        <c:if test="${!empty list }">
            <c:forEach items="${list }" var="message">
                <tr style="border-bottom: 0px solid black;">
                    <td style="width: 7%;  text-align: center">${message.m_receiver_Nick }</td><td style="width: 56%;">
                        <a href="detail?m_no=${message.m_no }">${message.m_content }</a>
                    </td>
                    <td style=";width: 25%">
                        ${message.m_sendDate }
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
                    	<a class="" href="<%=request.getContextPath()%>/message/sendList?page=1">&lt;&lt;</a>
                    </li>
                </c:if> 
                    
                <c:if test="${pageMaker.prev }"> 
                	<li class="page-item">
                		<a class="" href="<%=request.getContextPath()%>/message/sendList?page=${pageMaker.startPage-1 }">&lt;</a>
                	</li>
                </c:if>
                
                <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
                    <li class="page-item <c:out value=" ${pageMaker.cri.page==pageNum ?'active':''}" />">
                    <a class="" href="<%=request.getContextPath()%>/message/sendList?page=${pageNum }">${pageNum }</a>
                    </li>
                </c:forEach>
                
                <c:if test="${pageMaker.next }">
	                <li class="page-item">
	                    <a class="" href="<%=request.getContextPath()%>/message/sendList?page=${pageMaker.endPage+1 }">&gt;
	                    </a>
	                </li>
                </c:if>
                
                 <c:if test="${pageMaker.next }">
	                <li class="page-item">
	                    <a class="" href="<%=request.getContextPath()%>/message/sendList?page=${pageMaker.realEndPage }">
	                        &gt;&gt;
	                    </a>
	                </li>
                </c:if>
            </ul>
        </div>
    </div>









</body>