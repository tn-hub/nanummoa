<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="${initParam.CONTEXT_PATH}/resources/js/jquery-3.6.0.min.js"></script>
<style type="text/css">
#inputWrap {
	width: 1000px;
	margin: auto;
}

#inputBoxWrap {
	width: 1000px;
	height: 600px;
	text-align: center;
	/* background-color: red; */
}

#inputBoxWrap p{
	color: #444444;
	font-weight: bold;
}

#generalInputbox, #centerInputbox {
	position: absolute;
	border-radius: 30px;
	width: 400px;
	height: 500px;
	padding: 20px;
}

#generalInputbox {
	background-color: #93CAAB;
	left: 480px;
	top: 300px;
}

#generalInputbox h2, #centerInputbox h2 {
	font-size: 50px;
	margin-bottom: 30px;
}

#centerInputbox {
	background-color: #FF6670;
	right: 480px;
	top: 300px;
}

.fa-user, .fa-users {
	margin-top: 50px;
	font-size: 150px;
	color: #F1C40F;
}

#generalBtn, #centerBtn {
	border-radius: 30px;
	width: 350px;
	padding: 20px;
	height: 50px;
	line-height: 8px;
	background-color: #F8F7F7;
	font-weight: bold;
	font-size: 25px;
	border:none;
	cursor: pointer;
	
}

#centerBtn {
	margin-top: 10px;
}

#generalBtn {
	margin-top: 70px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#generalBtn").click(function(){
		$(location).attr("href", "${CONTEXT_PATH}/general/generalController?action=generalInputForm");
	});
	
	$("#centerBtn").click(function(){
		$(location).attr("href", "${CONTEXT_PATH}/center/centerController?action=centerInputForm");
	});
	
});
</script>
</head>
<body>
<%@ include file="/common/header.jsp"%>

<!-- 회원가입 링크 연결 -->
<div id="inputWrap">
	<h1>회원가입</h1>
	<hr class="head_hr">
	
	<div id="inputBoxWrap">
		<div id="generalInputbox">
				<i class="fas fa-user"></i>
				<h2>일반회원</h2>
				<p>봉사 참여 및 확인서를 발급 받을 수 있습니다</p>
				<input type="button" id="generalBtn" value="가입하기">
		</div>
		
		<div id="centerInputbox">
			<i class="fas fa-users"></i>
			<h2>센터회원</h2>
			<p>봉사 모집 및 확인서를 발급 할 수 있습니다</p>
			<p>서울시에 등록된 단체가 아닌경우 관리자 승인 후 활동 가능합니다</p>
			<input type="button" id="centerBtn" value="가입하기">
		</div>
	</div>
</div>	
<%@ include file="/common/footer.jsp"%>
</body>
</html>