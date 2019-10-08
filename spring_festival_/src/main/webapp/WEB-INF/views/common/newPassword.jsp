<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


   <div class="login-box" id="login-box" style="position:absolute; top: 14%; left: 0px; right: 0px; margin-left:auto; margin-right:auto;">
    <div class="login-logo" id="login-logo">
        <a class="baminfont-Pro" style="font-size: 1.3em">비밀번호 변경</a>
    </div>
    <div class="findPasswordPro" id="findPasswordPro">
      
        <form action="newPasswordUpdate" id="signForm" method="post">
        <input type="hidden" value="${id}" name="id"/>
          <p class="login-box-msg baminfont-Air" style="font-size: 1.2em">새 비밀번호를 입력하세요.</p>
        
            <div class="float-sm-left col-12">
                <div class="float-sm-left col-9">
                    
                    <label class="col-sm-4 control-label">비밀번호</label>
                    <div class="mb-3">
                        <input type="password" class="form-control" id="pwd" name="pwd" onkeyup="pwdChecking()">
            			<p id="pwdValid"></p>
                    </div>
                    <label class="col-sm-4 control-label">새 비밀번호</label>
                    <div class="mb-3">
                        <input type="password" class="form-control" id="pwdConf" onkeyup="pwdConfChecking()">
            			<p id="pwdConfValid"></p>
                    </div>
                    
                </div>
                <div class="float-sm-left col-3">
                    <div class="row">
                        <div class="">
                            <button type="button" class="btn btn-primary" id="findPasswordBtn" onclick="signUpCheck()" >확인</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="./login_js.jsp" %>
<% session.invalidate(); %>