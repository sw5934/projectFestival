<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<head>
<title>후기 등록</title>


</head>
<body>

	<div class="col-md-12">
	
			<!-- 작성자 -->					
				<div class="card ">
					<div class="card-header">
						<h4>후기 등록</h4>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="reviewRegist" name="registForm">
						<input type="hidden" name="id" id="writer" value="${loginUser.id}">		
						<input type="hidden" name="unq_Id" value="${review.unq_Id}">
							<div class="form-group row">
								<label class="col-sm-2 control-label" for="title">제 목</label> 
								<input class="col-sm-10 form-control" type="text" id="r_title"
									name='r_title' placeholder="제목을 쓰세요">
							</div>
							<div class="col-md-12">
								<textarea name="r_content" id="r_content"
										placeholder="1000자 내외로 작성하세요."></textarea>
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
			
			

<%@ include file = "./reviewRegist_js.jsp" %>
</body>

