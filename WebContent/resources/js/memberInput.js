/**
 * 회원가입, 수정 관련 자바스크립트
 */

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

/* 대표 휴대폰 체크 */
function ceoMobileCheck(){
	console.log("대표 휴대폰 들어옴");
	var ceoMobile2Element = $("#ceoMobile2");
	var ceoMobile3Element = $("#ceoMobile3");
	var ceoMobile2 = ceoMobile2Element.val();
	var ceoMobile3 = ceoMobile3Element.val();
	var ceoMobileMessageElement = $("#ceoMobileMessage");
	
	ceoMobile2 = ceoMobile2.trim();
	ceoMobile3 = ceoMobile3.trim();
	
	if (ceoMobile2.length == 0) {
		ceoMobileMessageElement.html("휴대폰 번호를 입력해 주세요");
		ceoMobile2Element.val("");
		ceoMobile2Element.focus();
		return false;
	} else if (!mobileReg(ceoMobile2)) {
		ceoMobileMessageElement.html("휴대폰 번호는 4자리의 숫자를 입력해 주세요");
		ceoMobile2Element.val("");
		ceoMobile2Element.focus();
		return false;
	} else if (ceoMobile3.length == 0) {
		ceoMobileMessageElement.html("휴대폰 번호를 입력해 주세요");
		ceoMobile3Element.val("");
		ceoMobile3Element.focus();
		return false;
	} else if (!mobileReg(ceoMobile3)) {
		ceoMobileMessageElement.html("휴대폰 번호는 4자리의 숫자를 입력해 주세요");
		ceoMobile3Element.val("");
		ceoMobile3Element.focus();
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

/* 센터 등록번호 체크 */
function registerCodeCheck(){
	console.log("등록번호 들어옴");
	var registerCodeElement = $("#registerCode");
	var registerCode = registerCodeElement.val();
	var registerCodeMessageElement = $("#registerCodeMessage");
	
	registerCode = registerCode.trim();
	
	if (registerCode.length == 0) {
		registerCodeMessageElement.html("등록번호를 입력해 주세요");
		registerCodeElement.val("");
		registerCodeElement.focus();
		return false;
	} else if (!registerCodeReg(registerCode)) {
		registerCodeMessageElement.html("등록번호를 형식에 맞게 입력해 주세요");
		registerCodeElement.val("");
		registerCodeElement.focus();
		return false;
	}	
	return true;
}

/* 센터 이름 체크 */
function centerNameCheck(){
	console.log("센터이름 들어옴");
	var centerNameElement = $("#centerName");
	var centerName = centerNameElement.val();
	var centerNameMessageElement = $("#centerNameMessage");
	
	centerName = centerName.trim();
	
	if (centerName.length == 0) {
		centerNameMessageElement.html("기관이름을 입력해 주세요");
		centerNameElement.val("");
		centerNameElement.focus();
		return false;
	} 
	
	return true;
}

/* 센터 등록일 체크 */
function centerEntryDateCheck(){
	console.log("센터등록일 들어옴");
	var centerEntryDateElement = $("#centerEntryDate");
	var centerEntryDate = centerEntryDateElement.val();
	var centerEntryDateMessageElement = $("#centerEntryDateMessage");
	
	centerEntryDate = centerEntryDate.trim();
	
	if (centerEntryDate.length == 0) {
		centerEntryDateMessageElement.html("등록(설립)일자를 입력해 주세요");
		centerEntryDateElement.val("");
		centerEntryDateElement.focus();
		return false;
	} else if (!centerEntryDateReg(centerEntryDate)){
		centerEntryDateMessageElement.html("형식에 맞게 입력해 주세요");
		centerEntryDateElement.val("");
		centerEntryDateElement.focus();
		return false;
	};
	
	return true;
}

/* 대표 이름 체크 */
function ceoNameCheck(){
	console.log("대표이름 들어옴");
	var ceoNameElement = $("#ceoName");
	var ceoName = ceoNameElement.val();
	var ceoNameMessageElement = $("#ceoNameMessage");
	
	ceoName = ceoName.trim();
	
	if (ceoName.length == 0) {
		ceoNameMessageElement.html("이름을 입력해 주세요");
		ceoNameElement.val("");
		ceoNameElement.focus();
		return false;
	} else if (!nameReg(ceoName)){
		ceoNameMessageElement.html("이름을 2자 이상 30자 이내 한글로 입력해 주세요");
		ceoNameElement.val("");
		ceoNameElement.focus();
		return false;
	};
	
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

/* 휴대폰 3번째칸으로 넘어가기*/
function next_ceoMobile3() {
	if ($("#ceoMobile2").val().length == 4) {
		$("#ceoMobile3").focus();
	}
}

/* 서비스 대상으로 넘어가기 */
function next_service() {
	if ($("#ceoMobile3").val().length == 4) {
		$("#service").focus();
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

/* 설립일 정규식 */
function centerEntryDateReg(data) {
	var regExp = /^(18|19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;;
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

/* 등록번호 정규식*/
function registerCodeReg(data){
	var regExp = /^\d{3}-\d{2}-\d{5}$/; 
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
	console.log("아이디 클리어");
	$("#idMessage").html("");
	$("#idBtn").prop("disabled", false);
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

/* 휴대폰 에러메세지 클리어 */
function clearMessageCeoMobile(){
	$("#ceoMobileMessage").html("");
}

/* 이메일 에러메세지 클리어 */
function clearMessageEmail(){
	$("#emailMessage").html("");
}

/* 등록번호 에러메세지 클리어 */
function clearMessageRegisterCode(){
	$("#registerCodeMessage").html("");
}

/* 센터이름 에러메세지 클리어 */
function clearMessageCenterName(){
	$("#centerNameMessage").html("");
	$("#centerNameBtn").prop("disabled", false);
}

/* 센터등록일 에러메세지 클리어 */
function clearMessageCenterEntryDate(){
	$("#centerEntryDateMessage").html("");
}

/* 대표이름 에러메세지 클리어 */
function clearMessageCeoName(){
	$("#ceoNameMessage").html("");
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

/* 일반회원 회원정보 수정 체크 */
function generalUpdateCheck() {
	if (nameCheck() && birthdayCheck() && addressCheck() && 
		mobileCheck() && emailCheck()) {
		if ($("#modiPw").prop("disabled")) {
			if (pwCheck()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	return false;
}

/* 센터회원 회원가입 체크 */
function centerInputCheck() {
	if (nameCheck() && idCheck() && pwCheck() && mobileCheck() && emailCheck() && registerCodeCheck() && 
		centerNameCheck() && centerEntryDateCheck() && addressCheck() && ceoNameCheck() && ceoMobileCheck()) {
		if (!$("#idBtn").prop("disabled")){
			alert("아이디 중복확인을 해주세요");
			return false;
		}
		if (!$("#centerNameBtn").prop("disabled")){
			alert("기관이름 인증확인을 해주세요");
			return false;
		}
		return true;
	}
	
	return false;
}

/* 센터회원 회원정보 수정 체크 */
function centerUpdateCheck() {
	if (nameCheck() && mobileCheck() && emailCheck() && registerCodeCheck() && 
		centerNameCheck() && centerEntryDateCheck() && addressCheck() && ceoNameCheck() && ceoMobileCheck()) {
		if ($("#modiPw").prop("disabled")) {
			if (pwCheck()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	return false;
}
