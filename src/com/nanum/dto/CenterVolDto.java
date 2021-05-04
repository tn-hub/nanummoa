/**
 * 
 */
package com.nanum.dto;

import java.sql.Date;

/**
 * 센터회원 게시판 정보
 * 
 * @author 메타쓰리
 *
 */
public class CenterVolDto {

	/** 글번호 */
	private int volInfoNo;

	/** 센터회원아이디 */
	private String centerId;

	/** 센터이름 */
	private String centerName;

	/** 제목 */
	private String volTitle;

	/** 모집시작일 */
	private Date startDate;

	/** 모집마감일 */
	private Date endDate;

	/** 봉사시작일 */
	private Date volStart;

	/** 봉사마감일 */
	private Date volEnd;

	/** 봉사분야번호 */
	private String categoryNo;

	/** 봉사분야이름 */
	private String categoryName;

	/** 봉사상세정보번호 */
	private int volDetailNo;

	/** 봉사실행일 */
	private String volDate;

	/** 모집상태 */
	private String recStatus;

	/** 마감일 */
	private int deadline;

	/**
	 * 기본 생성자
	 */
	public CenterVolDto() {
	}

	/**
	 * 전체 생성자
	 * 
	 * @param volInfoNo   글번호
	 * @param centerId    센터회원아이디
	 * @param centerName  센터이름
	 * @param volTitle    제목
	 * @param startDate   모집시작일
	 * @param endDate     모집마감일
	 * @param categoryNo  봉사분야번호
	 * @param volDetailNo 봉사상세정보번호
	 * @param volDate     봉사실행일
	 * @param recStatus   모집상태
	 * @param deadline    마감일
	 */
	public CenterVolDto(int volInfoNo, String centerId, String centerName, String volTitle, Date startDate,
			Date endDate, Date volStart, Date volEnd, String categoryNo, String categoryName, int volDetailNo,
			String volDate, String recStatus, int deadline) {
		super();
		this.volInfoNo = volInfoNo;
		this.centerId = centerId;
		this.centerName = centerName;
		this.volTitle = volTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.volStart = volStart;
		this.volEnd = volEnd;
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
		this.volDetailNo = volDetailNo;
		this.volDate = volDate;
		this.recStatus = recStatus;
		this.deadline = deadline;
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
	 * @return the centerId
	 */
	public String getCenterId() {
		return centerId;
	}

	/**
	 * @param centerId the centerId to set
	 */
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the volStart
	 */
	public Date getVolStart() {
		return volStart;
	}

	/**
	 * @param volStart the volStart to set
	 */
	public void setVolStart(Date volStart) {
		this.volStart = volStart;
	}

	/**
	 * @return the volEnd
	 */
	public Date getVolEnd() {
		return volEnd;
	}

	/**
	 * @param volEnd the volEnd to set
	 */
	public void setVolEnd(Date volEnd) {
		this.volEnd = volEnd;
	}

	/**
	 * @return the deadline
	 */
	public int getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the volDetailNo
	 */
	public int getVolDetailNo() {
		return volDetailNo;
	}

	/**
	 * @param volDetailNo the volDetailNo to set
	 */
	public void setVolDetailNo(int volDetailNo) {
		this.volDetailNo = volDetailNo;
	}

	/**
	 * @return the volDate
	 */
	public String getVolDate() {
		return volDate;
	}

	/**
	 * @param volDate the volDate to set
	 */
	public void setVolDate(String volDate) {
		this.volDate = volDate;
	}

	/**
	 * @return the recStatus
	 */
	public String getRecStatus() {
		return recStatus;
	}

	/**
	 * @param recStatus the recStatus to set
	 */
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(volInfoNo);
		builder.append(", ");
		builder.append(centerId);
		builder.append(", ");
		builder.append(centerName);
		builder.append(", ");
		builder.append(volTitle);
		builder.append(", ");
		builder.append(startDate);
		builder.append(", ");
		builder.append(endDate);
		builder.append(", ");
		builder.append(volStart);
		builder.append(", ");
		builder.append(volEnd);
		builder.append(", ");
		builder.append(categoryNo);
		builder.append(", ");
		builder.append(categoryName);
		builder.append(", ");
		builder.append(volDetailNo);
		builder.append(", ");
		builder.append(volDate);
		builder.append(", ");
		builder.append(recStatus);
		builder.append(", ");
		builder.append(deadline);
		return builder.toString();
	}

}
