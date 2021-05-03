<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<!-- jquery-3.6.0.min.js -->
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>

<script type="text/javascript">

</script>

<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">
<style type="text/css">
.wrapper{
width: 100%;
}

.title{
width: 1200px;
margin:  auto;
}

.wrap_form{
margin: 0 auto;
}

</style>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<div class="contents">
	<div class="wrapper">
		<div class="title">
			<h1>아이디 찾기</h1>
			<hr>
		</div>
	</div>
	<div class="wrap_form">
		<form action="" method="post">
				 <h3>이메일</h3>
				 <input type="text" id="email1" name="email1">
				@ <input type="text" id="email2" name="email2">
					<select>
						<option value="네이버">naver.com</option>
						<option value="구글">gmail.com</option>
					</select>
				<input type="text" id="code">	
				<input type="button" id="checkEmail" value="인증하기">
				<input type="submit" value="아이디 찾기">
			</form>
	</div>
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>