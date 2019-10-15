<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="festivalList" value="${dataList.festivalList }" />
<c:set var="reviewList" value="${dataList.reviewList }" />
<c:set var="togetherList" value="${dataList.togetherList }" />


    <table class="mt-5 col-10" style=" margin: 0 auto;">
        <c:if test="${empty festivalList }">
            <tr>
                <td colspan="5" class="text-center">
                    <strong>해당 내용이 없습니다.</strong>
                </td>
            </tr>
        </c:if>
        <c:if test="${!empty festivalList }">
            <c:forEach items="${festivalList }" var="festival" begin="1" end="4">
                <tr style="border-bottom: 0px solid black;cursor:default;">
                    <td style="width: 18%; text-align: center; border-bottom:1px solid #B9B9B9"><img src="<%=request.getContextPath()%>/resources/uploadImg/festival/${festival.f_writer }/${festival.unq_Id }.jpg" style="width:93px;height:70px"></td>
                    <td style="width: 59%; overflow:hidden; border-bottom:1px solid #B9B9B9">
                    
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
                    <td style="width: 27%; border-bottom:1px solid #B9B9B9">
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/like.png" style="width: 10%; float: left">
                            <p>　가고싶어요　${festival.vote1 }</p>
                        </span>
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/like.png" style="width: 10%; float: left">
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






 <table class="mt-5 col-10" style=" margin: 0 auto;">
        <c:if test="${empty reviewList }">
            <tr>
                <td colspan="5" class="text-center">
                    <strong>해당 내용이 없습니다.</strong>
                </td>
            </tr>
        </c:if>
        <c:if test="${!empty reviewList }">
            <c:forEach items="${reviewList }" var="review" begin="1" end="3">
                <tr style="border-bottom: 0px solid black;">
                    <td style="width: 18%;  text-align: center"><img src="<%=request.getContextPath()%>/resources/uploadImg/${review.id }/${review.unq_Id }.jpg" style="width:93px;height:70px"></td>
                    <td style="width: 59%;"> 
                        <a href="review/detail?rno=${review.rno }">
                        	<c:if test="${review.newCount == 2}"><span style="font-weight: bold;" >${review.r_title }&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/images/newArticle.png" style="width:30px;height:auto;"></span></c:if>
                    		<c:if test="${review.newCount != 2}"><span>${review.r_title }</span></c:if>
                    	                        
                        <c:if test="${review.commentcount>0}">[${review.commentcount }]</c:if></a>
                        <p>
                           <fmt:formatDate value="${review.r_regDate }" pattern="MM-dd HH:mm"/>&emsp;
                         ${review.nickname }</p>
                    </td>
                    <td style=";width: 27%">
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/like.png" style="width: 10%; float: left">
                            <p>　좋아요　${review.r_like }</p>
                        </span>
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/view.png" style="width: 10%; float: left">
                            <p>　조회수　${review.r_viewcnt }</p>
                        </span>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>






    <table class="mt-5 col-10" style=" margin: 0 auto;">
        <c:if test="${empty togetherList }">
            <tr>
                <td colspan="5" class="text-center">
                    <strong>해당 내용이 없습니다.</strong>
                </td>
            </tr>
        </c:if>
        
        <c:if test="${!empty togetherList }">
            <c:forEach items="${togetherList }" var="together" begin="1" end="3">
                <tr style="border-bottom: 0px solid black;">
                    
                   <c:if test="${together.t_state == 1 }">
                    	<td style="width: 16%;  text-align: center; font-weight: bold">[모집중]</td>
                   </c:if>
                   <c:if test="${together.t_state == 2 }">
                   	 <td style="width: 18%;  text-align: center; font-weight: bold; color: red;">[마감]</td>
                    </c:if> 
                    <td style="width: 59%;">
                     <c:set var="t_regDate" value="${together.t_regDate}" />                     
                        <a href="together/detail?tno=${together.tno }">
                        	<c:if test="${together.newCount == 2}"><span style="font-weight: bold;" >${together.t_title }&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/images/newArticle.png" style="width:30px;height:auto;"></span></c:if>
                    		<c:if test="${together.newCount != 2}"><span>${together.t_title }</span></c:if>
                    	
                        <c:if test="${together.commentcount>0}">[${together.commentcount }]</c:if></a>
                        <p><fmt:formatDate value="${together.t_regDate }" pattern="MM-dd HH:mm"/>&emsp;</p>
                        <div style="position:relative;">
                         <a class="user_Box" onclick="userBox($(this))">${together.nickname }</a>
                         </div>
                    </td>
                    <td style=";width: 27%">
                        <%-- <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/like.png" style="width: 10%; float: left">
                            <p>　좋아요　${together.r_like }</p>
                        </span> --%>
                        <span><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/view.png" style="width: 10%; float: left">
                            <p>　조회수　${together.t_viewcnt }</p>
                        </span>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>







<% session.invalidate(); %>