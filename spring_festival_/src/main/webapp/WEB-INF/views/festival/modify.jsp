<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  


<head>
<title>게시글 수정</title>
</head>
<body>

<!-- 작성자 -->
<input type="hidden" id="writer" value="${loginUser.id}">	
	<div class="col-md-12">
				<div class="card ">
					<div class="card-header">
						<h4>축제 수정</h4>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="modify" name="registForm">

								<input type="hidden" name="fno" value="${festival.fno }"/>
								<input type="hidden" name="listSort" value="${listSort }"/>
								<input type="hidden" name="page" value="${page }"/>
							<div class="form-group row">
								<label class="col-sm-2 control-label" for="title">제 목</label> 
								<input class="col-sm-10 form-control" type="text" id="title"
									name='f_title' placeholder="제목을 쓰세요" value="${festival.f_title }" readonly> 
							</div>
															
									
							<div class="col-md-12">
								<textarea name="f_content" id="content"
										placeholder="1000자 내외로 작성하세요.">${festival.f_content }</textarea>
							</div>								

			<div class="">
			<a class="baminfont-Air ml-1 pl-3 pr-3" style="font-size:1.4em;border-left:2px solid black;border-right:2px solid black;">상세정보</a> 
			
			</div>
			<div class="col-12">
				<div class="col-12 mb-2" style="margin: 0 auto;overflow:hidden">
					<div class="col-3 float-sm-left">축제명</div>
					<input type="text" name="f_name" class="col-9 float-sm-left" value="${festival.f_name }"></div>
				<div class="col-12 mb-2" style="margin: 0 auto;overflow:hidden">
					<div class="col-3 float-sm-left">시도명</div>
					 <select class="form-control col-4  mb-3" id="location1" name="f_location1">
            	<option value="서울특별시" <c:if test='${festival.f_location1 =="서울특별시"}'>selected</c:if> >서울특별시</option>
            	<option value="인천광역시" <c:if test='${festival.f_location1 =="인천광역시"}'>selected</c:if> >인천광역시</option>
            	<option value="대전광역시" <c:if test='${festival.f_location1 =="대전광역시"}'>selected</c:if> >대전광역시</option>
            	<option value="광주광역시" <c:if test='${festival.f_location1 =="광주광역시"}'>selected</c:if> >광주광역시</option>
            	<option value="울산광역시" <c:if test='${festival.f_location1 =="울산광역시"}'>selected</c:if> >울산광역시</option>
            	<option value="대구광역시" <c:if test='${festival.f_location1 =="대구광역시"}'>selected</c:if> >대구광역시</option>
            	<option value="부산광역시" <c:if test='${festival.f_location1 =="부산광역시"}'>selected</c:if> >부산광역시</option>
            	<option value="세종특별자치시" <c:if test='${festival.f_location1 =="세종특별자치시"}'>selected</c:if> >세종특별자치시</option>
            	<option value="경기도" <c:if test='${festival.f_location1 =="경기도"}'>selected</c:if> >경기도</option>
            	<option value="강원도" <c:if test='${festival.f_location1 =="강원도"}'>selected</c:if> >강원도</option>
            	<option value="충청북도" <c:if test='${festival.f_location1 =="충청북도"}'>selected</c:if> >충청북도</option>
            	<option value="충청남도" <c:if test='${festival.f_location1 =="충청남도"}'>selected</c:if> >충청남도</option>
            	<option value="전라북도" <c:if test='${festival.f_location1 =="전라북도"}'>selected</c:if> >전라북도</option>
            	<option value="전라남도" <c:if test='${festival.f_location1 =="전라남도"}'>selected</c:if> >전라남도</option>
            	<option value="경상북도" <c:if test='${festival.f_location1 =="경상북도"}'>selected</c:if> >경상북도</option>
            	<option value="경상남도" <c:if test='${festival.f_location1 =="경상남도"}'>selected</c:if> >경상남도</option> 
            </select>
            </div> 
				<div class="col-12 mb-2" style="margin: 0 auto;overflow:hidden">
					<div class="col-3 float-sm-left">구군명</div>
					<input type="text" name="f_location2" class="col-9 float-sm-left" value="${festival.f_location2 }"></div>
				<div class="col-12 mb-2" style="margin: 0 auto;overflow:hidden">
					<div class="col-3 float-sm-left">일시</div>
					<input type="text" name="f_period" class="col-9 float-sm-left" value="${festival.f_period }"></div>
				<div class="col-12 mb-2" style="margin: 0 auto;overflow:hidden">
					<div class="col-3 float-sm-left">주최 주관</div>
					<input type="text" name="f_org" class="col-9 float-sm-left" value="${festival.f_org }"></div>
				<div class="col-12 mb-2" style="margin: 0 auto;overflow:hidden">
					<div class="col-3 float-sm-left">참여 형태</div>
					 <select class="form-control col-4  mb-3" id="type" name="f_type">
            	<option value="참여" <c:if test='${festival.f_type =="참여"}'>selected</c:if> >참여</option>
            	<option value="관람" <c:if test='${festival.f_type =="관람"}'>selected</c:if> >관람</option>
            	<option value="전시" <c:if test='${festival.f_type =="전시"}'>selected</c:if> >전시</option>
            	<option value="기타" <c:if test='${festival.f_type =="기타"}'>selected</c:if> >기타</option>
            </select></div>
				<div class="col-12 mb-2" style="margin: 0 auto;overflow:hidden">
					<div class="col-3 float-sm-left">사이트 링크</div>
					<input type="text" name="f_ref"class="col-9 float-sm-left" value="${festival.f_ref }"></div>
				<div class="col-12 mb-2" style="margin: 0 auto;overflow:hidden">
					<div class="col-3 float-sm-left">해시태그</div>
					<input type="text" name="hashTagString" class="col-9 float-sm-left"></div>
			</div>
						</form>
					</div><!--end card-body  -->
					
					<div class="box-footer fileInput">
					</div>
					
					<div class="card-footer">
						<button type="button" class="btn btn-primary" id="registBtn">등 록</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-warning" id="cancelBtn">취 소</button>
					</div><!--end card-footer  -->
				</div><!-- end card -->	
			
</div>

<%@ include file = "./modify_js.jsp" %>
</body>


