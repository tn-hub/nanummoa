<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.nanum.dto.QnADto" %>    
<%@ page import="java.util.ArrayList" %>
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QNA</title>
<script type="text/javascript" src="${CONTEXT_PATH }/resources/js/jquery-3.6.0.min.js"></script>
<style type="text/css">
a{text-decoration:none;}
a:link{color: black;}
a:visited{color: gray;}
a:hover { font-weight: bold; }
a:active { font-weight: bold; }
a.bold { font-weight: bold; }


#section_contents{
	width: 1000px; 
	border: 1px gray; 
	margin: 0 auto;
}


.searchAllDVS_menu {
	padding:0px;
	text-align: center;
}


.searchAllDVS_menu li{
	background-color: #EAEAEA;
	padding: 10px 30px 10px 30px;
	width: 260px;
	display: inline-block;
	margin-bottom: 20px;
}



.searchAll_area{
	border-bottom: 2px solid black;	
	text-align: center;
	height: 40px;
	padding-bottom: 20px;
}

.searchAll_option{
	border-style: none;
	font-size: 20px;
	margin-right: 10px;
}

.searchAll_searchtext{
	width: 400px;
	font-size: 20px;
	margin-right: 30px;
	padding: 3px 10px 3px 10px;
}


.btn_searchAll{
	width: 100px;
	height: 40px;
	padding: 5px;
	font-size: 20px;
	border: none;
	border-radius: 5px;
}

.searchAllDVS_area{
	border: 1px solid gray;
}


.searchAllListUl{
width: 1000px;
	list-style:none;
	color: #5F5F5F;	
	padding-inline-start: 0px;
}


.sListSpan_r{
	float: right;
}

.searchList_box{
	border-top: 2px solid black;
	border-bottom: 2px solid black;	
	padding: 20px;
}

</style>
</head>


<script type="text/javascript">

$(document).ready(function() {
	$("#tab_searchAll").click(function () {
		$("#searchAll_opt").val('A');
		document.searchAllForm.submit();
	});
	
	$("#tab_searchVol").click(function () {
		$("#searchAll_opt").val('V');
		document.searchAllForm.submit();
	});
	
	$("#tab_searchQna").click(function () {
		$("#searchAll_opt").val('Q');	
		document.searchAllForm.submit();
	});
});


</script>
<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2>통합검색</h2>
<hr>
<form name="searchAllForm" id="searchAllForm" action="${CONTEXT_PATH}/common/commonController?action=searchAllForm" method="post">
<div class="searchAll_area">
	<select class="searchAll_option" id="searchAll_opt" name="searchAll_opt">
		<option value="A">전체</option>
		<option value="V" <c:if test="${searchAllOpt eq 'V'}">selected</c:if>>봉사 게시판</option>
		<option value="Q" <c:if test="${searchAllOpt eq 'Q'}">selected</c:if>>문의 게시판</option>
	</select> 
	<input type="text" class="searchAll_searchtext" id="searchAll_text" name="searchAll_text" value="${searchAllText}">
	<input type="submit" value="검색" class="btn_searchAll" id="btn_searchAllList" name="btn_searchAllList" style="cursor:hand;">
</div>
<div class="searchAllDVS_area">
<ul class="searchAllDVS_menu" style="cursor:hand;">
	<li id="tab_searchAll">전체</li>
	<li id="tab_searchVol">봉사</li>
	<li id="tab_searchQna">문의</li>
</ul>
</div>
</form>

<form>
<div class="search_all_list">
<ul class="searchAllListUl">
<c:forEach var="dto" items="${saList}">
<li>
	<div class="searchList_box">
			<b><span class="sListSpan">${dto.dvisionName}</span></b>
			<span class="sListSpan">${dto.title}</span>
			<span class="sListSpan_r">작성자 : ${dto.writer}</span>
			<br><br>${dto.contents}			
	</div>
</li>
</c:forEach>	
</ul>
</div>
</form>


<div id=page_btn>
	<input type="button" value="이전 < ">
	<input type="button" value="1">
	<input type="button" value="다음 > ">
</div>
<hr>
<div id="footer" class="footer">
		<%@ include file="/common/footer.jsp"%>
</div>
</div>
</body>
</html>