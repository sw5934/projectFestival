<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="myId" value="${dataMap.myId}"/>
<c:set var="followList" value="${dataMap.follow}"/>
<c:set var="followedList" value="${dataMap.followed}"/>
<c:set var="pageMaker" value="${dataMap.pageMaker  }" />
<c:set var="pageMakered" value="${dataMap.pageMakered  }" />





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>팔로우 / 팔로워 목록</title>
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
           <div class="card">
              <div class="card-header">
                <h3 class="card-title">나를 팔로우 한 사람,  총 ${pageMakered.totalCount}명 ,  출력 ${ followedList.size() }명</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table class="table table-condensed">
                
                
                  <thead>                  
                    <tr  align="center">
                      <th style="width: 40px">닉네임</th>
                    </tr>
                  </thead>
                  <tbody>
						<c:if test="${empty followedList }">
							<tr>
								<td colspan="5" class="text-center">
									<strong>회원님을 팔로우 한 사람이 없습니다.</strong>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty followedList }">
							<c:forEach items="${followedList }" var="vl" begin="0" end="4" step="1">
				                    <tr  align="center">
				                      	<td>${vl}</td>
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
								<!-- 제일 첫번째 페이지로 이동하기 위한 href이다. makeQuery()를 통해 url을 반환받고, 클릭시 수행된다. -->
								<input type="button" value="&lt;&lt;" class="page-link" 
									onclick="pageMove(1, 'followed' )"/>
							</li>
							
							<li class="page-item">
								<input type="button" value="&lt;" class="page-link" 
									onclick='pageMove(${pageMakered.startPage-1 }, "followed")'/>
							</li>
							<c:forEach begin="${pageMakered.startPage }" 
							           end="${pageMakered.endPage }" var="pageNum">
							<li class="page-item <c:out value="${pageMakered.cri.second_page == pageNum ?'active':''}"/>"`>
							 <input type="button" id="followedBtn" value="${pageNum }"
									 class="page-link" onclick="pageMove($(this).val(), 'followed')"/>
							</li>
							</c:forEach>	
							
							
							<li class="page-item">
								<input type="button" value="&gt;" class="page-link" 
									onclick='
									<c:if test="${pageMakered.next }">
									pageMove(${pageMakered.endPage+1 }, "followed")
									</c:if>
									<c:if test="${!pageMakerMe.next }">
									pageMove(${pageMakered.cri.second_page }, "followed" )
									</c:if>
									'/>
							</li>			
							
							<li class="page-item">
								<input type="button" value="&gt;&gt;" class="page-link" 
									onclick='pageMove(${pageMakered.realEndPage},"followed")'/>
							</li>
					
						</ul>
						</div>
					</div>			



<!-- ////////////////////////////////////////////////////////////////////////////////////  -->

           <div class="card">
              <div class="card-header">
                <h3 class="card-title">내가 팔로우 한 사람,  총 ${pageMaker.totalCount}명,  출력 ${ followList.size() }명</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table class="table table-condensed">
                
                
                  <thead>                  
                    <tr  align="center">
                      <th style="width: 40px">닉네임</th>
                      <th style="width: 40px">팔로우 취소</th>
                    </tr>
                  </thead>
                  <tbody>
						<c:if test="${empty followList }">
							<tr>
								<td colspan="5" class="text-center">
									<strong>회원님이 팔로우 한 사람이 없습니다.</strong>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty followList }">
							<c:forEach items="${followList }" var="vl" begin="0" end="4" step="1">
				                    <tr  align="center">
				                      <td>${vl}</td>
				                      <td><button class="badge bg-danger" onclick='unFollow("${vl}","${myId }")'>X</button></td>
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
								onclick='pageMove(1, "follow")'/>
							</li>
							
							<li class="page-item">
								<input type="button" value="&lt;" class="page-link" 
									onclick='
									<c:if test="${pageMaker.prev }">
										pageMove(${pageMaker.startPage-1 }, "follow")
									</c:if>
									'/>
							</li>
							<c:forEach begin="${pageMaker.startPage }" 
							           end="${pageMaker.endPage }" var="pageNum">
							<li class="page-item <c:out value="${pageMaker.cri.first_page == pageNum ?'active':''}"/>"`>
								<input type="button" id="followBtn" value="${pageNum }" 
									class="page-link" onclick="pageMove($(this).val(), 'follow')" />
								</input>
							</li>
							</c:forEach>	
							
							<li class="page-item">
								<input type="button" value="&gt;" class="page-link" 
									onclick='
									<c:if test="${pageMaker.next }">
										pageMove(${pageMaker.endPage+1},"follow")
									</c:if>
									<c:if test="${!pageMaker.next }">
										pageMove(${pageMaker.cri.first_page},"follow")
									</c:if>
									'/>
							</li>			
							
							<li class="page-item">
								<input type="button" value="&gt;&gt;" class="page-link"
								 onclick='pageMove(${pageMaker.realEndPage}, "follow")'/>
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
   		var followed_now_page = ${pageMakered.cri.second_page};  // 나를 팔로우한 사람의 기존 페이지
   		var follow_now_page = ${pageMaker.cri.first_page};  // 내가 팔로우한 사람의 기존 페이지
   		var click_page = this_page; // 클릭한 페이지
   		
  		
		/* 
   		alert('나를 팔로워한 사람 - 기존페이지 : '+followed_now_page);
   		alert('내가 팔로우한 사람 - 기존페이지 : '+follow_now_page);
   		alert('내가 클릭한 페이지 : '+click_page);
   		alert('클릭 타입 : '+type); 
   		 */
   		
   		if (type=='follow') { // 클릭한 페이지가 follow라면 follow는 클릭한 페이지를,  followed는 기존 페이지를 로딩한다.
   				// alert('if == follow');
   				var varUrl='<%= request.getContextPath()%>/follow/followList/?first_page='+click_page
				+'&second_page='+followed_now_page
				+'&perPageNum=5';
		} else if(type=='followed') {
				// alert('if == followed');
				var varUrl='<%= request.getContextPath()%>/follow/followList/?first_page='+follow_now_page
				+'&second_page='+click_page
				+'&perPageNum=5';
		}
   	
   		$("#hlist").attr("href", varUrl);
   	 	$('#hlist').get(0).click();
  	}
  	
  	
  	
  	
  	function unFollow(nName, myId) {  		
  		<%-- 
  		alert('unFollow버튼을 클릭하셨습니다.');
  		alert('<%= request.getContextPath()%>');
  		alert('nName = ' + nName + '___ id = ' + myId);
		 --%>
  		
  		// key : value의 형태로 data에 값을 저장함으로 한번에 데이터를 컨트롤러에게 넘긴다.
  		var data={nName:nName, myId:myId};
 
  		
  		$.ajax({
			url:"<%= request.getContextPath()%>/follow/unFollow",
			type:"post",
			data:JSON.stringify(data),
			contentType:'application/json',
			success:function(data){ /* data에는 menu.csv로부터 읽어들인 정보가 저장되어 있음 */
				//alert('unFollow 성공하였습니다.');
				location.reload();
			},
			error:function() {
				//alert('unFollow 실패하였습니다.');
			}
		});
  	}
  </script>
</div>
</div>
</body>
</html>