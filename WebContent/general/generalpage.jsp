<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 마이페이지</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">
</head>
<body>
<%@ include file="/common/header.jsp"%>
<div class="contents">   
    
	<div class="main_wrapper">
		<h1>회원페이지 개발용 (기능별 링크제공) </h1>
		<hr>
		<a href="${CONTEXT_PATH}/general/generalController?action=enrollVolForm&volInfoNo=1">봉사 신청하기 : (volInfoNo :1 하드코딩) </a><br>
		<a href="${CONTEXT_PATH}/general/generalController?action=volApplyList">봉사 신청내역</a><br>
		
	</div>
</div>


<%@ include file="/common/footer.jsp"%>
</body>
</html>