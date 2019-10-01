<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>

<script>	
	//id 중복검사
	var idchk = false;		// id검사 실행 여뷰
	var inputid = null;		// id검사가 실시된 id의 값
	var id;
	
	function idChecking() {
		id = $("#id").val();
		$('#idCheckBtn').attr('disabled', false);
		if(id.length == 0)
			$('#idCheckBtn').attr('disabled', true);
		idchk = false;
	}
	
	$('#idCheckBtn').click(function() {
		$.ajax({
			async: true,
			type : 'POST',
			data : $("#id").val(),
			url : "signUp/idCheck.do",
			dataType : "json",
			contentType: "application/json; charset=UTF-8",
			success : function(data) {
					alert("사용 가능한 아이디 입니다.");
					$('#idCheckBtn').attr('disabled', true);
					idchk = true;
					inputid = id;
			},
			error : function(error) {
				alert("사용할 수 없는 아이디 입니다.");
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
			$('#pwdConfValid').text("OK.");
			pwdConfchk = true;
		} else {
			$('#pwdConfValid').text("비밀번호가 일치하지 않습니다.");
			
			pwdConfchk = false;
		}
	}
	
	var name;
	var namechk = false;
	
	function nameChecking() {
		name = $('#name').val();
		
		if(name != null) {
			namechk = true;
		} else {
			namechk = false;
		}
	}
	
	var nickchk = false;	// 닉네임 검사 실행 여뷰
	var inputnick = null;		// 닉네임 검사가 실시된 닉네임의 값
	var nickName;
	
	function nickChecking() {
		nickName = $("#nickName").val();
		$('#nickCheckBtn').attr('disabled', false);
		if(nickName.length == 0)
			$('#nickCheckBtn').attr('disabled', true);
		nickchk = false;
	}
	
	$('#nickCheckBtn').click(function() {		
		$.ajax({
			async: true,
			type : 'POST',
			data : $("#nickName").val(),
			url : "signUp/nickCheck.do",
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
	
	
	//생년, 생월을 통해 일자를 28~31일까지 자동으로 조절하는 스크립트
	
	var birth;
	var birthchk = false;
	
	function setDate(){
		var month = $('#birthM').val();
		var dateCnt;
		switch(month){
		case '1': case '3': case '5': case '7': case '8': case '10': case '12': 
			dateCnt = 31;
		break;
		case '4': case '6': case '9': case '11':
			dateCnt = 30;
		break;
		default :
			dateCnt = 28;
		break;
		}
		if(dateCnt==28&&leapYear){
			dateCnt = 29;
		}
		
		$('#birthD *').remove();
		
		for(var i=1; i<=dateCnt; i++){
			$('#birthD').append('<option value="'+i+'">'+i+'</option>');
		}

	}

	var leapYear;	//윤년여부
	$('#birthY').change(function(){
		year = $('#birthY').val();
		if((year%4==0)&&(year%100!=0)||(year%400==0)){
			leapYear = true;
		}else{
			leapYear = false;
		}
		setDate();
	});
	
	function dateFill(a){
		if (a<10){
		return "0"+a;};
		return ""+a;
	};
	
	
	
	function birthCheck() {		
		birth = $('#birthY').val() + dateFill($('#birthM').val()) + dateFill($('#birthD').val());
		birthchk = true;
	}
	
	var sexchk = false;
	var sex;
	function sexCheck() {
		if($('#sex').val() != "선택") {
			sexchk = true;
			sex = $('#sex').val();
		} else {
			sexchk = false;
			sex = null;
		}
	}
	
	var telchk = false;
	var tel;
	function telCheck() {
		if($('#tel').val() != null) {
			telchk = true;
			tel = $('#tel').val();
		} else {
			telchk = false;
			tel = null;
		}
	}
	
	var emailchk = false;
	var email = $('#mailAddress').val();
	var code;
	
	function emailChecking() {
		email = $('#mailAddress').val();
		if(email == null) {
			$('#emailCheckBtn').attr('disabled', true);
		} else {
			$('#emailCheckBtn').attr('disabled', false);
		}
	}
	
	$('#emailCheckBtn').click(function() {
		$.ajax({
			async: true,
			type : 'POST',
			data : $('#mailAddress').val(),
			url : "signUp/sendMail.do",
			dataType : "json",
			contentType: "application/json; charset=UTF-8",
			success : function(data) {
					alert("인증코드를 입력해주세요.");
					$('#emailValidDiv').css('display','block');
					code = data.code;
			},
			error : function(error) {
				alert("이미 사용중인 이메일 입니다.");
				$('#emailValid').val("");
			}
		});
	});
	
	$('#emailValidCheckBtn').click(function() {
		if($('#emailValid').val() == code) {
			alert("인증되었습니다.");
			$('#emailValidDiv').css('display','none');
			$('#emailCheckBtn').attr('disabled', true);
			$('#mailAddress').attr('disabled', true);
			$('#realMail').val(email);
			$('#emailCheckBtn div').text("인증완료");
			emailchk = true;
		} else {
			alert("다시 입력해주세요.");
			emailchk = false;
		}
	});
	
	var address;
	var addresschk = false;
	
	function addressCheck() {
		if($('#location1').val() == "선택") {
			addresschk = false;
			address = "";
		} else {
			addresschk = true;
			address = $('#location1 option:selected').text() + " ";
		}
		
		address = address + $('#location2').val();
	}
	
	var prtPattern;
	var prtPatternchk = false;
	
	function prtPatternCheck() {
		if($('#prtPattern').val() == "선택") {
			prtPatternchk = false;
			prtPattern = null;
		} else {
			prtPatternchk = true;
			prtPattern = $('#prtPattern option:selected').text();
		}
	}
	
	function signUpCheck() {
		
		birthCheck();
		sexCheck();
		telCheck();
		addressCheck();
		prtPatternCheck();
		
		/* var infoStatus = $('#infoStatus').val(); */
		
		/*alert(id + "/" + pwd + "/" + pwdConf + "/" + name + "/" + nickName + "/" + birth + "/" + sex + "/" + tel 
				+ "/" + email + "/" + address + "/" + prtPattern);
		*/
		var signUpchk = idchk * pwdchk * pwdConfchk * namechk * nickchk * birthchk * sexchk * telchk 
		* emailchk * addresschk * prtPatternchk;
		/*
		alert("@@@@@@@"+signUpchk);
		*/
		if(signUpchk == 1) {
			/*alert("Dddddd");*/
			$('#signForm').submit();
		} else {
			alert("다시 확인하세요.");
			return;
		}
		
		/* alert(id + "/" + pwd + "/" + pwdConf + "/" + name + "/" + nickName + "/" + birth + "/" + sex + "/" + tel 
				+ "/" + email + "/" + address + "/" + prtPattern);
		
		alert(idchk + "/" + pwdchk + "/" + pwdConfchk + "/" + namechk + "/" + nickchk + "/" + birthchk + "/" + sexchk + "/" + telchk 
				+ "/" + emailchk + "/" + addresschk + "/" + prtPatternchk);
		
		alert(idchk * pwdchk * pwdConfchk * namechk * nickchk * birthchk * sexchk * telchk 
				* emailchk * addresschk * prtPatternchk); */
	}

</script>

<script>

function findIdCheck() {
	var name = $('#name').val();
	var email = $('#email').val();
	var id;
	
	if(name == "") {
		alert("이름을 입력하세요.");
		return;
	} else if(email == "") {
		alert("이메일을 입력하세요.");
		return;
	}
	
	$.ajax({
		async: true,
		type : 'POST',
		data : $("#findIdPost").serialize(), 
		url : "findID.do",
		success : function(data) {
				alert("id:"+data.id);
				
				$('#findIdPost').remove();
				$('#login-logo').remove();
				$('#findIdPro').append("<p class='login-box-msg baminfont-Air' style='font-size: 1.2em'>아이디를 찾았습니다!</p>");
				$('#findIdPro').append("<div class='float-sm col-12'><div class='float-sm-right col-8'>");
				$('#findIdPro').append("<p>회원님의 아이디는");
				$('#findIdPro').append(data.id);
				$('#findIdPro').append("입니다.</div><br><br><br>");
				$('#findIdPro').append("<div class='float-sm-right col-8'><div class='baminfont-Air' style='font-size: 1.1em'>");
				$('#findIdPro').append("</div></div></div>");	
		},
		error : function(data) {
			
		}
	});
}

function findPasswordCheck() {
	
	var id = $('#inputId').val();
	var name = $('#name').val();
	var email = $('#email').val();
	var pwd;
	if(id == "") {
		alert("아이디를 입력하세요.");
		return;
	} else if(name == "") {
		alert("이름을 입력하세요.");
		return;
	} else if(email == "") {
		alert("이메일을 입력하세요.");
		return;
	}
	
	var obj = {"id":id, "name":name, "email":email};
	
	var params = JSON.stringify(obj);
	
	alert("!!!!!!!!!"+params);
	
	$.ajax({
		async: true,
		type : 'POST',
		data : params,
		contentType:"application/json",
		url : "findPassword.do",
		success : function(data) {
				alert("pwd:"+data.pwd);
				/* 
				$('#findIdPost').remove();
				$('#login-logo').remove();
				$('#findIdPro').append("<p class='login-box-msg baminfont-Air' style='font-size: 1.2em'>아이디를 찾았습니다!</p>");
				$('#findIdPro').append("<div class='float-sm col-12'><div class='float-sm-right col-8'>");
				$('#findIdPro').append("<p>회원님의 아이디는");
				$('#findIdPro').append(data.id);
				$('#findIdPro').append("입니다.</div><br><br><br>");
				$('#findIdPro').append("<div class='float-sm-right col-8'><div class='baminfont-Air' style='font-size: 1.1em'>");
				$('#findIdPro').append("</div></div></div>");	 */
		},
		error : function(data) {
			
		}
	});
}

</script>