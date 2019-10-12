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
<style>
.myInput {
	border: 0px;
	border-bottom: #E2E2E2 1px solid;
	width: auto;
}
</style>

	<div class="card col-11 pt-3 pb-3" style="margin:0 auto; top:15px">
	<form role="form" method="post" action="myInfoModify" name="myInfoModifyForm">
		<div style="display:none;">
			<input name="id" value="${loginUser.id}" />
			<input name="name" value="${loginUser.name}" />
			<input id="loginPwd" value="${loginUser.pwd}" />
			<input id="nickOrigin" value="${loginUser.nickName}" />
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">닉네임 </div>
			<input class="col-9 float-sm-left myInput" id="nickName" name="nickName" value="${loginUser.nickName}" onkeyup="nickChecking()">
			<button type="button" class="col-2 float-sm-right baminfont-Air" id="nickCheckBtn" disabled="disabled">중복 확인</button>
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">비밀번호 </div>
			<input type="password" class="col-9 float-sm-left myInput" id="pwd" name="pwd" onkeyup="pwdChecking()">
			<p class="baminfont-Air" style="font-size:0.9em;height:3px;margin-top:5px;" id="pwdValid"></p>
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">비밀번호 확인 </div>
			<input type="password" class="col-9 float-sm-left myInput" id="pwdConf" name="pwdConf" onkeyup="pwdConfChecking()">
			<p class="baminfont-Air" style="font-size:0.9em;height:3px;margin-top:5px;" id="pwdConfValid"></p>
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">연락처 </div>
			<input class="col-9 float-sm-left myInput" id="tel" name="tel" value="${loginUser.tel}"></input>
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
			<select class="col-9 float-sm-left myInput" id="location1" name="location1">
            	<option value="선택" disabled>선택</option>
            	<option value="서울특별시" ${loginUser.location1 eq "서울특별시" ? "selected" :""}>서울특별시</option>
            	<option value="인천광역시" ${loginUser.location1 eq "인천광역시" ? "selected" :""}>인천광역시</option>
            	<option value="대전광역시" ${loginUser.location1 eq "대전광역시" ? "selected" :""}>대전광역시</option>
            	<option value="광주광역시" ${loginUser.location1 eq "광주광역시" ? "selected" :""}>광주광역시</option>
            	<option value="울산광역시" ${loginUser.location1 eq "울산광역시" ? "selected" :""}>울산광역시</option>
            	<option value="대구광역시" ${loginUser.location1 eq "대구광역시" ? "selected" :""}>대구광역시</option>
            	<option value="부산광역시" ${loginUser.location1 eq "부산광역시" ? "selected" :""}>부산광역시</option>
            	<option value="세종특별자치시" ${loginUser.location1 eq "세종특별자치시" ? "selected" :""}>세종특별자치시</option>
            	<option value="경기도" ${loginUser.location1 eq "경기도" ? "selected" :""}>경기도</option>
            	<option value="강원도" ${loginUser.location1 eq "강원도" ? "selected" :""}>강원도</option>
            	<option value="충청북도" ${loginUser.location1 eq "충청북도" ? "selected" :""}>충청북도</option>
            	<option value="충청남도" ${loginUser.location1 eq "충청남도" ? "selected" :""}>충청남도</option>
            	<option value="전라북도" ${loginUser.location1 eq "전라북도" ? "selected" :""}>전라북도</option>
            	<option value="전라남도" ${loginUser.location1 eq "전라남도" ? "selected" :""}>전라남도</option>
            	<option value="경상북도" ${loginUser.location1 eq "경상북도" ? "selected" :""}>경상북도</option>
            	<option value="경상남도" ${loginUser.location1 eq "경상남도" ? "selected" :""}>경상남도</option> 
            </select>
            <input type="text" class="col-9 float-sm-left myInput" placeholder="간단히 주소를 입력하세요(동/읍/면)" id="location2" name="location2" value="${loginUser.location2}">
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">여행타입 </div>
			<select class="col-9 float-sm-left myInput" id="prtPattern" name="prtPattern" value="${loginUser.prtPattern }">
            	<option disabled>선택</option>
            	<option value="가족" ${loginUser.prtPattern eq "가족" ? "selected" :""}>가족</option>
            	<option value="친구" ${loginUser.prtPattern eq "친구" ? "selected" :""}>친구</option>
            	<option value="연인" ${loginUser.prtPattern eq "연인" ? "selected" :""}>연인</option>
            	<option value="단체" ${loginUser.prtPattern eq "단체" ? "selected" :""}>단체</option>
            	<option value="솔로" ${loginUser.prtPattern eq "솔로" ? "selected" :""}>솔로</option>
            	<option value="기타" ${loginUser.prtPattern eq "기타" ? "selected" :""}>기타</option>
            </select>
		</div>
		<div class="col-12 float-sm-left">
			<div class="col-3 float-sm-left baminfont-Air" style="font-size:1.2em">정보 공개 여부</div>
			<div class="col-9 float-sm-left">
				<input type="radio" id="infoPublic" name="infoStatus" value='1' checked="checked"> 공개 &nbsp;&nbsp;&nbsp;
            	<input type="radio" id="infoPrivate" name="infoStatus" value='0'> 비공개
			</div> 
		</div>
	</form>
	</div>
		<div class="col-11" style="overflow:hidden;margin:0 auto; top:10px;"> 
			<button type="button" class="col-2 float-sm-right baminfont-Air" style="border:1px solid #B9B9B9;font-weight:bold; font-size:1.3em; text-align:center;" onclick="onSubmit('member/myInfoModify',document.myInfoModifyForm,'post');"> 수정 </button>
		</div>
		
<%@ include file="./myInfoModify_js.jsp" %>
</body>
</html>