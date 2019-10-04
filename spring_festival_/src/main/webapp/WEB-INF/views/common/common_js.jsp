<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>

<script>	
$('#loginBtn').click(function() {

	var id = $('#id').val();
	var pwd = $('#pwd').val();
	var data = {"id":id,"pwd":pwd};
	var jsonData = JSON.stringify(data);
	$.ajax({
		async: true,
		type : "POST",
		url : "/festival/login",
		data : jsonData,
		contentType: "application/json; charset=UTF-8",
        success : function(data) {
        	
        	if(data.status=="loginSuccess"){

				$('#id').val(data.id);
				$('#pwd').val(data.pwd);
				$('#loginForm').attr("action","<%=request.getContextPath()%>/"+data.url);
				$('#loginForm').submit();
        		
        	}else if(data.status=="loginFail"){
        		if(data.sendMail=="yes"){
        			alert("로그인에 5회 실패하여, 회원님의 메일로 임시 비밀번호를 발송하였습니다.");
        		}else{
        			alert("잘못된 회원입니다.");
        		}	        		
        	}else if(data.status=="IDNotFound"){
        		alert("입력하신 아이디가 존재하지 않습니다.");
        	}
        },
        error : function(error) {
            alert("잘못된 경로의 접근");
        }
    })
});

</script>