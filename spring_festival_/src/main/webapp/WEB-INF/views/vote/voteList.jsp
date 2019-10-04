<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- wantGo(가고싶어요) : First  //  goAndBack(다녀왔어요) : Second  -->
<c:set var="wantGoList" value="${voteListMap.wantGoList}" />
<c:set var="goAndBackList" value="${voteListMap.goAndBackList}" />
<c:set var="wantGoPM" value="${voteListMap.wantGoPM}" />
<c:set var="goAndBackPM" value="${voteListMap.goAndBackPM}" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>가고싶어요 / 다녀왔어요</title>
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

<body class="hold-transition sidebar-mini">

<div class="wrapper">
            <div class="col-11" style="margin: 0 auto;">
                <div class="baminfont-Pro" style="font-size:1.2em">가고 싶어요</div>
              <div class="">
                <table class="table table-condensed p-0">
                  <thead>                  
                    <tr class="p-0" align="center" style="font-size:0.9em">
                      <th class="p-0" style="width: 100px">번호</th>
                      <th class="p-0">축제 이름</th>
                      <th class="p-0">저장일시</th> 
                      <th class="p-0" style="width: 90px">취소</th>
                    </tr>
                  </thead>
                  <tbody>
						<c:if test="${empty wantGoList }">
							<tr>
								<td colspan="5" class="text-center">
									<strong>해당 내용이 없습니다.</strong>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty wantGoList }">
							<c:forEach items="${wantGoList }" var="vl" begin="0" end="4" step="1">
			                    <c:if test="${vl.f_seperate==1 }"><!-- 1:가고싶어요 -->
				                    <tr  class="p-1" align="center" style="font-size:0.9em">
				                      <td  class="p-1">${vl.unq_id}</td>
				                      <td  class="p-1" align="left">${vl.f_content}</td>
				                      <td  class="p-1">${vl.chkdate}</td>
				                      <td  class="p-1"><button class="badge bg-danger" onclick='unVote("${vl.id}", "${vl.unq_id}")'>X</button></td>
				                    </tr>
				                </c:if>
			    			</c:forEach>
			    		</c:if>
                  </tbody>
                </table>
              </div>
					<div class="clearfix"  >
						<div class="text-center">															
						<ul class="pagination pagination-sm m-0 float-right"> 							
							<li class="page-item">
								<!-- 제일 첫번째 페이지로 이동하기 위한 href이다. makeQuery()를 통해 url을 반환받고, 클릭시 수행된다. -->
								<input type="button" value="&lt;&lt;" class="page-link" 
									onclick="pageMove(1, 'wantGo' )"/>
							</li>
							
							<li class="page-item">
								<input type="button" value="&lt;" class="page-link" 
									onclick='pageMove(${wantGoPM.startPage-1 }, "wantGo")'/>
							</li>
							<c:forEach begin="${wantGoPM.startPage }" 
							           end="${wantGoPM.endPage }" var="pageNum">
							<li class="page-item <c:out value="${wantGoPM.cri.second_page == pageNum ?'active':''}"/>">
							 <input type="button" id="followedBtn" value="${pageNum }"
									 class="page-link" onclick="pageMove($(this).val(), 'wantGo')"/>
							</li>
							</c:forEach>	
							
							
							<li class="page-item">
								<input type="button" value="&gt;" class="page-link" 
									onclick='
									<c:if test="${wantGoPM.next }">
									pageMove(${wantGoPM.endPage+1 }, "wantGo")
									</c:if>
									<c:if test="${!wantGoPM.next }">
									pageMove(${wantGoPM.cri.second_page }, "wantGo" )
									</c:if>
									'/>
							</li>			
							
							<li class="page-item">
								<input type="button" value="&gt;&gt;" class="page-link" 
									onclick='pageMove(${wantGoPM.realEndPage},"wantGo")'/>
							</li>
					
						</ul>
						</div>
					</div>			


            
            
<!-- //////////////////////////////////////////////////////////////////////////////////////// -->
            
           <div class="">
              <div class="">
                <div class="baminfont-Pro" style="font-size:1.2em">다녀 왔어요</div>
              </div>
              <!-- /.card-header -->
              <div class="">
                <table class="table table-condensed">
                
                
                  <thead>                  
                    <tr class="p-0"  align="center" style="font-size:0.9em">
                      <th class="p-0" style="width: 100px">번호</th>
                      <th class="p-0">축제 이름</th>
                      <th class="p-0">저장일시</th>
                      <th class="p-0" style="width: 90px">취소</th>
                    </tr>
                  </thead>
                  <tbody>
						<c:if test="${empty goAndBackList }">
							<tr>
								<td colspan="5" class="text-center">
									<strong>해당 내용이 없습니다.</strong>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty goAndBackList }">
							<c:forEach items="${goAndBackList }" var="vl" begin="0" end="4" step="1">
								<c:if test="${vl.f_seperate==2 }">
				                    <tr  class="p-1" align="center" style="font-size:0.9em">
				                      <td class="p-1">${vl.unq_id}</td>
				                      <td class="p-1" align="left">${vl.f_content}</td>
				                      <td class="p-1">${vl.chkdate}</td>
				                      <td class="p-1"><button class="badge bg-danger" onclick='unVote("${vl.id}", "${vl.unq_id}")'>X</button></td>
				                    </tr>
			                    </c:if>
			    			</c:forEach>
			    		</c:if>
                  </tbody>
                </table>
              </div>
              



            



					<div class=" clearfix"  >
						<div class="text-center">															
						<ul class="pagination pagination-sm m-0 float-right"> 							
							<li class="page-item">
								<!-- 제일 첫번째 페이지로 이동하기 위한 href이다. makeQuery()를 통해 url을 반환받고, 클릭시 수행된다. -->
								<input type="button" value="&lt;&lt;" class="page-link" 
									onclick="pageMove(1, 'goAndBack' )"/>
							</li>
							
							<li class="page-item">
								<input type="button" value="&lt;" class="page-link" 
									onclick='pageMove(${goAndBackPM.startPage-1 }, "goAndBack")'/>
							</li>
							<c:forEach begin="${goAndBackPM.startPage }" 
							           end="${goAndBackPM.endPage }" var="pageNum">
							<li class="page-item <c:out value="${goAndBackPM.cri.second_page == pageNum ?'active':''}"/>">
							 <input type="button" id="followedBtn" value="${pageNum }"
									 class="page-link" onclick="pageMove($(this).val(), 'goAndBack')"/>
							</li>
							</c:forEach>	
							
							
							<li class="page-item">
								<input type="button" value="&gt;" class="page-link" 
									onclick='
									<c:if test="${goAndBackPM.next }">
									pageMove(${goAndBackPM.endPage+1 }, "goAndBack")
									</c:if>
									<c:if test="${!goAndBackPM.next }">
									pageMove(${goAndBackPM.cri.second_page }, "goAndBack" )
									</c:if>
									'/>
							</li>			
							
							<li class="page-item">
								<input type="button" value="&gt;&gt;" class="page-link" 
									onclick='pageMove(${goAndBackPM.realEndPage},"goAndBack")'/>
							</li>
					
						</ul>
						</div>
					</div>			


        
        
            </div>
		</div>
</div>
  <script>
	function pageMove(this_page , type) {
   		var goAndBack_now_page = ${goAndBackPM.cri.second_page};  // 나를 팔로우한 사람의 기존 페이지
   		var wantGo_now_page = ${wantGoPM.cri.first_page};  // 내가 팔로우한 사람의 기존 페이지
   		var click_page = this_page; // 클릭한 페이지
   		
  		

   		alert('다녀왔어요 - 기존페이지 : '+goAndBack_now_page);
   		alert('가고싶어요 - 기존페이지 : '+wantGo_now_page);
   		alert('내가 클릭한 페이지 : '+click_page);
   		alert('클릭 타입 : '+type); 
   		
   		
   		if (type=='follow') { // 클릭한 페이지가 follow라면 follow는 클릭한 페이지를,  followed는 기존 페이지를 로딩한다.
   				alert('if == follow');
   				var varUrl='<%= request.getContextPath()%>/follow/followList/?first_page='+click_page
				+'&second_page='+goAndBack_now_page
				+'&perPageNum=5';
		} else if(type=='followed') {
				alert('if == followed');
				var varUrl='<%= request.getContextPath()%>/follow/followList/?first_page='+wantGo_now_page
				+'&second_page='+click_page
				+'&perPageNum=5';
		}
   	
   		$("#hlist").attr("href", varUrl);
   	 	$('#hlist').get(0).click();
  	}
 
  
  
  
  
  	function unVote(id, u_id) {
  		alert('deleteVote 버튼을 클릭하셨습니다.');
  		alert('<%= request.getContextPath()%>');
  		alert('id = ' + id);
  		alert('u_id = ' + u_id);
		
  		
  		var data = {id:id, u_id:u_id};
  		
  		$.ajax({
			url:"<%= request.getContextPath()%>/vote/deleteVote",
			type:"post",
			data:JSON.stringify(data),
			contentType:'application/json',
			success:function(data){
				alert('deleteVote 성공하였습니다.');
				location.reload();
			},
			error:function() {
				alert('deleteVote 실패하였습니다.');
			}
		});
  		
  	}
  </script>
<!-- ./wrapper -->

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
