<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>센터회원 마이페이지</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">

<style type="text/css">
.wrapper{
width: 100%;
}

.title{
width: 1200px;
margin:  auto;
}

.link{
text-align: center;
}

</style>
</head>
<body>
<%@ include file="/common/header.jsp"%>



<div class="contents">
	<div class="wrapper">
		<div class="title">
			<h1>자원 봉사 목록</h1>
		<hr>
		<div class="link">
			<a href="">모집중 봉사</a>
			<a href="">종료된 봉사</a>
		</div>
		<hr>
	</div>
</div>
	
	
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>