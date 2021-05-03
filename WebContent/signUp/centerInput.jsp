<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>센터 회원가입</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">

<style type="text/css">
tr {
	margin : 5px;
}
input,th,td {
	margin : 5px;
	padding: 3px;
}

table{
width: 600px;
height: 200px;
}
</style>

</head>
<body>
	<div id="header" class="header">
		<%@ include file="/common/header.jsp"%>
	</div>

<h3>봉사센터 회원가입</h3>
<form action="" method="post">
	<table border="1">
		<tr>
			<th>이름</th>
			<td><input type="text" id="centerName" name="centerName" placeholder="이름" required="required"></td>
		</tr>
		
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" id="centerId" name="centerId" placeholder="6 ~ 30자리 이내" required="required" size="15">
				<input type="button" value="중복조회">
				</td>
		</tr>
		
		<tr>
			<th>비밀번호</th>
			<td><input type="password" id="centerPw" name="centerPw" placeholder="8 ~ 20자리 이내로 영문,숫자,특수문자 포함 입력" required="required"></td>
		</tr>
		
		<tr>
			<th>비밀번호 확인</th>
			<td><input type="password" id="centerPw2" name="centerPw2" required="required"></td>
		</tr>
		
		<tr>
			<th>휴대폰 번호</th>
			<td>
				  <input type="text" id="mobile1" name="mobile1" value="010" size="3">
				- <input type="text" id="mobile2" name="mobile2" size="3">
				- <input type="text" id="mobile3" name="mobile3" size="3">
			</td>
		</tr>
		
		<tr>
			<th>이메일</th>
			<td>
				  <input type="text" id="email1" name="email1" size="15">
				@ <input type="text" id="email2" name="email2" size="15">
					<select>
						<option value="none">직접입력</option>
						<option value="네이버">naver.com</option>
						<option value="구글">gmail.com</option>
					</select>
			</td>
		</tr>
	</table>
	
	<h3>봉사센터 추가 정보</h3>
	<table border="1">
		<tr>
			<th>등록번호</th>
			<td><input type="text" id="centerId" name="centerId" size="15"></td>
		</tr>
		
		<tr>
			<th>기관이름</th>
			<td><input type="text" id="centerName" name="centerName" size="15"></td>
		</tr>
		
		<tr>
			<th>등록(설립)일자</th>
			<td><input type="text" id="centerEntryDate" name="centerEntryDate" size="15"></td>
		</tr>
		
		<tr>
			<th>주소</th>
			<td>
				<input type="text" id="zipCode" name="zipCode" placeholder="우편번호" readonly="readonly" size="5">
				<input type="button" value="우편번호 찾기" ><br>
				
				<input type="text" id="adress" name="adress" placeholder="기본주소" size="30"></br>
				<input type="text" id="dteilAdress" name="dteilAdress" placeholder="상세주소" size="40">
			</td>
		</tr>
		
		<tr>
			<th>대표자 이름</th>
			<td><input type="text" id="ceoName" name="ceoName" size="5"></td>
		</tr>
		
		<tr>
			<th>대표자 번호</th>
			<td>
				  <input type="text" id="ceoMobile1" name="ceoMobile1" value="010" size="3">
				- <input type="text" id="ceoMobile2" name="ceoMobile2" size="3">
				- <input type="text" id="ceoMobile3" name="ceoMobile3" size="3">
			</td>
		</tr>
		
		<tr>
			<th>서비스 대상</th>
			<td><input type="text" id="service" name="service" size="5"></td>
		</tr>
	</table>
	
</form>
	<div id="footer" class="footer">
		<%@ include file="/common/footer.jsp"%>
	</div>
</body>
</html>