<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<c:if test="${!empty msg }">
<script>
	alert("${msg}");
</script>
</c:if>

   <div class="login-box" style="position:absolute; top: 14%; left: 0px; right: 0px; margin-left:auto; margin-right:auto;">
    <div class="login-logo">
        <a href="<%=request.getContextPath()%>/main" class="baminfont-Pro" style="font-size: 1.6em">축제의 민족</a>
    </div>
    <!-- /.login-logo -->
    <div class="">
        <p class="login-box-msg baminfont-Air" style="font-size: 1.2em">아이디와 비밀번호로 로그인하세요.</p>

        <form method="post" id="loginForm">
            <div class="float-sm-left col-12">
                <div class="float-sm-left col-9">
                    <div class="mb-3">
                        <input type="email" class="form-control" placeholder="ID" id="id" name="id" value="chun@naver.com">
                    </div>
                    <div class="mb-3">
                        <input type="password" class="form-control" placeholder="PASSWORD" id="pwd" name="pwd" value="chun">
                    </div>
                </div>
                <div class="float-sm-left col-3">
                    <div class="row">
                        <div class="">
                            <button type="button" id="loginBtn" class="btn btn-block pt-0"><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/login_btn.png" style="border-radius: 9%"></button>
                        </div>
                    </div>
                    <!-- /.col -->
                </div>
            </div>
        </form>

        <br />
        <p class="mb-1 ml-5 baminfont-Air" style="font-size: 1.1em">
            <a href="<%=request.getContextPath()%>/findID">아이디 찾기</a>|<a href="<%=request.getContextPath()%>/findPassword"> 비밀번호 찾기</a>|<a href="<%=request.getContextPath()%>/signUp" class="text-center"> 회원가입</a>
        </p>

    </div>
    <!-- /.login-card-body -->
</div>

<%@ include file="./common_js.jsp" %>
<% session.invalidate(); %>