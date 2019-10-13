<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

</div>
</div>
</div>
<footer class="main-footer p-5  baminfont-Air" style="text-align: center; margin:0px;">
    <strong>대표자 : bryan kim 전화번호 : 070-1234-1234<br>
        주소 : 대전광역시 서구 둔산서로 17 양호빌딩 6층<br>
        Copyright &copy; 축제의 민족 All rights reserved.</strong><br>
    <div class="float-right d-none d-sm-inline-block">
        <b>Version</b> 1.0.0
    </div>
</footer>
</div>
<!-- ./wrapper -->
 

<script>
function userBox(tar){
	$('.user_data').remove();
	var afterData = "<div class='user_data' style='position:absolute;display:block;z-index:10;left:0px;top:22px;windth:100px;"+
					"border:2px solid black'>"+
					"<ul class='user_data_list m-0 p-0'>"+ 
					"<li><a style='cursor:pointer;' onclick=window.open('<%=request.getContextPath()%>/member/memInfo?nick="+tar.html()+"','마이페이지','height=700,width=1000,resizable=0')>회원 정보 보기</a></li>"+
					"<li><a style='cursor:pointer;' onclick=window.open('<%=request.getContextPath()%>/message/sendMessage?m_sender_Nick="+tar.html()+"','마이페이지','height=700,width=1000,resizable=0')>쪽지 보내기</a></li>"+ 
					"<li><a href=''>작성글 보기</a></li>"+
					"<li><a style='cursor:pointer;' onclick="+"$('.user_data').remove()"+">닫기</a></li>"+ 
					"</ul>"+
					"</div>";
	tar.after(afterData);
	$('.user_data').css('background-image','url("<%=request.getContextPath()%>/resources/images/bg.png")');
	 
}
</script>

 onclick="location.href='<%=request.getContextPath() %>/member/follow?me=${loginUser.id}&follow=${member.id}'"



<!-- ChartJS -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jqvmap/maps/jquery.vmap.world.js"></script>
<!-- jQuery Knob Chart -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/moment/moment.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- overlayScrollbars -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- FastClick -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/dist/js/adminlte.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/dist/js/demo.js"></script>

