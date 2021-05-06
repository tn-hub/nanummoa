package com.nanum.dto;

import java.sql.Date;

/**
 * <pre>
 * 자원봉사 엔티티(info, detail)
 * </pre>
 * @author 메타쓰리
 *
 */
public class VolBlockDto {
	/** 글번호 */
	private int volInfoNo;
	
	/** 제목 */
	private String volTitle;
	
	/** 봉사분야번호 */
	private String categoryNo;
	
	/** 지역번호 */
	private String localNo;
	
	/** 모집시작일 */
	private String startDate;

	/** 모집마감일 */
	private String endDate;
	
	/** 봉사시작일 */
	private String startVolDate;

	/** 봉사종료일 */
	private String endVolDate;

	/**
	 * 기본 생성자 
	 */
	public VolBlockDto() {
	}

	/**
	 * @param volInfoNo
	 * @param volTitle
	 * @param categoryNo
	 * @param localNo
	 * @param startDate
	 * @param endDate
	 * @param startVolDate
	 * @param endVolDate
	 */
	public VolBlockDto(int volInfoNo, String volTitle, String categoryNo, String localNo, String startDate,
			String endDate, String startVolDate, String endVolDate) {
		this.volInfoNo = volInfoNo;
		this.volTitle = volTitle;
		this.categoryNo = categoryNo;
		this.localNo = localNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startVolDate = startVolDate;
		this.endVolDate = endVolDate;
	}

	/**
	 * @return the volInfoNo
	 */
	public int getVolInfoNo() {
		return volInfoNo;
	}

	/**
	 * @param volInfoNo the volInfoNo to set
	 */
	public void setVolInfoNo(int volInfoNo) {
		this.volInfoNo = volInfoNo;
	}

	/**
	 * @return the volTitle
	 */
	public String getVolTitle() {
		return volTitle;
	}

	/**
	 * @param volTitle the volTitle to set
	 */
	public void setVolTitle(String volTitle) {
		this.volTitle = volTitle;
	}

	/**
	 * @return the categoryNo
	 */
	public String getCategoryNo() {
		return categoryNo;
	}

	/**
	 * @param categoryNo the categoryNo to set
	 */
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
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
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startVolDate
	 */
	public String getStartVolDate() {
		return startVolDate;
	}

	/**
	 * @param startVolDate the startVolDate to set
	 */
	public void setStartVolDate(String startVolDate) {
		this.startVolDate = startVolDate;
	}

	/**
	 * @return the endVolDate
	 */
	public String getEndVolDate() {
		return endVolDate;
	}

	/**
	 * @param endVolDate the endVolDate to set
	 */
	public void setEndVolDate(String endVolDate) {
		this.endVolDate = endVolDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(volInfoNo);
		builder.append(", ");
		builder.append(volTitle);
		builder.append(", ");
		builder.append(categoryNo);
		builder.append(", ");
		builder.append(localNo);
		builder.append(", ");
		builder.append(startDate);
		builder.append(", ");
		builder.append(endDate);
		builder.append(", ");
		builder.append(startVolDate);
		builder.append(", ");
		builder.append(endVolDate);
		return builder.toString();
	}

}
