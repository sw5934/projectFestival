<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="${dataMap.pageMaker }" var="pageMaker"/>
<c:set value="${dataMap.holdingList }" var="holdingList"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>개최자의 게시글 페이지</title>
</head>
<body>
<div id="wrap">
<%-- 
a : ${tagMap }<br/>
b : ${tagMap.size() }<br/>
c : ${tagMap.get("서울 억새 축제") }<br/><br/>


e : ${cmtMap }<br/>
f : ${cmtMap.size() }<br/>
g : ${cmtMap.get(1) }<br/><br/>

h : ${holdingMap }<br/>
i : ${holdingMap.size() }<br/><br/>
--%>
    <div class="card-body">
	<table class="table table-condensed">
		<tr>
			<th colspan="2" class="text-center">축제 제목</th>
		</tr>
		
		
		<c:if test="${holdingList.size() <= 0}">
			반환 값이 없어 예외 처리 되었습니다.
		</c:if>
		



		<c:if test="${holdingList.size() > 0}">
		<c:forEach begin="0" end="${holdingList.size() }" var="num" items="${holdingList }">
				<tr>
					<td class="text-left" width="30px">
						<div class="card-left"><img height="70px" width="70px"/></div>
					</td>
					<td class="text-left">
						<a href='<%= request.getContextPath()%>/review/detail/?fno=${num.fno }'>${num.title }
							<c:if test="${num.comments ne 0 }">
							( ${num.comments } )
							</c:if>
							<br/>
						</a>
						TAG : ${num.hashTag.get(0) }
					</td>
				</tr>
		</c:forEach>
		</c:if>
	</table>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
					<div class="card-footer clearfix"  >
						<div class="text-center">															
						<ul class="pagination pagination-sm m-0 float-right"> 
							
							<li class="page-item">
								<a class="page-link" href="holding${pageMaker.makeQuery(1)}">&lt;&lt;</a>
							</li>
							
							<li class="page-item">
								<a class="page-link" href="holding
									<c:if test="${pageMaker.prev }">
										${pageMaker.makeQuery(pageMaker.startPage-1) }
									</c:if>
								">&lt;</a>
							</li>
							<c:forEach begin="${pageMaker.startPage }" 
							           end="${pageMaker.endPage }" var="pageNum">
							<li class="page-item <c:out value="${pageMaker.cri.page == pageNum ?'active':''}"/>"`>
								<a class="page-link" href="holding${pageMaker.makeQuery(pageNum) }" >
									${pageNum}
								</a>
							</li>
							</c:forEach>	
							
							<li class="page-item">
								<a class="page-link" href="holding
									<c:if test="${pageMaker.next }">
										${pageMaker.makeQuery(pageMaker.endPage+1) }
									</c:if>
									<c:if test="${!pageMaker.next }">
										${pageMaker.makeQuery(pageMaker.cri.page) }
									</c:if>
								">&gt;</a>
							</li>			
							
							<li class="page-item">
								<a class="page-link" href="holding${pageMaker.makeQuery(pageMaker.realEndPage) }">
									&gt;&gt;
								</a>
							</li>
					
						</ul>
						</div>
					</div>	
</div>


<script>
</script>
</body>
</html>