<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기 결과</title>
<style type="text/css">
.title{
width: 1000px;
margin:  auto;
}

#wrap .box{ 
    width:400px;
    height:300px;
    margin:0 auto;
}

</style>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<div class="contents">
	<div id="wrap">	
		<div class="box">
		<h1><font color="red">${message.message }</font></h1>
		
		<h3>${id }</h3>
		
		<a href="${message.url }">${message.linkTitle }</a>
		</div>
	</div>
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>