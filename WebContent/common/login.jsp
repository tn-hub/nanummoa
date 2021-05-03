<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">

<title>로그인</title>
</head>
<body>
<div id="header" class="header">
	<%@ include file="/common/header.jsp"%>
</div>

<h3>로그인</h3>
<hr>

<form action="${CONTEXT_PATH }/common/commonController?action=login" method="post">
	<input type="radio" id="genaral" name="grade" value="G" checked="checked">일반회원
	<input type="radio" id="center" name="grade" value="C" >센터회원
	<input type="radio" id="admin" name="grade" value="A">관리자
 
	<table>
		<tr>
			<td>아이디</td> 
		</tr>

		<tr>
			 <td><input type="text" id="memberId" name="memberId" placeholder="아이디"></td>
		</tr>		
				
		<tr>
			<td>비밀번호</td> 
		</tr>

		<tr>
			 <td><input type="password" id="memberPw" name="memberPw" placeholder="패스워드"></td>
		</tr>			
		
	
		<tr>
			 <td><input type="submit" value="로그인" ></td>
		</tr>
		
		<tr>
			 <td>
			 	  <a href="#">회원가입</a>
			 	| <a href="#">아이디 찾기</a>
			 	| <a href="#">비밀번호 찾기</a>
			 </td>
		</tr>
	</table>
</form>

<div id="footer" class="footer">
	<%@ include file="/common/footer.jsp"%>
</div>
</body>
</html>