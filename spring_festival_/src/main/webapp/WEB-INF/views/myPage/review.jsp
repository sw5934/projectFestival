<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="myId"    value="${dataMap.myId }"/>
<c:set var="reviewList" value="${dataMap.myReviewList }" />
<c:set var="pageMaker" value="${dataMap.pageMaker }" />




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>리뷰 게시판   </title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
  
  




</head>
<body>
 <div class="wrapper">
		<div class="col-md-6">
           <div class="card">
              <div class="card-header">
                <h3 class="card-title">나의 리뷰(후기) 목록</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body" style="width:700px">
                <table class="table table-condensed" > <!-- border="solid 1px black" -->
                
                
                  <thead>                  
                    <tr  align="center">
                      <th>글 번호</th>
                      <th>제  목</th>
                      <th>작성일자</th>
                    </tr>
                  </thead>
                  <tbody>
						<c:if test="${empty reviewList }">
							<tr>
								<td colspan="5" class="text-center">
									<strong>회원님이 작성한 후기 글이 없습니다.</strong>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty reviewList }">
							<c:forEach items="${reviewList }" var="rList"> <!--  begin="0" end="10" step="1" -->
				                    <tr  align="center">
				                      	<td width="100px" >${rList.bno}</td>
				                      	<td width="400px"><a href='<%= request.getContextPath()%>/review/detail/?rno=${rList.bno }'>${rList.title}(${rList.comments })</a></td>
				                      	<td width="100px">a${rList.regDate}</td>
				                    </tr>
			    			</c:forEach>
			    		</c:if>
                  </tbody>
                </table>
              </div>
           </div>




					<div class="card-footer clearfix"  >
						<div class="text-center">															
						<ul class="pagination pagination-sm m-0 float-right"> 
							
							<li class="page-item">
								<a class="page-link" href="review${pageMaker.makeQuery(1)}">&lt;&lt;</a>
							</li>
							
							<li class="page-item">
								<a class="page-link" href="review
									<c:if test="${pageMaker.prev }">
										${pageMaker.makeQuery(pageMaker.startPage-1) }
									</c:if>
								">&lt;</a>
							</li>
							<c:forEach begin="${pageMaker.startPage }" 
							           end="${pageMaker.endPage }" var="pageNum">
							<li class="page-item <c:out value="${pageMaker.cri.page == pageNum ?'active':''}"/>"`>
								<a class="page-link" href="review${pageMaker.makeQuery(pageNum) }" >
									${pageNum}
								</a>
							</li>
							</c:forEach>	
							
							<li class="page-item">
								<a class="page-link" href="review
									<c:if test="${pageMaker.next }">
										${pageMaker.makeQuery(pageMaker.endPage+1) }
									</c:if>
									<c:if test="${!pageMaker.next }">
										${pageMaker.makeQuery(pageMaker.cri.page) }
									</c:if>
								">&gt;</a>
							</li>			
							
							<li class="page-item">
								<a class="page-link" href="review${pageMaker.makeQuery(pageMaker.realEndPage) }">
									&gt;&gt;
								</a>
							</li>
					
						</ul>
						</div>
					</div>			


<!-- ////////////////////////////////////////////////////////////////////////////////////  -->






<!-- jQuery -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/demo.js"></script>


</div>
</div>	
</body>
</html>