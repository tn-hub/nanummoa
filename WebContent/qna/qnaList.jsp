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


#addQna{
	width: 1000px;
	height: 30px;
	float: right;  
	width: 20px; 
	margin-right: 80px; 
	margin-top: 10px;
}

#btn_addQna{
	width: 100px;
	height: 30px;
	border-style: none;
	font-size: 17px;
}

.search_opt{
	border-style: none;
	font-size: 17px;
}

#sec_vol_list{
	margin-top: 70px;
	border-top: 2px solid black;
	border-bottom: 2px solid black;	
}


.vol_list_ul {
		list-style:none;
		color: #5F5F5F;	
		padding-inline-start: 0px;
	}
	
.vol_list_ul li {
		text-indent: 20px;
	}	
	
	
.qna_list_span{
	float: right;
}	
	

#page_btn{
	text-align: center; 
	margin: 20px 0px 20px 0px;
}


#search_qna_table{
	border-top: 2px solid black;
	border-bottom: 2px solid black;	
	font-size: 17px;
	width: 1000px;
}

#search_qna_table tr, td{
	padding: 7px;
}



#search_qna_tdcnt{
	width: 400px;
	text-align: left;
}

#search_qna_tdSelect{
	width: 150px;
}

#search_qna_tdText{
	width: 250px;
}

#search_text{
	width: 230px;
	font-size: 17px;
}

#btn_searchQna{
	width: 60px;
	height: 30px;
	border-style: none;
	font-size: 17px;
}

em{
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
	$("#search_btn").click(function() {
		//????????????
		var searchOpt = $("#search_opt").val();   
		// ?????? ?????? ?????? 
		if (searchOpt == "T" || searchOpt == "C" || searchOpt == "W"){
			if ($("#search_text").val() == null || $("#search_text").val() == "") {
				alert("??????????????? ???????????????");
				$("#search_text").focus();
				return false;
			}
		}		
	});
	
	// ???????????????
	$("#preBtn").click(function() {
		var curPageNum = ${curPageNum};
		if (curPageNum> 1){
			$("#pageNum").val(curPageNum -1);
			document.qnaListForm.submit();
		}
	});
	
	// ???????????????
	$("#nestBtn").click(function() {
		var curPageNum = ${curPageNum};
		var lastPageNum = ${lastPageNum};
		
		if (curPageNum < lastPageNum){
			$("#pageNum").val(curPageNum +1);
			document.qnaListForm.submit();
		}
	});
});

	// ????????? submit
	function btnPageNum(ret){
		$("#pageNum").val(ret);
		document.qnaListForm.submit();
		
	}
	
</script>

<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h1>QNA</h1>
<form name="qnaListForm" id="qnaListForm" action="${CONTEXT_PATH}/common/commonController?action=qnaList" method="post">
<div id="search_qna">
	<table id="search_qna_table">
	<tr>
		<td id="search_qna_tdcnt">
			[?????? <em> ${cdto.totCnt}</em> ???, ?????? ????????? <em>${curPageNum}</em> 
			<c:choose>
			<c:when test="${lastPageNum == 0}">/1]</c:when>
			<c:otherwise>/ ${lastPageNum}]</c:otherwise>
			</c:choose>
		
		</td>
		<td id="search_qna_tdSelect">
			<select id="search_opt" name ="search_opt" class="search_opt">
				<option value="" >?????? </option>
				<option value="T" <c:if test="${searchOpt eq 'T'}">selected</c:if>> ?????? </option>
				<option value="C" <c:if test="${searchOpt eq 'C'}">selected</c:if>> ?????? </option>
				<option value="W" <c:if test="${searchOpt eq 'W'}">selected</c:if>> ????????? </option>
			</select>
		</td>
		<td id="search_qna_tdText">
			<input type="text" id="search_text" name ="search_text" value="${searchText}">
			<input type="submit" id="search_btn" name="search_btn" value="??????"  style="cursor:hand;">
		</td>
	</tr>
	</table>
</div>
<hr>
<c:if test="${not empty grade and grade != 'A'}">
<div id="addQna"><a href="${CONTEXT_PATH}/common/commonController?action=qnaInputForm"><input type="button" value="??? ??????" id="btn_addQna" style="cursor:hand;"></a></div>
</c:if>	
<div id="sec_vol_list">
<ul class="vol_list_ul">
<c:forEach var="dto" items="${qnaList}">
	<li>
		<span class="title_span">????????? : ${dto.qnaNo}</span>
		<span class="qna_list_span">?????? ?????? : ${dto.answerYn}</span>
		<span class="qna_list_span">????????? : ${dto.qnaWriter}</span>
		<span class="qna_list_span">????????? : ${dto.qnaWriteDate}</span>
		<h3>?????? : <a href="${CONTEXT_PATH}/common/commonController?action=qnaDtl&qnaNo=${dto.qnaNo}" >${dto.qnaTitle}</a></h3>
	</li>
	<hr>
</c:forEach>	
</ul>
</div>
<input type="hidden" value="1" id="pageNum" name="pageNum">
</form>
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



		<%@ include file="/common/footer.jsp"%>
</body>
</html>