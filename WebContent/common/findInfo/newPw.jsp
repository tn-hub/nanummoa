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
						<td><input type="password" id="memberPw" name="memberPw" size="30" required="required"></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th> 
						<td>
							<input type="password" id="checkMemberPw" name="checkMemberPw" size="30" required="required">
							<input type="checkbox" id="pwVisible" name="pwVisible" > <span>비밀번호 표시</span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="완료" >
						</td>
					</tr>
				</table>
			</form>
</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>