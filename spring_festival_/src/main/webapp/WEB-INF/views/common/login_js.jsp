<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>

<script>
	var pwd;
	var pwdchk = false;
	var passRule = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-`&])(?=.*[0-9]).{8,16}$/;
	
	function pwdChecking() {
		
		pwd = $('#pwd').val();
		if(passRule.test($('#pwd').val())) {
			$('#pwdValid').text(null);
			pwdchk = true;
		} else {
			if(pwd == "") {
				$('#pwdValid').text(null);
				pwdchk = false;
			} else {
				$('#pwdValid').text("8~15자 사이의 영문/숫자/특수문자로 입력하세요.");
				pwdchk = false;
			}
		};
	};
	
	var pwdConfchk = false;
	var pwdConf;
	
	function pwdConfChecking() {
		
		pwdConf = $('#pwdConf').val();
		
		if($('#pwdConf').val() == "") {
			$('#pwdConfValid').text(null);
			pwdConfchk = false;
		} else if($('#pwdConf').val() == $('#pwd').val()) {
			$('#pwdConfValid').text("OK.");
			pwdConfchk = true;
		} else {
			$('#pwdConfValid').text("비밀번호가 일치하지 않습니다.");
			
			pwdConfchk = false;
		}
	}
	
	function signUpCheck() {
		
		if(pwdchk+pwdConfchk == 2) {
			alert("비밀번호를 변경하였습니다. 다시 로그인 해 주세요.");
			$('#signForm').submit();
		} else {
			alert("다시 확인하세요.");
			return;
		}
	}

</script>
