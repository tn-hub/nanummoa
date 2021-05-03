/**
 * 
 */
package com.nanum.dto;

/**
 * <pre>
 * 지역
 * 
 * </pre>
 * @author 메타쓰리
 *
 */
public class LocalDto {
	/** 지역번호 */
	private String localNo;

	/** 지역이름 */
	private String localName;

	/**
	 * 기본생성자
	 */
	public LocalDto() {
	}

	/**
	 * 전체 생성자
	 * 
	 * @param localNo   지역번호
	 * @param localName 지역이름
	 */
	public LocalDto(String localNo, String localName) {
		this.localNo = localNo;
		this.localName = localName;
	}

	/**
	 * @return the localNo
	 */
	public String getLocalNo() {
		return localNo;
	}

	/**
	 * @param localNo the localNo to set
	 */
	public void setLocalNo(String localNo) {
		this.localNo = localNo;
	}

	/**
	 * @return the localName
	 */
	public String getLocalName() {
		return localName;
	}

	/**
	 * @param localName the localName to set
	 */
	public void setLocalName(String localName) {
		this.localName = localName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(localNo);
		builder.append(", ");
		builder.append(localName);
		return builder.toString();
	}

}
