<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body>

<form action="/festival/message/sendMessage" method="post">
<p>수신자</p>
<input type="text" name=m_receiver_Nick value="${sender }"/>
<input type="hidden" name="m_sender_Id" value="${loginUser.id }"/>
<input type="text" id="content" name="m_content">
<input type="submit" value="확인">
</form>
<script></script>

</body>