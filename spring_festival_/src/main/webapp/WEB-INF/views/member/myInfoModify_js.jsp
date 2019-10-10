<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>

$('#myModifyBtn').click(function(location1, prtPattern) {
	
	
});

var nickchk = true;	// 닉네임 검사 실행 여뷰
var inputnick = null;		// 닉네임 검사가 실시된 닉네임의 값
var nickName;

function nickChecking() {
	nickName = $("#nickName").val();
	$('#nickCheckBtn').attr('disabled', false);
	if(nickName.length == 0)
		$('#nickCheckBtn').attr('disabled', true);
	if(nickName == $('#nickOrigin').val()) {
		nickchk = true;
		$('#nickCheckBtn').attr('disabled', true);
	}
	nickchk = false;
}

$('#nickCheckBtn').click(function() {
	alert("기존 : " + $('#nickOrigin').val());
	alert("입력 : " + nickName);
	$.ajax({
		async: true,
		type : 'POST',
		data : $("#nickName").val(),
		url : "nickCheck.do",
		dataType : "json",
		contentType: "application/json; charset=UTF-8",
		success : function(data) {
				alert("사용 가능한 닉네임 입니다.");
				$('#nickCheckBtn').attr('disabled', true);
				nickchk = true;
				inputnick = nickName;
		},
		error : function(error) {
			alert("사용할 수 없는 닉네임 입니다.");
		}
	});
});

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
		$('#pwdConfValid').text("  OK.");
		pwdConfchk = true;
	} else {
		$('#pwdConfValid').text("  비밀번호가 일치하지 않습니다.");
		
		pwdConfchk = false;
	}
}

function onSubmit(url, form, method) {

	var modifyChk = pwdchk * pwdConfchk * nickchk;
	
	if(modifyChk == 1){
		form.action="<%=request.getContextPath()%>/"+url;
		form.method=method;
		form.submit();
		
		session.setAttribute("loginUser", loginUser);
	} else {
		alert("다시 입력하세요.");
	}
	
}
</script>