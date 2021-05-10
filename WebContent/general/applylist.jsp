<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>봉사 신청내역</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/applylist.css">
</head>
<body>
<%@ include file="/common/header.jsp"%>
<div class="contents">
	<div id="vol_list_div">
		<h1>봉사 신청내역</h1>
		<hr class="head_hr" style="margin-bottom:20px;">
		<p style="margin-bottom:-5px;">[전체 
			<em>${totalCnt}</em>
			건]
		</p>
		<p style="float: right;">
		※ 신청상태가 '접수'인 경우에 취소 가능합니다. '승인'인 경우 봉사센터로 문의해주세요.
		</p>
		<hr class="list_head_hr" style="clear: right;">
		
		<ul class="vol_list_ul">
		<c:forEach var="dto" items="${list}">
			<li>
				<div class="list_box">
					<div>
					
					<div class="apply_status">${dto.recStatus == "1" ? "승인" : "접수"}</div>
					<%-- <div class="apply_status">
					<c:choose>
						<c:when test="${dto.recStatus == '0'}">
							<label>접수</label>
						</c:when>
						<c:when test="${dto.recStatus == '1'}">
							<label>마감</label>
						</c:when>
						<c:when test="${dto.recStatus == '2'}">
							<label>활동완료</label>
						</c:when>
					</c:choose>
					</div>--%>
					<%-- <c:choose> 
						<c:when test="${dto.recStatus == '0'}">
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose> --%>
							<input type="button" value="취소하기" class="float_r g_btn" onclick="location.href='${CONTEXT_PATH}/general/generalController?action=cancelVol&volApplyNo=${dto.volApplyNo}&volDetailNo=${dto.volDetailNo}'">
						
						
					</div>
					<h3><a href="${CONTEXT_PATH}/common/commonController?action=volDetatilForm&volInfoNo=${dto.volInfoNo}">${dto.volTitle}</a></h3>
					<div class="span_box">
						<span class="title_span">[신청일자]</span>
						<span>${dto.applyDate}</span>
					</div>
					
					<div class="span_box">
						<span class="title_span">[봉사일자]</span>
						<span>${dto.volDate}</span>
					</div>
					
					<div class="span_box">
						<span class="title_span">[진행시간]</span>
						<span>${dto.startTime}</span> ~
						<span>${dto.endTime}</span>
					</div>
				</div>
				<hr class="list_hr">
			</li>
		</c:forEach>	
		</ul>
		<hr class="list_end_hr">
		<!-- 봉사모집글 목록 -->
	</div>
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>