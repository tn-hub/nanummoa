<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증서 다운</title>
<script type="text/javascript" src="${CONTEXT_PATH }/resources/js/common.js"></script>
<style type="text/css">
#pdf_wrap {
  position: absolute;
  width: 1000px;
  height: 1200px;
  padding: 15px;
  text-align:center;
  border: 1px solid black;
  margin: auto;
}


.memberInfo {
	width: 700px;
	height: 300px;
	text-align:left;
	margin: auto;
}

.memberInfo h3 {
	display: inline-block;
}

.memberInfo span {
	font-size: 20px;
}

.title_h {
	font-weight: bold;
	font-size: 60px;
	margin-top: 70px;
	margin-bottom: 100px;
}
	
.left_wrap {
	position: relative;
	left: -350px;
}

.right_wrap {
	position: relative;
	right: -350px;
}

.vol_info_ul {
	width: 700px;
	margin: 150px auto;
	text-align: left;
	font-size: 20px
}

.write_date {
	font-size: 25px;
	margin-bottom: 70px;
}

.title {
margin-bottom: 150px;
}

.foo{
margin-top: 300px; 
}

</style>
<script type = "text/javascript" src = "http://code.jquery.com/jquery-latest.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<script type = "text/javascript" src = "https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<form action="${CONTEXT_PATH }/center/centerController?action=volIssue" method="post" >
<input type="submit" class="y_btn detatil_btn" value="발급하기">

<input type="hidden" id="volInfoNo" name="volInfoNo" value="${map.volInfoNo}">
<input type="hidden" id="generalId" name="generalId" value="${generalId }">
<input type="hidden" id="volApplyNo" name="volApplyNo" value="${map.volApplyNo }">
<input type="hidden" id="issueDate" name="issueDate" value="${map.issueDate }">

<div id="pdf_wrap">
		<h1 class="title_h">자원봉사활동 확인서</h1>
		
		<div class="memberInfo">
		<div class="title">
			<h2>${map.volTitle }</h2>
		</div>
			<h3>봉사활동 내용 : <input type="text" id="contents" name="contents" placeholder="간략 봉사내용" size="50"></h3>
			<hr>
			<h3>자원봉사 활동기간 : ${map.startDate } ~ ${map.endDate }</h3>
			<hr>
			<h3>자원봉사 활동시간 : ${map.startTime } - ${map.endTime }</h3>
			<hr>
		</div>
<div class="foo">
		<h2>위와 같이 자원봉사 활동에 참여하였음을 확인함</h2>
		<p class="write_date">발행일 : ${map.issueDate }</p>

		<h1>${map.centerName }</h1>
</div>
</div>
</form>
</body>
</html>