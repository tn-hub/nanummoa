<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<div class="header">

	<div class="header_info">
		<a href ="${initParam.CONTEXT_PATH}/common/guide.jsp" class="">자원봉사 지원 플랫폼 <span class="info_color">나눔모아</span> 이용 안내 </a>
		<div class="login_menu">
			<ul>
			<c:choose>
				<c:when test="${empty dto or empty grade}">
					<li><a href="${CONTEXT_PATH}/common/commonController?action=loginForm">로그인</a></li>
					<li><a href="${CONTEXT_PATH}/general/generalController?action=generalInputForm">회원가입</a></li>
				</c:when>
				<c:when test="${grade == 'G' }">
					<li><a href="${CONTEXT_PATH}/common/commonController?action=logout">로그아웃</a></li>
					<li><a href="${CONTEXT_PATH}/general/generalController?action=">마이페이지</a></li>
					<li><a href="${CONTEXT_PATH}/general/generalpage.jsp">회원개발용</a></li>
				</c:when>
				<c:when test="${grade == 'C' }">
					<li><a href="${CONTEXT_PATH}/common/commonController?action=logout">로그아웃</a></li>
					<li><a href="${CONTEXT_PATH}/center/centerController?action=centerpage">센터페이지</a></li>
					<li><a href="${CONTEXT_PATH}/center/centerpage.jsp">센터개발용</a></li>
				</c:when>
				<c:when test="${grade == 'A' }">
					<li><a href="${CONTEXT_PATH}/common/commonController?action=logout">로그아웃</a></li>
					<li><a href="${CONTEXT_PATH}/admin/adminController?action=">관리자페이지</a></li>
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
				<li><a href="${CONTEXT_PATH}/general/generalController?action=">봉사신청</a></li>
				<li><a href="${CONTEXT_PATH}/general/generalController?action=">인증서발급</a></li>
				<li><a href="${CONTEXT_PATH}/common/commonController?action=qnaList">문의하기</a></li>
				<li><a href="${CONTEXT_PATH}/general/generalController?action=">나의 봉사내역</a></li>
				<li><a href="#"><i class="fas fa-search search_btn"></i></a></li>
			</ul>
		</div>
	</div>
</div>
<div class="header_end"></div>
