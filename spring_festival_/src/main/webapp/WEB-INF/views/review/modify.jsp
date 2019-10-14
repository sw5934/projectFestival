<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    


<head>
<title>게시글 수정</title>
</head>
<body>

<style>
.star-input>.input,
.star-input>.input>label:hover,
.star-input>.input>input:focus+label,
.star-input>.input>input:checked+label{display: inline-block;vertical-align:middle;background:url("<%=request.getContextPath()%>/resources/images/grade_img.png")no-repeat;}
.star-input{display:inline-block; white-space:nowrap;width:225px;height:40px;padding:25px;line-height:30px;}
.star-input>.input{display:inline-block;width:150px;background-size:150px;height:28px;white-space:nowrap;overflow:hidden;position: relative;}
.star-input>.input>input{position:absolute;width:1px;height:1px;opacity:0;}
star-input>.input.focus{outline:1px dotted #ddd;}
.star-input>.input>label{width:30px;height:0;padding:28px 0 0 0;overflow: hidden;float:left;cursor: pointer;position: absolute;top: 0;left: 0;}
.star-input>.input>label:hover,
.star-input>.input>input:focus+label,
.star-input>.input>input:checked+label{background-size: 150px;background-position: 0 bottom;}
.star-input>.input>label:hover~label{background-image: none;}
.star-input>.input>label[for="p1"]{width:30px;z-index:5;}
.star-input>.input>label[for="p2"]{width:60px;z-index:4;}
.star-input>.input>label[for="p3"]{width:90px;z-index:3;}
.star-input>.input>label[for="p4"]{width:120px;z-index:2;}
.star-input>.input>label[for="p5"]{width:150px;z-index:1;}
</style>

<!-- 작성자 -->
<input type="hidden" id="writer" value="${loginUser.id}">	
	<div class="col-md-12">
				<div class="card ">
					<div class="card-header">
						<h4>게시글 수정</h4>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="modify" name="registForm">

								<input type="hidden" name="rno" value="${review.rno }"/>
								<input type="hidden" name="listSort" value="${listSort }"/>
								<input type="hidden" name="page" value="${page }"/>
							<div class="form-group row">
								<label class="col-sm-2 control-label" for="title">제 목</label> 
								<input class="col-sm-10 form-control" type="text" id="title"
									name='r_title' placeholder="제목을 쓰세요" value="${review.r_title }">
							</div>
															
									
							<div class="col-md-12">
								<textarea name="r_content" id="r_content"
										placeholder="1000자 내외로 작성하세요.">${review.r_content }</textarea>
							</div>	
							
							<!-- 별점 수정 -->
							
							<span class="star-input">								
								축제는 어떠셨나요?
								<span class="input" id="r_score" >							
							    	<input type="radio"  name="starInput" value="1" id="p1">
							    	<label for="p1">1</label>
							    	<input type="radio" name="starInput" value="2" id="p2">
							    	<label for="p2">2</label>
							    	<input type="radio" name="starInput" value="3" id="p3">
							    	<label for="p3">3</label>
							    	<input type="radio" name="starInput" value="4" id="p4">
							    	<label for="p4">4</label>
							    	<input type="radio" name="starInput" value="5" id="p5">
							    	<label for="p5">5</label>
							  	</span>					
							</span>
													
						</form>
					</div><!--end card-body  -->
					<div class="card-footer">
						<button type="button" class="btn btn-warning" id="modifyBtn" onclick="onSubmit('${category}',document.registForm,'modify','post');">수 정</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-secondary" id="cancelBtn" onclick="onCancel();">취 소</button>
					</div><!--end card-footer  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->

<!-- star.js -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/build/js/stra.js"></script>			

<%@ include file = "./modify_js.jsp" %>
</body>