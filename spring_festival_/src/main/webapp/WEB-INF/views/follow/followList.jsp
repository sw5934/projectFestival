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
<div style="margin-left: 15px; margin-right: 15px; top: -30px; position: relative;">
<a id="hlist" href=""></a>

	<div class="mt-3 mb-1" style="padding: 0px; height:38px;"> 
	<div class="float-sm-right" style="padding: 0px;">
	<div class="float-sm-left" style="width: 140px; border-left:3px solid black; border-right:3px solid black; text-align:center; font-size:1.4em; background: #E4E4E4;">
	<a class="baminfont-Pro" href="<%=request.getContextPath()%>/follow/followList" style="color: black;">팔로우 목록</a></div>
	<div class="float-sm-left" style="width: 140px; border-right:3px solid black; text-align:center; font-size:1.4em;">
	<a class="baminfont-Pro" href="<%=request.getContextPath()%>/follow/f_write" style="color: black;">팔로워 활동</a></div></div>
	</div>
	<div class="col-11 mt-1 pt-4 pb-2" style="margin:0; top: 30px; overflow:hidden; border: 1px solid #D8D8D8;">
		<div class="col-6 float-sm-left"> 
			<div class="baminfont-Pro col-6 mb-2 ml-1" style="border-left:2px solid black;border-right:2px solid black;font-size:1.2em;text-align:center;">내 팔로워</div>
			<div style="border:1px solid #D8D8D8;overflow:hidden;height:220px;">
				<div style="height: 180px;">
				<table class="ml-3 mr-3 col-11" style="margin: 0 auto;">               
					<tr align="center" style="border-bottom: 1px solid #B9B9B9;">
						<td class="baminfont-Air" style="width: 40px; font-size:1.2em">닉네임</td>
					</tr>
					<c:if test="${empty followedList }">
						<tr>
							<td colspan="5" class="text-center" style="border-bottom: 1px solid #B9B9B9;">
								<strong>회원님을 팔로우 한 사람이 없습니다.</strong>
							</td>
						</tr>
					</c:if>
					<c:if test="${!empty followedList }">
						<c:forEach items="${followedList }" var="vl" begin="0" end="4" step="1">
			                    <tr align="center">
			                      	<td style="border-bottom: 1px solid #B9B9B9;height:28px; font-size:0.8em;">${vl.nickName}</td>
			                    </tr>
		    			</c:forEach>
		    		</c:if>
                </table>
                </div>
				<ul class="pagination pagination-sm" style="justify-content: center; margin-left: auto; margin-right: auto; bottom:10px"> 							

					<li class="page-item">
						<input type="button" value="&lt;&lt;" class="" style="border: 0px; background: none;" onclick="pageMove(1, 'followed' )"/>
					</li>
					
					<li class="page-item">
						<input type="button" value="&lt;" class="" style="border: 0px; background: none;" onclick='pageMove(${pageMakered.startPage-1 }, "followed")'/>
					</li>
					<c:forEach begin="${pageMakered.startPage }" end="${pageMakered.endPage }" var="pageNum">
					<li class="page-item <c:out value="${pageMakered.cri.second_page == pageNum ?'active':''}"/>">
					 <input type="button" id="followedBtn" value="${pageNum }" class="" style="border: 0px; background: none;" onclick="pageMove($(this).val(), 'followed')"/>
					</li>
					</c:forEach>	
					<li class="page-item">
						<input type="button" value="&gt;" class="" style="border: 0px; background: none;" onclick='
							<c:if test="${pageMakered.next }">
							pageMove(${pageMakered.endPage+1 }, "followed")
							</c:if>
							<c:if test="${!pageMakerMe.next }">
							pageMove(${pageMakered.cri.second_page }, "followed")
							</c:if>'/>
					</li>			
					
					<li class="page-item">
						<input type="button" value="&gt;&gt;" class="" style="border: 0px; background: none;" onclick='pageMove(${pageMakered.realEndPage},"followed")'/>
					</li>
			
				</ul>
			</div>
 		</div>
 		
		<div class="col-6 float-sm-left"> 	
			<div class="baminfont-Pro col-6 mb-2 ml-1" style="border-left:2px solid black;border-right:2px solid black;font-size:1.2em;text-align:center;">나의 팔로잉</div>
			<div style="border:1px solid #D8D8D8;overflow:hidden; height:220px;">
				<div style="height: 180px;">
				<table class="ml-3 mr-3 col-11" style="margin: 0 auto;">               
					<tr  align="center" style="border-bottom: 1px solid #B9B9B9;">
						<td class="baminfont-Air" style="width: 65px;font-size:1.2em;">닉네임</td>
						<td class="baminfont-Air" style="width: 15px;font-size:1.2em;">취소</td>
					</tr>  
					<c:if test="${empty followList }">
					<tr>
						<td colspan="5" class="text-center" style="border-bottom: 1px solid #B9B9B9;">
							<strong>회원님이 팔로우 한 사람이 없습니다.</strong>
						</td>
					</tr>
					</c:if>
					<c:if test="${!empty followList }">
					<c:forEach items="${followList }" var="vl" begin="0" end="4" step="1">
					<tr align="center">
						<td style="border-bottom: 1px solid #B9B9B9; font-size:0.8em;">${vl.nickName}</td>
						<td style="border-bottom: 1px solid #B9B9B9;"><button class="badge bg-danger" onclick='unFollow("${vl}","${myId }")'>X</button></td>
					</tr>
					</c:forEach>
					</c:if>
				</table>
				</div>
				<ul class="pagination pagination-sm" style="justify-content: center; margin-left: auto; margin-right: auto; bottom:10px"> 
					<li class="page-item">
						<input type="button" value="&lt;&lt;" class="" style="border: 0px; background: none;" onclick='pageMove(1, "follow")'/>
					</li>
					
					<li class="page-item">
						<input type="button" value="&lt;" class="" style="border: 0px; background: none;" onclick='
							<c:if test="${pageMaker.prev }">
								pageMove(${pageMaker.startPage-1 }, "follow")
							</c:if>'/>
					</li>
					<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
					<li class="page-item <c:out value="${pageMaker.cri.first_page == pageNum ?'active':''}"/>">
						<input type="button" id="followBtn" value="${pageNum }" class="" style="border: 0px; background: none;" onclick="pageMove($(this).val(), 'follow')" />
					</li>
					</c:forEach>	
					
					<li class="page-item">
						<input type="button" value="&gt;" class="" style="border: 0px; background: none;" onclick='
							<c:if test="${pageMaker.next }">
								pageMove(${pageMaker.endPage+1},"follow")
							</c:if>
							<c:if test="${!pageMaker.next }">
								pageMove(${pageMaker.cri.first_page},"follow")
							</c:if>'/>
					</li>			
					
					<li class="page-item">
						<input type="button" value="&gt;&gt;" class="" style="border: 0px; background: none;" onclick='pageMove(${pageMaker.realEndPage}, "follow")'/>
					</li>
					
				</ul>	
			</div>
		</div>
	</div>
</div>

<script>
  	function pageMove(this_page , type) {
   		var followed_now_page = ${pageMakered.cri.second_page};  // 나를 팔로우한 사람의 기존 페이지
   		var follow_now_page = ${pageMaker.cri.first_page};  // 내가 팔로우한 사람의 기존 페이지
   		var click_page = this_page; // 클릭한 페이지
   		
  		
   		
   		if (type=='follow') { // 클릭한 페이지가 follow라면 follow는 클릭한 페이지를,  followed는 기존 페이지를 로딩한다.
   				var varUrl='<%= request.getContextPath()%>/follow/followList/?first_page='+click_page
				+'&second_page='+followed_now_page
				+'&perPageNum=5';
		} else if(type=='followed') {
				var varUrl='<%= request.getContextPath()%>/follow/followList/?first_page='+follow_now_page
				+'&second_page='+click_page
				+'&perPageNum=5';
		}
   	
   		$("#hlist").attr("href", varUrl);
   	 	$('#hlist').get(0).click();
  	}
  	
  	
  	
  	
  	function unFollow(nName, myId) {  		
  		var data={nName:nName, myId:myId};
  		
  		$.ajax({
			url:"<%= request.getContextPath()%>/follow/unFollow",
			type:"post",
			data:JSON.stringify(data),
			contentType:'application/json',
			success:function(data){ /* data에는 menu.csv로부터 읽어들인 정보가 저장되어 있음 */
				location.reload();
			},
			error:function() {
			}
		});
  	}
</script>

</body>
</html>