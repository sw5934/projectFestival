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
                    <c:if test="${id } != null"></c:if>
                        <a class="nav-link float-sm-right" href="<%=request.getContextPath()%>/login">로그인</a>
                        <a class="nav-link float-sm-right" href="#">회원가입</a>
                        <a class="nav-link float-sm-right" href="#">ID/PW찾기</a>
                        <a class="nav-link float-sm-right"><b>${loginUser.nickName }</b>&nbsp;<span>님 어서 오세요</span></a>
                    </div>
                </div>
                <div class="float-sm-left col-12">
                    <!-- Navbar -->
                    <div class="float-sm-left col-3 pl-5"><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/logo.png" width="200px" class=" ml-5"></div>
                    <div class="float-sm-left col-9 pr-5">
                        <div>
                            <div class="float-sm-left col-12">
                                <!-- SEARCH FORM -->
                                <form class="form-inline  float-sm-right mr-5">
                                    <div class="input-group input-group-sm">
                                        <select name="searchOption" class="mr-3">
                                            <option value="tcw">통합검색</option>
                                            <option value="t">제목</option>
                                            <option value="c">내용</option>
                                            <option value="w">작성자</option>
                                            <option value="tc">제목+내용</option>
                                        </select>
                                        <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search" style="border:0; border-bottom:solid #65ddda">
                                        <div class="input-group-append">
                                            <button class="btn btn-navbar" type="submit">
                                                <i class="fas fa-search"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="float-sm-left col-12 pb-3">
                                <nav class="navbar navbar-expand navbar-white navbar-light float-sm-right mr-5">
                                    <!-- Left navbar links -->
                                    <ul class="nav navbar-nav">
                                        <li class="nav-item d-none d-sm-inline-block">
                                            <a href="#" class="nav-link">축제보러가기</a>
                                        </li>
                                        <li class="nav-item d-none d-sm-inline-block">
                                            <a href="#" class="nav-link">후기게시판</a>
                                        </li>
                                        <li class="nav-item d-none d-sm-inline-block">
                                            <a href="#" class="nav-link">같이가요</a>
                                        </li>
                                        <li class="nav-item d-none d-sm-inline-block">
                                            <a href="#" class="nav-link">마이페이지</a>
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