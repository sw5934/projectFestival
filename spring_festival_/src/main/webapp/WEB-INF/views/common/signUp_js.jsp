<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>	
	//id 중복검사
	var idchk = false		// id검사 실행 여뷰
	var inputid = null;		// id검사가 실시된 id의 값
	$('#idCheckBtn').click(function() {		
		var id = $("#id").val();
		$.ajax({
			async: true,
			type : 'POST',
			data : $("#id").val(),
			url : "signUp/idCheck.do",
			dataType : "json",
			contentType: "application/json; charset=UTF-8",
			success : function(data) {
					alert("사용 가능한 아이디 입니다."); 
					idchk = true;
					inputid = id;
					
					alert(idchk + "" + inputid);
			},
			error : function(error) {
				alert("사용할 수 없는 아이디 입니다.");
				alert(idchk + "" + inputid);
			}
		});
	});
	
	//생년, 생월을 통해 일자를 28~31일까지 자동으로 조절하는 스크립트
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
		var year = $('#birthY').val();
		if((year%4==0)&&(year%100!=0)||(year%400==0)){
			leapYear = true;
		}else{
			leapYear = false;
		}
		setDate();
	});
	
	$('#birthM').change(setDate);
	
	
</script>