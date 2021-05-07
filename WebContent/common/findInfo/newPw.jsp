<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script type="text/javascript" src="${CONTEXT_PATH }/resources/js/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {

	$("#pwVisible").click(function () {
		var visiblePwElement = $("#pwVisible");
		var pwElement = $("#memberPw");
		var pw2Element = $("#checkMemberPw");
		
		if (visiblePwElement.is(":checked")) {
			pwElement.prop("type", "text");
			pw2Element.prop("type", "text");
		} else {
			pwElement.prop("type", "password");
			pw2Element.prop("type", "password");
		}
	});
	
	function pwCheck(){
		console.log("비밀번호 들어옴");
		var pwElement = $("#memberPw");
		var pw = pwElement.val();
		var pwMessageElement = $("#pwMessage");
		
		var pw2Element = $("#checkMemberPw");
		var pw2 = pw2Element.val();
		var pw2MessageElement = $("#pw2Message");
		
		pw = pw.trim();
		pw2 = pw2.trim();
		
		if (pw.length == 0) {
			pwMessageElement.html("비밀번호를 입력해 주세요");
			pwElement.val("");
			pwElement.focus();
			return false;
		} else if (!pwReg(pw)) {
			pwMessageElement.html("비밀번호는 8 ~ 20자리 이내 숫자,영문,특수문자를 포함하여 입력해 주세요");
			pwElement.val("");
			pwElement.focus();
			return false;
		} else if (pw2.length == 0) {
			pw2MessageElement.html("비밀번호를 입력해 주세요");
			pw2Element.val("");
			pw2Element.focus();
			return false;
		} else if (pw != pw2) {
			pw2MessageElement.html("비밀번호가 일치하지 않습니다");
			pw2Element.val("");
			pw2Element.focus();
			return false;
		}
		
		return true;
	}
	
	/* 비밀번호 정규식 */
	function pwReg(data){
		var regExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*()+=]).{8,20}$/;
		return regExp.test(data);
	}
	
	/* 비밀번호 에러메세지 클리어 */
	function clearMessagePw(){
		$("#pwMessage").html("");
	}

	/* 비밀번호2 에러메세지 클리어 */
	function clearMessagePw2(){
		$("#pw2Message").html("");
	}
	
});
</script>

<link type="text/css" rel="stylesheet"
	href="${initParam.CONTEXT_PATH}/resources/css/common.css">
<style type="text/css">
tr {
	margin : 5px;
}
input,th,td {
	margin : 5px;
	padding: 3px;
}

th {
	background-color: #FBD157;		
}

table{
	width: 500px;
	height: 200px;
	margin-left:auto; 
	margin-right:auto;
}

.title{
	width: 1000px;
	margin:  auto;
}

#wrap .box{ 
    width:300px;
    height:300px;
    margin:0 auto;
}

.email{
text-align: center;
}

 .g_btn{
 font-weight: bold;
 }

</style>

</head>
<body>
	<%@ include file="/common/header.jsp"%>
<div class="contents">
		<div class="title">
			<h1>새 비밀번호</h1>
			<hr>
	</div>
			<form action="${CONTEXT_PATH}/common/commonController?action=newPw" method="post">
			<input type="hidden" id="grade" name="grade" value="${grade }">
			
			<c:choose>
				<c:when test="${grade eq 'G'}">
					<input type="hidden" id="email" name="email" value="${dto.generalEmail }">
				</c:when>
				<c:when test="${grade eq 'C'}">
					<input type="hidden" id="email" name="email" value="${dto.centerEmail }">
				</c:when>
			</c:choose>
				<table border="1">
					<tr>
						<th>새 비밀번호</th> 
						<td>
							<input type="password" id="memberPw" name="memberPw" size="30" required="required">
							<div class="pwMessage"></div>
						</td>
					</tr>
						
					<tr>
						<th>비밀번호 확인</th> 
						<td>
							<input type="password" id="checkMemberPw" name="checkMemberPw" size="30" required="required">
							<div class="pwMessage"></div>
							<input type="checkbox" id="pwVisible" name="pwVisible" > <span>비밀번호 표시</span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" class="g_btn" value="완료" >
						</td>
					</tr>
				</table>
			</form>
</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>