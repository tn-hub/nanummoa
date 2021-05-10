/**
 * 공통기능 관련 자바스크립트
 */

/** 현재날짜 반환 yyyy-mm-dd*/
function getCurrentDate(){
	var today = new Date();   
	
	var year = today.getFullYear();
	var month = today.getMonth() + 1; 
	var date = today.getDate();
	
	month = month < 10 ? "0" + month : month;
	date = date < 10 ? "0" + date : date;
	return year + "-" + month + "-" + date;
	
}

/** x달 후 날짜 반환 yyyy-mm-dd*/
function getAddDate(num){
	var today = new Date();

	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var date = today.getDate();
	var addMonth = month + num;

	if (date == 31) {
		if (addMonth <= 12) {
			if (addMonth == 2) {
				date = 28;
			} else if (addMonth == 4 || addMonth == 6 || addMonth == 9
					|| addMonth == 11) {
				date = 30;
			}

		} else if (addMonth > 12) {
			var resultMonth = addMonth - 12;
			if (resultMonth == 2) {
				date = 28;
			} else if (resultMonth == 4 || resultMonth == 6 || resultMonth == 9
					|| resultMonth == 11) {
				date = 30;
			}
		}
	}

	if (addMonth <= 12) {
		if (addMonth == 2 && date >= 29) {
			date = 28;
		}
		month = addMonth < 10 ? "0" + addMonth : addMonth;
	} else if (addMonth > 12) {
		year = year + 1;
		var resultMonth = addMonth - 12;
		if (resultMonth == 2 && date >= 29) {
			date = 28;
		}
		month = resultMonth < 10 ? "0" + resultMonth : resultMonth;
	}
	
	date = date < 10 ? "0" + date : date;
	
	return year + "-" + month + "-" + date;
	
	
}