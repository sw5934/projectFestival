<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="list" value="${data.list }" />
<c:set var="pageMaker" value="${data.pageMaker }" />

<body>
<div class="col-11 mb-1" style="margin: 0 auto;padding: 0px; overflow:hidden; height:50px;"> 
<div class="col-7 float-sm-left" style="padding: 0px; top: 5px;">
<a class="baminfont-Air mt-2" href="<%=request.getContextPath()%>/message/sendMessage" style="color: darkslategrey; border: 1px solid black; padding: 8px 40px; font-size:1.2em"> 쪽지 보내기 </a>
</div>
<div class="col-5 float-sm-left" style="padding: 0px; font-size:1.3em;"> 
<div class="col-6 float-sm-left" style="padding: 0px; border-right:2px solid black; text-align:center;">
<a class="baminfont-Pro" href="<%=request.getContextPath()%>/message/receiveList?page=1" style="color: darkslategrey;">수신 목록</a></div>
<div class="col-6 float-sm-left" style="padding: 0px; border-right:2px solid black; text-align:center;">
<a class="baminfont-Pro" href="<%=request.getContextPath()%>/message/sendList?page=1" style="color: darkslategrey;">발신 목록</a></div>
</div>
</div>
<div  class="col-11" style=" margin: 0 auto; border:1px solid gray; height:306px;">
<table class="col-11 mt-3"style=" margin: 0 auto;">
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
 	                <td style="width: 30px; border-bottom:1px solid gray;  font-size:0.9em">
                    <td style="width: 125px; border-bottom:1px solid gray; font-size:0.9em">${message.m_receiver_Nick }</td>
                    <td style="width: 410px; border-bottom:1px solid gray;  font-size:0.9em">
                        <a href="detail?m_no=${message.m_no }" style="color: darkslategrey;">${message.m_content }</a>  
                    </td>
                    <td style="width: 100px; border-bottom:1px solid gray;  font-size:0.9em">
                        <fmt:formatDate value="${message.m_sendDate }"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <div class="mt-1 col-10" style="margin:0 auto  position:absolute; bottom:0px">
        <div class="text-center">
            <ul class="pagination" style="font-size:1.2em">    
            
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
                    <li class="page-item <c:out value=" ${pageMaker.cri.page==pageNum ?'active':''}" />"  style="width:20px;">
                    <a class="" "href="<%=request.getContextPath()%>/message/sendList?page=${pageNum }"> ${pageNum } </a>
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


</div>






</body>