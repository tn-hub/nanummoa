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
	margin-top: 70px;
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
	border-radius: 5px;
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
	height: 300px; 
	padding: 5px; 
	margin-bottom: 10px; 
	border: 1px solid gray;
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
<h2>자원봉사 상세</h2>
<hr>
<input class="btn_list" type="button" value="목록"  style="cursor:hand;" onclick="location.href='${CONTEXT_PATH}/common/commonController?action=volListForm'">
<c:if test="${grade == 'G' }">
<input id="btn_apply" type="button" value="신청하기" style="cursor:hand;" onclick="location.href='${CONTEXT_PATH}/general/generalController?action=enrollVolForm&volInfoNo=${vDto.volInfoNo}'">
</c:if>
<hr>

<h3>${vDto.volTitle} <span id="recSt">${vDto.recStatuse}</span></h3>   
<form action="${CONTEXT_PATH}/common/commonController?action=volDetatilForm" method="post">
	<table id="volAdd">
		<tr>
			<th>봉사 시작일</th>
				<td>
					${vDto.startTime}
				</td>
			<th>봉사 마감일</th>
			<td>
				${vDto.endTime}
			</td>
		</tr>
		<tr>
			<th>봉사 시작 시간</th>
			<td>${vDto.volStartTime}</td>
			<th>봉사 마감 시간</th>
			<td>${vDto.volEndTime}</td>
		</tr>
		<tr>
			<th>모집 시작일</th>
			<td>
				${vDto.startDate}
			</td>
			<th>모집 마감일</th>
			<td>
				${vDto.endDate}
			</td>
		</tr>
		<tr>
			<th>모집 인원</th>
			<td>${vDto.totalCount} 명</td>
			<th>봉사 장소</th>
			<td id="place_td" colspan="3">
				${vDto.volPlace}
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
	<div id="map" >
	
	</div>
	
	<div id="center_info">
		<ul>
			<li>담당자명 : ${vDto.name}</li>
			<li>전화번호 : ${vDto.mobile}</li>
			<li>주소 : ${vDto.address}</li>
		</ul>
	</div>
	<hr>
	<!-- 어드민 만보이겠금 -->
	<c:if test = "${grade == 'C'}">
	<div id="add_btn">
		<input class="btn_add" type="button" value="삭제하기"  style="cursor:hand;" onclick="location.href='${CONTEXT_PATH}/center/centerController?action=updateVolForm&volInfoNo=${vDto.volInfoNo}'">
		<input class="btn_add" type="button" value="수정하기"  style="cursor:hand;" onclick="location.href='${CONTEXT_PATH}/center/centerController?action=deleteVol&volInfoNo=${vDto.volInfoNo}'">
	</div>
	</c:if>
</form>
<%@ include file="/common/footer.jsp"%>
</div>
</body>
</html>