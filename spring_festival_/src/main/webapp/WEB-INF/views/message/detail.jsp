<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="list" value="${message }" />

<body>
<input type="hidden" id="sender"value="${list.m_sender_Id}"/>
<input type="hidden" id="receiver"value="${list.m_receiver_Id}"/>
<p>발신자 : ${list.m_sender_Nick }</p>
<p>수신자 : ${list.m_receiver_Nick }</p>
<p>발신일 : ${list.m_sendDate }</p>
<p>내용 : ${list.m_content }</p>

<c:if test="${loginUser.id!=list.m_sender_Id}">
<form action="/festival/message/sendMessage" method="get">
	<input type="hidden" name="m_sender_Nick" value="${list.m_sender_Nick}">
	<input type="submit" value="답장">
	</form>
</c:if>

<c:if test="${loginUser.id!=list.m_sender_Id}">
	<a type="button" href="<%=request.getContextPath()%>/message/receiverDelete?m_no=${list.m_no }">삭제</a>
</c:if>
<c:if test="${loginUser.id!=list.m_receiver_Id}">
	<a type="button" href="<%=request.getContextPath()%>/message/senderDelete?m_no=${list.m_no }">삭제</a>
</c:if>


<button>닫기</button>





</body>