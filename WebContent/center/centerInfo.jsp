<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>센터회원 자원봉사 목록</title>
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
		/* background-color: blue; */
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
	
	.vol_list_ul .span_box {
		display: inline-block;
		width: 300px;
	}
	
	.vol_list_ul .deadline_box {
		text-align: right;
	}
	
	.link_box{
	display: inline-block;
    font-weight: bold;
    width: 490px;
    margin: 0 auto;
    text-align: center;
	}
	em{
	color: red;
	}
	.g_btn{
	    margin-left: 10px;
	}
	.bg_y{
	background-color: #ffdf89;
	}
</style>
</head>
<body>
<%@ include file="/common/header.jsp"%>

<div class="contents">
	<div class="wrapper">
		<div class="title">
			<h1>자원 봉사 목록</h1>
		<hr>
		<div class="link_box ${tap == 1? ' bg_y' : ''}">
			<a href="${CONTEXT_PATH}/center/centerController?action=centerVolListForm">모집 봉사</a>
		</div>
		<div class="link_box ${tap == 2? 'bg_y' : ''}">
			<a href="${CONTEXT_PATH }/center/centerController?action=deadlineList">마감 봉사</a>
		</div>
		<hr>
		
	<p>[전체 <em style="color: red;">${fn:length(list)}</em>건]</p>
	<hr class="list_head_hr">
	<c:forEach var="dto" items="${list }">
	<ul class="vol_list_ul">
		<li>
			<div class="list_box">
				<div>
					<c:choose>
						<c:when test="${dto.recStatus == '0'}">
							<span class="title_span">모집중</span>
						</c:when>
						<c:when test="${dto.recStatus == '1'}">
							<span class="title_span">모집마감</span>
						</c:when>
						<c:when test="${dto.recStatus == '2'}">
							<span class="title_span">활동종료</span>
						</c:when>
					</c:choose>
					<span>${dto.categoryName}</span>
				</div>
					<input type="button" value="신청 내역" class="float_r g_btn" onclick="location.href='${CONTEXT_PATH }/center/centerController?action=applyList&volInfoNo=${dto.volInfoNo}'">
				<c:choose>
					<c:when test="${dto.recStatus == '0' }">
						<input type="button" value="모집 마감 " class="float_r g_btn" onclick="location.href='${CONTEXT_PATH }/center/centerController?action=endVol&volInfoNo=${dto.volInfoNo}&recStatus=${dto.recStatus}'">
					</c:when>
					<c:when test="${dto.recStatus == '1' }">
						<input type="button" value="활동 종료 " class="float_r g_btn" onclick="location.href='${CONTEXT_PATH }/center/centerController?action=endVol&volInfoNo=${dto.volInfoNo}&recStatus=${dto.recStatus}'">
					</c:when>
				</c:choose>
				<h3><a href="${CONTEXT_PATH}/common/commonController?action=volDetatilForm&volInfoNo=${dto.volInfoNo}">${dto.volTitle }</a></h3>
				<div class="span_box clear_b ">
					<span class="title_span">[모집기관]</span>
					<span>${dto.centerName }</span>
				</div>
				
				<div class="span_box">
					<span class="title_span">[모집기간]</span>
					<span>${dto.startDate }</span> ~
					<span>${dto.endDate }</span>
				</div>
				
				<div class="span_box">
					<span class="title_span">[봉사기간]</span>
					<span>${dto.volStart }</span> ~
					<span>${dto.volEnd }</span>
				</div>
			</div>
											
			<div class="deadline_box">
			<c:choose>	
				<c:when test="${dto.deadline > 0}">
					<p>마감 <em>${dto.deadline }</em> 일전</p>
				</c:when>
				<c:when test="${dto.deadline == 0}">
					<em>오늘 마감</em>
				</c:when>
				<c:otherwise>
					<p>마감 종료</p>
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
