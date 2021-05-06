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

#search_opt{
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





</style>
</head>


<script type="text/javascript">


function search_qna(){
	
	//검색조건
	var searchOptEl = ${"search_opt"};   
	var searchOpt = searchOptEl.value;
	
	// 검색 값
	var searchTextEl = ${"search_text"}; 	
	var searchText = searchTextEl.value;
	
	if (searchOpt == "T" || searchOpt == "C" || searchOpt == "W"){
		if (searchText == null || searchText == ""){
			alert("검색내용을 입력하세요");
			searchText.focus();
			return;
		}		
	}
	// 검색 submit 
	document.qnaListForm.submit();
}


</script>

<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2>QNA</h2>
<form name="qnaListForm" id="qnaListForm" action="${CONTEXT_PATH}/common/commonController?action=qnaList" method="post">
<div id="search_qna">
	<table id="search_qna_table">
	<tr>
		<td id="search_qna_tdcnt">
			[전체 <em> ${cdto.totCnt}</em> 건, 현재 페이지 <em>1</em> /1]
		
		</td>
		<td id="search_qna_tdSelect">
			<select  id="search_opt" name ="search_opt">
				<option>전체</option>
				<option value="T" >제목 </option>
				<option value="C" >내용</option>
				<option value="W" >작성자</option>
			</select>
		</td>
		<td id="search_qna_tdText">
			<input type="text" id="search_text" name ="search_text">
			<input type="button" value="검색" onclick="search_qna()" style="cursor:hand;">
		</td>
	</tr>
	</table>
</div>
<hr>
<div id="addQna"><a href="${CONTEXT_PATH}/common/commonController?action=qnaInputForm"><input type="button" value="글 쓰기" id="btn_addQna" style="cursor:hand;"></a></div>
<div id="sec_vol_list">
<ul class="vol_list_ul">
<c:forEach var="dto" items="${qnaList}">
<li>
	<div class="list_box">
		<div>
			<span class="title_span">글번호 : </span>
			<span>${dto.qnaNo}</span>
				<span class="qna_list_span">답변 여부 : ${dto.answerYn}</span>
				<span class="qna_list_span">작성자 : ${dto.qnaWriter}</span>
				<span class="qna_list_span">작성일 : ${dto.qnaWriteDate}</span>
			
		</div>
		
		<h3>제목 : <a href="${CONTEXT_PATH}/common/commonController?action=qnaDtl&qnaNo=${dto.qnaNo}" >${dto.qnaTitle}</a></h3>
		
	</div>
	
	<hr class="list_hr">
</li>
</c:forEach>	
</ul>
</div>
</form>
</div>
<div id=page_btn>
	<input type="button" value="이전 < ">
	<input type="button" value="1">
	<input type="button" value="다음 > ">
</div>
<hr>
<div id="footer" class="footer">
		<%@ include file="/common/footer.jsp"%>
</div>
</body>
</html>