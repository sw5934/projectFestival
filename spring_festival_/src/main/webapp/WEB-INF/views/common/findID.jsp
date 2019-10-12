<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


   <div class="login-box" id="login-box" style="position:absolute; top: 14%; left: 0px; right: 0px; margin-left:auto; margin-right:auto;">
    <div class="login-logo" id="login-logo">
        <a href="<%=request.getContextPath()%>/main" class="baminfont-Pro" style="font-size: 1.3em">ID/Password 찾기</a>
    </div>
    <!-- /.login-logo -->
    <div class="findIdPro" id="findIdPro">
      
        <form action="findIdPost" id="findIdPost" method="post">
          <p class="login-box-msg baminfont-Air" style="font-size: 1.2em">이름, 이메일을 입력하세요.</p>
        
            <div class="float-sm-left col-12">
                <div class="float-sm-left col-9">
                    
                    <div class="mb-3">
                        <input type="text" class="form-control" id="name" name="name" placeholder="이름">
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" id="email" name="email" placeholder="이메일">
                    </div>
                </div>
                <div class="float-sm-left col-3">
                    <div class="row">
                        <div class="">
                            <button type="button" class="btn btn-primary baminfont-Air" id="findIdBtn" onclick="findIdCheck()" style="font-size:1.2em">아이디 찾기</button>
                        </div>
                    </div>
                    <!-- /.col -->
                </div>
            </div>
        </form>

        <br />

    </div>
        <p class="mb-1 ml-5 baminfont-Air" style="font-size: 1.1em">
            <a href="<%=request.getContextPath()%>/findPassword" > 비밀번호 찾기</a>|
            <a href="<%=request.getContextPath()%>/signUp" class="text-center"> 회원가입</a>
        </p>
    

	<br /><br /><br />
	
</div>

<%@ include file="./signUp_js.jsp" %>
<% session.invalidate(); %>