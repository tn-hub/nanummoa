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
<script src="${initParam.CONTEXT_PATH}/resources/js/memberInput.js"></script>
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
			<input class="y_btn" type="submit" value="회원가입">
			<input class="g_btn" type="reset" value="초기화">
		</div>
	</form>
</div>
<!-- 회원가입 폼 -->

<!-- footer -->
<%@ include file="/common/footer.jsp"%>
</body>
</html>