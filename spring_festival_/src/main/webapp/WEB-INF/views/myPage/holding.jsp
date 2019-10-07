<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="${dataMap.pageMaker }" var="pageMaker"/>
<c:set value="${dataMap.cmtMap }" var="cmtMap"/>
<c:set value="${dataMap.tagMap }" var="tagMap"/>
<c:set value="${dataMap.holdingMap }" var="holdingMap"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>개최자의 게시글 페이zz지</title>
</head>
<body>
<div id="wrap">

a : ${tagMap }<br/>
b : ${tagMap.size() }<br/>
c : ${tagMap.get("서울 억새 축제") }<br/><br/>


e : ${cmtMap }<br/>
f : ${cmtMap.size() }<br/>
g : ${cmtMap.get(1) }<br/><br/>

h : ${holdingMap }<br/>
i : ${holdingMap.size() }<br/><br/>

	<table>
		<tr>
			<th>축제 ㅋㅋ제목</th>
		</tr>
		
		<tr>
			<td>
				<c:forEach begin="0" end="${holdingMap.size()-1 }" var="num" items="${holdingMap }">
				${num }(${cmtMap.get(num) })<br/>
				TAG : 
					<c:forEach items="${tagMap.get(num) }" var="tag">　${tag},
					</c:forEach><br/>
				</c:forEach>
			</td>
		</tr>

	</table>
</div>


<script>
</script>
</body>
</html>