<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<!-- jquery-3.6.0.min.js -->
<script type="text/javascript" src="${CONTEXT_PATH }/resources/js/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$("#email2").attr("readonly",true);
	
	$("#selectEmail").change(function() {
		
		if ($("#selectEmail").val() == "none") {
			$("#email2").val("");
			$("#email2").attr("readonly",true);
		}else if($("#selectEmail").val() == "네이버"){
			$("#email2").val("naver.com");
			$("#email2").attr("readonly",true);
		}else if($("#selectEmail").val() == "구글"){
			$("#email2").val("gmail.com");
			$("#email2").attr("readonly",true);
		}else if($("#selectEmail").val() == "on"){
			$("#email2").val("");
			$("#email2").attr("readonly",false);
		}
		
	});
	
});

</script>

<link type="text/css" rel="stylesheet"
	href="${initParam.CONTEXT_PATH}/resources/css/common.css">
<style type="text/css">
.wrapper {
	width: 100%;
}

.title {
	width: 1200px;
	margin: auto;
}

.wrap_form {
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
					<form action="${CONTEXT_PATH}/common/commonController?action=addSecureCode" method="post">
						<h3>이메일</h3>
						  <input type="text" id="email1" name="email1"> 
						@ <input type="text" id="email2" name="email2"> 
						<select id="selectEmail">
							<option value="none">선택</option>
							<option value="네이버">naver.com</option>
							<option value="구글">gmail.com</option>
							<option value="on">직접입력</option>
						</select> 
						<input type="submit" id="checkEmail" value="인증하기" >
						</form>
						
						<form action="${CONTEXT_PATH}/common/commonController?action=mail" method="post">
						<input type="text" id="code" name="code">
						<input type="submit" id="checkId" name="checkId" value="아이디 찾기">
						</form>
		 </div>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>