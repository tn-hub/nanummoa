<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %> 

<div class="contents">   
    
	<div class="main_wrapper">
		<div class="main_banner">
			<img src="${initParam.CONTEXT_PATH}/resources/img/main_banner.png">
		</div>
		
		<h2 class="main_title">모집중인 자원봉사</h2>
		<div class="vol_wrapper">
		<c:forEach var="vol" items="${volList}" varStatus="vs" begin="0" end="11">
			<c:if test="${vs.count % 4 == 1 }">
			<div class="block_row">
			</c:if>
				<div class="block_col">
					<div class="vol_local">${localMap[vol.localNo].localName}</div>
					<div class="vol_info">
						<p class="vol_block_title">${volCategoryMap[vol.categoryNo].categoryName}</p>
						<span class="vol_title">${vol.volTitle}</span>
						<p class="vol_period">모집기간 : ${vol.startDate}~${vol.endDate}</p>
						<p class="vol_period">봉사기간 : ${vol.startVolDate}~${vol.endVolDate}</p>
						<button onclick="location.href='${CONTEXT_PATH}/common/commonController?action=volDetatilForm&volInfoNo=${vol.volInfoNo}'" class="apply_btn">신청하기</button>
					</div>
				</div>
			<c:if test="${vs.count % 4 == 0 }">
			</div>
			</c:if>
		</c:forEach>
		</div>
		
		<div class="more_btn_line">
			<button class="more_btn" onclick="location.href='${CONTEXT_PATH}/common/commonController?action=volListForm'">더보기</button>
		</div>
		
		<h2 class="main_title">자원봉사 통계</h2>
		<div class="chart_wrapper">
			<div id="container" class="graph"></div>
			<div id="container2" class="graph"></div>
			<div id="container3" class="graph"></div>
		</div>
	</div>
</div> 