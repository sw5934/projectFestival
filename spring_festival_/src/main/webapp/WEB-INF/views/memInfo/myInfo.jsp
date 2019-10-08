<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

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

	<div class="card col-11 pt-3 pb-3" style="margin:0 auto; top:50px">
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">닉네임 </div>
			<div class="col-9 float-sm-left">${loginUser.nickName}</div>
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">연락처 </div>
			<div class="col-9 float-sm-left">${loginUser.tel}</div>
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">이메일 </div>
			<div class="col-9 float-sm-left">${loginUser.email}</div>
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">생년월일</div>
			<div class="col-9 float-sm-left">${birthData.year}년 ${birthData.month}월 ${birthData.date}일</div>
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">성별 </div>
			<div class="col-9 float-sm-left">${loginUser.sex}</div>
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">지역 </div>
			<div class="col-9 float-sm-left">${loginUser.address}</div>
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">여행타입 </div>
			<div class="col-9 float-sm-left">${loginUser.prtPattern }</div>
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">정보 공개 여부</div>
			<div class="col-9 float-sm-left">
				<c:if test="${loginUser.infoStatus==1 }">공개	</c:if>
				<c:if test="${loginUser.infoStatus==2 }">비공개</c:if>
			</div> 
		</div>
	</div>
		<div class="col-11" style="overflow:hidden;margin:0 auto; top:52px;"> 
			<div class="col-2 float-sm-right baminfont-Air" style="border:1px solid #B9B9B9;font-weight:bold; font-size:1.3em; text-align:center;"> 수정 </div>
		</div>
</body>
</html>