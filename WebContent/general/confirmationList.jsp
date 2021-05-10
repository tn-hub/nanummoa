<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증서 발급</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">
<style type="text/css">
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
	.list_end_hr {
		border: 1px solid black;
		margin-top: -21px;
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
		width: 295px;
	}
	
	.vol_list_ul .deadline_box {
		text-align: right;
	}
	.apply_status{
		background-color: #FBD157;
		width: 70px;
		height: 25px;
		border-radius: 25px;
		margin-left: 20px;
		text-align: center;
		text-indent: 0px;
		padding: 5px;
	}
	
	.confirm_span_box {
		display: inline-block;
		width: 295px;
	}
</style>
<script type="text/javascript">
	function createPdf(volInfoNo, volConNo){
		console.log("들어옴");
		console.log(volInfoNo, volConNo);
		var popupX = (window.screen.width / 2) - 600;
		var popupY = (window.screen.height /2) - 200;
		window.open('${CONTEXT_PATH}/general/generalController?action=confirmationForm&volInfoNo=' +volInfoNo + '&volConNo=' + volConNo, 
				'window팝업', 'status=no, height=400, width=1200, left='+ popupX +
				'top='+ popupY + ', screenX='+ popupX + ', screenY= '+ popupY);
		
	}
</script>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<div class="contents">
	<div id="vol_list_div">
		<h1>봉사 활동내역</h1>
		<hr class="head_hr" style="margin-bottom:20px;">
		<!-- 봉사모집글 목록----------------------------------------------------------------------->
		<p style="margin-bottom:-5px;">[전체 
			<em>${totalcount}</em>
			건]
		</p>
		<p style="float: right;">
		</p>
		<hr class="list_head_hr" style="clear: right;">
		
		<ul class="vol_list_ul">
		<c:forEach var="dto" items="${list}">
			<li>
				<div class="list_box">
					<input type="button" value="다운로드" class="float_r y_btn" onclick="createPdf('${dto.volInfoNo}', '${dto.volConNo}')">	
					<h3><a href="${CONTEXT_PATH}/common/commonController?action=volDetatilForm&volInfoNo=${dto.volInfoNo}">${dto.volTitle}</a></h3>
					<div class="confirm_span_box">
						<span class="title_span">[모집기관]</span>
						<span>${dto.centerName}</span>
					</div>
					
					<div class="confirm_span_box">
						<span class="title_span">[봉사기간]</span>
						<span>${dto.startDate}</span> -
						<span>${dto.endDate}</span>
					</div>
					
					<div class="confirm_span_box">
						<span class="title_span">[진행시간]</span>
						<span>${dto.startTime}</span> -
						<span>${dto.endTime}</span>
					</div>
				</div>
				<hr class="list_hr">
			</li>
		</c:forEach>	
		</ul>
		<hr class="list_end_hr">
		<!-- 봉사모집글 목록 -->
	</div>
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>