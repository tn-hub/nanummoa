<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 승인 센터회원</title>
<style type="text/css">
#section_contents{
	width: 1000px; 
	border: 1px gray; 
	margin: 0 auto;
}

#cenList_btnArea{
	margin: 20px 0px 20px 20px;
}

.btn_cenList{
	border-style: none;
	width: 495px;
	height: 40px;
	text-align: center;
}

#cList_tableArea{
	text-align: center;
}

#cList_table{
	margin-top:20px;
}

#cenList_name{
	width: 150px;
}

#cenList_entryDate{
	width: 120px;
}

#cenList_adrr{
	width: 550px;
}

#cenList_service{
	width: 150px;
}



#cList_table tr, th, td{
	border: 1px solid gray; 
	padding: 5px;
}
</style>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2>가입 승인 센터회원 목록</h2>
<hr>
<div class="cenList_btnArea">
	<a href="${CONTEXT_PATH}/admin/adminController?action=centerInputAcceptForm"><input class="btn_cenList" type="button" value="가입대기 센터회원"></a>
	<a href="${CONTEXT_PATH}/admin/adminController?action=centerAcceptListForm"><input class="btn_cenList" type="button" value="가입승인 센터회원"></a>
</div>

<div id="cList_tableArea">
<form name="qnaListForm" action="${CONTEXT_PATH}/admin/adminController?action=centerList" method="post">
<table id="cList_table">
	<tr>
		<th id="cenList_name">센터이름</th>
		<th id="cenList_adrr">센터 주소</th>
		<th id="cenList_service">서비스 대상</th>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
	</tr>
</table>
</form>
</div>
<hr>
</div>
</body>
</html>