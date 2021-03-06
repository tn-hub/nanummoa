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

em{
	color: red;
}

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
	background-color: #FBD157;
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

em {
	color: red;
}

#page_btn {
    text-align: center;
    margin: 50px 0px 20px 0px;
	}
	
	#preBtn, #nestBtn {
		cursor: pointer;
		width: 60px;
		height: 40px;
		text-align: center;
		border: 1px solid #DDD;
	}
	
	input[name="btnPageNum"] {
		cursor: pointer;
		width: 40px;
    	height: 40px;
		border: 1px solid #DDD;
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
	
	// ???????????????
	$("#preBtn").click(function() {
		var curPageNum = ${curPageNum};
		if (curPageNum> 1){
			$("#pageNum").val(curPageNum -1);
			document.searchAllForm.submit();
		}
	});
	
	// ???????????????
	$("#nestBtn").click(function() {
		var curPageNum = ${curPageNum};
		var lastPageNum = ${lastPageNum};
		
		if (curPageNum < lastPageNum){
			$("#pageNum").val(curPageNum +1);
			document.searchAllForm.submit();
		}
	});
	
});

//????????? submit
function main_btnPageNum(ret){
	$("#pageNum").val(ret);
	document.searchAllForm.submit();
	
}

</script>
<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2>????????????</h2>
<hr>
<form name="searchAllForm" id="searchAllForm" action="${CONTEXT_PATH}/common/commonController?action=searchAllForm" method="post">
<div class="searchAll_area">
	<select class="searchAll_option" id="searchAll_opt" name="searchAll_opt">
		<option value="A">??????</option>
		<option value="V" <c:if test="${searchAllOpt eq 'V'}">selected</c:if>>?????? ?????????</option>
		<option value="Q" <c:if test="${searchAllOpt eq 'Q'}">selected</c:if>>?????? ?????????</option>
	</select> 
	<input type="text" class="searchAll_searchtext" id="searchAll_text" name="searchAll_text" value="${searchAllText}">
	<input type="submit" value="??????" class="btn_searchAll" id="btn_searchAllList" name="btn_searchAllList" style="cursor:hand;">
</div>
<div class="searchAllDVS_area">
<ul class="searchAllDVS_menu" style="cursor:hand;">
	<li id="tab_searchAll" <c:if test="${searchAllOpt eq 'A'}">style="font-weight:bold;"</c:if>>??????</li>
	<li id="tab_searchVol" <c:if test="${searchAllOpt eq 'V'}">style="font-weight:bold;"</c:if> >??????</li>
	<li id="tab_searchQna" <c:if test="${searchAllOpt eq 'Q'}">style="font-weight:bold;"</c:if>>??????</li>
</ul>
</div>
<input type="hidden" value="1" id="pageNum" name="pageNum">
</form>
[?????? <em> ${totCnt}</em> ???, ?????? ????????? <em>${curPageNum}</em>
		<c:choose>
			<c:when test="${lastPageNum == 0}">/1]</c:when>
			<c:otherwise>/ ${lastPageNum}]</c:otherwise>
		</c:choose>
<div class="search_all_list">
<ul class="searchAllListUl">
<c:forEach var="dto" items="${saList}">
<li>
	<div class="searchList_box">
			<b><span class="sListSpan">${dto.dvisionName}</span></b>
			<c:if test="${dto.divisionSub eq 'qna'}">
				<a href="${CONTEXT_PATH}/common/commonController?action=qnaDtl&qnaNo=${dto.infoNo}">${dto.title}</a>
			</c:if>
			<c:if test="${dto.divisionSub eq 'vol'}">
  			  <a href="${CONTEXT_PATH}/common/commonController?action=volDetatilForm&volInfoNo=${dto.infoNo}">${dto.title}</a>
			</c:if>
			<span class="sListSpan_r">????????? : ${dto.writer}</span>
			<br><br>${dto.contents}			
	</div>
</li>
</c:forEach>	
</ul>
</div>
<div id="page_btn">
	<input type="button" value="?????? " id="preBtn" name="preBtn">
	 <c:choose>
			<c:when test="${lastPageNum == 0}">
				<input type="button" value= 1  name="btnPageNum" style="background-color: #FBD157;">
			</c:when>
			<c:otherwise>
				 <c:forEach var="i" begin="${ 1 }" end="${lastPageNum}">
             <input type="button" value=${ i }  name="btnPageNum"
             	<c:if test="${curPageNum == i}">
             		style="background-color: #FBD157;"
             	</c:if>
             >
       </c:forEach>   
			</c:otherwise>
		</c:choose>
	<input type="button" value="?????? " id="nestBtn" name="nestBtn">	
</div>
</div>
<div id="footer" class="footer">
	<%@ include file="/common/footer.jsp"%>
</div>
</body>
</html>