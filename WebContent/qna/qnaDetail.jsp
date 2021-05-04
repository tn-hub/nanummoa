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
	width: 900px; 
	margin: 0 auto;
	border: 1px solid #333;
}

#qna_detail{
	width: 890px;
	padding: 5px;
}

h4{
	border-top: 1px solid #333;
	border-bottom: 1px solid #333;	
	background-color: #F6F6F6;
	padding : 5px;
}

#up_qnaTitle{
	border-style: none;
	padding: 10px;
	background-color: #F6F6F6;
	width: 790px;
}

#qna_det_info{
	height: 40px;
}

#qna_det_contexts textarea{
	width:880px; 
	height: 350px; 
	margin-top: 10px;
	margin-left: 5px;
	background-color: #F6F6F6;
}

ul li{
	list-style-type: none;
	float: left;
	margin-right: 30px;
	padding: 0px;
}

#uptQna_btn{
	float: right;
}

#rAdd{
	height: 30px;
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
	width:880px; 
	height: 200px; 
	margin-top: 10px;
}

#r_input_contexts{
	width:880px; 
	height: 100px; 
	background-color: #F6F6F6;
	margin-left: 5px;
}

#btn_r_resp{
	float: right;
	margin-top: 5px;
}
</style>
</head>
<script type="text/javascript">
function upt_qna(){
	alert("수정하기");
}

function del_qna() {
	alert("삭제하기");
}

</script>
<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<form name="qnaDetailForm" action="${CONTEXT_PATH}/common/commonController?action=qnaUpt" method="post">
<h3>게시글 상세</h3>
<hr>
<div id="qna_detail">
	  <h4>제목 : <input type="text"  id="qnaTitle" name="qnaTitle" value="${sdto.qnaTitle}"></h4>
		<div id="qna_det_info">
		<ul>
			<li id="qnaNo" >글번호 : ${sdto.qnaNo}</li>
		</ul>
		<ul>
			<li>작성자 : ${sdto.qnaWriter}</li>
		</ul>
		<ul>	
			<li>작성일 : ${sdto.qnaWriteDate}</li>
			<li id="uptQna_btn">
				<a href="#" onclick="javascript:document.qnaDetailForm.submit();">수정</a>
				<a href="${CONTEXT_PATH}/common/commonController?action=qnaDel&qnaNo=${sdto.qnaNo}" >삭제</a>
			</li>
		</ul>	
		</div>
		<hr>
	<div id="qna_det_contexts"><textarea id="qnaContents" name="qnaContents">${sdto.qnaContents}</textarea> </div>	
	<hr>
	</div>
	<div id="rAdd"><input id="btn_rAdd" type="button" value="댓글달기"></div>
	<hr>
	<div id="r_text_input">	
		<div id="r_det_contexts"><textarea id="r_input_contexts"></textarea>
		<div id="r_resp"><input id="btn_r_resp" type="button" value="댓글 등록"></div>
		</div>
	</div>	
	<hr>
	<div id="r_text_input">	
		<div id="r_det_contexts"><textarea id="r_input_contexts"></textarea>
		<div id="r_resp">
		
			<input id="btn_r_resp" type="button" value="수정하기">
			<input id="btn_r_resp" type="button" value="삭제하기">
		</div>
		</div>
	</div>	
	 <input type="hidden"  id="qnaNo" name="qnaNo" value="${sdto.qnaNo}">
	</form>
</div>
</body>
</html>