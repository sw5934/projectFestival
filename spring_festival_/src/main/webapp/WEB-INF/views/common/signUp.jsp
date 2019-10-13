<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>회원가입 페이지</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition register-page">
<div class="" style="position:absolute; top: 14%; left: 0px; right: 0px; margin-left:auto; margin-right:auto;">
  <div class="register-logo">
    <a href="<%=request.getContextPath()%>/resources/bootstrap/index2.html"><b>회원가입</b></a>
  </div>

  <div class="card">
    <div class="card-body register-card-body">

      <form name="signUpForm" id="signForm" action="signUp" class="form-horizontal" method="post" enctype="multipart/form-data">
        <div class="form-group row">
          <label class="col-sm-2 control-label">아이디</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" id="id" name="id" onkeyup="idChecking()">
          </div>&nbsp;&nbsp;&nbsp;
          <button type="button" class="btn btn-primary" id="idCheckBtn" disabled="disabled">중복 확인</button>
        </div>
        
        <div class="form-group row"> 
          <label class="col-sm-2 control-label">비밀번호</label>
          <div class="col-sm-8">
            <input type="password" class="form-control" id="pwd" name="pwd" onkeyup="pwdChecking()">
            <p id="pwdValid"></p>
          </div>
        </div>
        
        <div class="form-group row">
          <label class="col-sm-2 control-label">비밀번호 확인</label>
          <div class="col-sm-8">
            <input type="password" class="form-control" id="pwdConf" onkeyup="pwdConfChecking()">
            <p id="pwdConfValid"></p>
          </div>
        </div>
        
        <div class="form-group row">
          <label class="col-sm-2 control-label">이름</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" id="name" name="name" onkeyup="nameChecking()">
          </div>
        </div>
        
        <div class="form-group row">
          <label class="col-sm-2 control-label">닉네임</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" id="nickName" name="nickName" onkeyup="nickChecking()">
          </div>&nbsp;&nbsp;&nbsp;
          <button type="button" class="btn btn-primary" id="nickCheckBtn" disabled="disabled">중복 확인</button>
        </div>
        
        <div class="form-group row">
          <label class="col-sm-2 control-label">생년월일</label>
          <div class="col-sm-8">
            
            <select class="form-control" id="birthY" name="birthY">
            	<c:forEach begin="0" end="${2019-1900}" var="i">
            	<c:set var="year" value="${2019-i}"/>
            		<option value="${year}">${year}</option>
            	</c:forEach>
            </select>
            
            <select class="form-control" id="birthM" name="birthM">
            	<c:forEach begin="1" end="12" var="month">
            		<option value="${month}">${month}</option>
            	</c:forEach>
            </select>
            
            <select class="form-control" id="birthD" name="birthD">
            	<c:forEach begin="1" end="31" var="Date">
            		<option value="${Date}">${Date}</option>
            	</c:forEach>
            </select>
            
          </div>
        </div>
        
        <div class="form-group row">
          <label class="col-sm-2 control-label">성별</label>
          <div class="col-sm-8">
            <select class="form-control" id="sex" name="sex">
            	<option value="선택">선택</option>
            	<option value="남자">남자</option>
            	<option value="여자">여자</option>
            </select>
          </div>
        </div>
        
        <div class="form-group row">
          <label class="col-sm-2 control-label">연락처</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" id="tel" name="tel">
          </div>
        </div>
        
        <div class="form-group row">
          <label class="col-sm-2 control-label">이메일</label>
          <div class="col-sm-8">
            <input type="email" class="form-control" id="mailAddress" onkeyup="emailChecking()"></input>
            <input type="hidden" id="realMail"  name="mailAddress" value=""></input>
          </div>&nbsp;&nbsp;&nbsp;
          <button type="button" class="btn btn-primary" id="emailCheckBtn" disabled="disabled"><div>인증</div></button>
        </div>
        <div id="emailValidDiv" style="display:none;">
        	<input type="text" class="form-control" id="emailValid"></input>
        	<button type="button" class="btn btn-primary" id="emailValidCheckBtn">확인</button>
        </div>
        
        <div class="form-group row">
          <label class="col-sm-2 control-label">지역</label>
          <div class="col-sm-8">
            <select class="form-control" id="location1" name="location1">
            	<option>선택</option>
            	<option value="서울특별시">서울특별시</option>
            	<option value="인천광역시">인천광역시</option>
            	<option value="대전광역시">대전광역시</option>
            	<option value="광주광역시">광주광역시</option>
            	<option value="울산광역시">울산광역시</option>
            	<option value="대구광역시">대구광역시</option>
            	<option value="부산광역시">부산광역시</option>
            	<option value="세종특별자치시">세종특별자치시</option>
            	<option value="경기도">경기도</option>
            	<option value="강원도">강원도</option>
            	<option value="충청북도">충청북도</option>
            	<option value="충청남도">충청남도</option>
            	<option value="전라북도">전라북도</option>
            	<option value="전라남도">전라남도</option>
            	<option value="경상북도">경상북도</option>
            	<option value="경상남도">경상남도</option> 
            </select>
            <input type="text" class="form-control" placeholder="세부 주소를 입력하세요" id="location2" name="location2">
          </div>
        </div>
        
        <div class="form-group row">
          <label class="col-sm-2 control-label">축제메이트는?</label>
          <div class="col-sm-8">
            <select class="form-control" id="prtPattern" name="prtPattern">
            	<option>선택</option>
            	<option value="가족">가족</option>
            	<option value="친구">친구</option>
            	<option value="연인">연인</option>
            	<option value="단체">단체</option>
            	<option value="솔로">솔로</option>
            	<option value="기타">기타</option>
            </select>
          </div>
        </div>
        <div class="form-group row">
          <label class="col-sm-2 control-label">정보공개여부</label>
          <div class="col-sm-8">
            <input type="radio" id="infoPublic" name="infoStatus" value='1' checked="checked"> 동의 &nbsp;&nbsp;&nbsp;
            <input type="radio" id="infoPrivate" name="infoStatus" value='0'> 비동의
          </div>
        </div>
        
        <div class="">
          
       
        </div>
       </form>
<button type="button" class="btn btn-info float-center" id="submit" onClick="signUpCheck()">Sign in</button>
       <button type="button" class="btn btn-default float-center">Cancel</button>
    </div>
    <!-- /.form-box -->
  </div><!-- /.card -->
</div>
<!-- /.register-box -->

<!-- jQuery -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

<%@ include file="./signUp_js.jsp" %>
</body>

