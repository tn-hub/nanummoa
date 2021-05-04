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
	
	$("#checkId").click(function () {
		if ($("#name").val() == null || $("#name").val() == "") {
			$("#name").focus();
			return false;
		}else if($("#email1").val() == null || $("#email1").val() == ""){
			$("#email1").focus();
			return false;
		}else if($("#email2").val() == null || $("#email2").val() == ""){
			$("#email2").focus();
			return false;
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
		<form action="${CONTEXT_PATH}/common/commonController?action=addSecureCode" method="post">
			<table border="1">
				<tr>
						<th>이름</th> 
						<td><input type="text" id="name" name="name" size="30" required="required"></td>
				</tr>
				<tr>
						<th>이메일</th>
						<td>  
							<input type="text" id="email1" name="email1" size="10" required="required"> 
								@ <input type="text" id="email2" name="email2" size="10" required="required">
							<select id="selectEmail">
								<option value="none">선택</option>
								<option value="네이버">naver.com</option>
								<option value="구글">gmail.com</option>
								<option value="on">직접입력</option>
							</select>
							<input type="submit" id="checkEmail" value="인증하기" >
						</td>
				</tr>
		</form>
		<form action="${CONTEXT_PATH}/common/commonController?action=mail" method="post">
				<tr>
					<th>인증번호</th>
					<td>
						<input type="text" id="code" name="code" size="30" required="required">
						<input type="submit" id="checkId" name="checkId" value="확인" >
					</td>
				</tr>
			</table>
			
		</form>
			
						
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>