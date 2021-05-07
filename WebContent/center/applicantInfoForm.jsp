<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청자 승인</title>
<!-- jquery-3.6.0.min.js -->
<script type="text/javascript" src="${CONTEXT_PATH }/resources/js/jquery-3.6.0.min.js"></script>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">
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
.wrapper{
width: 100%;
}

.title{
width: 1000px;
margin:  auto;
}

.link{
text-align: center;

}

#vol_list_div {
		width: 1000px;
		/* background-color: blue; */
		margin: auto;
	}
	
	#vol_list_div a:link {
		color: #3F3F3F;
		text-decoration: none;
	}
	
	#vol_list_div a:visited {
		color: #3F3F3F;
		text-decoration: none;
	}
	
	#vol_list_div a:hover {
		text-decoration:underline;
	}
	
	#vol_search_table {
		text-align: center;
		border-top: 2px solid black;
		border-bottom: 2px solid black;	
		width: 900px;
		border-collapse: collapse;
		margin-bottom: 30px;
		font-size: 17px;
	}  
	
	#vol_search_table th {
		border-right: 1px solid #b3b3b3;
		height: 50px;
	}
	
	#vol_search_table td {
		border-right: 1px solid  #b3b3b3;
		border-top: 1px solid  #b3b3b3;
	}
	
	#vol_search_table th:last-child,  #vol_search_table td:last-child{
		border-right: none;
	}
	
	.search_table_large_th {
		width: 300px;
	}
	
	#vol_search_table select {
		border-style: none;
		height: 30px;
		color: #5F5F5F;
		font-size: 17px;
	}
	
	.search_large_select {
		width: 345px;
	}
	
	.search_small_select {
		width: 150px;
	}
	
	#vol_list_div .head_hr {
		margin-bottom: 50px;
	}
	
	#vol_search_box {
		width: 960px;
		height: 150px;
		border: 1px solid #b3b3b3;
		padding: 30px 20px;
	}
	
	#vol_search_box span {
		display: inline-block;
		width: 120px;
	}
	
	#vol_search_box div {
		width: 480px;
		height: 60px;
		float: left;
		color: #5F5F5F;	
		font-size: 17px;
		font-weight: bold;
	}
	
	#vol_search_box input[type="date"] {
		width: 130px;
		height: 30px;
	}
	
	#vol_search_box input[type="text"] {
		width: 285px;
		height: 30px;
		line-height: 30px;
	}
	
	#search_button_box {
		width: 1000px !important;
		text-align: center;
	}
	
	#search_button_box input {
		width: 100px;
		height: 40px;
		margin-right: 10px;
		cursor: pointer;
	}
	
	#vol_search {
		height: 400px;
	}
	
	#vol_list_div em {
		color: red;
	}
	
	.list_head_hr {
		border: 1px solid black;
		margin-bottom: 20px;
	}
	
	.list_hr {
		margin-top: 20px;
		margin-bottom: 20px;
	}
	
	.vol_list_ul {
		list-style:none;
		color: #5F5F5F;	
		padding-inline-start: 0px;
	}
	
	.vol_list_ul li {
		text-indent: 20px;
	}
	
	 .title_span {
	 	color: #3F3F3F !important;
		font-weight: bold;
	}
	
	.vol_list_ul .span_box1 {
		display: inline-block;
		width: 250px;
	}
	
	.vol_list_ul .deadline_box {
		text-align: right;
	}
	
	.link_box{
	display: inline-block;
    font-weight: bold;
    width: 490px;
    margin: 0 auto;
    text-align: center;
	}
	
	h2{
	text-align: center;
	}
	
	.detatil_btn{
	margin-left: 85px; 
	}
</style>
<script type="text/javascript">
$(document).ready(function() {
	/* 신청승인 버튼 체크박스 값 없을시 return */
	 $("#btn_add").click(function () {
		 if($("input:checkbox[name='checkDate']:checked").length == 0){
			alert("체크박스를 선택해주세요.");	
			return false;
		 }
	 });
	 
});
 
</script>
</head>
<body>
<%@ include file="/common/header.jsp"%>
	<div class="wrapper">
		<div class="title">	
<h1>신청자 정보</h1>
	<table id="volAdd">
		<tr>
			<th>이름</th>
			<td>
				${general.generalName }
			</td>
			<th>성별</th>
			<c:choose>
			<c:when test="${general.gender == 'M'}">
				<td>남자</td>
			</c:when>
			<c:when test="${general.gender == 'W'}">
				<td>여자</td>
			</c:when>
			</c:choose>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>
				${general.birthday }
			</td>
			<th>기본 주소</th>
			<td>
				${general.generalAddress }
			</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td>
				${general.generalMobile }
			</td>
			<th>이메일</th>
			<td>
				${general.generalEmail }
			</td>
		</tr>
		<tr>
			<th>희망 분야</th>
			<td>
				${general.categoryName }
			</td>
			<th>희망 지역</th>
			<td>
				${general.localName }
			</td>
		</tr>
	</table>
<form action="${CONTEXT_PATH }/center/centerController?action=applyGeneral" method="post">
	<input type="hidden" id="generalId" name="generalId" value="${list[0].generalId }">
	<input type="hidden" id="volInfoNo" name="volInfoNo" value="${list[0].volInfoNo }">

	<h2> ${list[0].volTitle } </h2>
	<hr class="list_head_hr">
	<c:forEach var="dto" items="${list }">
	<ul class="vol_list_ul">
		<li>
			<div class="list_box">
					<span class="title_span">
						<c:choose>
							<c:when test="${dto.volStatus == '0'}">
								<span><input type="checkbox" name="checkDate" id="checkDate" value="${dto.volApplyNo }"></span>
							</c:when>
							<c:otherwise>
								<span><input type="checkbox" name="checkDate" id="checkDate" value="${dto.volApplyNo }" disabled="disabled" ></span>
							</c:otherwise>
						</c:choose>
					</span>
					
				<div class="span_box1">
					<span class="title_span">[봉사기간]</span>
					<span>${dto.volDate }</span>
				</div>
				
				<div class="span_box1">
					<span class="title_span">[봉사상태]</span>
					<c:choose>
						<c:when test="${dto.recStatus == '0'}">
							<span>모집중</span>
						</c:when>
						<c:when test="${dto.recStatus != '0'}">
							<span>모집마감</span>
						</c:when>
					</c:choose>
				</div>
				
				<div class="span_box1">
					<span class="title_span">[모집인원]</span>
					<span>${dto.applyCount }/${dto.totalCount }명</span>
				</div>
				
					<c:if test="${dto.volStatus == '1'}">
						<span class="title_span">
						<input type="button" value="승인 취소" class="g_btn detatil_btn" 
						onclick="location.href='${CONTEXT_PATH}/center/centerController?action=closeApply&volApplyNo=${dto.volApplyNo }&generalId=${list[0].generalId }&volInfoNo=${list[0].volInfoNo }'"></span>
					</c:if>
			</div>
			<div class="deadline_box">
			</div>
			<hr class="list_hr">
		</li>
	</ul>
	</c:forEach>
		<div id="add_btn">
			<input class="y_btn" id="btn_add" type="submit" value="신청승인"  style="cursor:hand;">
		</div>
	
</form>
		</div>
	</div>	
<%@ include file="/common/footer.jsp"%>
</body>
</html>