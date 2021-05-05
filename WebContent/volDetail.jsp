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

#add_map{
	height: 300px; 
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
<input class="btn_list" type="button" value="목록"  style="cursor:hand;">
<input id="btn_apply" type="button" value="신청하기" style="cursor:hand;">
<hr>

<a href="${CONTEXT_PATH}/common/commonController?action=volDetatilForm&volInfoNo=1">조회</a>
<input id="vol_info_title" type="text" value="${vDto.volTitle}" required="required"> 
<form action="">
	<table id="volAdd">
		<tr>
			<th>봉사 시작일</th>
			<td>
				<input type="date" value="${vDto.startTime}">
			</td>
			<th>봉사 마감일</th>
			<td>
				<input type="date" value="${vDto.endTime}">
			</td>
		</tr>
		<tr>
			<th>봉사 시작 시간</th>
			<td><input type="text" value="${vDto.volStartTime}"></td>
			<th>봉사 마감 시간</th>
			<td><input type="text" value="${vDto.volEndTime}"></td>
		</tr>
		<tr>
			<th>모집 시작일</th>
			<td>
				<input type="date" value="${vDto.startDate}">
			</td>
			<th>모집 마감일</th>
			<td>
				<input type="date" value="${vDto.endDate}">
			</td>
		</tr>
		<tr>
			<th>모집 인원</th>
			<td><input type="text" value="${vDto.totalCount}"></td>
			<th>신청 인원</th>
			<td><input type="text" value="${vDto.applyCount}"></td>
		</tr>
		<tr>
			<th>봉사 분야</th>
			<td>
				<select class="large_select" name="categoryNo">
						<option value="none">선택</option>	
						<c:forEach var="dto" items="${volCategory}">
							<option value="${dto.categoryNo}" ${dto.categoryNo == vDto.categoryNo ? 'selected="selected"' : '' }>${dto.categoryName}</option>			
						</c:forEach>		
					</select>
			</td>
			<th>봉사자 유형 </th>
			<td><select>
				<option>== 유형 선택 ==</option>
				<option value="성인" ${vDto.volType == "성인" ? 'selected="selected"' : '' }>성인</option>
				<option value="청소년" ${vDto.volType == "청소년" ? 'selected="selected"' : '' }>청소년</option>
				<option value="성인,청소년" ${vDto.volType == "성인,청소년" ? 'selected="selected"' : '' }>성인,청소년</option>
			</select></td>
		</tr>
		<tr>
			<th>봉사 장소</th>
			<td id="place_td" colspan="3">
				<input id="vol_adrr3" type="text" placeholder="상세 주소" value="${vDto.volPlace}">
			</td>
		</tr>
	</table>
	<div id="area_text"><textarea>${fn:replace(vDto.volContents,newLineChar,"<br/>")}</textarea></div>
	<div id="add_map" >
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8d66e864b87e987208f8177b0a743d9e&libraries=services,clusterer,drawing"></script>
<script> 
	var container = document.getElementById('add_map');
	var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};

	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	
	alert(map);
</script>
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
		<input class="btn_add" type="button" value="삭제하기"  style="cursor:hand;">
		<input class="btn_add" type="button" value="수정하기"  style="cursor:hand;">
	</div>
	</c:if>
</form>
<%@ include file="/common/footer.jsp"%>
</div>
</body>
</html>