<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    


<head>
<title>같이가요 수정</title>
</head>
<body>

<!-- 작성자 -->
<input type="hidden" id="writer" value="${loginUser.id}">	
	<div class="col-md-12">
				<div class="card ">
					<div class="card-header">
						<h4>같이가요 수정</h4>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="modify" name="registForm">

								<input type="hidden" name="tno" value="${together.tno }"/>
								<input type="hidden" name="listSort" value="${listSort }"/>
								<input type="hidden" name="page" value="${page }"/>
							<div class="form-group row">
								<label class="col-sm-2 control-label" for="title">제 목</label> 
								<input class="col-sm-10 form-control" type="text" id="title"
									name='t_title' placeholder="제목을 쓰세요" value="${together.t_title }">
							</div>
							<div class="form-group row">
								<label class="col-sm-2 control-label" for="title">정보 공개 여부</label>
								<div class = "infocheck"> 								
									<div class="form-group">
									<c:if test="${together.articleStatus == 1 }">
					                    <input type="radio" id="infoPublic" name="articleStatus" value='1' checked="checked"> 공개 &nbsp;&nbsp;&nbsp;
					                    <input type="radio" id="infoPrivate" name="articleStatus" value='0'> 비공개
					                </c:if>
					                <c:if test="${together.articleStatus != 1 }">
					                    <input type="radio" id="infoPublic" name="articleStatus" value='1' > 공개 &nbsp;&nbsp;&nbsp;
					                    <input type="radio" id="infoPrivate" name="articleStatus" value='0' checked="checked"> 비공개
					                </c:if> 
					                 </div>	
								</div>	
							</div>
															
									
							<div class="col-md-12">
								<textarea name="t_content" id="t_content"
										placeholder="1000자 내외로 작성하세요.">${together.t_content }</textarea>
							</div>	
													
						</form>
					</div><!--end card-body  -->
					<div class="card-footer">
						<button type="button" class="btn btn-warning" id="modifyBtn" onclick="onSubmit('${category}',document.registForm,'modify','post');">수 정</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-secondary" id="cancelBtn" onclick="onCancel();">취 소</button>
					</div><!--end card-footer  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->

<%@ include file = "./modify_js.jsp" %>
</body>


