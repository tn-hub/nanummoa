<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 비밀번호 찾기 결과</title>
</head>
<body>
<c:choose>
	<c:when test="${dto.generalId != null}">
		일반회원 : ${dto.generalId }
	</c:when>
	<c:when test="${center.centerId != null}">
		센터회원 : ${dto.centerId }
	</c:when>
</c:choose>


</body>
</html>