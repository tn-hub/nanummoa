<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.nanum.dto.QnADto" %>    
<%@ page import="java.util.ArrayList" %>
<%@ include file="/inc/taglib_menu.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
<style type="text/css">
#section_contents{
	width: 1000px; 
	margin: 0 auto;
}

#qna_detail{
	width: 990px;
	padding: 5px;
}

h4{
	border-top: 1px solid #333;
	border-bottom: 1px solid #333;	
	padding : 5px;
}

#qnaTitle{
	width: 900px; 
	padding: 5px; 
	font-size: 17px;
	border-style: none;
}

#qna_det_info{
	height: 40px;
}

#qna_det_contexts textarea{
	width: 985px;
	height: 300px;
	padding: 10px; 
	margin-bottom: 20px;
	font-size: 17px;
	border: none;
	resize : none;
}


.qna_detail_ul, .qna_detail_ul li{
	list-style-type: none;
	float: left;
	margin-right: 30px;
	padding: 0px;
}


#uptQna_btn{
	float: right;
	margin-left: 140px;
}

.btn_qna{
	width: 100px;
	height: 30px;
	border-style: none;
	font-size: 17px;
	margin-right: 10px;
}

#rAdd{
	height: 40px;
}

#btn_rAdd{
	float: right;
	margin-right: 10px;
	margin-bottom: 5px;
}

#r_det_info{
	height: 40px;
}

#r_det_contexts{
	width:985px; 
	height: 200px; 
	margin-top: 40px;
}

#r_input_contexts{
	width:985px; 
	height: 100px; 
	background-color: #F6F6F6;
	margin-left: 5px;
	resize : none;
	border: 1px solide #AAA !important;
}

#btn_r_resp{
	float: right;
	margin-top: 5px;
}

.r_resp{
	float: right;
	margin-top: -40px;
}

.admin_id {
	font-weight: bold;
}

.admin_writeDate {
	color: #5F5F5F;

}

.reply_content {
	width: 700px;
}

.no_reply {
	color: #5F5F5F;
	width:985px;
	height: 80px;
	line-height: 80px;
	text-align: center;
}

.btn_margin {
	margin-right: 5px;
}

#updateText {
	width: 985px;
	height: 100px;
	resize : none;
	
}

.btn_right_box {
	width: 985px;
	text-align: right;
}

.updateFormBtn {
	margin-top: 10px;
}
</style>
</head>
<script type="text/javascript">

$(document).ready(function() {
	
	// 댓글조회
	function selectReply(rNo) {
		var qno = ${sdto.qnaNo};
		console.log("댓글조회 : " + qno);
		$.ajax({
			  type:'post',
			  url:'${CONTEXT_PATH}/admin/adminController?action=getReply',
			  data:{qno:qno},
			  dataType: 'json',
			  success: function(data, textStatus){
				  var html = "";
				     $.each(data, function(index, item) {
				    	 if (rNo == item.replyNo) {
				    		 html += "<textarea id='updateText'>" +  item.replyContents + "</textarea><br>";
				    		 html += "<div class='btn_right_box'>";
				    		 html += "<button class='btn_qna r_btn btn_margin updateBtn' value='" +  item.replyNo + "'>등록</button>";
				    		 html += "</div>";
				    	 } else {
				    		 html += "<span class='admin_id'>" + item.adminId + "</span>";
					           html += "<p class='reply_content'>" + item.replyContents + "</p>";
					           html += "<span class='dmin_writeDate'>" + item.replyWriteDate + "</span>";
					           
					        	   if ($("#adminId").val() == item.adminId) {
					        		   html += "<div class='r_resp'>";
							           html += "<button class='btn_qna g_btn btn_margin updateFormBtn' value='" +  item.replyNo + "'>수정</button>";
							           html += "<button class='btn_qna g_btn deleteBtn' value='" +  item.replyNo + "'>삭제</button>";
							           html += "</div>"; 
					        	   }
					           
					           
				    	 }
				    	 html += "<hr>";
				          
				       });
						console.log("html : " + html);
				     $("#r_text_input").html(html);
				     $("#r_input_contexts").val("");
			  },
			  error : function(xhr,status,error) {
			     console.log("faild");
			  }
			});
	}
	
	
	
	// 댓글등록 이벤트 
	$("#btn_r_resp").click(function () {
		var content = $("#r_input_contexts").val();
		var qno = ${sdto.qnaNo};
		console.log(content, qno);
		$.ajax({
			  type:'post',
			  url:'${CONTEXT_PATH}/admin/adminController?action=addReply',
			  data:{qno:qno, content:content},
			  dataType: 'text',
			  success: function(result, textStatus){
				  if (result == "success") {
					  selectReply(-1);
				  } else {
					  alert("답글 등록 실패");
				  }
			  },
			  error : function(xhr,status,error) {
			     console.log("error");
			  }
			});
	});
	
	// 댓글삭제 이벤트
	$(document).on("click",".deleteBtn",function(e){
		var rNo = $(e.target).val();
		console.log("삭제 : " + rNo);
		if(confirm("삭제하시겠습니까?")) {
			$.ajax({
				  type:'post',
				  url:'${CONTEXT_PATH}/admin/adminController?action=deleteReply',
				  data:{rNo:rNo},
				  dataType: 'text',
				  success: function(result, textStatus){
					  if (result == "success") {
						  selectReply(-1);
					  } else {
						  alert("답글 삭제 실패");
					  }
				  },
				  error : function(xhr,status,error) {
				     console.log("error");
				  }
				});
		}
	}); 
	
	// 댓글수정 폼 조회
	$(document).on("click",".updateFormBtn",function(e){
		var rNo = $(e.target).val();
		console.log("수정폼 : " + rNo);
		selectReply(rNo);
	}); 
	
	// 댓글업데이트 이벤트
	$(document).on("click",".updateBtn",function(e){
		var rNo = $(e.target).val();
		var content = $("#updateText").val();
		console.log("수정 : " + rNo + ", " + content);
	
			$.ajax({
				  type:'post',
				  url:'${CONTEXT_PATH}/admin/adminController?action=updateReply',
				  data:{rNo:rNo, content:content},
				  dataType: 'text',
				  success: function(result, textStatus){
					  if (result == "success") {
						  selectReply(-1);
					  } else {
						  alert("답글 수정 실패");
					  }
				  },
				  error : function(xhr,status,error) {
				     console.log("error");
				  }
		}); 
	});
	
	selectReply(-1);
	
});
</script>
<script type="text/javascript">

</script>
<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<form name="qnaDetailForm" action="${CONTEXT_PATH}/common/commonController?action=qnaUpt" method="post">
<h1>문의글 상세</h1>
<hr>
<div id="qna_detail">
	
	<div id="qna_det_info">
	<ul class="qna_detail_ul">
		<li id="qnaNo" >글번호 : ${sdto.qnaNo}</li>
	</ul>
	<ul class="qna_detail_ul">	
		<li>작성자 : ${sdto.qnaWriter} [${sdto.generalId}${sdto.centerId}]</li>
	</ul>
		<ul class="qna_detail_ul">	
			<li>작성일 : ${sdto.qnaWriteDate}</li>
		</ul>
	</div>
	<h4>제목 : <input type="text" id="qnaTitle" name="qnaTitle" value="${sdto.qnaTitle}"></h4>
	<div id="qna_det_contexts"><textarea id="qnaContents" name="qnaContents">${sdto.qnaContents}</textarea> </div>	
	</div>
	<c:if test="${(grade eq 'G' and dto.generalId eq sdto.generalId) or (grade eq 'C' and dto.centerId eq sdto.centerId)}">
		<div class="btn_right_box">
			<a href="${CONTEXT_PATH}/common/commonController?action=qnaUptForm&qnaNo=${sdto.qnaNo}"><input type="button" class="btn_qna g_btn" value="수정"></a>  
			<a href="${CONTEXT_PATH}/common/commonController?action=qnaDel&qnaNo=${sdto.qnaNo}"><input type="button" class="btn_qna r_btn" value="삭제"></a>
		</div>
	</c:if>	
	<c:if test="${grade eq 'A'}">
		<div class="btn_right_box">
			<a href="${CONTEXT_PATH}/common/commonController?action=qnaDel&qnaNo=${sdto.qnaNo}"><input type="button" class="btn_qna r_btn" value="삭제"></a>
		</div>
	</c:if>	
	<input type="hidden"  id="qnaNo" name="qnaNo" value="${sdto.qnaNo}">
	<c:choose> 
		<c:when test="${not empty dto and grade == 'A'}">
			<input type="hidden"  id="adminId" value="${dto.adminId}">
		</c:when>
		<c:otherwise>
			<input type="hidden"  id="adminId" value="notAdmin">
		</c:otherwise>
	</c:choose>
</form>
	<span>답글</span>
	<hr>
	
	<!-- 댓글 -->
	<div id="r_text_input">
		<c:if test="${empty reply }">
			<div class="no_reply">
				<span>등록된 답글이 없습니다</span>
			</div>
		</c:if>
	</div>
	
	<c:if test="${not empty dto and grade == 'A'}">
	<div id="r_det_contexts">
		<textarea id="r_input_contexts" name="replyContent"></textarea>
			<input class="btn_qna y_btn" id="btn_r_resp" type="button" value="답글 등록">
	</div>	
	</c:if>
	 
	
</div>

<!-- footer -->
<%@ include file="/common/footer.jsp"%>
</body>
</html>