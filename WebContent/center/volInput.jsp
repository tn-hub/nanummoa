<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자원봉사 등록</title>
<!-- 통합 로딩 방식 : postcode.v2.js 라는 이름의 파일 로딩을 통해 우편번호 서비스를 이용 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=nqehifvf28&submodules=geocoder"></script>
<script type="text/javascript">
	function postcode() {
	   new daum.Postcode({
		    oncomplete: function(data) {
		    	document.getElementById("volPlace").value = data.zonecode;
		    	document.getElementById("address").value = data.roadAddress;
		    	document.getElementById("detailAddress").focus();
		    	
		    	var address =  document.getElementById("address").value;
				var localArr = address.split(' ');
				if(localArr[0] == '서울'){
					var local = localArr[1];
					console.log(local);
				}
				document.getElementById("local").value = local;
				searchAddressToCoordinate();
		    }
		}).open();
	}
	
	function searchAddressToCoordinate() {
		var address = document.getElementById("address").value;
			
		 naver.maps.Service.geocode({
		    query: address
		  }, function(status, response) {
		    if (status === naver.maps.Service.Status.ERROR) {
		      if (!address) {
		        return alert('Geocode Error, Please check address');
		      }
		      return alert('Geocode Error, address:' + address);
		    }

		    if (response.v2.meta.totalCount === 0) {
		      return alert('No result.');
		    }

		    var htmlAddresses = [],
		      item = response.v2.addresses[0],
		      point = new naver.maps.Point(item.x, item.y);
		    
		    console.log(item);
			console.log(point);
			document.getElementById("latitude").value = item.y;
			document.getElementById("longitude").value = item.x;
		});
	}
	
	window.onload = function() {
		today = new Date();
		today = today.toISOString().slice(0, 10);
		
		document.getElementById("startVolDate").value = today;
		document.getElementById("endVolDate").value = today;
		document.getElementById("startDate").value = today;
		document.getElementById("endDate").value = today;
		
		// 날짜체크
		var startDate = document.getElementById('startDate');
		var endDate = document.getElementById('endDate');
		var startVolDate = document.getElementById('startVolDate');
		var endVolDate = document.getElementById('endVolDate');
		
		startDate.addEventListener('change', function() {
		    if (startDate.value)
		    	endDate.min = startDate.value;
		   	 	startVolDate.min = startDate.value;
		    	endVolDate.min = startDate.value;
		}, false);
		endDate.addEventListener('change', function() {
		    if (endDate.value)
		    	startDate.max = endDate.value;
		}, false);
		startVolDate.addEventListener('change', function() {
		    if (startVolDate.value)
		    	endVolDate.min = startVolDate.value;
		}, false);
		endVolDate.addEventListener('change', function() {
		    if (endVolDate.value)
		    	startVolDate.max = endVolDate.value;
		}, false);

		// 시간체크
		var startTime = document.getElementById('startTime');
		var endTime = document.getElementById('endTime');
		
		startTime.addEventListener('change', function() {
		    if (startTime.value)
		    	endTime.value = startTime.value;
		}, false);
		endTime.addEventListener('change', function() {
		    if (startTime.value > endTime.value)
		    	alert("종료시간은 시간시간 이후로 설정해주세요.")
		}, false);
		
		
	}
</script>
<style type="text/css">
#section_contents{
	width: 1000px; 
	border: 1px gray; 
	margin: 0 auto;
	margin-top: 70px;
	margin-bottom: 70px;
}

.btn_list{
	padding: 7px; 
	width: 50px;
}

#volTitle{
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
	border-bottom: 2px solid black;	
}

#volAdd th{
	padding: 5px;
	background-color: #EAEAEA;
}

#volAdd td {
	width: 320px;
	border-bottom: 2px solid black;	
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

.vol_contents{
	border-style: none;
	height: 300px; 
	width: 100%; 
	font-size: 17px;
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
	margin-right: 20px; 
	padding: 7px; 
	width: 50px;
}

</style>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<hr>
<div id="section_contents">
<h1>자원봉사 등록</h1>
<hr>
<input class="g_btn" type="button" value="목록">
<hr>
<form action="${CONTEXT_PATH}/center/centerController?action=volInput" method="post" id="input_form">
	<input id="volTitle" name="volTitle" type="text" placeholder="제목을 입력하세요" required="required">
	<table id="volAdd">
		<tr>
			<th>모집 시작일</th>
			<td>
				<input type="date" id="startDate" name="startDate" required="required">
			</td>
			<th>모집 마감일</th>
			<td>
				<input type="date" id="endDate" name="endDate" required="required">
			</td>
		</tr>
		<tr>
			<th>봉사 시작일</th>
			<td>
				<input type="date" id="startVolDate" name="startVolDate" required="required">
			</td>
			<th>봉사 마감일</th>
			<td>
				<input type="date" id="endVolDate" name="endVolDate" required="required">
			</td>
		</tr>
		<tr>
			<th>봉사 시작시간</th>
			<td>
				<input type="time" id="startTime" name="startTime" required="required">
			</td>
			<th>봉사 마감시간</th>
			<td>
				<input type="time" id="endTime" name="endTime" required="required">
			</td>
		</tr>
		<tr>
			<th>모집 인원</th>
			<td>
				<input type="text" id="totalCount" name="totalCount" size="3" pattern="[0-9]+" required="required"> 명
			</td>
			<th>봉사 분야</th>
			<td>
			<select id="categoryNo" name="categoryNo" required="required">
				<option value="none">== 선택 ==</option>	
				<c:forEach var="dto" items="${volCategory }">
					<option value="${dto.categoryNo }">${dto.categoryName }</option>			
				</c:forEach>		
			</select>
			</td>
		</tr>
		<tr>
			<th>봉사 대상</th>
			<td>
			<select id="volSubject" name="volSubject" required="required">
				<option value="none">== 선택 ==</option>	
				<c:forEach var="dto" items="${volSubject }">
					<option value="${dto.serviceNo }">${dto.serviceName }</option>			
				</c:forEach>		
			</select>
			
			</td>
			<th>봉사자 유형</th>
			<td>
				<select id="volType" name="volType">
					<option value="none">== 선택 ==</option>
					<option value="성인">성인</option>
					<option value="청소년">청소년</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>봉사 장소</th>
			<td colspan="3">
				<input type="text" id="volPlace" name="volPlace" placeholder="우편번호" size="5" readonly="readonly">
				<input type="button" value="우편번호 찾기" onclick="postcode();">
				<span id="addressMessage" class="error_message"></span>
				
				<input type="text" id="address" name="address" placeholder="기본주소" size="40" readonly="readonly" required="required">
				<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" size="40">
				<input type="hidden" id="local" name="local">
				<input type="hidden" id="latitude" name="latitude">
				<input type="hidden" id="longitude" name="longitude">
			</td>
		</tr>
	</table>
	<div id="area_text">
<textarea class="vol_contents" name="volContents" id="volContents" placeholder="내용을 입력하세요." required="required">
 - 봉사내용 :
 - 봉사장소 : 
 - 봉사문의 :
 - 주의사항 :
</textarea>
	</div>
	<div id="center_info">
	<h3>◎ 담당자 정보</h3>
	<input type="button" value="내정보수정" class="float_r y_btn minus_mg_t" onclick="location.href='${CONTEXT_PATH}/center/centerController?action=centerMyInfoForm'">
	<hr class="clear_b">
	<ul class="vol_list_ul">
		<li>
			<div class="span_box">
				<span class="title_span">[이름]</span>
				<span>${dto.centerName}</span>
			</div>
			<div class="span_box">
				<span class="title_span">[연락처]</span>
				<span>${dto.centerMobile}</span>
			</div>
			<div class="span_box">
				<span class="title_span">[이메일]</span>
				<span>${dto.centerEmail}</span>
			</div>
		</li>
	</ul>
	</div>
	<hr>
	<div id="add_btn">
		<input class="y_btn" type="submit" value="등록">
		<input class="g_btn" type="reset" value="취소">
	</div>
</form>
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>