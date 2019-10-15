<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="festivalList" value="${dataMap.festivalList }" />
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="listSort" value="${dataMap.listSort }" />
<c:set var="cri" value="${dataMap.cri }" />
<c:set var="page" value="${dataMap.page }" />
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
    
    
    <div class="box pt-3 col-10" style="left: 0px; right: 0px; margin-left:auto; margin-right:auto;">
        <a class="col-10 baminfont-Pro mt-1" style="font-size: 1.6em; padding: 0px">축제 게시판
        
        ${Date}

</a>
    </div>
    <div class="col-10 mt-3 reviewHeader" style="overflow: hidden; margin: 0 auto;"> 
        <!-- 정렬 이벤트, 글 작성 이벤트 넣기 -->
         <div class="float-sm-left col-4 mt-1 p-0">
            <button id="newBtn" class="reviewRegist ml-2" onclick="location.href='festivalRegist'">글 작성</button></div>
        <div class="float-sm-left col-2 reviewHeaderSort"><a href="?listSort=fno">최신 일자</a></div>
        <div class="float-sm-left col-2 reviewHeaderSort"><a href="?listSort=f_viewCnt">조회 수</a></div>
        <div class="float-sm-left col-2 reviewHeaderSort"><a href="?listSort=vote1">가고싶어요</a></div>
        <div class="float-sm-left col-2 reviewHeaderSort" style="border: 0"><a href="?listSort=vote2">다녀왔어요</a></div>
    </div>
    
    <div style="width: 100%; margin-top: 10px;">
    <form class="col-12" style="width: 700px;float: right; margin-right: 80px;" id="listForm" action="<%=request.getContextPath() %>/festival/list" method="get">
	    <select name="searchType">
	    	<option value="tcw">전체 </option>
	    	<option value="f_title" <c:if test='${cri.searchType =="f_title"}'>selected</c:if> >글 제목 </option>
	    	<option value="f_name" <c:if test='${cri.searchType =="f_name"}'>selected</c:if> >축제 명 </option>
	    	<option value="f_content" <c:if test='${cri.searchType =="f_content"}'>selected</c:if> >글 내용 </option>
	    	<option value="f_org" <c:if test='${cri.searchType =="f_org"}'>selected</c:if> >개최기관 </option>
	    	<option value="hashTag" <c:if test='${cri.searchType =="hashTag"}'>selected</c:if> >해시태그 </option>
	    </select>
    	<input type="text" name="keyword" value="${cri.keyword }">
    	<label>개최지</label>
    	<select name="searchType2">
    		<option value="전국">전국</option>
    		<option value="서울특별시" <c:if test='${cri.searchType2 =="서울특별시"}'>selected</c:if> >서울특별시</option>
           	<option value="인천광역시" <c:if test='${cri.searchType2 =="인천광역시"}'>selected</c:if> >인천광역시</option>
           	<option value="대전광역시" <c:if test='${cri.searchType2 =="대전광역시"}'>selected</c:if> >대전광역시</option>
           	<option value="광주광역시" <c:if test='${cri.searchType2 =="광주광역시"}'>selected</c:if> >광주광역시</option>
           	<option value="울산광역시" <c:if test='${cri.searchType2 =="울산광역시"}'>selected</c:if> >울산광역시</option>
           	<option value="대구광역시" <c:if test='${cri.searchType2 =="대구광역시"}'>selected</c:if> >대구광역시</option>
           	<option value="부산광역시" <c:if test='${cri.searchType2 =="부산광역시"}'>selected</c:if> >부산광역시</option>
           	<option value="세종특별자치시" <c:if test='${cri.searchType2 =="세종특별자치시"}'>selected</c:if> >세종특별자치시</option>
           	<option value="경기도" <c:if test='${cri.searchType2 =="경기도"}'>selected</c:if> >경기도</option>
           	<option value="강원도" <c:if test='${cri.searchType2 =="강원도"}'>selected</c:if> >강원도</option>
           	<option value="충청북도" <c:if test='${cri.searchType2 =="충청북도"}'>selected</c:if> >충청북도</option>
           	<option value="충청남도" <c:if test='${cri.searchType2 =="충청남도"}'>selected</c:if> >충청남도</option>
           	<option value="전라북도" <c:if test='${cri.searchType2 =="전라북도"}'>selected</c:if> >전라북도</option>
           	<option value="전라남도" <c:if test='${cri.searchType2 =="전라남도"}'>selected</c:if> >전라남도</option>
           	<option value="경상북도" <c:if test='${cri.searchType2 =="경상북도"}'>selected</c:if> >경상북도</option>
           	<option value="경상남도" <c:if test='${cri.searchType2 =="경상남도"}'>selected</c:if> >경상남도</option> 
    	</select>
    	
    	<label>축제 유형</label>
    	<select name="searchType3">
    		<option value="전체">전체</option>
    		<option value="참여" <c:if test='${cri.searchType3 =="참여"}'>selected</c:if> >참여</option>
            	<option value="관람" <c:if test='${cri.searchType3 =="관람"}'>selected</c:if> >관람</option>
            	<option value="전시" <c:if test='${cri.searchType3 =="전시"}'>selected</c:if> >전시</option>
            	<option value="기타" <c:if test='${cri.searchType3 =="기타"}'>selected</c:if> >기타</option>
    	</select>
    	<input type="hidden" name="listSort" value="${listSort}">
    	<button type="button" onclick="$('#listForm').submit()">검색</button>
    </form>
    </div>
    
    <table class="mt-5 col-10" style=" margin: 0 auto;">
        <c:if test="${empty festivalList }">
            <tr>
                <td colspan="5" class="text-center">
                    <strong>해당 내용이 없습니다.</strong>
                </td>
            </tr>
        </c:if>
        <c:if test="${!empty festivalList }">
            <c:forEach items="${festivalList }" var="festival">
                <tr style="border-bottom: 0px solid black;cursor:default;">
                    <td style="width: 7%;  text-align: center; border-bottom:1px solid #B9B9B9">${festival.fno }</td>
                    <td style="width: 16%; text-align: center; border-bottom:1px solid #B9B9B9"><img src="<%=request.getContextPath()%>/resources/uploadImg/festival/${festival.f_writer }/${festival.unq_Id }.jpg" style="width:93px;height:70px"></td>
                    <td style="width: 56%; overflow:hidden; border-bottom:1px solid #B9B9B9">
                    
                         <div class="col-12 m-0 p-0" style="cursor:default;">개최기간 : [${festival.f_period }]</div>
                         <div class="col-12 m-0 p-0">
                         	<a href="detail?fno=${festival.fno }&listSort=${listSort}&page=${page}">${festival.f_title } <c:if test="${festival.commentCount>0}">[${festival.commentCount }]</c:if></a>
                         </div>
                         <div class="col-12 m-0 p-0" style="cursor:default;">축제 명 : ${festival.f_name }</div>
                         <div class="col-12 m-0 p-0" style="overflow:hidden;"> 
	                         <div class="col-6 m-0 p-0 float-sm-left">장소 : <a href="<%=request.getContextPath()%>/festival/list?searchType2=${festival.f_location1}">${festival.f_location1}${festival.f_location2 }</a></div>
	                         <div class="col-6 m-0 p-0 float-sm-left">주최·주관 : <a href="<%=request.getContextPath()%>/festival/list?searchType=f_org&keyword=${festival.f_org}">${festival.f_org}</a></div>
                         </div>
                         
                         <div class="col-12 m-0 p-0" style="overflow:hidden;">
	                         <div class="col-6 m-0 p-0 float-sm-left">
	                         	참여 유형 : <a href="<%=request.getContextPath()%>/festival/list?searchType3=${festival.f_type}">${festival.f_type}</a>
	                         </div> 
	                         <div class="col-6 m-0 p-0 float-sm-left"> 
		                         <c:if test="${festival.scoreAvg==0}">
		                         	후기 없음  
		                         </c:if>
		                         <c:if test="${festival.scoreAvg>=1&&festival.scoreAvg<2}">
		                         	후기 평점 : ★☆☆☆☆
		                         </c:if>
		                         <c:if test="${festival.scoreAvg>=2&&festival.scoreAvg<3}">
		                         	후기 평점 : ★★☆☆☆
		                         </c:if>
		                         <c:if test="${festival.scoreAvg>=3&&festival.scoreAvg<4}">
		                         	후기 평점 : ★★★☆☆
		                         </c:if>
		                         <c:if test="${festival.scoreAvg>=4&&festival.scoreAvg<5}">
		                         	후기 평점 : ★★★★☆
		                         </c:if>
		                         <c:if test="${festival.scoreAvg>=5}">
		                         	후기 평점 : ★★★★★
		                         </c:if>
	                         </div>
                         </div> 
                         
                          
                         <c:if test="${festival.hashTagList.size()!=0}"> 
	                         <div class="col-12 m-0 p-0"> 
	                         <a>축제 관련 태그 : </a>
	                         	<c:forEach items="${festival.hashTagList}" var="tag">
	                         		<a href="<%=request.getContextPath() %>/festival/list?searchType=hashTag&keyword=${tag.hashTag }"> #${tag.hashTag } </a>
	                         	</c:forEach>
	                         </div>
                         </c:if> 
                         
                    </td>
                    <td style="width: 25%; border-bottom:1px solid #B9B9B9">
                        <span><img src="<%=request.getContextPath()%>/resources/images/willgo.png" style="width: 10%; height: 25px; float: left">
                            <p>　가고싶어요　${festival.vote1 }</p>
                        </span>
                        <span><img src="<%=request.getContextPath()%>/resources/images/visit.png" style="width: 10%; height: 20px; float: left">
                            <p>　다녀왔어요　${festival.vote2 }</p>
                        </span>
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/view.png" style="width: 10%; float: left">
                            <p>　조회수　${festival.f_viewCnt }</p>
                        </span>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <div class="mt-3 col-10" style="margin:0 auto">
        <div class="text-center">
            <ul class="pagination">   
            
                <c:if test="${pageMaker.prev }">
                	<li class="page-item">
                    	<a class="page-link" href="list${pageMaker.makeQuery(1)}">&lt;&lt;</a>
                    </li>
                </c:if>
                    
                <c:if test="${pageMaker.prev }"> 
                	<li class="page-item">
                		<a class="page-link" href="list${pageMaker.makeQuery(pageMaker.startPage-1) }">&lt;</a>
                	</li>
                </c:if>
                
                <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
                    <li class="page-item <c:out value=" ${pageMaker.cri.page==pageNum ?'active':''}" />">
                    <a class="page-link" href="list${pageMaker.makeQuery(pageNum) }">
                        ${pageNum }
                    </a>
                    </li>
                </c:forEach>
                
                <c:if test="${pageMaker.next }">
	                <li class="page-item">
	                    <a class="page-link" href="list${pageMaker.makeQuery(pageMaker.endPage+1) }">&gt;
	                    </a>
	                </li>
                </c:if>
                
                 <c:if test="${pageMaker.next }">
	                <li class="page-item">
	                    <a class="page-link" href="list${pageMaker.makeQuery(pageMaker.realEndPage) }">
	                        &gt;&gt;
	                    </a>
	                </li>
                </c:if>
            </ul>
        </div>
    </div>    
</body>

</html>