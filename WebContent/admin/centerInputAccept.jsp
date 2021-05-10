<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.nanum.dto.CenterInfoDto" %>    
<%@ page import="java.util.ArrayList" %>    
<%@ include file="/inc/taglib_menu.jsp" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>센터회원  승인관리</title>
<style type="text/css">

em{
	color: red;
}

#section_contents{
	width: 1000px; 
	border: 1px gray; 
	margin: 0 auto;
	min-height: 900px;
}

#cecnter_standBy{
	border: 1px solid gray;
	width: 1000px;
	height: 80px;
	margin-top: 20px;
	margin-bottom: 20px;
}


#cecnter_standBy_ul li{
	list-style-type: none;
	float: left;
	margin-right: 30px;
}

#centerNAme_li{
	width: 250px;
}

#centerId_li{
	width: 170px;
}

#centerDate_li{
	width: 170px;
}

#c_stan_btnArea{
	padding-top: 15px;
	float: right;
}


.btn_cenList{
	border-style: none;
	width: 495px;
	height: 40px;
	text-align: center;
}




#centerDetail{
	border: 1px solid gray;
}


#page_btn{
	text-align: center; 
	margin: 20px 0px 20px 0px;
}

.r_btn{
	margin-left: 20px;
}
.bg_y{
	background-color: #fbd157;
	}
.link_box{
display: inline-block;
   font-weight: bold;
   width: 320px;
   height:20px;
   padding:10px 0;
   margin: 0 auto;
   text-align: center;
}
</style>
</head>


<body>
<%@ include file="/common/header.jsp"%>
<div id="section_contents">
<h2>센터회원 가입대기 목록</h2><br>
<hr>
<div class="link_box ${tap == 1? ' bg_y' : ''}" style="cursor:hand;">
	<a href="${CONTEXT_PATH}/admin/adminController?action=generalMinList">일반회원</a>
</div>
<div class="link_box ${tap == 2? ' bg_y' : ''}" style="cursor:hand;">
	<a href="${CONTEXT_PATH}/admin/adminController?action=centerMinList">센터회원</a>
</div>
<div class="link_box ${tap == 3? ' bg_y' : ''}" style="cursor:hand;">
	<a href="${CONTEXT_PATH}/admin/adminController?action=centerAcceptList">센터대기회원</a>
</div>
<hr>
[가입대기 센터회원 <em>${cDto.totAcceptCnt}</em> 건]
<form name="centerAcceptListForm" id="centerAcceptListForm" action="${CONTEXT_PATH}/admin/adminController?action=centerAcceptList" method="post">
<c:forEach var="dto" items="${centerActList}">
<div id="cecnter_standBy">
	<ul id="cecnter_standBy_ul">
		<li id="centerNAme_li">[센터명] <br>${dto.centerName}</li>
		<li id="centerId_li">[센터 아이디]<br><a href="${CONTEXT_PATH}/admin/adminController?action=centerDetail&centerId=${dto.centerId}">${dto.centerId}</a></li>
		<li id="centerDate_li">[신청일] <br>${dto.cmemberEntryDate}</li>
		<li id="c_stan_btnArea">
			<a href="${CONTEXT_PATH}/admin/adminController?action=centerRefuse&centerId=${dto.centerId}"><input type="button" value="반려" class="y_btn" class="btn_ac"></a>
			<a href="${CONTEXT_PATH}/admin/adminController?action=centerAccept&centerId=${dto.centerId}"><input type="button" value="승인" class="r_btn" class="btn_re"></a>
	</ul>
</div>
</c:forEach>
</form>

</div>
<div id="footer" class="footer">
	<%@ include file="/common/footer.jsp"%>
</div>
</body>
</html>