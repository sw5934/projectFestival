<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
       
        
        .side-menu{
            margin-top: 10px
        }
        
        .main-footer{
            font-size: 0.9em;
        }
        a:link {
            text-decoration: none;
        }

        a:visited {
            text-decoration: none;
        }

        a:hover {
            text-decoration: none;
        }
        .baminfont-menu {
            font-family: bamin-hanna-Air;
            color: white;
            font-size: 1.4em;
        }

        .baminfont-my-title {
            font-family: bamin-hanna-Pro;
            font-size: 3em;
            padding: 35px
        }
    </style>

</head>

<body class="skin-blue fixed sidebar-mini sidebar-mini-expand-feature">
    <div class="wrapper" style="width: 1000px; height:700px; overflow:hidden;resize:none;">
        <div class="card" style="height:525px;margin:0px;" id="temp">
            <aside class="main-sidebar" style="background-color: #65ddda; width: 25%;">
                <div style="border: white solid 7px; width:80%; left:10%; top: 5%; position: relative"><img src="<%=request.getContextPath()%>/resources/bootstrap/plugins/cm/logo.png" style="width: 100%"></div>
                <div style="border: white solid 7px; width:80%; left:10%; top: 10%; position: relative; padding: 0px;">
                    <ul class="" style="padding: 5px;">
                        <li class="side-menu"><a class="baminfont-menu" href="<%=request.getContextPath() %>/member/myInfo?id=${loginUser.id}">@ 내 정보 수정</a></li>
                        <li class="side-menu"><a class="baminfont-menu" href="<%=request.getContextPath() %>/follow/followList">@ 팔로우 현황 보기</a></li>
                        <li class="side-menu"><a class="baminfont-menu" href="<%=request.getContextPath() %>/vote/voteList">@ 내가 찜한 축제</a></li>
                        <li class="side-menu"><a class="baminfont-menu" href="<%=request.getContextPath() %>/myPage/review">@ 내가 쓴 글/댓글</a></li>
                        <li class="side-menu"><a class="baminfont-menu" href="<%=request.getContextPath() %>/message/receiveList">@ 내 쪽지 보기</a></li>
                    </ul>
                </div>
            </aside> 
            <div style="position: absolute;right:0px;width: 75%; height: 100%">
                <div style="height: 26%">
                    <p class="baminfont-my-title" style="cursor:pointer;" onClick='self.location.href="${request.getContextPath()}${categoryMain}"'>${loginUser.nickName} 님의 ${view}</p> 
                </div>
                <div style="height: 70%">