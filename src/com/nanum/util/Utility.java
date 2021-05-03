/**
 * 
 */
package com.nanum.util;

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
}
