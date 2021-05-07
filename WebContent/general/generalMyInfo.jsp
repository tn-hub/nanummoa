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
	$("#pw").hide();
	$("#confirmPw_tr").hide();
	$("#pwSpan").hide();
	
	$("#modiPw").click(function(){
		$("#pw").show();
		$("#confirmPw_tr").show();
		$("#pwSpan").show();
		$("#pw").focus();
		$("#modiPw").hide();
		$("#modiPw").attr("disabled", true);
	});
	
	/* 회원수정 입력항목 체크*/
	$("input[type='submit']").click(function(e){
		e.preventDefault();
		if (generalUpdateCheck()) {
			$("#generalUpdateForm").submit();
		}
	});
	
	/* 회원 탈퇴*/
	$("#delBtn").click(function(){
		if(confirm("회원탈퇴 하시겠습니까?")) {
			$(location).attr("href", "${CONTEXT_PATH}/general/generalController?action=generalDelete");
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
<h1>내 정보</h1>
<hr class="head_hr">
	<form action="${CONTEXT_PATH}/general/generalController?action=generalUpdate" method="post" id="generalUpdateForm">
		<table border="1">
			<tr>
				<th>이름</th>
				<td>
					<input type="text" id="name" name="name" placeholder="이름" size="30" 
					value="${GeneralDto.generalName}" onkeydown="clearMessageName()">
					<span id="nameMessage" class="error_message"></span>
				</td>
			</tr>
			
			<tr>
				<th>성별</th>
				<td>
					<input type="radio" id="man" name="gender" value="M" 
						<c:if test="${GeneralDto.gender == 'M'}">
							checked="checked"
						</c:if>
					> 남성
					<input type="radio" id="woman" name="gender" value="Y"
						<c:if test="${GeneralDto.gender == 'Y'}">
							checked="checked"
						</c:if>
					> 여성
				</td>
			</tr>
		
			<tr>
				<th>생년월일</th>
				<td>
					<input type="text" id="birthday" name="birthday" placeholder="ex) 19901010"  
					size="30" onkeydown="clearMessageBirthday()" value="${GeneralDto.birthday}">
					<span id="birthdayMessage" class="error_message"></span>
				</td>
			</tr>
			
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" id="id" name="id" placeholder="6 ~ 30자리 이내" size="30" 
					onkeydown="clearMessageId()" readonly="readonly" value="${GeneralDto.generalId}">
				</td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td>
					<input id="modiPw" type="button" value="변경하기">
					<input type="password" id="pw" name="pw" placeholder="8 ~ 20자리 이내" size="30" onkeydown="clearMessagePw()">
					<span id="pwSpan" class="small_span">* 숫자,영문,특수문자(~!@#$%^&*()+=) 포함</span>
					<span id="pwMessage" class="error_message"></span>
				</td>
			</tr>
			
			<tr id="confirmPw_tr">
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
					<input type="text" id="zipCode" name="zipCode" placeholder="우편번호" size="5" 
					value="${GeneralDto.generalZipCode}" readonly="readonly">
					<input type="button" value="우편번호 찾기" onclick="postcode(); clearMessageAddress()">
					<span id="addressMessage" class="error_message"></span><br>
					
					<input type="text" id="address" name="address" placeholder="기본주소" size="40" 
					value="${GeneralDto.generalAddress}" readonly="readonly"><br>
					<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" size="40">
				</td>
			</tr>
			
			<tr>
				<th>휴대폰 번호</th>
				<td>
					<c:set var="num" value="${fn:split(GeneralDto.generalMobile,'-')}" />	
					<select id="mobile1" name="mobile1">
						<option <c:if test="${num[0] == '010'}">selected="selected"</c:if> >010</option>
						<option <c:if test="${num[0] == '011'}">selected="selected"</c:if> >011</option>
						<option <c:if test="${num[0] == '016'}">selected="selected"</c:if> >016</option>
						<option <c:if test="${num[0] == '017'}">selected="selected"</c:if> >017</option>
						<option <c:if test="${num[0] == '018'}">selected="selected"</c:if> >018</option>
						<option <c:if test="${num[0] == '019'}">selected="selected"</c:if> >019</option>
					</select>
					- <input type="text" id="mobile2" name="mobile2" size="3" 
					onkeydown="clearMessageMobile()" onkeyup="next_mobile3()" value="${num[1]}">
					- <input type="text" id="mobile3" name="mobile3" size="3" 
					onkeydown="clearMessageMobile()" onkeyup="next_email()" value="${num[2]}">
					<span id="mobileMessage" class="error_message"></span>
				</td>
			</tr>
			
			<tr>
				<th>이메일</th>
				<td>
						<c:set var="email" value="${fn:split(GeneralDto.generalEmail, '@')}" />
					  <input type="text" id="email1" name="email1" size="15" onkeydown="clearMessageEmail()" value="${email[0]}">
					@ <input type="text" id="email2" name="email2" size="15" onkeydown="clearMessageEmail()" value="${email[1]}">
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
							<option value="${dto.localNo}" 
							<c:if test="${GeneralDto.localNo == dto.localNo}">selected="selected"</c:if> >
							${dto.localName}</option>
						</c:forEach>
					</select>
				</td>		
			</tr>
			
			<tr>
				<th>희망분야</th>
				<td>
					<select class="large_select" name="categoryNo">
						<option value="none">선택</option>	
						<c:forEach var="dto" items="${volCategory}">
							<option value="${dto.categoryNo }"
							<c:if test="${GeneralDto.categoryNo == dto.categoryNo}">selected="selected"</c:if> >
							${dto.categoryName }</option>			
						</c:forEach>		
					</select>
				</td>
			</tr>
		</table>
		<div class="input_button_div">
			<input class="y_btn" type="submit" value="저장">
			<input class="g_btn" type="reset" value="초기화">
			<input class="r_btn" type="button" value="회원탈퇴" id="delBtn">
		</div>
	</form>
</div>
<!-- 회원가입 폼 -->

<!-- footer -->
<%@ include file="/common/footer.jsp"%>
</body>
</html>