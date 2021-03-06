<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.nanum.dto.VolInfoDto" %>    
<%@ page import="java.util.ArrayList" %>
<%@ include file="/inc/taglib_menu.jsp" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자원봉사 상세</title>
<!-- 카카오맵 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bea7e22656a0be157b22405262037bb6"></script>
<script src="${initParam.CONTEXT_PATH}/resources/js/jquery-3.6.0.min.js"></script>
<style type="text/css">
#section_contents{
	width: 1000px; 
	border: 1px gray; 
	margin: 0 auto;
	margin-top: 20px;
	margin-bottom: 70px;
}

.btn_list{
	padding: 10px; 
	width: 70px;
	border-style: none;
	margin-left: 10px;
	border-radius: 5px;
	padding: 10px; 
	font-size: 16px;
}

#btn_apply{
	float: right;
	border-style: none;
	width: 100px;
	padding: 10px; 
	margin-right: 10px;
	font-size: 16px;
	border-radius: 5px;
    background: #FBD157;
}

#vol_info_title{
	width: 990px; 
	margin-top:20px; 
	margin-bottom: 20px; 
	padding: 5px; 
	font-size: 17px;
}

#recSt{
	float: right;
	padding: 10px;
	background-color: #FF9090;
	margin-right: 10px;
	margin-bottom: 10px;
	border-radius: 25px;
	font-size: smaller;
}

#volAdd{
	width:1000px;
	margin-bottom: 20px;
	border-top: 2px solid black;
	border-bottom: 2px solid black;	
	border-collapse: collapse;
}

#volAdd tr{
	border-bottom: 1px solid  #b3b3b3;
}

#volAdd th{
	padding: 5px;
	background-color: #EAEAEA;
}

#volAdd td {
	width: 320px;
	border-top: 1px solid  #b3b3b3;
	padding-left: 10px;
}

#volAdd select{
	width: 320px;
	border-style: none;
	height: 30px;
	color: #5F5F5F;
	font-size: 17px;
}

#place_td{
	padding: 5px;
}				

#vol_adrr1{
	width: 80px;
}

#vol_adrr2{
	width: 300px;
}

#vol_adrr3{
	width: 440px;
}

#area_text{
	min-height: 230px; 
	padding-left : 30px; 
	padding-bottom : 30px;
	margin-bottom: 5px; 
}

textarea{
	border-style: none;
	height: 300px; 
	width: 980px; 
	font-size: 17px;
	padding: 10px;
}

#map{
	height: 800px; 
	border:1px solid gray;
}

#add_btn{
	text-align: center; 
	margin-top: 20px; 
	margin-bottom: 20px;
}

.btn_add{
	padding: 10px; 
	width: 100px;
	font-size: 16px;
	border-radius: 5px;
	border-style: none;
}

.vol_list_ul {
	list-style:none;
	color: #5F5F5F;	
	padding-inline-start: 0px;
}

.vol_list_ul .span_box {
	display: inline-block;
	width: 300px;
}

 .title_span {
 	color: #3F3F3F !important;
	font-weight: bold;
}


</style>

<script type="text/javascript">
$(document).ready(function(){
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
       center: new kakao.maps.LatLng(${vDto.latitude},  ${vDto.longitude}), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	// 지도를 클릭한 위치에 표출할 마커입니다
	var marker = new kakao.maps.Marker({ 
	    // 지도 중심좌표에 마커를 생성합니다 
	    position: map.getCenter() 
	}); 
	// 지도에 마커를 표시합니다
	marker.setMap(map);
	
	// 지도에 클릭 이벤트를 등록합니다
	// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
	   kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
	    
	    // 클릭한 위도, 경도 정보를 가져옵니다 
	    var latlng = mouseEvent.latLng; 
	    
	    // 마커 위치를 클릭한 위치로 옮깁니다
	   /*  marker.setPosition(latlng); */
	    
	    
	    var resultDiv = document.getElementById('clickLatlng'); 
	    resultDiv.innerHTML = message;
	    
	    var lt=latlng.getLat();
	    var lg=latlng.getLng(); 
	    
	}); 	
}); 	

</script>
</head>

<body>
<%
pageContext.setAttribute("newLineChar", "\n");
%> 
<%@ include file="/common/header.jsp"%>
<hr>
<div id="section_contents">
<h1>자원봉사 상세</h1>
<hr>
<input class="btn_list g_btn" type="button" value="목록"  style="cursor:hand;" onclick="location.href='${CONTEXT_PATH}/common/commonController?action=volListForm'">
<c:if test="${grade == 'G' and  vDto.recStatuse == '모집중'}">
<input id="btn_apply" class="y_btn" type="button" value="신청하기" style="cursor:hand;" onclick="location.href='${CONTEXT_PATH}/general/generalController?action=enrollVolForm&volInfoNo=${vDto.volInfoNo}'">
</c:if>

<c:if test = "${grade == 'C' and dto.centerId eq vDto.centerId}">
	<input class="g_btn float_r" type="button" value="삭제하기"  style="cursor:hand;margin-left: 10px;" onclick="location.href='${CONTEXT_PATH}/center/centerController?action=deleteVol&volInfoNo=${vDto.volInfoNo}'">
	<input class="y_btn float_r" type="button" value="수정하기"  style="cursor:hand;" onclick="location.href='${CONTEXT_PATH}/center/centerController?action=updateVolForm&volInfoNo=${vDto.volInfoNo}'">
</c:if>

<c:if test = "${grade == 'A'}">
	<input class="g_btn float_r" type="button" value="삭제하기"  style="cursor:hand;margin-left: 10px;" onclick="location.href='${CONTEXT_PATH}/admin/adminController?action=deleteVol&volInfoNo=${vDto.volInfoNo}'">
</c:if>


<hr class="clear_b">

<h3>${map['volTitle']} <span id="recSt">${vDto.recStatuse}</span></h3>   
<form action="${CONTEXT_PATH}/common/commonController?action=volDetatilForm" method="post">
	<table id="volAdd">
		<tr>
			<th>모집 시작일</th>
			<td>${map['startDate']}</td>
			<th>모집 마감일</th>
			<td>${map['endDate']}</td>
		</tr>
		<tr>
			<th>봉사 시작일</th>
			<td>${map['startVolDate']}</td>
			<th>봉사 종료일</th>
			<td>${map['endVolDate']}</td>
		</tr>
		<tr>
			<th>봉사 시작 시간</th>
			<td>${map['startTime']}</td>
			<th>봉사 마감 시간</th>
			<td>${map['endTime']}</td>
		</tr>
		
		<tr>
			<th>모집 인원</th>
			<td>${map['totalCount']}명/1일</td>
			<th>봉사 장소</th>
			<td id="place_td" colspan="3">
				${map['volPlace']}
			</td>
		</tr>
		<tr>
			<th>봉사 분야</th>
				<td>
					${vDto.categoryName}
				</td>
			<th>봉사자 유형 </th>
				<td>
					${vDto.volType}
				</td>
		</tr>
		
	</table>
	<div id="area_text">${fn:replace(vDto.volContents,newLineChar,"<br/>")}</div>
	<div id="map">
	</div>
	
	<hr>
	<h3>◎ 담당자 정보</h3>
	<hr>
	<ul class="vol_list_ul">
		<li>
			<div class="span_box">
				<span class="title_span">[담당자]</span>
				<span>${vDto.name}</span>
			</div>
			<div class="span_box">
				<span class="title_span">[연락처]</span>
				<span>${vDto.mobile}</span>
			</div>
			<div class="span_box">
				<span class="title_span">[이메일]</span>
				<span>${vDto.email}</span>
			</div>
		</li>
	</ul>
	<hr>
</form>
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>