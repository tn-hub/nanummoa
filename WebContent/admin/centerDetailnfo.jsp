<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>센터 회원가입</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">
<script src="${initParam.CONTEXT_PATH}/resources/js/jquery-3.6.0.min.js"></script>
<!-- 통합 로딩 방식 : postcode.v2.js 라는 이름의 파일 로딩을 통해 우편번호 서비스를 이용 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="${initParam.CONTEXT_PATH}/resources/js/memberInput.js"></script>
<style type="text/css">
tr {
	margin : 5px;
	
}
input,th,td {
	margin : 5px;
	padding: 3px;
	padding-left: 20px;
}

	th {
	background-color: #FBD157;	
}

table{
width: 1000px;
height: 200px;
}

#userInput_div {
	width: 1000px;
	margin: auto;
	margin-bottom: 30px;
}

.head_hr {
		margin-bottom: 50px;
}

.input_button_div {
	margin-top: 30px;
	text-align: center;
}

.error_message {
	color: red;
	font-size: 13px;
	display: inline-block;
	width: 440px;
}

.small_span {
	font-size: 7px;
}

#mobile1, #ceoMobile1 {
	margin-left: 5px;
	height: 30px;
}

.large_select {
	width: 150px;
}
</style>


</head>
<body>
<!-- header -->
<%@ include file="/common/header.jsp"%>

<!-- 센터 회원 상세 --------------------------------------------------------------------------------->
<div id="userInput_div">
<h1>내 정보</h1>
<hr class="head_hr">
	<form action="${CONTEXT_PATH}/admin/adminController?action=centerDetail" method="post" id="centerUpdateForm">
		<table border="1">
			<tr>
				<th>이름</th>
				<td>
					${dto.cmemberName}
				</td>
			</tr>
			
			<tr>
				<th>아이디</th>
				<td>
					${dto.centerId}
				</td>
			</tr>
			
			
			<tr>
				<th>휴대폰 번호</th>
				<td>
					${dto.ceoMobile}
				</td>
			</tr>
			
			<tr>
				<th>이메일</th>
				<td>
					${dto.cmemberEmail}
				</td>
			</tr>
		</table>

			<h1>봉사센터 정보</h1>
			<table border="1">
				<tr>
					<th>등록번호</th>
					<td>
						${dto.registerCode}
					</td>
				</tr>

				<tr>
					<th>기관이름</th>
					<td>
						${dto.centerName}
					</td>
				</tr>

				<tr>
					<th>등록(설립)일자</th>
					<td>
						${dto.centerEntryDate}
					</td>
				</tr>

				<tr>
				<th>주소</th>
				<td>
					${dto.centerAddress}
				</td>
			</tr>

				<tr>
					<th>대표자 이름</th>
					<td>
						${dto.ceoName}
					</td>
					
				</tr>

				<tr>
				<th>휴대폰 번호</th>
				<td>
					${dto.ceoMobile}
				</td>
			</tr>

				<tr>
					<th>서비스 대상</th>
					<td>
						${dto.service}
					</td>
				</tr>
			</table>
		</form>
</div>
<!-- 회원가입 폼 -->
	
<!-- footer -->
<%@ include file="/common/footer.jsp"%>
</body>
</html>