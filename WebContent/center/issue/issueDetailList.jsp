<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청자 목록</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">

<style type="text/css">
.wrapper{
width: 100%;
}

.title{
width: 1000px;
margin:  auto;
}

.link{
text-align: center;

}

#vol_list_div {
		width: 1000px;
		margin: auto;
	}
	
	#vol_list_div a:link {
		color: #3F3F3F;
		text-decoration: none;
	}
	
	#vol_list_div a:visited {
		color: #3F3F3F;
		text-decoration: none;
	}
	
	#vol_list_div a:hover {
		text-decoration:underline;
	}
	
	#vol_search_table {
		text-align: center;
		border-top: 2px solid black;
		border-bottom: 2px solid black;	
		width: 900px;
		border-collapse: collapse;
		margin-bottom: 30px;
		font-size: 17px;
	}  
	
	#vol_search_table th {
		border-right: 1px solid #b3b3b3;
		height: 50px;
	}
	
	#vol_search_table td {
		border-right: 1px solid  #b3b3b3;
		border-top: 1px solid  #b3b3b3;
	}
	
	#vol_search_table th:last-child,  #vol_search_table td:last-child{
		border-right: none;
	}
	
	.search_table_large_th {
		width: 300px;
	}
	
	#vol_search_table select {
		border-style: none;
		height: 30px;
		color: #5F5F5F;
		font-size: 17px;
	}
	
	.search_large_select {
		width: 345px;
	}
	
	.search_small_select {
		width: 150px;
	}
	
	#vol_list_div .head_hr {
		margin-bottom: 50px;
	}
	
	#vol_search_box {
		width: 960px;
		height: 150px;
		border: 1px solid #b3b3b3;
		padding: 30px 20px;
	}
	
	#vol_search_box span {
		display: inline-block;
		width: 120px;
	}
	
	#vol_search_box div {
		width: 480px;
		height: 60px;
		float: left;
		color: #5F5F5F;	
		font-size: 17px;
		font-weight: bold;
	}
	
	#vol_search_box input[type="date"] {
		width: 130px;
		height: 30px;
	}
	
	#vol_search_box input[type="text"] {
		width: 285px;
		height: 30px;
		line-height: 30px;
	}
	
	#search_button_box {
		width: 1000px !important;
		text-align: center;
	}
	
	#search_button_box input {
		width: 100px;
		height: 40px;
		margin-right: 10px;
		cursor: pointer;
	}
	
	#vol_search {
		height: 400px;
	}
	
	#vol_list_div em {
		color: red;
	}
	
	.list_head_hr {
		border: 1px solid black;
		margin-bottom: 20px;
	}
	
	.list_hr {
		margin-top: 20px;
		margin-bottom: 20px;
	}
	
	.vol_list_ul {
		list-style:none;
		color: #5F5F5F;	
		padding-inline-start: 0px;
	}
	
	.vol_list_ul li {
		text-indent: 20px;
	}
	
	 .title_span {
	 	color: #3F3F3F !important;
		font-weight: bold;
	}
	
	.vol_list_ul .span_box1 {
		display: inline-block;
		width: 250px;
	}
	
	.vol_list_ul .deadline_box {
		text-align: right;
	}
	
	.link_div{
	text-align: right;
    height: 30px;
    width: 900px;
	}
	
	.link_box{
	display: inline-block;
    font-weight: bold;
    width: 490px;
    margin: 0 auto;
    text-align: center;
	}
	
	.link{
	margin: -60px;
    padding: 7px 0;
    border: 2px solid #666;
    font-size: 16px;
    text-align: center;
	
	}
	
	h2{
	text-align: center;
	}	
	
	.detatil_btn{
	margin-left: 3px; 
	}
	
</style>
<body>
<%@ include file="/common/header.jsp"%>

<div class="contents">
	<div class="wrapper">
		<div class="title">
			<h1>신청자 목록</h1>
		<hr>
		
	<h2>${list[0].volTitle }</h2>
	<p>[전체 <em style="color: red;">${fn:length(list)}
	</em>건, 현재페이지 <em>0</em>/0]</p>
	<hr class="list_head_hr">
	<c:forEach var="dto" items="${list}">
	<ul class="vol_list_ul">
		<li>
			<div class="list_box">
				<div class="span_box1">
					<span class="title_span">[신청인 이름]</span>
					<span>${dto.generalName }</span>
				</div>
				
				<div class="span_box1">
					<span class="title_span">[신청회원 아이디]</span>
					<span>${dto.generalId }</span>
				</div>
				
				<div class="span_box1">
					<span class="title_span">[연락처]</span>
					<span>${dto.generalMobile }</span> 
				</div>
				
				<input type="button" value="상세정보" class="g_btn detatil_btn" onclick="location.href='${CONTEXT_PATH }/center/centerController?action=issueInfoForm&volInfoNo=${dto.volInfoNo}&generalId=${dto.generalId }'">
				<c:choose>
					<c:when test="${dto.activityStatus == 2 and dto.recStatus == 2}">
							<input type="button" value="인증서 발급" class="y_btn detatil_btn" onclick="location.href='${CONTEXT_PATH }/center/centerController?action=volIssueForm&volInfoNo=${dto.volInfoNo}&generalId=${dto.generalId }'">
					</c:when>
					<c:when test="${dto.activityStatus == 3 and dto.recStatus == 2}">
							<input type="button" disabled="disabled" value="인증서 발급" class="y_btn detatil_btn" onclick="location.href='${CONTEXT_PATH }/center/centerController?action=volIssueForm&volInfoNo=${dto.volInfoNo}&generalId=${dto.generalId }'">
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose> 	
			</div>
			
			<hr class="list_hr">
		</li>
	</ul>
	</c:forEach>
		</div>
	</div>
</div>

<%@ include file="/common/footer.jsp"%>
</body>
</html>