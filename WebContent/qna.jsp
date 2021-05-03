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




#section_contents{
	width: 900px; 
	border: 1px gray; 
	margin: 0 auto;
}

#search_qna{
	float: right;
}

#addQna{
	width: 900px;
	height: 30px;
	float: right;  
	width: 20px; 
	margin-right: 80px; 
	margin-top: 10px;
}

#btn_addQna{
	width: 100px;
	height: 30px;
}

#sec_qTable{
	height: 500px; 
	width: 900px; 
}

#qna_table{
	margin-top: 70px; 
	text-align: center; 
	border-collapse: collapse;
}

#qna_table tr, th, td{
	border: 1px solid gray; 
	padding: 5px;
}

.qna_no{
	width: 50px;
}

.qna_ti{
	width: 400px;
}

.qna_wr{
	width: 105px;
}

.qna_dt{
	width: 217px;
}

#page_btn{
	text-align: center; 
	margin: 20px 0px 20px 0px;
}

</style>
</head>
<body>
<div id="section_contents">
<h2>QNA</h2>
<hr>
[전체 <em>0</em> 건, 현재 페이지 <em>1</em> /1]
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
<div id="addQna"><input type="button" value="글 쓰기" id="btn_addQna"></div>
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