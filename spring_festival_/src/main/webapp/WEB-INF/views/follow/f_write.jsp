<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--
	Map = { "three_board_list" , " wantGoList", "wantGoPM", "threeBoardPM" }
-->
<c:set var="three_board_list" value="${dataMap.three_board_list}"/>
<c:set var="wantGoList" value="${dataMap.wantGoList}"/>
<c:set var="wantGoPM" value="${dataMap.wantGoPM}"/>
<c:set var="threeBoardPM" value="${dataMap.threeBoardPM}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>팔로워의 작성 글</title>
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
<a id="hlist" href=""></a>

 <div class="wrapper">
		<div class="col-md-6">
           
           
           
           
           
           
           
           
          <!-- /////////////////////////////////////////////////////////////////////////// -->
<div class="card" style="width:800px">
              <div class="card-header">
                <h3 class="card-title">팔로워의 게시판(3개) 글, 총 ${threeBoardPM.totalCount}개의 글 중 ${three_board_list.size()}개만 출력</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body" >
                <table class="table table-condensed" border=" 0px black">
                  <thead>                  
                    <tr  align="center">
                      <th >카테고리</th>
                      <th >글 제목</th>
                      <th >닉네임</th>
                      <th >작성일</th>
                    </tr>
                  </thead>
                  <tbody>
						<c:if test="${empty three_board_list }">
							<tr>
								<td colspan="5" class="text-center">
									<strong>팔로워가 작성한 글이 없습니다.</strong>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty three_board_list }">
							<c:forEach items="${three_board_list }" var="vl">   <!-- begin="0" end="4" step="1" -->
				                    <tr  align="center">
				                      <td>${vl.boardType}</td>
				                      <td>${vl.title}</td>
				                      <td>${vl.nickName}</td>
				                      <td>${vl.writeDate}</td>
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
								<input type="button" value="&lt;&lt;" class="page-link" 
								onclick='pageMove(1, "threeBoard")'/>
							</li>
							
							<li class="page-item">
								<input type="button" value="&lt;" class="page-link" 
									onclick='
									<c:if test="${threeBoardPM.prev }">
										pageMove(${threeBoardPM.startPage-1 }, "threeBoard")
									</c:if>
									'/>
							</li>
							<c:forEach begin="${threeBoardPM.startPage }" 
							           end="${threeBoardPM.endPage }" var="pageNum">
							<li class="page-item <c:out value="${threeBoardPM.cri.first_page == pageNum ?'active':''}"/>"`>
								<input type="button" id="followBtn" value="${pageNum }" 
									class="page-link" onclick="pageMove($(this).val(), 'threeBoard')" />
								</input>
							</li>
							</c:forEach>	
							
							<li class="page-item">
								<input type="button" value="&gt;" class="page-link" 
									onclick='
									<c:if test="${threeBoardPM.next }">
										pageMove(${threeBoardPM.first_endPage+2},"threeBoard")
									</c:if>
									<c:if test="${!threeBoardPM.next }">
										pageMove(${threeBoardPM.cri.first_page},"threeBoard")
									</c:if>
									'/>
							</li>			
							
							<li class="page-item">
								<input type="button" value="&gt;&gt;" class="page-link"
								 onclick='pageMove(${threeBoardPM.realEndPage}, "threeBoard")'/>
							</li>
					
						</ul>
						</div>
					</div>			
		

<!-- //////////////////////////////////////////////////////////////////////////////////// -->
           
           
           
           
<div class="card" style="width:800px">
              <div class="card-header">
                <h3 class="card-title">팔로워의 가고싶어요, 총 ${wantGoPM.totalCount}개의 글 중 ${wantGoList.size()}개만 출력</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body" >
                <table class="table table-condensed" border=" 0px black">
                  <thead>                  
                    <tr  align="center">
                      <th >카테고리aa</th>
                      <th >글 제목</th>
                      <th >닉네임</th>
                      <th >작성일</th>
                    </tr>
                  </thead>
                  <tbody>
						<c:if test="${empty wantGoList }">
							<tr>
								<td colspan="5" class="text-center">
									<strong>팔로워가 작성한 글이 없습니다.</strong>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty wantGoList }">
							<c:forEach items="${wantGoList }" var="vl">  <!-- begin="0" end="4" step="1" -->
				                    <tr  align="center">
				                      <td>${vl.boardType}</td>
				                      <td>${vl.title}</td>
				                      <td>${vl.nickName}</td>
				                      <td>${vl.writeDate}</td>
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
								<input type="button" value="&lt;&lt;" class="page-link" 
								onclick='pageMove(1, "wantGo")'/>
							</li>
							
							<li class="page-item">
								<input type="button" value="&lt;" class="page-link" 
									onclick='
									<c:if test="${wantGoPM.prev }">
										pageMove(${wantGoPM.startPage-1 }, "wantGo")
									</c:if>
									'/>
							</li>
							<c:forEach begin="${wantGoPM.startPage }" 
							           end="${wantGoPM.endPage }" var="pageNum">
							<li class="page-item <c:out value="${wantGoPM.cri.second_page == pageNum ?'active':''}"/>"`>
								<input type="button" id="followBtn" value="${pageNum }" 
									class="page-link" onclick="pageMove($(this).val(), 'wantGo')" />
								</input>
							</li>
							</c:forEach>	
							
							<li class="page-item">
								<input type="button" value="&gt;" class="page-link" 
									onclick='
									<c:if test="${wantGoPM.next }">
										pageMove(${wantGoPM.second_endPage+1},"wantGo")
									</c:if>
									<c:if test="${!wantGoPM.next }">
										pageMove(${wantGoPM.cri.first_page},"wantGo")
									</c:if>
									'/>
							</li>			
							
							<li class="page-item">
								<input type="button" value="&gt;&gt;" class="page-link"
								 onclick='pageMove(${wantGoPM.realEndPage}, "wantGo")'/>
							</li>
					
						</ul>
						</div>
					</div>			
		


<!-- jQuery -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/demo.js"></script>

  
  <script>
	function pageMove(this_page , type) {
   		var wantGO_now_page = ${wantGoPM.cri.second_page};  // 나를 팔로우한 사람의 기존 페이지
   		var threeBoard_now_page = ${threeBoardPM.cri.first_page};  // 내가 팔로우한 사람의 기존 페이지
   		var click_page = this_page; // 클릭한 페이지
   		
  		
/* 
   		alert('게시판 3개 - 기존페이지 : '+threeBoard_now_page);
   		alert('가고싶어요 - 기존페이지 : '+wantGO_now_page);
   		alert('내가 클릭한 페이지 : '+click_page);
   		alert('클릭 타입 : '+type); 
   		 */
   		
   		if (type=='threeBoard') { // 클릭한 페이지가 follow라면 follow는 클릭한 페이지를,  followed는 기존 페이지를 로딩한다.
   				// alert('if == threeBoard');
   				var varUrl='<%= request.getContextPath()%>/follow/f_write/?first_page='+click_page
				+'&second_page='+wantGO_now_page
				+'&perPageNum=5';
		} else if(type=='wantGo') {
				// alert('if == wantGo');
				var varUrl='<%= request.getContextPath()%>/follow/f_write/?first_page='+threeBoard_now_page
				+'&second_page='+click_page
				+'&perPageNum=5';
		}
   	
   		$("#hlist").attr("href", varUrl);
   	 	$('#hlist').get(0).click();
  	}
  	
  	 
  </script>

</div>
</div>
</body>
</html>