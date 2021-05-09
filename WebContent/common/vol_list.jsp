<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>봉사 조회</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/resources/css/common.css">
<script src="${initParam.CONTEXT_PATH}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${initParam.CONTEXT_PATH}/resources/js/utility.js"></script>
<style type="text/css">
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
	
	.vol_list_ul .span_box {
		display: inline-block;
		width: 300px;
	}
	
	.vol_list_ul .deadline_box {
		text-align: right;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	
	// 검색목록 초기화
	$("#resetBtn").click(function(){
		console.log("검색항목 초기화");
		$("#local option:eq(0)").attr("selected", "selected");
		$("#category option:eq(0)").attr("selected", "selected");
		$("#service option:eq(0)").attr("selected", "selected");
		$("#status option:eq(1)").attr("selected", "selected");
		$("#volStart").val(getCurrentDate());
		$("#volEnd").val(getAddDate(3));
		$("input[name='volType']").attr("checked", "checked");
		$("#volTitle").val("");
		$("#centerName").val("");
	});
	
	// 검색항목 검증
	$("#searchBtn").click(function(e){
		e.preventDefault()
		if ( $("#volStart").val() > $("#volEnd").val() ) {
			alert("봉사 시작일을 봉사 마감일 이전으로 지정해 주세요");
			return;
		}
		if ( $("input[name='volType']").is(":checked") == false) {
			alert("봉사유형을 선택해 주세요");
			return;
		}
		$("#searchForm").submit();
	});
	
	// 이전페이지
  	$("#preBtn").click(function() {
  		var curPageNum = ${curPageNum};
  		if (curPageNum> 1){
  			$("#pageNum").val(curPageNum -1);
  			$("#searchForm").submit();
  		}
  	});
  	
  	// 다음페이지
  	$("#nestBtn").click(function() {
  		console.log("다음");
  		var curPageNum = ${curPageNum};
  		var lastPageNum = ${lastPageNum};
  		
  		if (curPageNum < lastPageNum){
  			$("#pageNum").val(curPageNum +1);
  			$("#searchForm").submit();
  		}
  	});
  	
  //페이징 submit
    $("#page_btn").on("click", "input[name='btnPageNum']", function(e){
    		console.log("페이징 submit : " + $(e.target).val());
        	$("#pageNum").val($(e.target).val());
        	$("#searchForm").submit();
    });
	  	
});
</script>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<div id="vol_list_div">
	<h1>봉사조회</h1>
	<hr class="head_hr">
	
	<!-- 검색창 -------------------------------------------------------------------------------------------->
	<div id="vol_search">
		<form action="${CONTEXT_PATH}/common/commonController?action=volListForm" method="post" id="searchForm">
		<input type="hidden" value="1" id="pageNum" name="pageNum">
			<table id="vol_search_table">
				<tr>
					<th class="search_table_large_th">봉사지역</th>
					<th class="search_table_large_th">봉사분야</th>
					<th>봉사대상</th>
					<th>모집상태</th>
				</tr>
				<tr>
					<td>
						<select class="search_large_select" id="local" name="local">
							<option value="0">전체</option>
							<c:forEach var="dto" items="${localList}">
								<option value="${dto.localNo}"
								<c:if test="${searchMap.local == dto.localName}">
									selected="selected"
								</c:if>
								>${dto.localName}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select class="search_large_select" id="category" name="category">
							<option value="0">전체</option>
							<c:forEach var="dto" items="${volCategoryList}">
								<option value="${dto.categoryNo}"
								<c:if test="${searchMap.category == dto.categoryNo}">
									selected="selected"
								</c:if>
								>${dto.categoryName}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select class="search_small_select" id="service" name="service">
							<option value="0">전체</option>
							<c:forEach var="dto" items="${serviceList}">
								<option value="${dto.serviceNo}"
								<c:if test="${searchMap.service == dto.serviceNo}">
									selected="selected"
								</c:if>
								>${dto.serviceName}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select class="search_small_select" id="status" name="status">
							<option value="2">전체</option>
							<option value="0" 
							<c:if test="${searchMap.status == '0'}">
									selected="selected"
							</c:if>
							>모집중</option>
							<option value="1"
							<c:if test="${searchMap.status == '1'}">
									selected="selected"
							</c:if>
							>모집완료</option>
						</select>
					</td>
				</tr>
			</table>
			
			<div id="vol_search_box">
				<div>
					<span>봉사기간</span>
					
					<input type="date" name="volStart" id="volStart" value="${searchMap.volStart}"> ~ 
					<input type="date" name="volEnd" id="volEnd" value="${searchMap.volEnd}">
				</div>
				
				<div>
					<span>봉사자유형</span>
					<input type="checkbox" name="volType" value="성인" 
					<c:if test="${empty searchMap.volType || searchMap.volType == '성인'}">
									checked="checked"
					</c:if>
					> 성인
					<input type="checkbox" name="volType" value="청소년" 
					<c:if test="${empty searchMap.volType || searchMap.volType == '청소년'}">
									checked="checked"
					</c:if>
					> 청소년
				</div>
				
				<div>
					<span>봉사명</span>
					<input type="text" name="volTitle" id="volTitle"
					<c:if test="${!empty searchMap.volTitle}">
						value="${searchMap.volTitle}";
					</c:if>
					>
				</div>
				
				<div>
					<span>봉사단체명</span>
					<input type="text" name="centerName" id="centerName"
					<c:if test="${!empty searchMap.centerName}">
						value="${searchMap.centerName}";
					</c:if>
					>
				</div>
				
				<div id="search_button_box">
					<input class="y_btn" type="submit" id="searchBtn" value="검색">
					<input class="g_btn" type="button" id="resetBtn" value="초기화">
				</div>
			</div>
		</form>
	</div>
	<!-- 검색창 -->
	
	<!-- 봉사모집글 목록----------------------------------------------------------------------->
	<p>[전체 
		<em>${total}</em>
		건, 현재페이지 
		<em>${curPageNum}</em>
		/${lastPageNum}]
	</p>
	<c:if test="${grade == 'C' }">
		<button class="y_btn float_r minus_mg_t" onclick="location.href='${CONTEXT_PATH}/center/centerController?action=volInputForm'">글작성</button>
	</c:if>	
	<hr class="list_head_hr clear_b">
	
	<ul class="vol_list_ul">
	<c:forEach var="list" items="${volList}">
		<li>
			<div class="list_box">
				<div>
					<span class="title_span">
					<c:choose> 
						<c:when test="${list.recStatus == '0'}">모집중</c:when>
						<c:when test="${list.recStatus == '1'}">모집완료</c:when>
					</c:choose>
					</span>
					<span>(${list.categoryName})</span>
				</div>
				
				<h3><a href="${CONTEXT_PATH}/common/commonController?action=volDetatilForm&volInfoNo=${list.volInfoNo}">${list.volTitle}</a></h3>
				
				<div class="span_box">
					<span class="title_span">[모집기관]</span>
					<span>${list.centerName}</span>
				</div>
				
				<div class="span_box">
					<span class="title_span">[모집기간]</span>
					<span>${list.startDate}</span> ~
					<span>${list.endDate}</span>
				</div>
				
				<div class="span_box">
					<span class="title_span">[봉사기간]</span>
					<span>${list.volStart}</span> ~
					<span>${list.volEnd}</span>
				</div>
			</div>
			<div class="deadline_box">
					<p>마감
						<em>${list.deadLine}</em>
						일전
					</p>
			</div>
			<hr class="list_hr">
		</li>
		</c:forEach>
	</ul>
	<!-- 봉사모집글 목록 -->
	
	<!-- 페이징--------------------------------------------------------------------->
	<div id="page_btn">
  	<input type="button" value="이전 " id="preBtn" name="preBtn">
  	 <c:forEach var="i" begin="${ 1 }" end="${lastPageNum}">
             <input type="button" value=${ i }  name="btnPageNum"
             	<c:if test="${curPageNum == i}">
             		style="background-color: #FBD157;"
             	</c:if>
             >
       </c:forEach>   
  	<input type="button" value="다음 " id="nestBtn" name="nestBtn">	
  </div> 
	<!-- 페이징 -->
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>