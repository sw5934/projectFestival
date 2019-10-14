<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</style>
</head>

<body class="hold-transition sidebar-mini layout-top-nav">
    <div class="wrapper">
        <header class="card">
            <div class="headerDiv" style="width:1200px; margin:0 auto">
                <div class="float-sm-right col-12 pb-2">
                    <div class="mr-5 pr-5">
                    <c:if test="${loginUser==null}">
                        <a class="nav-link float-sm-right" href="<%=request.getContextPath()%>/login">로그인</a>
                        <a class="nav-link float-sm-right" href="<%=request.getContextPath()%>/signUp">회원가입</a>
                        <a class="nav-link float-sm-right" href="<%=request.getContextPath()%>/findID">ID/PW찾기</a>
                    </c:if>
                    <c:if test="${loginUser!=null}">                    
                        <a class="nav-link float-sm-right" onClick="window.open('<%=request.getContextPath()%>/member/myInfo?id=${loginUser.id}', '마이 페이지', 'height=700,width=1000,resizable=0');" style="cursor:pointer;color:blue;">마이페이지</a>
                        <a class="nav-link float-sm-right" href="<%=request.getContextPath()%>/logOut">로그아웃</a>
                        <a class="nav-link float-sm-right"><b>${loginUser.nickName }</b>&nbsp;<span>님 어서 오세요</span></a>
                    </c:if>
                    </div>
                </div>
                <div class="float-sm-left col-12">
                    <!-- Navbar -->
                    <div class="float-sm-left col-3 pl-5"><a href="<%=request.getContextPath()%>/login"><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/logo.png" width="200px" class=" ml-5"></a></div>
                    <div class="float-sm-left col-9 pr-5">
                        <div>
                            <div class="float-sm-left col-12">
                                <!-- SEARCH FORM -->
                                <div class="form-inline  float-sm-right mr-5"> 
                                    <div class="input-group input-group-sm">
                                    <form  id="searchForm" action="<%=request.getContextPath()%>/totalSearch" method="get">
                                        <select name="searchType" class="mr-3">
                                            <option value="tcw" <c:if test='${searchCri.searchType =="tcw"}'>selected</c:if> >통합검색</option>
                                            <option value="title" <c:if test='${searchCri.searchType =="title"}'>selected</c:if> >제목</option>
                                            <option value="content" <c:if test='${searchCri.searchType =="content"}'>selected</c:if> >내용</option>
                                            <option value="writer" <c:if test='${searchCri.searchType =="writer"}'>selected</c:if> >작성자</option>
                                            <option value="tc" <c:if test='${searchCri.searchType =="tc"}'>selected</c:if> >제목+내용</option>
                                        </select>
                                        <input class="form-control form-control-navbar" name="keyword" type="search" placeholder="Search" aria-label="Search" style="border:0; border-bottom:solid #65ddda" value="${searchCri.keyword }">
                                     </form>
                                     <div class="input-group-append">
                                            <button class="btn btn-navbar" type="button" onclick="$('#searchForm').submit();">
                                                <i class="fas fa-search"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="float-sm-left col-12 pb-3">
                                <nav class="navbar navbar-expand navbar-white navbar-light float-sm-right mr-5">
                                    <!-- Left navbar links -->
                                    <ul class="nav navbar-nav">
                                        <li class="nav-item d-none d-sm-inline-block">
                                            <a href="<%=request.getContextPath() %>/manage/authoritySet" class="nav-link">회원 관리</a>
                                        </li>
                                        <li class="nav-item d-none d-sm-inline-block">
                                            <a href="<%=request.getContextPath() %>/manage/reportList" class="nav-link">신고게시판</a>
                                        </li>
                                        <li class="nav-item d-none d-sm-inline-block">
                                            <a href="<%=request.getContextPath() %>/festival/list" class="nav-link">축제보러가기</a>
                                        </li>
                                        <li class="nav-item d-none d-sm-inline-block">
                                            <a href="<%=request.getContextPath() %>/review/list" class="nav-link">후기게시판</a>
                                        </li>
                                        <li class="nav-item d-none d-sm-inline-block">
                                            <a href="<%=request.getContextPath() %>/together/list" class="nav-link">같이가요</a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <div class="content-wrapper" style="background-color: white">
            <div class="container">
                <div class="content">