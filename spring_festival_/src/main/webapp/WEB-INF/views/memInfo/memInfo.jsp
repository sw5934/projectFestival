<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="myInfo"  value="${dataMap.myInfo}"  />

<%
   request.setCharacterEncoding("UTF-8");
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 3 | Simple Tables</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body>
	닉네임 : ${myInfo.nickName}<br/>
	연락처 : ${myInfo.tel}<br/>
	이메일 : ${myInfo.email}<br/>
	생년월일 : ${myInfo.birth}</br>
	성별 : ${myInfo.sex }</br>
	지역 : ${myInfo.location}</br>
	여행타입 : ${myInfo.prtPattern } </br>
	
	
	<c:if test="${myInfo.infoStatus==1 }">
	정보 공개 여부 : 동의함</br>
	</c:if>
	
	
	<c:if test="${myInfo.infoStatus==2 }">
	정보 공개 여부 : 동의 안함</br>
	</c:if>
</body>
</html>