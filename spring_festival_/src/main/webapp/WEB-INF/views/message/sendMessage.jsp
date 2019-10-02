<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body>

<form action="/festival/message/sendMessage" method="post" id="messageForm">
<input type="hidden" name="m_sender_Id" value="${loginUser.id }"/>


<div class="col-11 p-0" style="border:1px solid black; margin: 0 auto; height:330px; font-size:0.9em">
<div class="col-12 pt-2 pb-1 pr-0 pl-0 m-0" style="overflow:hidden;background-color:#D8D8D8; border-bottom:1px solid black">
<p class="m-0 ml-3 float-sm-left">수신자 :</p><input type="text" class="ml-3 float-sm-left" style="width:260px "id="sender"name=m_receiver_Nick value="${sender }"/></div>
<div class="col-12 p-0" style="height:285px; overflow:hidden"> 
<textarea id="content" name="m_content" style="height:296px; width: 685.5px; padding:8px;border:0px"></textarea>
</div>
 
</div>


</form>

<div class="col-11" style="margin:0 auto;padding:0px; overflow:hidden;">
<a  class="baminfont-Pro mt-2 float-sm-right" onClick="history.back()" style="cursor:pointer;color: darkslategrey; border: 1px solid black; padding: 6px 23px; font-size:1.2em">취소</a>

<a  class="baminfont-Pro mt-2 mr-3 float-sm-right" onClick="onSubmit();" style="cursor:pointer;color: darkslategrey; border: 1px solid black; padding: 6px 23px; font-size:1.2em">보내기</a>
</div>


<script>
function onSubmit(){
	
	alert($('#sender').val());
	
	if($('#sender').val()==""){
		alert("수신자 닉네임을 입력해 주세요.");
		return;
	}else if($('#content').val()==""){
		alert("쪽지 내용을 작성해 주세요.");
		return;
	}else if($('#content').val().length>1000){
		alert("1000자 이내로 작성해 주세요.");
		return;
	}else	
		var data=$("#sender").val();
		
		$.ajax({			
			type : 'get',			
			url : "checkNick?nickname="+data,			
			success : function() {
					alert("쪽지가 발송되었습니다."); 
					$('#messageForm').submit();
					},
			error : function() {
				alert("잘못된 수신자 입니다.");
			}
		});	
}

</script>

</body>