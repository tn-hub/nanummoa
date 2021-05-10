<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.nanum.dto.QnADto" %>    
<%@ page import="java.util.ArrayList" %>
<%@ include file="/inc/taglib_menu.jsp" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 전체 조회</title>
<style type="text/css">

#section_contents{
	width: 1000px; 
	border: 1px gray; 
	margin: 0 auto;
}

.searchAllMember {
	padding:0px;
	text-align: center;
}

.searchAllMember li{
	background-color: #EAEAEA;
	padding: 10px 30px 10px 30px;
	width: 260px;
	display: inline-block;
	margin-bottom: 20px;
}

#page_btn{
	text-align: center; 
	margin: 20px 0px 20px 0px;
}


</style>
</head>
<script type="text/javascript">


</script>
<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2> 회원 전체 조회 </h2>
<hr>

<form name="searchAllMinMemberForm" id="searchAllMinMemberForm" action="${CONTEXT_PATH}/common/commonController?action=searchAllForm" method="post">
<div>
<ul class="searchAllMember" style="cursor:hand;">
	<li id="tab_searchAll"><a href="${CONTEXT_PATH}/admin/adminController?action=generalMinList">일반회원</a></li>
	<li id="tab_searchVol"><a href="${CONTEXT_PATH}/admin/adminController?action=centerMinList">샌터회원</a></li>
	<li id="tab_searchQna"><a href="${CONTEXT_PATH}/admin/adminController?action=centerAcceptList">센터대기회원</a></li>
</ul>
</div>
</form>
</div>
<c:if test="${dto.gubun eq 'gen'}">
<div>
	<c:forEach var="dto" items="${glist}">
	<ul>
		<li id="genMinMemberId">[아이디] <br>${dto.generalId}</li>
		<li id="genMinMemberName">[이름] <br>${dto.generalName}</li>
		<li id="genMinMemberEmail">[이메일] <br>${dto.generalEmail}</li>
	</ul>
	</c:forEach>
</div>
</c:if>
<c:if test="${dto.gubun eq 'cen'}">
<div>
	<c:forEach var="dto" items="${list}">
	<ul id="cecnter_standBy_ul">
		<li id="cenMinMemberId">[아이디] <br>${cDto.centerName}</li>
		<li id="cenMinMemberName">[이름] <br>${cDto.centerName}</li>
		<li id="cenMinMemberEmail">[이메일] <br>${cDto.cmemberEntryDate}</li>
	</ul>
	</c:forEach>
</div>
</c:if>		

<div id="page_btn">
	<input type="button" value="이전 " id="preBtn" name="preBtn">
	 <c:forEach var="i" begin="${ 1 }" end="${lastPageNum}">
           <input type="button" value=${ i } id="btnPageNum" name="btnPageNum" onclick="btnPageNum(${ i })">
     </c:forEach>   
	<input type="button" value="다음 " id="nestBtn" name="nestBtn">	
</div>

<div id="footer" class="footer">
	<%@ include file="/common/footer.jsp"%>
</div>

</body>
</html>