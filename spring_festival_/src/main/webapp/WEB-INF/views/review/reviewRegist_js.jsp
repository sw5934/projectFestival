<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- jQuery -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery-ui/jquery-ui.min.js"></script>

<script>
    $.widget.bridge('uibutton', $.ui.button) 
    $.widget.bridge('uitooltip', $.ui.tooltip);
</script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Summernote -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/summernote/summernote-bs4.min.js"></script>
<!-- summernote -->

<script>
<!-- 글등록 내용 스마트에디터 적용 -->
$('#r_content').summernote({	
	height:200,
	placeholder:"1000자는 넘기지 말자^^",
	tabsize:'1',
	fontNames:['궁서', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New',],
	fontNamesIgnoreCheck : ['궁서'],
	focus: true,
	lang: 'ko-KR',

	callbacks:{
		onImageUpload : function(files, editor, welEditable){
			for (var i = files.length - 1; i >= 0; i--) {
				if(files[i].name.substring(files[i].name.lastIndexOf(".")+1).toLowerCase() != "jpg"){
					alert("jpg 확장자만 가능");
					return;
				}
				//독립된 if문은 or의 의미
				//alert(files[i].name);
				if(files[i].size > 1024*1024*1){
					alert("이미지는 1MB 미만입니다.");
					return;
				}
			
			}		
			//for(var i in files){
			for (var i = files.length - 1; i >= 0; i--) {
				sendFile(files[i], this);
			}
			
		},
		onMediaDelete : function(target) {
			//alert(target[0].src);
			deleteFile(target[0].src);
		}
	}
	});


/* onSubmit */

function onSubmit(category, form, url, method) {

	//유효성처리
	//validation();
	
	
<%-- 		열어서 주제를 선택하는 거라면 ("action","<%=request.getContextPath()%>/board/${category}/admin/regist") --%>
	form.action="<%=request.getContextPath()%>/"+category+"/"+url;
	form.method=method;
	form.submit();
}



$('#registBtn').on('click',function(e){
	alert("등록버튼 클릭");
	var form = document.registForm;
	
	
		/*  */
		for(var i=0;i<form.elements.length;i++){
			var input =form.elements[i];
			if(input.name == "title" || input.name == "content"){
				if(input.value == ""){
					$(input).addClass("is-invalid");
					alert(input.name+"은 필수입니다.")
					return;
				}			
			}
		}
		
		if(form.content.value.length>1000){
			alert("글자수가 1000자를 초과할 수 없업");
			return;
		}
		
		onSubmit('${category}',document.registForm,'reviewRegist','post');
});

$("#cancelBtn").click(function(){
    history.go(-1)();
});

$("input").on('keyup',function(e){
	if($(this).val()!=""){
		$(this).removeClass("is-invalid");
	}
});
</script>