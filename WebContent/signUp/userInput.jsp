<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원가입</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">
<script src="${initParam.CONTEXT_PATH}/resources/js/jquery-3.6.0.min.js"></script>
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

.error_message {
	color: red;
	font-size: 13px;
	display: inline-block;
	width: 440px;
}

.small_span {
	font-size: 7px;
}

#mobile1 {
	height: 30px;
}

.large_select {
	width: 150px;
}
</style>
<script type="text/javascript">
/* 우편번호 검색기능 */
function postcode() {
   new daum.Postcode({
	    oncomplete: function(data) {
	    	document.getElementById("zipCode").value = data.zonecode;
	    	document.getElementById("address").value = data.roadAddress;
	    	document.getElementById("detailAddress").focus();
	    }
	}).open();
}

/* 이름 체크 */
function nameCheck(){
	var nameElement = $("#name");
	var name = nameElement.val();
	var nameMessageElement = $("#nameMessage");
	name = name.trim();
	
	if (name.length == 0) {
		nameMessageElement.html("이름을 입력해 주세요");
		nameElement.val("");
		nameElement.focus();
		return false;
	} else if (!nameReg(name)) {
		nameMessageElement.html("이름을 2자 이상 30자 이내 한글로 입력해 주세요");
		nameElement.val("");
		nameElement.focus();
		return false;
	}
	
	return true;
}

/* 생년월일 체크 */
function birthdayCheck(){
	console.log("생년월일 들어옴");
	var birthdayElement = $("#birthday");
	var birthday = birthdayElement.val();
	var birthdayMessageElement = $("#birthdayMessage");
	birthday = birthday.trim();
	
	if (birthday.length == 0) {
		birthdayMessageElement.html("생년월일을 입력해 주세요");
		birthdayElement.val("");
		birthdayElement.focus();
		return false;
	} else if (!birthdayReg(birthday)) {
		birthdayMessageElement.html("생년월일을 형식에 맞게 입력해 주세요 ex)19901010");
		birthdayElement.val("");
		birthdayElement.focus();
		return false;
	}
	
	return true;
}

/* 아이디 체크 */
function idCheck(){
	console.log("아이디 들어옴");
	var idElement = $("#id");
	var id = idElement.val();
	var idMessageElement = $("#idMessage");
	id = id.trim();
	
	if (id.length == 0) {
		idMessageElement.html("아이디를 입력해 주세요");
		idElement.val("");
		idElement.focus();
		return false;
	} else if (!idReg(id)) {
		idMessageElement.html("아이디는 6 ~ 30자리 이내 영문,숫자로 입력해 주세요");
		idElement.val("");
		idElement.focus();
		return false;
	}
	
	return true;
}

/* 비밀번호 체크 */
function pwCheck(){
	console.log("비밀번호 들어옴");
	var pwElement = $("#pw");
	var pw = pwElement.val();
	var pwMessageElement = $("#pwMessage");
	
	var pw2Element = $("#pw2");
	var pw2 = pw2Element.val();
	var pw2MessageElement = $("#pw2Message");
	
	pw = pw.trim();
	pw2 = pw2.trim();
	
	if (pw.length == 0) {
		pwMessageElement.html("비밀번호를 입력해 주세요");
		pwElement.val("");
		pwElement.focus();
		return false;
	} else if (!pwReg(pw)) {
		pwMessageElement.html("비밀번호는 8 ~ 20자리 이내 숫자,영문,특수문자를 포함하여 입력해 주세요");
		pwElement.val("");
		pwElement.focus();
		return false;
	} else if (pw2.length == 0) {
		pw2MessageElement.html("비밀번호를 입력해 주세요");
		pw2Element.val("");
		pw2Element.focus();
		return false;
	} else if (pw != pw2) {
		pw2MessageElement.html("비밀번호가 일치하지 않습니다");
		pw2Element.val("");
		pw2Element.focus();
		return false;
	}
	
	return true;
}

/* 주소 체크 */
function addressCheck(){
	console.log("주소 들어옴");
	var zipCodeElement = $("#zipCode");
	var zipCode = zipCodeElement.val();
	var addressMessageElement = $("#addressMessage");
	
	if (zipCode.length == 0) {
		addressMessageElement.html("주소를 입력해 주세요");
		zipCodeElement.focus();
		return false;
	} 
	
	return true;
}

/* 휴대폰 체크 */
function mobileCheck(){
	console.log("휴대폰 들어옴");
	var mobile2Element = $("#mobile2");
	var mobile3Element = $("#mobile3");
	var mobile2 = mobile2Element.val();
	var mobile3 = mobile3Element.val();
	var mobileMessageElement = $("#mobileMessage");
	
	mobile2 = mobile2.trim();
	mobile3 = mobile3.trim();
	
	if (mobile2.length == 0) {
		mobileMessageElement.html("휴대폰 번호를 입력해 주세요");
		mobile2Element.val("");
		mobile2Element.focus();
		return false;
	} else if (!mobileReg(mobile2)) {
		mobileMessageElement.html("휴대폰 번호는 4자리의 숫자를 입력해 주세요");
		mobile2Element.val("");
		mobile2Element.focus();
		return false;
	} else if (mobile3.length == 0) {
		mobileMessageElement.html("휴대폰 번호를 입력해 주세요");
		mobile3Element.val("");
		mobile3Element.focus();
		return false;
	} else if (!mobileReg(mobile3)) {
		mobileMessageElement.html("휴대폰 번호는 4자리의 숫자를 입력해 주세요");
		mobile3Element.val("");
		mobile3Element.focus();
		return false;
	}
	
	return true;
}

/* 이메일 체크 */
function emailCheck(){
	console.log("이메일 들어옴");
	var email1Element = $("#email1");
	var email2Element = $("#email2");
	var email1 = email1Element.val();
	var email2 = email2Element.val();
	var emailMessageElement = $("#emailMessage");
	
	email1 = email1.trim();
	email2 = email2.trim();
	
	if (email1.length == 0) {
		emailMessageElement.html("이메일 주소를 입력해 주세요");
		email1Element.val("");
		email1Element.focus();
		return false;
	} else if (email2.length == 0) {
		emailMessageElement.html("이메일 주소를 입력해 주세요");
		email2Element.val("");
		email2Element.focus();
		return false;
	} else if (!email1Reg(email1)) {
		emailMessageElement.html("이메일 주소 형식에 맞게 입력해 주세요");
		email1Element.val("");
		email1Element.focus();
		return false;
	} else if (!email2Reg(email2)) {
		emailMessageElement.html("이메일 주소 형식에 맞게 입력해 주세요");
		email2Element.val("");
		email2Element.focus();
		return false;
	}
	
	return true;
	
}

/* 비밀번호 표시 함수 */
function visiblePw() {
	console.log("비밀번호 체크 들어옴");
	var visiblePwElement = $("#PwVisible");
	var pwElement = $("#pw");
	var pw2Element = $("#pw2");
	
	if (visiblePwElement.is(":checked")) {
		pwElement.prop("type", "text");
		pw2Element.prop("type", "text");
	} else {
		pwElement.prop("type", "password");
		pw2Element.prop("type", "password");
	}
}


/* 이메일 선택 및 입력 여부 설정 */
function lockEmail() {
	var takeData = $("#emailSelect option:selected").val();
	var email2Element = $("#email2")
	
	if (takeData == "none") {
		email2Element.val("");
		email2Element.attr("readonly", false);
		email2Element.focus();
	} else if (takeData == "네이버") {
		email2Element.val("naver.com");
		email2Element.attr("readonly", true);
	} else if (takeData == "구글") {
		email2Element.val("gmail.com");
		email2Element.attr("readonly", true);
	}  else if (takeData == "다음") {
		email2Element.val("daum.net");
		email2Element.attr("readonly", true);
	} else if (takeData == "네이트") {
		email2Element.val("nate.com");
		email2Element.attr("readonly", true);
	}
}

/* 휴대폰 3번째칸으로 넘어가기*/
function next_mobile3() {
	if ($("#mobile2").val().length == 4) {
		$("#mobile3").focus();
	}
}

/* 이메일로 넘어가기 */
function next_email() {
	if ($("#mobile3").val().length == 4) {
		$("#email1").focus();
	}
}

/* 이름 정규식 */
function nameReg(data) {
	var regExp = /^[가-힣]{2,30}$/;
	return regExp.test(data);
}

/* 생년월일 정규식 */
function birthdayReg(data) {
	var regExp = /^(19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
	return regExp.test(data);
}

/* 아이디 정규식 */	
function idReg(data){
	var regExp = /^[a-zA-Z0-9]{6,30}$/;
	return regExp.test(data);
}

/* 비밀번호 정규식 */
function pwReg(data){
	var regExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*()+=]).{8,20}$/;
	return regExp.test(data);
}

/* 휴대폰번호  정규식  */
function mobileReg(data){
	var regExp = /^\d{4}$/;
	return regExp.test(data);
}

/* 이메일 정규식*/
function email1Reg(data){
	var regExp = /^([0-9a-zA-Z_\.-]+)$/
	return regExp.test(data);
}

/* 이메일 정규식2*/
function email2Reg(data){
	var regExp = /^([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
	return regExp.test(data);
}

/* 이름 에러메세지 클리어 */
function clearMessageName(){
	$("#nameMessage").html("");
}

/* 생년월일 에러메세지 클리어 */
function clearMessageBirthday(){
	$("#birthdayMessage").html("");
}

/* 아이디 에러메세지 클리어, 중복확인 버튼 활성화 */
function clearMessageId(){
	$("#idMessage").html("");
	$("#idBtn").attr("disabled", false);
}

/* 비밀번호 에러메세지 클리어 */
function clearMessagePw(){
	$("#pwMessage").html("");
}

/* 비밀번호2 에러메세지 클리어 */
function clearMessagePw2(){
	$("#pw2Message").html("");
}

/* 주소 에러메세지 클리어 */
function clearMessageAddress(){
	$("#addressMessage").html("");
}

/* 휴대폰 에러메세지 클리어 */
function clearMessageMobile(){
	$("#mobileMessage").html("");
}

/* 이메일 에러메세지 클리어 */
function clearMessageEmail(){
	$("#emailMessage").html("");
}

/* 일반회원 회원가입 체크 */
function generalInputCheck() {
	if (nameCheck() && birthdayCheck() && idCheck() &&
		pwCheck() && addressCheck() && mobileCheck() && emailCheck()) {
		if (!$("#idBtn").prop("disabled")){
			alert("아이디 중복확인을 해주세요");
			return false;
		}
		return true;
	}
	
	return false;
}

</script>
<script type="text/javascript">
$(document).ready(function(){
	console.log("제이쿼리");
	
	/* 회원가입 입력항목 체크*/
	$("input[type='submit']").click(function(e){
		e.preventDefault();
		if (generalInputCheck()) {
			$("#generalInputForm").submit();
		}
	});
	
	/* 아이디 중복 확인*/
	$("#idBtn").click(function(){
		if(idCheck()) {
			var id = $("#id").val();
			$.ajax({
				  type:'get',
				  url:'${CONTEXT_PATH}/general/generalController?action=idCheck',
				  data:{id:id},
				  dataType: 'text',
				  success: function(data, textStatus){
					  if (data == "usable") {
						  alert("사용 가능한 아이디 입니다");
						  $("#idBtn").attr("disabled", true);
					  } else if (data == "not-usable") {
						  alert("이미 존재하는 아이디 입니다");
					  } else if (data == "none") {
						  alert("아이디를 입력해 주세요");
					  }
				      
				  },
				  error : function(xhr,status,error) {
				     console.log("error");
				  }
				});
		}
	});
	
});

</script>
</head>
<body>
<!-- header -->
<%@ include file="/common/header.jsp"%>

<!-- 회원가입 폼 --------------------------------------------------------------------------------->
<div id="userInput_div">
<h1>회원가입</h1>
<hr class="head_hr">
	<form action="${CONTEXT_PATH}/general/generalController?action=generalInput" method="post" id="generalInputForm">
		<table border="1">
			<tr>
				<th>이름</th>
				<td>
					<input type="text" id="name" name="name" placeholder="이름" size="30" onkeydown="clearMessageName()">
					<span id="nameMessage" class="error_message"></span>
				</td>
			</tr>
			
			<tr>
				<th>성별</th>
				<td>
					<input type="radio" id="man" name="gender" value="M" checked="checked"> 남성
					<input type="radio" id="woman" name="gender" value="Y"> 여성
				</td>
			</tr>
		
			<tr>
				<th>생년월일</th>
				<td>
					<input type="text" id="birthday" name="birthday" placeholder="ex) 19901010"  size="30" onkeydown="clearMessageBirthday()">
					<span id="birthdayMessage" class="error_message"></span>
				</td>
			</tr>
			
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" id="id" name="id" placeholder="6 ~ 30자리 이내" size="30" onkeydown="clearMessageId()">
					<input type="button" value="중복확인" id="idBtn">
					<span id="idMessage" class="error_message"></span>
					</td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" id="pw" name="pw" placeholder="8 ~ 20자리 이내" size="30" onkeydown="clearMessagePw()">
					<span class="small_span">* 숫자,영문,특수문자(~!@#$%^&*()+=) 포함</span>
					<span id="pwMessage" class="error_message"></span>
				</td>
			</tr>
			
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" id="pw2" placeholder="8 ~ 20자리 이내" name="pw2" size="30" onkeydown="clearMessagePw2()">
					<span id="pw2Message" class="error_message"></span><br>
					<input type="checkbox" name="PwVisible" id="PwVisible" onclick="visiblePw()">
					<span class="small_span">비밀번호 표시</span>
				</td>
			</tr>
			
			<tr>
				<th>주소</th>
				<td>
					<input type="text" id="zipCode" name="zipCode" placeholder="우편번호" size="5" readonly="readonly">
					<input type="button" value="우편번호 찾기" onclick="postcode(); clearMessageAddress()">
					<span id="addressMessage" class="error_message"></span><br>
					
					<input type="text" id="address" name="address" placeholder="기본주소" size="40" readonly="readonly"><br>
					<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" size="40">
				</td>
			</tr>
			
			<tr>
				<th>휴대폰 번호</th>
				<td>
					<select id="mobile1" name="mobile1">
						<option>010</option>
						<option>011</option>
						<option>016</option>
						<option>017</option>
						<option>018</option>
						<option>019</option>
					</select>
					- <input type="text" id="mobile2" name="mobile2" size="3" onkeydown="clearMessageMobile()" onkeyup="next_mobile3()">
					- <input type="text" id="mobile3" name="mobile3" size="3" onkeydown="clearMessageMobile()" onkeyup="next_email()">
					<span id="mobileMessage" class="error_message"></span>
				</td>
			</tr>
			
			<tr>
				<th>이메일</th>
				<td>
					  <input type="text" id="email1" name="email1" size="15" onkeydown="clearMessageEmail()">
					@ <input type="text" id="email2" name="email2" size="15" onkeydown="clearMessageEmail()">
						<select id="emailSelect" onchange="lockEmail()">
							<option value="none">직접입력</option>
							<option value="네이버">naver.com</option>
							<option value="구글">gmail.com</option>
							<option value="다음">daum.net</option>
							<option value="네이트">nate.com</option>
						</select>
						<span id="emailMessage" class="error_message"></span>
				</td>
			</tr>
			
			<tr>
				<th>봉사희망지역</th>
				<td>
					<select class="large_select" name="localNo">
						<option value="none">선택</option>
						<c:forEach var="dto" items="${local}">
							<option value="${dto.localNo}">${dto.localName }</option>
						</c:forEach>
					</select>
				</td>		
			</tr>
			
			<tr>
				<th>희망분야</th>
				<td>
					<select class="large_select" name="categoryNo">
						<option value="none">선택</option>	
						<c:forEach var="dto" items="${volCategory }">
							<option value="${dto.categoryNo }">${dto.categoryName }</option>			
						</c:forEach>		
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