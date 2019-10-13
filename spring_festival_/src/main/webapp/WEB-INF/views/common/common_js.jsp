<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>

<script>	
$('#loginBtn').click(function() {

	var id = $('#id').val();
	var pwd = $('#pwd').val();
	
	alert(id);
	alert(pwd);
	
	
	var data = {"id":id,"pwd":pwd};
	var jsonData = JSON.stringify(data);
	alert(jsonData);
	$.ajax({
		async: true,
		type : "POST",
		url : "/festival/login",
		data : jsonData,
		contentType: "application/json; charset=UTF-8",
        success : function(data) {
			if(data.status == 'loginFail') {
				alert('로그인에 실패했습니다.');
			} else {
				alert("로그인 성공!!");
				alert(data.url);

				$('#id').val(data.id);
				$('#pwd').val(data.pwd);
				$('#loginForm').submit();
            }
        },

        error : function(error) {

            /* alert("error:" + data.status);
            alert(data.url); */
            alert("로그인 에러!!!");

        }

    })

});

</script>