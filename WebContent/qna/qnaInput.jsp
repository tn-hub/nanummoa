<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QNA 등록</title>
<style type="text/css">
#section_contents{
	width: 1000px; 
	border: 1px gray; 
	margin: 0 auto;
}

#qnaTitle{
	width: 990px; 
	height: 20px; 
	margin-bottom: 20px;
}

#qnaContents{
	width: 975px;
	height: 500px;
	padding: 10px; 
	margin-bottom: 20px;
}

.btn_list{}

#btn_addQna{
	float: right;
}

#add_qna{
	text-align: center;
}

</style>
</head>
<script type="text/javascript">

function add_qna(){
	var qnaTitleEl = ${"qnaTitle"};
	var title = qnaTitleEl.value;

	// 제목 빈값 확인 
	if (title == "" || title == null){
		alert("제목을 입력해주세요");
		qnaTitleEl.focus();
		return;
	}
	
	var qnaContentsEl = ${"qnaContents"}; 
	var qnaContents = qnaContentsEl.value;

	// 제목 빈값 확인 
	if (qnaContents == "" || qnaContents == null){
		alert("내용을 입력해주세요");
		qnaContentsEl.focus();
		return;
	}
	
	// 저장 submit 
	document.qnaAddForm.submit();
	alert("저장되었습니다.");
	
}


</script>
<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2>질문 하기</h2>
<hr>
<form name="qnaAddForm" action="${CONTEXT_PATH}/common/commonController?action=qnaInput" method="post">
<input class="btn_list" type="button" value="목록">
<hr>
<table id="input_qna">
	<tr>
		<td><input type="text" placeholder="제목을 입력해 주세요." id="qnaTitle" name="qnaTitle"></td>
	</tr>
	<tr>
		<td><textarea id="qnaContents" name="qnaContents" placeholder="내용을 입력하세요."></textarea></td>
	</tr>
</table>
<div id="add_qna">
<input type="button" value="이전 페이지">
<input type="button" value="등록 하기" onclick="add_qna()">
</div>
</form>
<div id="footer" class="footer">
		<%@ include file="/common/footer.jsp"%>
	</div>
</div>
</body>
</html>