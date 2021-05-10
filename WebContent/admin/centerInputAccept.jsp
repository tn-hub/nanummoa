<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.nanum.dto.CenterInfoDto" %>    
<%@ page import="java.util.ArrayList" %>    
<%@ include file="/inc/taglib_menu.jsp" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>센터회원 목록</title>
<style type="text/css">

em{
	color: red;
}

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


#cecnter_standBy_ul li{
	list-style-type: none;
	float: left;
	margin-right: 30px;
}

#centerNAme_li{
	width: 250px;
}

#centerId_li{
	width: 170px;
}

#centerDate_li{
	width: 170px;
}

#c_stan_btnArea{
	padding-top: 15px;
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
	background-color: #D6D09C;
	color: #FFFFFF;
}

#c_in_accept{
	border-style: none;
	width: 100px;
	height: 30px;
	border-radius: 5px;
	font-size: 16px;
	background-color: #F15F5F;
	color: #FFFFFF;
}


#centerDetail{
	border: 1px solid gray;
}


#page_btn{
	text-align: center; 
	margin: 20px 0px 20px 0px;
}

</style>
</head>
<script type="text/javascript">

$(document).ready(function() {

	// 페이징 submit
	function btnPageNum(ret){
		$("#pageNum").val(ret);
		document.centerAcceptListForm.submit();
		
	}
	
	function divhideen(ret){
		alert(ret);
		
	}
	
	

</script>

<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2>센터회원 가입대기 목록</h2><br>
[가입대기 센터회원 <em>${cDto.totAcceptCnt}</em> 건]
<hr>
<form name="centerAcceptListForm" id="centerAcceptListForm" action="${CONTEXT_PATH}/admin/adminController?action=centerAcceptList" method="post">
<c:forEach var="dto" items="${centerActList}">
<div id="cecnter_standBy">
	<ul id="cecnter_standBy_ul">
		<li id="centerNAme_li">[센터명] <br>${dto.centerName}</li>
		<li id="centerId_li">[센터 아이디] <br><span id="detailCId" style="cursor:hand;" onclick="divhideen(${dto.centerId})">${dto.centerId}</span></li>
		<li id="centerDate_li">[신청일] <br>${dto.cmemberEntryDate}</li>
		<li id="c_stan_btnArea">
			<a href="${CONTEXT_PATH}/admin/adminController?action=centerRefuse&centerId=${dto.centerId}"><input type="button" value="반려" id="c_in_refuse" style="cursor:hand;"></a>
			<a href="${CONTEXT_PATH}/admin/adminController?action=centerAccept&centerId=${dto.centerId}"><input type="button" value="승인" id="c_in_accept" style="cursor:hand;"></a>
	</ul>
</div>
<hr>
</c:forEach>
</form>

<div id="footer" class="footer">
	<%@ include file="/common/footer.jsp"%>
</div>
</div>
</body>
</html>