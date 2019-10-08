<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="list" value="${message }" />

<body>
<input type="hidden" id="sender"value="${list.m_sender_Id}"/>
<input type="hidden" id="receiver"value="${list.m_receiver_Id}"/>



 


<div class="col-11 p-0" style="margin: 0 auto; border:1px solid #B9B9B9;height:330px; font-size:0.9em">
<div class="col-12 pt-2 pb-1 pr-0 pl-0 m-0" style="background-color:#DaDaDa; border-bottom:1px solid #B9B9B9">
<p class="m-0 ml-3">발신자 : ${list.m_sender_Nick }</p></div>
<div class="col-12 pt-2 pb-1 pr-0 pl-0 m-0" style="background-color:#DaDaDa; border-bottom:1px solid #B9B9B9">
<p class="m-0 ml-3">수신자 : ${list.m_receiver_Nick }</p></div>
<div class="col-12 pt-2 pb-1 pr-0 pl-0 m-0" style="background-color:#DaDaDa; border-bottom:1px solid #B9B9B9">
<p class="m-0 ml-3">발신일 : <fmt:formatDate value="${list.m_sendDate }" type="both"/></p></div>
  
<div class="col-12 p-2">
 
<p>${list.m_content }</p>
</div>

</div>
<div class="col-11" style="margin:0 auto;padding:0px; overflow:hidden;">

<c:if test="${loginUser.id!=list.m_sender_Id}">
<a  class="baminfont-Pro mt-2 float-sm-right" href="<%=request.getContextPath()%>/message/receiveList?page=${pageNum }" style="cursor:pointer;color: darkslategrey; border: 1px solid #B9B9B9; padding: 6px 23px; font-size:1.2em">닫기</a>
</c:if>
<c:if test="${loginUser.id!=list.m_receiver_Id}">
<a  class="baminfont-Pro mt-2 float-sm-right" href="<%=request.getContextPath()%>/message/sendList?page=${pageNum }" style="cursor:pointer;color: darkslategrey; border: 1px solid #B9B9B9; padding: 6px 23px; font-size:1.2em">닫기</a>
</c:if>

<c:if test="${loginUser.id!=list.m_sender_Id}">
	<a class="baminfont-Pro mt-2 mr-3 float-sm-right"  href="<%=request.getContextPath()%>/message/receiverDelete?m_no=${list.m_no }" style="color: darkslategrey; border: 1px solid #B9B9B9; padding: 6px 23px; font-size:1.2em">삭제</a>
</c:if>
<c:if test="${loginUser.id!=list.m_receiver_Id}">
	<a  class="baminfont-Pro mt-2 mr-3  float-sm-right" href="<%=request.getContextPath()%>/message/senderDelete?m_no=${list.m_no }" style="color: darkslategrey; border: 1px solid #B9B9B9; padding: 6px 23px; font-size:1.2em">삭제</a>
</c:if>



<c:if test="${loginUser.id!=list.m_sender_Id}">
	<input type="hidden" name="m_sender_Nick" value="${list.m_sender_Nick}">
	
	<a class="baminfont-Pro mt-2 mr-3  float-sm-right" href="<%=request.getContextPath()%>/message/sendMessage?m_sender_Nick=${list.m_sender_Nick}" style="color: darkslategrey; border: 1px solid #B9B9B9; padding: 6px 23px; font-size:1.2em"> 
	답장</a>
</c:if>


</div>













</body>