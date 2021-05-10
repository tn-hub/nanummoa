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

em{
	color: red;
}

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
	background-color: #FBD157;
	padding: 10px 30px 10px 30px;
	width: 260px;
	display: inline-block;
	margin-bottom: 20px;
}

#page_btn{
	text-align: center; 
	margin: 20px 0px 20px 0px;
}


.grandMinList_area{
	text-align: center;
}
.genMinList_area{
	border: 2px solid gray;
	padding: 30px;
	height: 30px;
}

.genMinList_span{
	padding-right: 50px;
	float: left;
}

.genMinList_spanR{
	float: right;
	padding-left: 20px;
}

</style>
</head>
<script type="text/javascript">
$(document).ready(function(){
	// 이전페이지
  	$("#preBtn").click(function() {
  		var curPageNum = ${curPageNum};
  		if (curPageNum> 1){
  			$("#pageNum").val(curPageNum -1);
  			document.searchAllForm.submit();
  		}
  	});
  	
  	// 다음페이지
  	$("#nestBtn").click(function() {
  		var curPageNum = ${curPageNum};
  		var lastPageNum = ${lastPageNum};
  		
  		if (curPageNum < lastPageNum){
  			$("#pageNum").val(curPageNum +1);
  			document.searchAllForm.submit();
  		}
  	});
	
}); 	

//페이징 submit
function main_btnPageNum(ret){
	$("#pageNum").val(ret);
	document.searchAllForm.submit();
	
}   

</script>
<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2> 회원 전체 조회 </h2>
<hr>

<form action="${CONTEXT_PATH}/admin/adminController?action=generalMinList" method="post">
<div>
<ul class="searchAllMember" style="cursor:hand;">
	<li><a href="${CONTEXT_PATH}/admin/adminController?action=generalMinList">일반회원</a></li>
	<li><a href="${CONTEXT_PATH}/admin/adminController?action=centerMinList">센터회원</a></li>
	<li><a href="${CONTEXT_PATH}/admin/adminController?action=centerAcceptList">센터대기회원</a></li>
</ul>
 <input type="hidden" value="1" id="pageNum" name="pageNum">
</div>
</form>
<form action="${CONTEXT_PATH}/admin/adminController?action=generalMinList" method="post">

<c:if test="${tDto.gubun eq 'gen'}">
[전체 <em>${tDto.totCnt}</em> 회원]
</c:if>
<c:if test="${tDto.gubun eq 'cen'}">
[전체 <em>${tDto.totCnt}</em> 회원]
</c:if>

<div class="grandMinList_area">
<hr>
	<c:forEach var="dto" items="${list}">
	<c:if test="${dto.gubun eq 'gen'}">
	<ul class="genMinList_area">
		<li>
			<span class="genMinList_span">[아이디] ${dto.generalId}</span>
			<span class="genMinList_span">[이름] ${dto.generalName}</span>
			<span class="genMinList_span">[이메일] ${dto.generalEmail}</span>
			<span class="genMinList_spanR"><a href="${CONTEXT_PATH}/admin/adminController?action=generalDetail&generalId=${dto.generalId}"><input type="button" value="상세보기" class="r_btn" class="btn_ac"></a></span>
			<span class="genMinList_spanR"><a href="${CONTEXT_PATH}/admin/adminController?action=generalDel&generalId=${dto.generalId}"><input type="button" value="깅제탈퇴" class="y_btn" class="btn_re"></a></span>
		</li>
	</ul>
	</c:if>
	<c:if test="${dto.gubun eq 'cen'}">
	<ul class="genMinList_area">
		<li>
			<span class="genMinList_span">[아이디] ${dto.centerId}</span>
			<span class="genMinList_span">[이름] ${dto.centerName}</span>
			<span class="genMinList_span">[이메일] ${dto.centerEmail}</span>
			<span class="genMinList_spanR"><a href="${CONTEXT_PATH}/admin/adminController?action=centerDetail&centerId=${dto.centerId}"><input type="button" value="상세보기" class="r_btn" class="btn_ac" ></a></span>
			<span class="genMinList_spanR"><a href="${CONTEXT_PATH}/admin/adminController?action=centerDel&centerId=${dto.centerId}"><input type="button" value="깅제탈퇴" class="y_btn" class="btn_re"></a></span>
		</li>
	</ul>
	</c:if>	
	</c:forEach>
</div>
</form>

</div>	

<div id="footer" class="footer">
	<%@ include file="/common/footer.jsp"%>
</div>

</body>
</html>