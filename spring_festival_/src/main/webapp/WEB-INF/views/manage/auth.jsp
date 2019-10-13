<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="${dataMap.authSetList }" var="authSetList"/>
<c:set value="${dataMap.pageMaker }" var="pageMaker"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>권한 지정(설정)</title>


  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

</head>
<body onload="startSet()">
<a id="hlist" href=""></a>
									<div class="input-group input-group-sm">
                                        <select name="searchOption" id="sltMem" class="mr-3">
                                            <option value="i_id">아이디</option>
                                            <option value="i_name">이름</option>
                                            <option value="i_nName">닉네임</option>
                                        </select>
                                        <input class="form-control form-control-navbar" id="inputVal" type="search" placeholder="Search" aria-label="Search" style="border:0; border-bottom:solid #65ddda">
                                        <div class="input-group-append">
                                            <button class="btn btn-navbar" onclick="memSearch(inputVal)">
                                                <i class="fas fa-search"></i>
                                            </button>
                                        </div>
                                    </div>

<div class="row" style="width:900px" border="solid 1px black">
<br/>
  <table class="table table-condensed" width="800px">
  	<tr>
  		<th width="100px" align="center">아z이디</th>
  		<th width="200px" align="center">이메일</th>
  		<th width="100px" align="center">닉네임</th>
  		<th width="" align="center">이름</th>
  		<th width="100px" align="center">권한 설정</th>
  		<th width="100px" align="center">제재기간</th>
  		<th width="" align="center">수정</th>
  	</tr>

	<c:forEach items="${authSetList }" var="tuple" begin="0" step="1" varStatus="num">
		<tr height="20px">
			<td>${tuple.id }</td>
			<td>${tuple.email }</td>
			<td>${tuple.nickName }</td>
			<td>${tuple.name }</td>
			<td width="120px">
				<select class="custom-select" id="auth${num.count }"    onchange="sltPeriod(auth${num.count }, ${num.count-1 })" style="height:40px">
				   <c:choose>
						<c:when test="${tuple.auth_no==4 }">
                   			<option>관리자</option>
			                   <option>개최자</option>
			                   <option>일반회원</option>
			                   <option>제재회원</option>
			                   <option>비회원</option>
                   		</c:when>
						<c:when test="${tuple.auth_no==3 }">
                   			<option>개최자</option>
			                   <option>관리자</option>
			                   <option>일반회원</option>
			                   <option>제재회원</option>
			                   <option>비회원</option>
                   		</c:when>
						<c:when test="${tuple.auth_no==2 }">
                   			<option>일반회원</option>
			                   <option>관리자</option>
			                   <option>개최자</option>
			                   <option>제재회원</option>
			                   <option>비회원</option>
                   		</c:when>
						<c:when test="${tuple.auth_no==1 }">
                   			<option>제재회원</option>
			                   <option>관리자</option>
			                   <option>개최자</option>
			                   <option>일반회원</option>
			                   <option>비회원</option>
                   		</c:when>
						<c:when test="${tuple.auth_no==0 }">
                   			<option>비회원</option>
			                   <option>관리자</option>
			                   <option>개최자</option>
			                   <option>일반회원</option>
			                   <option>제재회원</option>
                   		</c:when>
				   </c:choose>
              	</select>
            </td>
			<td width="90px" height="20px">
				
				
				<select class="custom-select" id="period${num.count-1 }" style="height:40px;"
					 	<c:if test="${tuple.auth_no ne 1 }">disabled="disabled"</c:if>>
				   <c:choose>
						<c:when test="${tuple.auth_no ne 1 }">
                   		</c:when>
						<c:when test="${tuple.auth_no eq 1 }">
                   			<option>${tuple.period }</option>
                   			<option>30일</option>
                   			<option>60일</option>
                   			<option>90일</option>
                   		</c:when>
				   </c:choose>
              	</select>
			</td>
			<td>
				<button value="수정" onclick="authUpdate(period${num.count-1 }, '${tuple.id }', auth${num.count } )">수정</button>
			</td>	
              
        </tr>      
     </c:forEach>
   
              
</table>


					<div class="card-footer clearfix"  >
						<div class="text-center">															
						<ul class="pagination pagination-sm m-0 float-right"> 
							
							<li class="page-item">
								<a class="page-link" href="authoritySet${pageMaker.makeQuery(1)}">&lt;&lt;</a>
							</li>
							
							<li class="page-item">
								<a class="page-link" href="authoritySet
									<c:if test="${pageMaker.prev }">
										${pageMaker.makeQuery(pageMaker.startPage-1) }
									</c:if>
								">&lt;</a>
							</li>
							<c:forEach begin="${pageMaker.startPage }" 
							           end="${pageMaker.endPage }" var="pageNum">
							<li class="page-item <c:out value="${pageMaker.cri.page == pageNum ?'active':''}"/>"`>
								<a class="page-link" href="authoritySet${pageMaker.makeQuery(pageNum) }" >
									${pageNum}
								</a>
							</li>
							</c:forEach>	
							
							<li class="page-item">
								<a class="page-link" href="authoritySet
									<c:if test="${pageMaker.next }">
										${pageMaker.makeQuery(pageMaker.endPage+1) }
									</c:if>
									<c:if test="${!pageMaker.next }">
										${pageMaker.makeQuery(pageMaker.cri.page) }
									</c:if>
								">&gt;</a>
							</li>
							
							
							<li class="page-item">
								<a class="page-link" href="authoritySet${pageMaker.makeQuery(pageMaker.realEndPage) }">
									&gt;&gt;
								</a>
							</li>
						</ul>
						</div>
					</div>			

</div>




<script>
$(function() {
	startSet();

});


function memSearch(sVal) {
	alert('검색 시작!');
	
	var searchStr = $(sVal).val();
	var searchType = $('#sltMem').val();
	var varUrl='<%= request.getContextPath()%>/manage/authoritySet?str2='+searchStr+'&searchType='+searchType;
	

	$("#hlist").attr("href", varUrl);
 	$('#hlist').get(0).click();
	
	
}


function authUpdate(tag, mem, auth) {		/* tag : <select>태그 자체가 넘어온다. */
	var sltTag = tag.getAttribute("id");	/* <select>태그의 id값을 반환 */
	var sltValue = $('#'+sltTag).val();		/* <select>태그의 option값을 반환 */
	var sltAuth = $(auth).val();
 	var period = 0;   /* 제재회원의 제재기간을 할당하는 변수 */
 	
 	
 	switch(sltValue){
 	case '30일':
 		period = 30;
 		break;
 	case '60일':
 		period = 60;
 		break;
 	case '90일':
 		period = 90;
 		break;
 	}
	
	var data={mem:mem, auth:sltAuth, period:period};
	
	
	$.ajax({
		url:"<%= request.getContextPath()%>/manage/authUpdate",
		type:"post",
		data:JSON.stringify(data),
		contentType:'application/json',
		success: function() {
			alert('성공했습니다.');
			reload();
		},
		error : function() {
			alert('실패했습니다.');
		}
	});
	
	
	 
	 
	 
/* 
 	alert(period);
	alert('authUpdate() 시작');
	alert(mem);
	alert(sltAuth);	 
 */
	console.log(tag);
	console.log(sltTag);
	console.log(sltValue);
	
}





function startSet() {
	/* 
	alert('start()');
	*/
}



function sltPeriod(data, num) {
	var test = $(data).val();
	
	switch(test) {
	case '제재회원':
		document.getElementById("period"+ num).removeAttribute("disabled");
		$("#period"+num).append("<option>30일</option>");
		$("#period"+num).append("<option>60일</option>");
		$("#period"+num).append("<option>90일</option>");
		break;
	default:
		$("#period"+num).children().remove();
		document.getElementById("period"+ num).setAttribute("disabled", "disabled");
		break;	
	}
}
</script>


 
 
<!-- jQuery -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/demo.js"></script>




</body>
</html>