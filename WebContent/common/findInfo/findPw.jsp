<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
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
	height: 100px;
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

.radio{
	height : 50px;
	text-align: center;
	font-size: 22px;
	font-weight: bold;
	margin: 0 auto;
}

.submit{
	text-align: center;
	margin: 10px;
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
			<h1>비밀번호 찾기</h1>
			<hr>
	</div>
			<form action="${CONTEXT_PATH}/common/commonController?action=findPw" method="post">
				<div class="radio">
					<input type="radio" id="genaral" name="grade" value="G" checked="checked">일반회원
					<input type="radio" id="center" name="grade" value="C" >센터회원
				</div>
				
				<table border="1">
					<tr>
						<th>아이디</th> 
						<td>
							<input type="text" id="memberId" name="memberId" size="30" required="required">
							<input type="submit" class="g_btn" id="checkPw" name="checkPw" value="다음" >
						</td>
					</tr>
				</table>
			<div class="submit">
				
			</div>
			</form>
</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>