<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.min.js">
</script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.2.1.min.js"></script>

<script id="comments-list-template" type="text/x-handlebars-template">
{{#each .}}
<li class="commentsLi" data-c_no={{c_no}}>
<i class="fa fa-comments bg-blue"></i>
 <div class="timeline-item" >
  <span class="time">
    <i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
	 <a class="btn btn-primary btn-xs" id="DeleteCommentsBtn"
	    data-c_writer={{c_writer}}  data-c_no={{c_no}} data-toggle="modal" data-target="#modifyModal">삭제</a>
<a href="<%=request.getContextPath()%>/manage/doReportComment?c_no={{c_no}}&no=${together.tno}&page=${dataMap.page}&listSort=${dataMap.listSort}&originCategory=${category}">신고</a>
  </span>
  <h3 class="timeline-header"><strong style="display:none;">{{c_no}}</strong>{{c_writer}}</h3>
  <div class="timeline-body">{{c_content}}</div>
</li>
{{/each}}	
</script>

<script>
/* 목록가기 */


 
function onSubmit(category, form, url, method) {

		//유효성처리
		//validation();
		
		
<%-- 		열어서 주제를 선택하는 거라면 ("action","<%=request.getContextPath()%>/board/${category}/admin/regist") --%>
		form.action="<%=request.getContextPath()%>/"+category+"/"+url;
		form.method=method;
		form.submit();
	}
</script>


<script>
	Handlebars.registerHelper("prettifyDate",function(timeValue){
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth()+1;
		var date = dateObj.getDate();
		return year+"/"+month+"/"+date;
	});

	var printData=function(commentsArr, target, templateObject){
		var template = Handlebars.compile(templateObject.html());
		var html = template(commentsArr);
		$('.commentsLi').remove();
		target.after(html);
	};

	var commentsPage = 1;

	/* ajax를 메소드형식으로 바꿈 */
	function getPage(pageInfo){
		$.getJSON(pageInfo,function(data){
			//console.log(data.replyList.rno);
			printData(data.commentsList, $('#commentsDiv'), $('#comments-list-template'));
			printPaging(data.pageMaker,$('.pagination'));
		});
	}

	getPage("<%=request.getContextPath()%>/comments/${together.unq_Id}/"+commentsPage);

	//comments pagination
	var printPaging = function(pageMaker, target){
		var str="";
		if(pageMaker.prev){
			str += "<li class='page-item'><a href = '"+(pageMaker.startPage-1)
					+"'class='page-link'> << </a></li>";
		}
		for(var i = pageMaker.startPage, len = pageMaker.endPage; i<=len; i++){
			var strClass = pageMaker.cri.page == i? 'active':'';
			str += "<li class='page-item" + strClass + "'><a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
		if(pageMaker.next){
			str +="<li class='page-item'><a class='page-link' href='"+(pageMaker.endPage+1)
				+"'> >> </a></li>";
		}
		target.html(str);
	}

	$('.pagination').on('click','li a',function(event){
		event.preventDefault();
		commentsPage=$(this).attr("href");
		getPage("<%=request.getContextPath()%>/comments/${together.unq_Id}/"+commentsPage);
	});

	$('#commentsAddBtn').on('click', function(e){
		//alert("click reply-add-btn");                                                                                           

		var c_writer=$('#newC_Writer').val();
		var c_content=$('#newC_Content').val();

		//alert("replyer : " + replyer + "\nreplytext : " + replytext);

		if(c_content ==""){
			alert('댓글 내용은 필수입니다.');
			$('#newC_Content').focus().css("border-color","red")
			.attr("placeholder","내용은 필수입니다.");
			return;
		}
		var data={
				"unq_Id":"${together.unq_Id}",
				"c_writer":c_writer,
				"c_content":c_content
		}

		$.ajax({
			url:"<%=request.getContextPath()%>/comments",
			type:"post",
			data:JSON.stringify(data),
			contentType:"application/json",			//보내는 data 형식 지정
			dataType:"text",						//받는 data 형식 지정
			success:function(data){
				if(data=="SUCCESS"){
					alert('댓글이 등록되었습니다.');
					getPage("<%=request.getContextPath()%>/comments/${together.unq_Id}/"+commentsPage);
					$('#newC_Content').val("");
				}else{
					alert('댓글 등록이 취소되었습니다.');
				}
			},
			error:function(error){
				alert('서버 오류로 인하여 댓글 등록을 실패했습니다.');
			}
		});
	});
	<%--
	 $('ul.timeline').on('click','#DeleteCommentsBtn',function(event){
		//alert($(this).attr("data-replyer"));
		//$(this)은 $(event.target)와 동일한 객체
		var c_writer=$('#newC_Writer').val();
		var c_content=$('#newC_Content').val();
	    alert(c_content);
		var c_writer=$(event.target).attr("data-c_writer");
		if(c_writer != "${loginUser.id}"){
			alert("삭제가 불가합니다.");
			$(this).attr("data-toggle","");
		}
	});
	
	$('.timeline').on('click','.commentsLi',function(event){
		var comments=$(this);
		$('#c_content').val(comments.find('.timeline-body').text());
		$('.modal-title').html(comments.attr('data-c_no'));
	});
	
	$('#commentsModBtn').on('click', function(event){
		//alert("reply modify btn");
		var c_no=$('.modal-title').html();
		var c_content=$('#c_content').val();
		
		var sendData={
				c_no:c_no,
				c_content:c_content
		}
		
		$.ajax({
			url:"<%=request.getContextPath() %>/comments/"+c_no,
			type:"put",
			data:JSON.stringify(sendDATA),
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"PUT"
			},
			success:function(result){
				if(result=="SUCCESS"){
					alert("수정되었읍니다.");
					getPage("<%=request.getContextPath() %>/comments/${review.unq_Id}/"+commentsPage);
				}
			},
			error:function(error){
				alert("댓글 수정에 실패했습니다.");				
			},
			complete:function(){
				$('#modifyModel').model('hide');
			}
		});
	}); --%>
	<%--
	 $('ul.timeline').on('click','#DeleteCommentsBtn',function(event){
			//alert($(this).attr("data-replyer"));
			//$(this)은 $(event.target)와 동일한 객체
		    
			var c_writer=$(event.target).attr("data-c_writer");
			if(c_writer != "${loginUser.id}"){
				alert("삭제가 불가합니다.");
				$(this).attr("data-toggle","");
			}
		});--%>
		
		$('.timeline').on('click','.commentsLi',function(event){
			var comments=$(this);
			$('#c_content').val(comments.find('.timeline-body').text());
			$('.modal-title').html(comments.attr('data-c_no')); 
		});
	
		$('ul.timeline').on('click','#DeleteCommentsBtn',function(event){
		//alert(rno);
		
		
		var c_writer=$(event.target).attr("data-c_writer");
		if(c_writer != "${loginUser.id}"){
			alert("삭제가 불가합니다.");
			$(this).attr("data-toggle","");
		}
		

		var c_no=$(event.target).attr("data-c_no");
		
		alert(c_no);
		
		var sendData={
				"c_no":c_no
		}
		$.ajax({
			url:"<%=request.getContextPath()%>/comments/"+c_no,
			type:"delete",
			data:JSON.stringify(sendData),
			headers:{
				"Content-type":"application/json",
				"X-HTTP-Overrride":"delete"
			},
			success:function(result){
				if(result=="SUCCESS"){
					alert("삭제되었읍니다.");
					getPage("<%=request.getContextPath()%>/comments/${together.unq_Id}/"+commentsPage);
				}
			},
			error:function(error){
				alert('삭제 실패했습니다.');
			},
			complete:function(){
				$('#modifyModal').modal('hide');
			}
		});
	});
	
</script>