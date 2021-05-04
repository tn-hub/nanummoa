<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<div class="header">

	<div class="header_info">
		<a href ="${initParam.CONTEXT_PATH}/common/guide.jsp" class="">자원봉사 지원 플랫폼 <span class="info_color">나눔모아</span> 이용 안내 </a>
		<div class="login_menu">
			<ul>
				<li id= "" class=""><a href="${CONTEXT_PATH}/common/commonController?action=loginForm" class="">로그인</a></li>
				<li id= "" class="" ><a href="${CONTEXT_PATH}/general/generalController?action=generalInputForm" class="">회원가입</a></li>
			</ul>
		</div>
	</div>
	
		
	<div class="header_wrapper">
		<div class="header_logo">
			<a href="${initParam.CONTEXT_PATH}/home"><img src="${initParam.CONTEXT_PATH}/resources/img/logo_title.png"/></a>
		</div>
		
		<div class="header_menu">
			<ul>
				<li id= "" class=""><a href="#" class="">봉사신청</a></li>
				<li id= "" class="" ><a href="#" class="">인증서발급</a></li>
				<li id= "" class=""><a href="${CONTEXT_PATH}/common/commonController?action=qnaList" class="">문의하기</a></li>
				<c:choose>
					<c:when test="${empty grade}">
						<li id= "" class=""><a href="${CONTEXT_PATH}/common/commonController?action=loginForm" class="">나의 봉사내역</a></li>
					</c:when>
					<c:when test="${grade == 'G'}">
						<li id= "" class=""><a href="#" class="">나의 봉사내역</a></li>
					</c:when>
					<c:when test="${grade == 'C'}">
						<li id= "" class=""><a href="${CONTEXT_PATH}/center/centerController?action=centerVolListForm" class="">나의 봉사내역</a></li>
					</c:when>
				</c:choose>
				<li id= "" class=""><a href="#" class=""><i class="fas fa-search search_btn"></i></a></li>
			</ul>
		</div>
	</div>
</div>
<div class="header_end"></div>
