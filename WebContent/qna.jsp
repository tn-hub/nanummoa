<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QNA</title>
<style type="text/css">
a{text-decoration:none;}
a:link{color: black;}
a:visited{color: gray;}
a:hover { font-weight: bold; }
a:active { font-weight: bold; }
a.bold { font-weight: bold; }
#section_contents{width: 1000px; border: 1px gray; margin: 0 auto;}
#search_qna{float: right;}
#sec_qTable{border: 1px solid red; height: 600px;}
#addQna_btn{float: right;}
#qna_table{ margin-top: 30px;}
#qna_table tr, th, td{border: 1px solid gray; padding: 5px 5px 5px 5px;}
.qna_no{width: 70px;}
.qna_ti{width: 438px;}
.qna_wr{width: 150px;}
.qna_dt{width: 200px;}
#page_btn{text-align: center; margin: 20px 0px 20px 0px;}
</style>
</head>
<body>
<div id="section_contents">
<h2>QNA</h2>
<hr>
[전체 0건, 현재 페이지 1/1]
<div id="search_qna">
	<select>
		<option>== 검색 조건 ==</option>
		<option>제목</option>
		<option>내용</option>
		<option>작성자</option>
	</select>
	<input type="text">
	<input type="submit" value="검색">
</div>
<hr>
<div id="addQna_btn"><input type="button" value="글 쓰기"></div>
<div id="sec_qTable">
<table id="qna_table">
	<tr>
		<th class="qna_no">글번호</th>
		<th class="qna_ti">제목</th>
		<th class="qna_wr">작성자</th>
		<th class="qna_dt">잘성일</th>
		<th class="qna_yn">답변 여부</th>
	</tr>
		<tr>
		<td></td>
		<td><a href="">클릭</a></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
</table>
</div>
<div id=page_btn>
	<input type="button" value="이전 < ">
	<input type="button" value="1">
	<input type="button" value="다음 > ">
</div>
<hr>
</div>
</body>
</html>