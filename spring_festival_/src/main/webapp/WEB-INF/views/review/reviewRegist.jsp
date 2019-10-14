<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<head>
<title>후기 등록</title>


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

	<div class="col-md-12">
	
			<!-- 작성자 -->					
				<div class="card ">
					<div class="card-header">
						<h4>후기 등록</h4>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="reviewRegist" name="registForm">
							<%
								int f_no = Integer.parseInt(request.getParameter("fno"));
								String f_name = request.getParameter("f_name");
								System.out.println("f_no : "+f_no);
								System.out.println("f_name : "+f_name);
							%>
							<input type="hidden" name="id" id="writer" value="${loginUser.id}">		
							<input type="hidden" name="unq_Id" value="${review.unq_Id}">
							<input type="hidden" name="f_no" value="<%=f_no %>">
								<div class="form-group row">
									<label class="col-sm-2 control-label" for="title">제 목</label> 
									<input class="col-sm-10 form-control" type="text" id="r_title"
										name='r_title' placeholder="제목을 쓰세요">
								</div>
								<div class="form-group row">
									
									
									<label class="col-sm-2 control-label" for="title">축제명</label>
									<input class="col-sm-10 form-control" type="text" id="f_name"
										name='f_name' value="<%=f_name %>" disabled="disabled">
								</div>
								<div class="col-md-12">
									<textarea name="r_content" id="r_content"
											placeholder="1000자 내외로 작성하세요."></textarea>
								</div>	
							
						<!-- 별점주기 -->
						<div class="form-group">
						<label for="review_star">축제는 어떠셨나요?</label>
							<span class="star-input">
								<span class="input" id="r_score">							
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
						</div>								

						</form>
					</div><!--end card-body  -->
					
					<div class="box-footer fileInput">
					</div>
					
					<div class="card-footer">
						<button type="button" class="btn btn-primary" id="registBtn" onclick="onSubmit('${category}',document.registForm,'reviewRegist','post');">등 록</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-warning" id="cancelBtn">취 소</button>
					</div><!--end card-footer  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
	
<!-- star.js -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/build/js/star.js">
</script>			
			

<%@ include file = "./reviewRegist_js.jsp" %>
</body>

