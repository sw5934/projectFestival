<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="review" value="${dataMap.review }" />
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<!DOCTYPE html>
<html>

<head>
   
    <meta charset="UTF-8">
    <title>축제 게시판</title>
</head>

<body>

 <style>
        .reviewHeaderSort {
            font-family: bamin-hanna-Pro;
            font-size: 1.4em;
            text-align: center;
            border-right: 3px solid darkslategrey;

            padding: 0px
        }


        a:link {
            color: darkslategrey;
            text-decoration: none;
        }

        a:visited {
            color: darkslategrey;
            text-decoration: none;
        }

        a:hover {
            color: darkorange;
            text-decoration: none;
        }

        .reviewRegist {
            font-family: bamin-hanna-Pro;
            font-size: 1.2em;
            line-height: inherit;
            padding: 5px 15px;
            border: 1px solid darkslategrey;
            border-radius: 3%;
        }

        table {
            border-collapse: separate;
            border-spacing: 0 10px;
        }
    </style>
    
    
    
    
   
    <div class="mt-3 col-10" style="margin:0 auto">
    <div class="col-12" style="border-bottom: 3px solid black; overflow:hidden;">
	    <div class="float-sm-left col-3">${review.rno}</div>
	    <div class="float-sm-left col-6">${review.r_title }</div>
	    <div class="float-sm-left col-3">${review.id }</div>
    </div>
    <div class="col-12" style="border-bottom: 3px solid black; overflow:hidden;">
	    <div class="float-sm-left col-9">${review.r_title }</div>
	    <div class="float-sm-left col-3">${review.r_regDate }</div>
    </div>
    
    <div class="col-12" style="overflow:hidden;">
	    <a class="float-sm-right">평점모듈</a>
    </div>
    
    
    <div class="col-12 pl-5 pr-5">
	    <div>${review.r_content }</div>
    </div>
    
    
    <div class="col-12 mb-5" style="border-bottom: 3px solid black; overflow:hidden;">
	    <div class="float-sm-left col-4">목록</div>
	    <div class="float-sm-left col-1">${review.r_like }</div>
	    <div class="float-sm-left col-1">신고</div>
	    <div class="float-sm-left col-3">수정</div>
	    <div class="float-sm-left col-1">삭제</div>
    </div>
    
    </div>
</body>

</html>