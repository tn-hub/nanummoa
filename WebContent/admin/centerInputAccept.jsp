<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

#cenList_btnArea{
	text-align: center;
	margin-bottom: 20px;
	margin-top: 20px;
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
}

#c_in_accept{
	border-style: none;
	width: 100px;
	height: 30px;
}

</style>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2>센터회원 가입대기 목록</h2>
<hr>
<div class="cenList_btnArea">
	<a href=""><input class="btn_cenList" type="button" value="가입대기 센터회원"></a>
	<a href="${CONTEXT_PATH}/admin/adminController?action=centerAcceptListForm"><input class="btn_cenList" type="button" value="가입승인 센터회원"></a>
</div>
<hr>

[가입대기 센터회원 <em>0</em> ]
<div id="cecnter_standBy">
<ul>
	<li>센터명 : </li>
	<li>센터 아이디 : </li>
	<li>신청일 : </li>
	<li id="c_stan_btnArea">
		<input type="button" value="반려" id="c_in_refuse">
		<input type="button" value="승인" id="c_in_accept">
	</li>
</ul>
</div>

<div id="footer" class="footer">
	<%@ include file="/common/footer.jsp"%>
</div>
</div>
</body>
</html>