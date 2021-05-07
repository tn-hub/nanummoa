<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증서 다운</title>
<style type="text/css">
#pdf_wrap {
  position: absolute;
  width: 1000px;
  height: 1400px;
  padding: 15px;
  text-align:center;
  border: 1px solid black;
  margin: auto;
}


.memberInfo {
	width: 700px;
	height: 200px;
	text-align:left;
	margin: auto;
}

.memberInfo h3 {
	display: inline-block;
}

.memberInfo span {
	font-size: 20px;
}

.title_h {
	font-weight: bold;
	font-size: 60px;
	margin-top: 70px;
	margin-bottom: 200px;
}
	
.left_wrap {
	position: relative;
    left: -278px;
    top: -24px;
    font-weight: bold;
}

.right_wrap {
	position: relative;
	right: -350px;
}

.vol_info_ul {
	width: 700px;
	margin: 150px auto;
	text-align: left;
	font-size: 20px
}

.write_date {
	font-size: 25px;
	margin-bottom: 70px;
}

.logo_img {
    width: 130px;
    height: 58px;
    margin-right: 75px;
}

</style>
<script type = "text/javascript" src = "http://code.jquery.com/jquery-latest.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<script type = "text/javascript" src = "https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
	
		
		html2canvas($('#pdf_wrap')[0]).then(function(canvas) {
		    var doc = new jsPDF('p', 'mm', 'a4'); //jspdf객체 생성
		    var imgData = canvas.toDataURL('image/png'); //캔버스를 이미지로 변환
		    var imgWidth = 190; // 이미지 가로 길이(mm) / A4 기준 210mm
		    var pageHeight = imgWidth * 1.414;  // 출력 페이지 세로 길이 계산 A4 기준
		    var imgHeight = canvas.height * imgWidth / canvas.width;
		    var heightLeft = imgHeight;
		    var margin = 10; // 출력 페이지 여백설정
		    var position = 10;
		    doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight); //이미지를 기반으로 pdf생성
		    doc.save('자원봉사활동 확인서.pdf'); //pdf저장
		  });
	
});

</script>
</head>
<body>

<div id="pdf_wrap">
		<span class="left_wrap">발급번호 : ${map.volConNo}</span>
		<span class="right_wrap">
			<img class="logo_img" src="${initParam.CONTEXT_PATH}/resources/img/logo_title.png"/>
		</span>
		<h1 class="title_h">자원봉사활동 확인서</h1>
		<div class="memberInfo">
			<h3>성명 :</h3>
			<span>${map.generalName}</span>
			<hr>
			<h3>주소 :</h3>
			<span>${map.generalAddress}</span>
			<hr>
		</div>
		

		<ul class="vol_info_ul">
			<li>자원봉사 활동기간 : ${map.startDate} ~ ${map.endDate} 중 ${map.totalDate}일</li>
			<li>자원봉사 활동시간 : ${map.startTime} ~ ${map.endTime}</li>
			<li>봉사활동 내용 : ${map.contents}</li>
		</ul>

		<h2>위와 같이 자원봉사 활동에 참여하였음을 확인함</h2>
		<p class="write_date">${map.volDate}</p>

		<h1>${map.centerName}</h1>
</div>
</body>
</html>