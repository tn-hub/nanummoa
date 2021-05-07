/**
 * 
 */
package com.nanum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * 나눔모아 프로젝트에 사용 가능한 공통 기능을 갖는 클래스
 *
 */
public class Utility {
	/**
	 * 임시번호 발급
	 * @param length
	 * @param isUpper
	 * @return
	 */
	public static String getSecureString(int length, boolean isUpper) {
		Random extractNo = new Random((long) (Math.random() * System.nanoTime()));
		String secureCode = "";
		for (int index = 0; index < length; index++) {
			if (extractNo.nextBoolean()) {
				secureCode += extractNo.nextInt(8); // 0 ~ 9 숫자
			} else {
				if (isUpper) { // 영문 대문자
					secureCode += (char) (extractNo.nextInt(26) + 65);
				} else { // 영문 소문자
					secureCode += (char) (extractNo.nextInt(26) + 97);
				}
			}
		}

		return secureCode;
	}
	
	/**
	 * 현재날짜 반환 
	 * @return 현재 기본형식(년도4-월2-일2) 날짜 
	 */
	public static String getCurrentDate(int month) {
		String date = getCurrentDate("yyyy-MM-dd", Locale.KOREA);
		String[] dateSplit = date.split("-");
		int dateMonth = Integer.parseInt(dateSplit[1]);
		
		int addMonth = dateMonth + month;
		
		if (dateSplit[2].equals("31")) {
			if (addMonth <= 12) {
				if (addMonth == 2) {
					dateSplit[2] = "28";
				} else if (addMonth == 4 || addMonth == 6 || addMonth == 9 || addMonth == 11) {
					dateSplit[2] = "30";
				}
				
			}  else if (addMonth > 12) {
				int resultMonth = dateMonth + month - 12;
				if (resultMonth == 2) {
					dateSplit[2] = "28";
				} else if (resultMonth == 4 || resultMonth == 6 || resultMonth == 9 || resultMonth == 11) {
					dateSplit[2] = "30";
				}
			}
		}
		
		if (addMonth <= 12) {
			if (addMonth == 2 && Integer.parseInt(dateSplit[2]) >= 29) {
				dateSplit[2] = "28";
			}
			dateSplit[1] = addMonth < 10 ? "0" + addMonth : Integer.toString(addMonth);
		} else if (dateMonth + month > 12) {
			int dateYear = Integer.parseInt(dateSplit[0]);
			dateSplit[0] = Integer.toString(dateYear + 1);
			int resultMonth = addMonth - 12;
			if (resultMonth == 2 && Integer.parseInt(dateSplit[2]) >= 29) {
				dateSplit[2] = "28";
			}
			dateSplit[1] = resultMonth < 10 ? "0" + resultMonth : Integer.toString(resultMonth);
		}
		
		return dateSplit[0] + "-" + dateSplit[1] + "-" + dateSplit[2];
	}
	
	public static String getCurrentDate() {
		return getCurrentDate("yyyy-MM-dd", Locale.KOREA);
	}
	
	public static String getCurrentDate(String pattern) {
		return getCurrentDate(pattern, Locale.KOREA);
	}
	
	public static String getCurrentDate(String pattern, Locale locale) {
		return new SimpleDateFormat(pattern, locale).format(new Date());
	}
	
	/**
	 * 두 날짜 기간 사이 날짜별 배열 구하기 
	 * @throws ParseException 
	 */
	public static ArrayList<String> getDateList(String startVolDateStr, String endVolDateStr) throws ParseException {
		ArrayList<String> list = new ArrayList<String>();

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		Date startVolDate = sdfDate.parse(startVolDateStr);
		Date endVolDate = sdfDate.parse(endVolDateStr);
		System.out.println("봉사시작일 " + startVolDate);
		System.out.println("봉사종료일 " + endVolDate);
		
		long diffDay =( (endVolDate.getTime() - startVolDate.getTime()) / (24 * 60 * 60 * 1000) )+ 1;
		System.out.println("총" + diffDay + "일 간");
		
		Date d1 = new Date(endVolDate.getTime()+(1000*60*60*24*-1));
		Date d2 = new Date(d1.getTime()+(1000*60*60*24*-1));

		Date curDate = endVolDate;
		list.add(sdfDate.format(curDate));
		for (int i = 1; i < diffDay; i++) {
			System.out.println("하루전");
			Date d = new Date(curDate.getTime()+(1000*60*60*24*-1));
			curDate = d;
			System.out.println("curDate : " + sdfDate.format(d));
			list.add(sdfDate.format(d));
		}
		
		return list;
	}
	
}

