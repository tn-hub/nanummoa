<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	padding: 5px;
	border-top: 1px solid #333;
	border-bottom: 1px solid #333;	
	background-color: #F6F6F6;
	
}

#qna_det_info{
	height: 40px;
}

#qna_det_contexts{
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
<body>
<div id="section_contents">
<h3>게시글 상세</h3>
<hr>
<div id="qna_detail">
	<h4>제목</h4>
		<div id="qna_det_info">
		<ul>
			<li>작성자 : 유연우</li>
		</ul>
		<ul>	
			<li>작성일 : 2021-05-02</li>
		</ul>	
		</div>
		<hr>
	<div id="qna_det_contexts"></div>	
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
	<div id="r_det_info">
		<ul>
			<li>작성자 : 유연우</li>
		</ul>
		<ul>	
			<li>작성일 : 2021-05-02</li>
		</ul>	
	</div>
	<div id="r_text_input">	
		<div id="r_det_contexts"><textarea id="r_input_contexts"></textarea>
		<div id="r_resp">
			<input id="btn_r_resp" type="button" value="수정하기">
			<input id="btn_r_resp" type="button" value="삭제하기">
		</div>
		</div>
	</div>	
</div>
</body>
</html>