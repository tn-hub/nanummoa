<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QNA 등록</title>
<style type="text/css">
#section_contents{
	width: 900px; 
	border: 1px gray; 
	margin: 0 auto;
}

#qnaInTitle{
	width: 890px; 
	height: 20px; 
	margin-bottom: 20px;
}

#qna_text{
	width: 875px;
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
<body>
<div id="section_contents">
<h2>질문 하기</h2>
<hr>
<form>
<input class="btn_list" type="button" value="목록">
<input id="btn_addQna" type="button" value="등록 하기">
<hr>
<table id="input_qna">
	<tr>
		<td><input type="text" placeholder="제목을 입력해 주세요." id="qnaInTitle" name="qnaInTitle"></td>
	</tr>
	<tr>
		<td><textarea id="qna_text" name="qna_text" placeholder="내용을 입력하세요."></textarea></td>
	</tr>
</table>
<div id="add_qna">
<input type="button" value="이전 페이지">
<input type="button" value="등록 하기">
</div>
</form>
</div>
</body>
</html>