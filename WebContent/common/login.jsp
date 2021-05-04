<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">

<style type="text/css">

.login{
	margin:0 auto;
}

#wrap{
    width:100%;
    height:100%;
}
  
#wrap .box{ 
    width:300px;
    height:300px;
    margin:0 auto;
}

input[type='submit']{
    width: 403px;
    padding: 10px 30px;
    cursor: pointer;
    margin: auto;
    background: linear-gradient(to right, #ff105f, #ffad06);
    border: 0;
    outline: none;
    border-radius: 30px;
}

.wrap_form{
width: 300px;
height: 230px;
}

.wrapper{
width: 100%;
}

.title{
width: 1200px;
margin:  auto;
}

input[type='text'],input[type='password'] {
	height: 40px;
    font-size: 20px;
}

.radio{
height : 100px;
width: 500px;

font-size: 22px;
font-weight: bold;
margin: 0 auto;

}
input[type='radio'] {
	width: 30px;
    height: 20px;
}
    
</style>

<title>로그인</title>
</head>
<body>
<%@ include file="/common/header.jsp"%>

<div class="contents">

<div class="wrapper">
	<div class="title">
		<h1>로그인</h1>
		<hr>
	</div>
</div>

	<div id="wrap">	
		<div class="box">
		<form action="${CONTEXT_PATH }/common/commonController?action=login" method="post">
		<div class="radio">
			<input type="radio" id="genaral" name="grade" value="G" checked="checked">일반회원
			<input type="radio" id="center" name="grade" value="C" >센터회원
			<input type="radio" id="admin" name="grade" value="A">관리자
		</div>
		 <div class="wrap_form">
			<h2>아이디</h2>
			<input type="text" id="memberId" name="memberId" placeholder="아이디" size="40" height="50px">
			<h2>비밀번호</h2>
			<input type="password" id="memberPw" name="memberPw" placeholder="패스워드" size="40">
		</div>
		
		<div id="submit">
			<input type="submit" value="로그인" >
		</div>
		
			<br>
			  <a href="#">회원가입</a>
			| <a href="#">아이디 찾기</a>
			| <a href="#">비밀번호 찾기</a>
		</form>
		</div>
	</div>
</div>

<%@ include file="/common/footer.jsp"%>
</body>
</html>