<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.nanum.dto.CenterInfo" %>    
<%@ page import="java.util.ArrayList" %>    
<%@ include file="/inc/taglib_menu.jsp" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>센터회원 목록</title>
<style type="text/css">
#section_contents{
	width: 1000px; 
	border: 1px gray; 
	margin: 0 auto;
}

#cecnter_standBy{
	border: 1px solid gray;
	width: 1000px;
	height: 80px;
	margin-top: 20px;
	margin-bottom: 20px;
}


ul li{
	list-style-type: none;
	float: left;
	margin-right: 30px;
	padding-top: 20px;
}

#c_stan_btnArea{
	float: right;
}


.btn_cenList{
	border-style: none;
	width: 495px;
	height: 40px;
	text-align: center;
}

#c_in_refuse{
	border-style: none;
	width: 100px;
	height: 30px;
	margin-right: 30px;
	border-radius: 5px;
	font-size: 16px;
	background-color: gold;
}

#c_in_accept{
	border-style: none;
	width: 100px;
	height: 30px;
	border-radius: 5px;
	font-size: 16px;
	background-color: red;
}

</style>
</head>

<script type="text/javascript">

$(document).ready(function() {
	$('#centerDetailInfo').hide();
	 
	$("#detailCId").click(function () {
		if($('#centerDetailInfo').css('display') == 'none'){
            $('#centerDetailInfo').show();
        }else{
            $('#centerDetailInfo').hide();
        }
	});
	
});

</script>

<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2>센터회원 가입대기 목록</h2><br>
[가입대기 센터회원 <em>${cDto.totAcceptCnt}</em> 건]
<a href="${CONTEXT_PATH}/admin/adminController?action=centerAcceptList" style="float:right;">조회</a>
<hr>
<c:forEach var="dto" items="${centerActList}">
<div id="cecnter_standBy">
	<ul>
		<li>센터명 : ${dto.centerName}</li>
		<li>센터 아이디 : <span id="detailCId" style="cursor:hand;">${dto.centerId}</span></li>
		<li>신청일 : ${dto.centerEntryDate}</li>
		<li id="c_stan_btnArea">
			<a href="${CONTEXT_PATH}/admin/adminController?action=centerRefuse&centerId=${dto.centerId}"><input type="button" value="반려" id="c_in_refuse" style="cursor:hand;"></a>
			<a href="${CONTEXT_PATH}/admin/adminController?action=centerAccept&centerId=${dto.centerId}"><input type="button" value="승인" id="c_in_accept" style="cursor:hand;"></a>
		</li>
	</ul>
</div>
</c:forEach>
<div id="centerDetailInfo">
		<ul>
			<li>센터명 : </li>
			<li>센터 아이디 : <span id="detailCId" ></span></li>
		</ul>
	</div>

<div id="footer" class="footer">
	<%@ include file="/common/footer.jsp"%>
</div>
</div>
</body>
</html>