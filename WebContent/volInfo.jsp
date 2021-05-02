<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자원봉사 등록</title>
<style type="text/css">
#section{width: 1000px; border: 1px gray; margin: 0 auto;}
#section_contents{margin-left: 40px;}
.btn_list{margin-left: 40px;}
#title{width: 900px; margin-bottom: 20px; padding: 5px 5px 5px 5px;}
#volAdd{margin-bottom: 20px;}
table#volAdd tr, th, td{border: 1px solid gray; padding: 5px 5px 5px 5px;}
table#volAdd td{width: 320px;}
select{width: 320px;}
#vol_adrr1{width: 80px;}
#vol_adrr2{width: 300px;}
#vol_adrr3{width: 440px;}
textarea{width: 900px; height: 300px; padding: 5px 5px 5px 5px; margin-bottom: 10px;}
#add_map{width: 910px; height: 300px; border:1px solid gray;}
#add_btn{text-align: center; margin-top: 20px; margin-bottom: 20px;}
.btn_add{margin-right: 20px;}
</style>
</head>
<body>
<div id="section">
<h3>자원봉사 등록</h3>
<hr>
<input class="btn_list" type="button" value="목록">
<hr>
<div id="section_contents">
<input id="title" type="text" placeholder="제목을 입력하세요">
<table id="volAdd">
	<tr>
		<th>봉사 시작 일</th>
		<td><select>
			<option>== 날짜 선택 ==</option>
			<option>2021-05-01</option>
		</select></td>
		<th>봉사 마감 일</th>
		<td><select>
			<option>== 날짜 선택 ==</option>
			<option>2021-05-01</option>
		</select></td>
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
		<th>모집 시작 일</th>
		<td><select>
			<option>== 날짜 선택 ==</option>
			<option>2021-05-01</option>
		</select></td>
		<th>모집 마감 일</th>
		<td><select>
			<option>== 날짜 선택 ==</option>
			<option>2021-05-01</option>
		</select></td>
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
		<td colspan="3">
			<input id="vol_adrr1" type="text" placeholder="우편 번호">
			<input type="button" value="우편번호 찾기"><br>
			<input id="vol_adrr2" type="text" placeholder="기본 주소">
			<input id="vol_adrr3" type="text" placeholder="상세 주소">
			</td>
	</tr>
</table>
<textarea placeholder="내용을 입력하세요."></textarea>
<div id="add_map">지도영역</div>
<div id="center_info">
	<ul>
		<li>담당자명 : </li>
		<li>전화번호 : </li>
		<li>주소 : </li>
	</ul>
</div>
</div>
<hr>
<div id="add_btn">
<input class="btn_add" type="button" value="취소">
<input class="btn_add" type="submit" value="등록">
</div>
</div>
</body>
</html>