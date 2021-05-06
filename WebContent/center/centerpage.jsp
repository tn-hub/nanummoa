<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>센터회원 마이페이지</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">
</head>
<body>
<%@ include file="/common/header.jsp"%>
<div class="contents">   
    
	<div class="main_wrapper">
		<h1>센터페이지 개발용 (기능별 링크제공) </h1>
		<hr>
		<a href="${CONTEXT_PATH}/center/centerController?action=volInputForm">봉사등록</a><br>
		<a href="${CONTEXT_PATH}/center/centerController?action=updateVolForm">봉사수정</a><br>
		<a href="${CONTEXT_PATH}/center/centerController?action=deleteVol">봉사삭제</a>
		
	</div>
</div>


<%@ include file="/common/footer.jsp"%>
</body>
</html>