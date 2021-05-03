<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원가입</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">
<!-- 통합 로딩 방식 : postcode.v2.js 라는 이름의 파일 로딩을 통해 우편번호 서비스를 이용 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style type="text/css">
tr {
	margin : 5px;
	
}
input,th,td {
	margin : 5px;
	padding: 3px;
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
}

.head_hr {
		margin-bottom: 50px;
}

.input_button_div {
	margin-top: 30px;
	text-align: center;
}
</style>
<script>
// 우편번호찾기 버튼 클릭시 우편번호 검색창 오픈
function postcode() {
   new daum.Postcode({
	    oncomplete: function(data) {
	    	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	    	// data.zonecode에 우편번호값, data.roadAddress에 도로명주소값, data.jibunAddress에 지번주소값 담겨있습니다.
	    	 document.getElementById('zipCode').value = data.zonecode;
	    	 document.getElementById("address").value = data.roadAddress;
	         document.getElementById("detailAddress").focus();
	    }
	}).open();
}
</script>
</head>
<body>
<!-- header -->
<%@ include file="/common/header.jsp"%>

<!-- 회원가입 폼 --------------------------------------------------------------------------------->
<div id="userInput_div">
<h3>회원가입</h3>
<hr class="head_hr">
	<form action="" method="post">
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
				<td><input type="text" id="brith" name="brith" placeholder="ex) 19901010" required="required" size="20"></td>
			</tr>
			
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" id="generalId" name="generalId" placeholder="6 ~ 30자리 이내" required="required" size="20">
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
					<input type="button" value="우편번호 찾기" onclick="postcode()"><br>
					
					<input type="text" id="address" name="address" placeholder="기본주소" size="40" readonly="readonly"></br>
					<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" size="40">
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
		<div class="input_button_div">
			<input type="submit" value="회원가입">
			<input type="reset" value="초기화">
		</div>
	</form>
</div>
<!-- 회원가입 폼 -->

<!-- footer -->
<%@ include file="/common/footer.jsp"%>
</body>
</html>