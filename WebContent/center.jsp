<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원가입</title>

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
<h3>회원가입</h3>

<table border="1">
	<tr>
		<th>이름</th>
		<td><input type="text" id="generalName" name="generalName" placeholder="이름" required="required"></td>
	</tr>
	
	<tr>
		<th>성별</th>
		<td>
			<input type="radio" id="man" name="gender"> 남성
			<input type="radio" id="woman" name="gender"> 여성
		</td>
	</tr>

	<tr>
		<th>생년월일</th>
		<td><input type="text" id="brith" name="brith" placeholder="ex) 19901010" required="required" size="10"></td>
	</tr>
	
	<tr>
		<th>아이디</th>
		<td>
			<input type="text" id="generalId" name="generalId" placeholder="6 ~ 30자리 이내" required="required" size="15">
			<input type="button" value="중복조회">
			</td>
	</tr>
	
	<tr>
		<th>비밀번호</th>
		<td><input type="password" id="generalPw" name="generalPw" placeholder="8 ~ 20자리 이내로 영문,숫자,특수문자 포함 입력" required="required"></td>
	</tr>
	
	<tr>
		<th>비밀번호 확인</th>
		<td><input type="password" id="generalPw2" name="generalPw2" required="required"></td>
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
	
	<tr>
		<th>봉사희망지역</th>
		<td>
			<select>
				<option value="none">시/군/구 선택</option>
				<option value="1">은평구</option>
				<option value="2">도봉구</option>
			</select>
		</td>		
	</tr>
	
	<tr>
		<th>희망분야</th>
		<td>
			<select>
				<option value="none">선택</option>			
				<option value="1">생활편의</option>			
				<option value="2">주거환경</option>			
				<option value="3">상담</option>
			</select>
		</td>
	</tr>
</table>
</body>
</html>