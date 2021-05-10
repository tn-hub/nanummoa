<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자원봉사 신청</title>
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

</style>
<script type="text/javascript">
	function selectAll(selectAll)  {
	  const checkboxes  = document.getElementsByName('volDetailNo');
	  checkboxes.forEach((checkbox) => {
	    checkbox.checked = selectAll.checked;
	  })
	};
</script>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<form action="${CONTEXT_PATH}/general/generalController?action=enrollVol" method="post">
<div id="vol_list_div" style="margin-bottom: 150px;">
	<h1>자원봉사 신청</h1>
	<hr class="head_hr" style="margin-bottom:20px;">
	<h2>${list[0].volTitle}</h2>
	
	<h3>◎ 봉사일자 선택</h3>
	<ul>
	<input type="checkbox" id="checkAll" name="checkAll" onclick="selectAll(this)"/> <b>전체선택</b>
	<c:forEach var="dto" items="${list}">
		<li>
			<input type="checkbox" id="checked" name="volDetailNo" value="${dto.volDetailNo}">
			${dto.volDate}<span class="separator">|</span>
			${dto.recStatus eq "0" ? "모집중" : "마감"}<span class="separator">|</span>
			신청인원 : ${dto.applyCount < 1  ? 0 : dto.applyCount}/${dto.totalCount}명
		</li>	
	</c:forEach>
	</ul>
	
	<h3>◎ 신청자 정보</h3>
	<input type="button" value="내정보수정" class="float_r y_btn" onclick="location.href='${CONTEXT_PATH}/general/generalController?action=generalMyInfoForm'">
	<hr class="clear_b">
	<ul class="vol_list_ul">
		<li>
			<div class="span_box">
				<span class="title_span">[이름]</span>
				<span>${dto.generalName}</span>
			</div>
			<div class="span_box">
				<span class="title_span">[연락처]</span>
				<span>${dto.generalMobile}</span>
			</div>
			<div class="span_box">
				<span class="title_span">[이메일]</span>
				<span>${dto.generalEmail}</span>
			</div>
		</li>
	</ul>
	<hr>
	<ul style="margin: 50px;">
		<li>회원님이 등록하신 정보가 표시됩니다. 등록된 정보가 정확한지 확인해주세요.</li>
		<li>연락처가 미등록된 경우 봉사활동 정보를 받으실 수 없습니다.</li>
		<li>휴대폰번호 및 이메일로 봉사활동 정보를 제공 받으시겠습니까?</li>
	</ul>
	<p style="text-align: center;"><input type="checkbox" name="agree" id="agree" required="required"> (필수) 정보제공 및 수신동의</p>
	<div style="text-align: center;">
		<input type="submit" class="y_btn" value="신청하기">
		<input type="reset" class="g_btn" value="취소">		
	</div>
</div>
</form>
<%@ include file="/common/footer.jsp"%>
</body>
</html>