<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 봉사내역</title>
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
		width: 300px;
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
	.cancel_vol_btn{
		float: right;
	}
</style>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<div id="vol_list_div">
	<h1>나의 봉사내역</h1>
	<hr class="head_hr" style="margin-bottom:20px;">
	<!-- 봉사모집글 목록----------------------------------------------------------------------->
	<p style="margin-bottom:-5px;">[전체 
		<em>00</em>
		건, 현재페이지 
		<em>0</em>
		/0]
	</p>
	<p style="float: right;">
	※ 신청상태가 '접수'인 경우에 취소 가능합니다. '승인'인 경우 봉사센터로 문의해주세요.
	</p>
	<hr class="list_head_hr" style="clear: right;">
	
	<ul class="vol_list_ul">
		<li>
			<div class="list_box">
				<div>
					<div class="apply_status">접수</div>
					<input type="button" value="취소하기">
					<input type="button" value="취소하기" class="cancel_vol_btn" onclick="location.href='${CONTEXT_PATH}/general/generalController?action=cancelVol&volDetailNo='">
				</div>
				
				<h3><a href="#">코로나19 예방접종 보조지원(안내 및 체온측정)</a></h3>
				
				
				<div class="span_box">
					<span class="title_span">[신청일자]</span>
					<span>2021-00-00</span>
				</div>
				
				<div class="span_box">
					<span class="title_span">[봉사일자]</span>
					<span>2021-00-00</span>
				</div>
				
				<div class="span_box">
					<span class="title_span">[진행시간]</span>
					<span>09:00</span> ~
					<span>14:00</span>
				</div>
			</div>
			
			<hr class="list_hr">
		</li>
		<li>
			<div class="list_box">
				<div>
					<div class="apply_status">접수</div>
				</div>
				
				<h3><a href="#">코로나19 예방접종 보조지원(안내 및 체온측정)</a></h3>
				
				<div class="span_box">
					<span class="title_span">[신청일자]</span>
					<span>2021-00-00</span>
				</div>
				
				<div class="span_box">
					<span class="title_span">[봉사일자]</span>
					<span>2021-00-00</span>
				</div>
				
				<div class="span_box">
					<span class="title_span">[진행시간]</span>
					<span>09:00</span> ~
					<span>14:00</span>
				</div>
			</div>
			<hr class="list_hr">
		</li>
	</ul>
	<hr class="list_end_hr">
	<!-- 봉사모집글 목록 -->
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>