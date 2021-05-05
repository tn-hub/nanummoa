<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<!-- jquery-3.6.0.min.js -->
<script type="text/javascript" src="${CONTEXT_PATH }/resources/js/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#code").attr("readonly",true);
	$("#checkCode").attr("disabled",true);
	
	/* 이메일 인증*/
	$("#checkEmail").click(function(){
			var email = $("#email").val();
			var grade = $(".grade").val();
			var name = $("#name").val();
			
			$.ajax({
				  type:'get',
				  url:'${CONTEXT_PATH}/common/commonController?action=addSecureCode',
				  data:{email:email,grade:grade,name:name},
				  dataType: 'text',
				  success: function(data, textStatus){
					  if (data == "send_email") {
						  alert("메일을 보냈습니다");
						  $("#name").attr("readonly", true);
						  $("#email").attr("readonly", true);
						  $("#checkEmail").attr("disabled", true);
						  
						  $("#code").attr("readonly",false);
						  $("#checkCode").attr("disabled",false);
					  } else if (data == "not-email") {
						  alert("이메일이 맞지 않습니다");
					  } else if (data == "none_email") {
						  alert("이메일을 입력해 주세요.");
					  }else if (data == "none_name") {
						  alert("이름을 입력해 주세요.");
					  }
				  },
				  error : function(xhr,status,error) {
				     console.log("error");
				  }
				});
	});
});
</script>

<link type="text/css" rel="stylesheet"
	href="${initParam.CONTEXT_PATH}/resources/css/common.css">
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
	width: 500px;
	height: 200px;
	margin-left:auto; 
	margin-right:auto;
}

.title{
	width: 1000px;
	margin:  auto;
}

#wrap .box{ 
    width:300px;
    height:300px;
    margin:0 auto;
}

.email{
text-align: center;
}

.submit{
	text-align: center;
	margin: 10px;
}

</style>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
<div class="contents">
		<div class="title">
			<h1>이메일 인증</h1>
			<hr>
	</div>
			<form action="${CONTEXT_PATH}/common/commonController?action=checkEmail" method="post">
			<input type="hidden" id="grade" class="grade" name="grade" value="${grade }">
				<table border="1">
					<tr>
						<th colspan="2">
						<c:choose>
							<c:when test="${grade eq 'G'}">
								이메일 : ${dto.generalEmail }
								<td><input type="hidden" id="email" name="email" value="${dto.generalEmail }"></td>
							</c:when>
							<c:when test="${grade eq 'C'}">
								이메일 : ${dto.centerEmail }
								<td><input type="hidden" id="email" name="email" value="${dto.centerEmail }"></td>
							</c:when>
						</c:choose>
						</th>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" id="name" name="name" size="30" required="required"></td>
					</tr>
					<tr>
						<th>인증번호</th> 
						<td>
							<input type="text" id="code" name="code" size="30" required="required">
							<input type="button" id="checkEmail" value="인증번호 받기">
							<%-- <c:choose>
								<c:when test="${grade eq 'G'}">
									<input type="button" value="인증번호 받기" onclick="location.href='${CONTEXT_PATH }/common/commonController?action=addSecureCode&email=${dto.generalEmail }&grade=${grade }'">		
								</c:when>
								<c:when test="${grade eq 'C'}">
									<input type="button" value="인증번호 받기" onclick="location.href='${CONTEXT_PATH }/common/commonController?action=addSecureCode&email=${dto.centerEmail }&grade=${grade }'">
								</c:when>
							</c:choose> --%>
							
						</td>
					</tr>
				</table>
				<div class="submit">
					<input type="submit" id="checkPw" name="checkPw" value="다음" >
				</div>
			</form>
			
</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>