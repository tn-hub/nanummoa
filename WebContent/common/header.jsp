<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<style type="text/css">
.searchAll_area{
	margin-top: 20px;
	border-bottom: 2px solid black;	
	text-align: center;
	height: 40px;
	padding-top: 20px;
	padding-bottom: 20px;
}

.searchAll_option{
	border-style: none;
	font-size: 20px;
	margin-right: 10px;
}

.searchAll_searchtext{
	width: 400px;
	font-size: 25px;
	margin-right: 30px;
}


.btn_searchAll{
	width: 100px;
	height: 40px;
	padding: 5px;
	font-size: 20px;
}
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<script type="text/javascript">

$(document).ready(function() {
	$('#searchSlide').click(function () {
		var bool= $('#searchAll_area').is(":visible");
		
		if(!$('#searchAllForm').is(":visible")){
			if (bool){
				$('#searchAll_area').hide(); 
			}else{
				$('#searchAll_area').show(); 
			}
		}
	});
	
	$('#btn_searchAllList').click(function () {
		if ($("#main_searchAll_text").val() == null || $("#main_searchAll_text").val() == "") {
			alert("검색내용을 입력하세요");
			$("#searchAll_text").focus();
			return false;
		}
		
		document.main_searchAllForm.submit();
	});
});

</script>
<div class="header">

	<div class="header_info">
		<a href ="${initParam.CONTEXT_PATH}/common/guide.jsp" class="">자원봉사 지원 플랫폼 <span class="info_color">나눔모아</span> 이용 안내 </a>
		<div class="login_menu">
			<ul>
			<c:choose>
				<c:when test="${empty dto or empty grade}">
					<li><a href="${CONTEXT_PATH}/common/commonController?action=loginForm">로그인</a></li>
					<li><a href="${CONTEXT_PATH}/common/commonController?action=inputForm">회원가입</a></li>
				</c:when>
				<c:when test="${grade == 'G' }">
					<li><a href="${CONTEXT_PATH}/common/commonController?action=logout">로그아웃</a></li>
					<li><a href="${CONTEXT_PATH}/general/generalController?action=generalMyInfoForm">마이페이지</a></li>
				</c:when>
				<c:when test="${grade == 'C' }">
					<li><a href="${CONTEXT_PATH}/common/commonController?action=logout">로그아웃</a></li>
					<li><a href="${CONTEXT_PATH}/center/centerController?action=centerMyInfoForm">센터페이지</a></li>
				</c:when>

				<c:when test="${grade == 'A' }">
					<li><a href="${CONTEXT_PATH}/common/commonController?action=logout">로그아웃</a></li>
					<li><a href="${CONTEXT_PATH}/admin/adminController?action=adminMyInfoForm">관리자페이지</a></li>
				</c:when>
			</c:choose>
			</ul>
		</div>
	</div>
	
		
	<div class="header_wrapper">
		<div class="header_logo">
			<a href="${initParam.CONTEXT_PATH}/home"><img src="${initParam.CONTEXT_PATH}/resources/img/logo_title.png"/></a>
		</div>
		
		<div class="header_menu">
			<ul>
				<c:choose>
					<c:when test="${empty grade or empty dto}">
						<li><a href="${CONTEXT_PATH}/common/commonController?action=volListForm">봉사조회</a></li>
						<li><a href="${CONTEXT_PATH}/common/commonController?action=loginForm">인증서발급</a></li>
						<li><a href="${CONTEXT_PATH}/common/commonController?action=qnaList">문의하기</a></li>
						<li><a href="${CONTEXT_PATH}/common/commonController?action=loginForm" class="">나의 봉사내역</a></li>
					</c:when>
					<c:when test="${grade == 'G'}">
						<li><a href="${CONTEXT_PATH}/common/commonController?action=volListForm">봉사조회</a></li>
						<li><a href="${CONTEXT_PATH}/general/generalController?action=confirmationListForm">인증서발급</a></li>
						<li><a href="${CONTEXT_PATH}/common/commonController?action=qnaList">문의하기</a></li>
						<li><a href="${CONTEXT_PATH}/general/generalController?action=volApplyList" class="">나의 봉사내역</a></li>
					</c:when>
					<c:when test="${grade == 'C'}">
						<li><a href="${CONTEXT_PATH}/common/commonController?action=volListForm">봉사조회</a></li>
						<li><a href="${CONTEXT_PATH}/center/centerController?action=issueListForm">인증서발급</a></li>
						<li><a href="${CONTEXT_PATH}/common/commonController?action=qnaList">문의하기</a></li>
						<li><a href="${CONTEXT_PATH}/center/centerController?action=centerVolListForm" class="">나의 봉사내역</a></li>
					</c:when>
					<c:when test="${grade == 'A'}">
						<li><a href="${CONTEXT_PATH}/common/commonController?action=volListForm">봉사 관리</a></li>
						<li><a href="${CONTEXT_PATH}/admin/adminController?action=confirmationListForm">인증서 관리</a></li>
						<li><a href="${CONTEXT_PATH}/common/commonController?action=qnaList">문의 내역</a></li>
						<li><a href="${CONTEXT_PATH}/admin/adminController?action=searchAllMemberForm" class="">회원 관리</a></li>
					</c:when>
				</c:choose>
				<li id="searchSlide"><a href="#"><i class="fas fa-search search_btn"></i></a></li>
			</ul>
			
			
			
			</div>
			
		</div>
	</div>
<div class="header_end"></div>
<!-- 메인에서 검색시 통합검색으로 이동 -->
<form name="main_searchAllForm" action="${CONTEXT_PATH}/common/commonController?action=searchAllForm" method="post">
<div class="searchAll_area" id="searchAll_area" style="display: none;">
<select class="searchAll_option" id="main_searchAll_opt" name="main_searchAll_opt">
	<option value="A">전체</option>
	<option value="V" <c:if test="${searchAllOpt eq 'V'}">selected</c:if>>봉사 게시판</option>
	<option value="Q" <c:if test="${searchAllOpt eq 'Q'}">selected</c:if>>문의 게시판</option>
</select> 
<input type="text" class="searchAll_searchtext" id="main_searchAll_text" name="main_searchAll_text" value="${searchAllText}">
<input type="button" value="검색" class="btn_searchAll" id="btn_searchAllList" name="btn_searchAllList" style="cursor:hand;">
</div>
</form>
