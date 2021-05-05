<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QNA 등록</title>
<script type="text/javascript" src="${CONTEXT_PATH }/resources/js/jquery-3.6.0.min.js"></script>
<style type="text/css">
#section_contents{
	width: 1000px; 
	border: 1px gray; 
	margin: 0 auto;
}

#qnaTitle{
	width: 990px; 
	margin-top:20px; 
	margin-bottom: 20px; 
	padding: 5px; 
	font-size: 17px;
}

#qnaContents{
	width: 985px;
	height: 500px;
	padding: 10px; 
	margin-bottom: 20px;
	font-size: 17px;
}

.btn_list{
	padding: 10px; 
	width: 70px;
	border-style: none;
	font-size: 17px;
}

#btn_addQna{
	float: right;
}

#add_qnaArea{
	text-align: center;
}

.btn_add_qnaArea{
	padding: 10px; 
	width: 150px;
	border-style: none;
	font-size: 17px;
}

</style>
</head>
<script type="text/javascript">

$(document).ready(function() {
	$("#add_qna").click(function () {
		
		// 제목 빈값 확인 
		if ($("#qnaTitle").val() == null || $("#qnaTitle").val() == "") {
			alert("제목을 입력해주세요");
			$("#qnaTitle").focus();
			return false;
		}

		// 내용 빈값 확인 
		if ($("#qnaContents").val() == null || $("#qnaContents").val() == "") {
			alert("내용을 입력해주세요");
			$("#qnaContents").focus();
			return false;
		}
			
	});
	
});

</script>
<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2>질문 하기</h2>
<hr>
<form name="qnaAddForm" action="${CONTEXT_PATH}/common/commonController?action=qnaInput" method="post">
<input class="btn_list" type="button" value="목록" style="cursor:hand;">
<hr>
<table id="input_qna">
	<tr>
		<td><input type="text" placeholder="제목을 입력해 주세요." id="qnaTitle" name="qnaTitle"></td>
	</tr>
	<tr>
		<td><textarea id="qnaContents" name="qnaContents" placeholder="내용을 입력하세요."></textarea></td>
	</tr>
</table>
<div id="add_qnaArea">
<input type="reset" value="초기화" id="btn_cancel" name="btn_cancel" class="btn_add_qnaArea" style="cursor:hand;">
<input type="submit" id="add_qna" name="add_qna" value="등록 하기" class="btn_add_qnaArea" style="cursor:hand;">
</div>
</form>
<div id="footer" class="footer">
		<%@ include file="/common/footer.jsp"%>
	</div>
</div>
</body>
</html>