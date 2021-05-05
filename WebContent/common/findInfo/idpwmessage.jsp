<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기 결과</title>
<style type="text/css">
.title{
width: 1000px;
margin:  auto;
}

#wrap .box{ 
    width:300px;
    height:300px;
    margin:0 auto;
}

</style>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<div class="contents">
	<div id="wrap">	
		<div class="box">
		<h1><font color="red">${message.message }</font></h1>

			<c:choose>
				<c:when test="${dto.generalId != null}">
					<h2>일반회원 : ${dto.generalId }</h2>
				</c:when>
				<c:when test="${center.centerId != null}">
					<h2>센터회원 : ${dto.centerId }</h2>
				</c:when>
			</c:choose>
		
		<a href="${message.url }">${message.linkTitle }</a>
		</div>
	</div>
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>