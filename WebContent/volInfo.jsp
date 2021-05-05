<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자원봉사 등록</title>
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
	width: 880px; 
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
<h2>자원봉사 등록</h2>
<hr>
<input class="btn_list" type="button" value="목록">
<hr>
<form action="">
	<input id="vol_info_title" type="text" placeholder="제목을 입력하세요">
	<table id="volAdd">
		<tr>
			<th>봉사 시작일</th>
			<td>
				<input type="date">
			</td>
			<th>봉사 마감일</th>
			<td>
				<input type="date">
			</td>
		</tr>
		<tr>
			<th>봉사 시작 시간</th>
			<td><select>
				<option>== 시간 선택 ==</option>
				<option>16:00</option>
			</select></td>
			<th>봉사 마감 시간</th>
			<td><select>
				<option>== 시간 선택 ==</option>
				<option>16:00</option>
			</select></td>
		</tr>
		<tr>
			<th>모집 시작일</th>
			<td>
				<input type="date">
			</td>
			<th>모집 마감일</th>
			<td>
				<input type="date">
			</td>
		</tr>
		<tr>
			<th>모집 인원</th>
			<td><select>
				<option>== 인원 선택 ==</option>
				<option> 1명 </option>
			</select></td>
			<th>신청 인원</th>
			<td></td>
		</tr>
		<tr>
			<th>봉사 분야</th>
			<td><select>
				<option>== 분야 선택 ==</option>
				<option>생활편의</option>
				<option>주거환경</option>
				<option>상담</option>
				<option>교육</option>
				<option>보건의료</option>
			</select></td>
			<th>봉사자 유형</th>
			<td><select>
				<option>== 유형 선택 ==</option>
				<option>성인</option>
				<option>청소년</option>
			</select></td>
		</tr>
		<tr>
			<th>봉사 장소</th>
			<td id="place_td" colspan="3">
				<input id="vol_adrr1" type="text" placeholder="우편 번호">
				<input type="button" value="우편번호 찾기"><br>
				<input id="vol_adrr2" type="text" placeholder="기본 주소">
				<input id="vol_adrr3" type="text" placeholder="상세 주소">
				</td>
		</tr>
	</table>
	<div id="area_text"><textarea placeholder="내용을 입력하세요."></textarea></div>
	<div id="add_map">지도영역</div>
	<div id="center_info">
		<ul>
			<li>담당자명 : </li>
			<li>전화번호 : </li>
			<li>주소 : </li>
		</ul>
	</div>
	<hr>
	<div id="add_btn">
		<input class="btn_add" type="button" value="취소">
		<input class="btn_add" type="submit" value="등록">
	</div>
</form>
<%@ include file="/common/footer.jsp"%>
</body>
</html>