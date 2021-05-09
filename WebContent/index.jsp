<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- highcharts -->
<script src="http://code.highcharts.com/highcharts.js"></script>
<script type="text/javascript">
$(function () { 
	Highcharts.chart('container', {
	    chart: {
	        type: 'bar'
	    },
	    title: {
	        text: '분야별 자원봉사 모집 현황'
	    },
	    xAxis: {
	        categories: [
	        	<c:forEach var="category" items="${categoryStatistics}" varStatus="status">
    			<c:choose>
    				<c:when test="${status.last}">
 	                 '${category.categoryName}'
    				</c:when>
    				<c:otherwise>
    				'${category.categoryName}',
    				</c:otherwise>
    			</c:choose>
    	</c:forEach>
	        	],
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '모집 수',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '모집 수',
	        data: [
	        	<c:forEach var="category" items="${categoryStatistics}" varStatus="status">
    			<c:choose>
    				<c:when test="${status.last}">
 	                 ${category.count}
    				</c:when>
    				<c:otherwise>
    				${category.count},
    				</c:otherwise>
    			</c:choose>
    	</c:forEach>
	        	
	        	]
	    }]
	});
	
	Highcharts.chart('container2', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: '지역별 자원봉사 현황'
	    },
	    accessibility: {
	        announceNewData: {
	            enabled: true
	        }
	    },
	    xAxis: {
	        type: 'category'
	    },
	    yAxis: {
	        title: {
	            text: '신청자 수 '
	        }

	    },
	    legend: {
	        enabled: false
	    },
	    plotOptions: {
	        series: {
	            borderWidth: 0,
	            dataLabels: {
	                enabled: true,
	                format: '{point.y}명'
	            }
	        }
	    },

	    tooltip: {
	        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}명</b><br/>'
	    },

	    series: [
	        {
	            name: "신청자 수",
	            colorByPoint: true,
	            data: [
	            	<c:forEach var="local" items="${localStatistics}" varStatus="status">
            			<c:choose>
            				<c:when test="${status.last}">
            				{
         	                    name: "${local.localName}",
         	                    y: ${local.count}
         	                }
            				</c:when>
            				<c:otherwise>
            				{
         	                    name: "${local.localName}",
         	                    y: ${local.count}
         	                },
            				</c:otherwise>
            			</c:choose>
            	</c:forEach>
	            ]
	        }
	    ]
	    
	});
	
	Highcharts.chart('container3', {
		chart: {
	        type: 'column'
	    },
	    title: {
	        text: '이용자별 가입 현황'
	    },
	    accessibility: {
	        announceNewData: {
	            enabled: true
	        }
	    },
	    xAxis: {
	        type: 'category'
	    },
	    yAxis: {
	        title: {
	            text: '회원 수 '
	        }

	    },
	    legend: {
	        enabled: false
	    },
	    plotOptions: {
	        series: {
	            borderWidth: 0,
	            dataLabels: {
	                enabled: true,
	                format: '{point.y}명'
	            }
	        }
	    },

	    tooltip: {
	        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}명</b><br/>'
	    },

	    series: [
	        {
	            name: "회원 수",
	            colorByPoint: true,
	            data: [
            				{
         	                    name: "청소년 봉사자",
         	                    y: ${memberStatistics.teenager}
         	                },
            				{
         	                    name: "성인 봉사자",
         	                    y: ${memberStatistics.adult}
         	                },
         	                {
         	                    name: "봉사센터",
         	                    y: ${memberStatistics.centerMember}
         	                }
	            ]
	        }
	    ]
	});
});
</script>
<title>나눔모아</title>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/main_contents.jsp"%>
<%@ include file="/common/footer.jsp"%>
</body>
</html>