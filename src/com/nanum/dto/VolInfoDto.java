/**
 * 
 */
package com.nanum.dto;

import java.sql.Date;

/**
 * <pre>
 * 봉사 게시판
 * 
 * </pre>
 * 
 * @author 메타쓰리
 *
 */
public class VolInfoDto {
	/** 글번호 */
	private int volInfoNo;

	/** 센터회원아이디 */
	private String centerId;

	/** 제목 */
	private String volTitle;

	/** 내용 */
	private String volContents;

	/** 작성일 */
	private Date volWriteDate;

	/** 봉사시작시간 */
	private Date startTime;

	/** 봉사마감시간 */
	private Date endTime;

	/** 모집시작일 */
	private Date startDate;

	/** 모집마감일 */
	private Date endDate;

	/** 봉사분야번호 */
	private String categoryNo;

	/** 지역번호 */
	private String localNo;

	/** 봉사자유형 */
	private String volType;

	/** 봉사장소 */
	private String volPlace;

	/** 위도 */
	private String latitude;

	/** 경도 */
	private String longitude;

	/** 봉사대상 */
	private String volSubject;

	/**
	 * 기본 생성자
	 */
	public VolInfoDto() {
	}

	/**
	 * 전체 생성자
	 * 
	 * @param volInfoNo    글번호
	 * @param centerId     센터회원아이디
	 * @param volTitle     제목
	 * @param volContents  내용
	 * @param volWriteDate 작성일
	 * @param startTime    봉사시작시간
	 * @param endTime      봉사마감시간
	 * @param startDate    모집시작일
	 * @param endDate      모집마감일
	 * @param categoryNo   봉사분야번호
	 * @param localNo      지역번호
	 * @param volType      봉사자유형
	 * @param volPlace     봉사장소
	 * @param latitude     위도
	 * @param longitude    경도
	 * @param volSubject   봉사대상
	 */
	public VolInfoDto(int volInfoNo, String centerId, String volTitle, String volContents, Date volWriteDate,
			Date startTime, Date endTime, Date startDate, Date endDate, String categoryNo, String localNo,
			String volType, String volPlace, String latitude, String longitude, String volSubject) {
		this.volInfoNo = volInfoNo;
		this.centerId = centerId;
		this.volTitle = volTitle;
		this.volContents = volContents;
		this.volWriteDate = volWriteDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.categoryNo = categoryNo;
		this.localNo = localNo;
		this.volType = volType;
		this.volPlace = volPlace;
		this.latitude = latitude;
		this.longitude = longitude;
		this.volSubject = volSubject;
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
	 * @return the volContents
	 */
	public String getVolContents() {
		return volContents;
	}

	/**
	 * @param volContents the volContents to set
	 */
	public void setVolContents(String volContents) {
		this.volContents = volContents;
	}

	/**
	 * @return the volWriteDate
	 */
	public Date getVolWriteDate() {
		return volWriteDate;
	}

	/**
	 * @param volWriteDate the volWriteDate to set
	 */
	public void setVolWriteDate(Date volWriteDate) {
		this.volWriteDate = volWriteDate;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	 * @return the volType
	 */
	public String getVolType() {
		return volType;
	}

	/**
	 * @param volType the volType to set
	 */
	public void setVolType(String volType) {
		this.volType = volType;
	}

	/**
	 * @return the volPlace
	 */
	public String getVolPlace() {
		return volPlace;
	}

	/**
	 * @param volPlace the volPlace to set
	 */
	public void setVolPlace(String volPlace) {
		this.volPlace = volPlace;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the volSubject
	 */
	public String getVolSubject() {
		return volSubject;
	}

	/**
	 * @param volSubject the volSubject to set
	 */
	public void setVolSubject(String volSubject) {
		this.volSubject = volSubject;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(volInfoNo);
		builder.append(", ");
		builder.append(centerId);
		builder.append(", ");
		builder.append(volTitle);
		builder.append(", ");
		builder.append(volContents);
		builder.append(", ");
		builder.append(volWriteDate);
		builder.append(", ");
		builder.append(startTime);
		builder.append(", ");
		builder.append(endTime);
		builder.append(", ");
		builder.append(startDate);
		builder.append(", ");
		builder.append(endDate);
		builder.append(", ");
		builder.append(categoryNo);
		builder.append(", ");
		builder.append(localNo);
		builder.append(", ");
		builder.append(volType);
		builder.append(", ");
		builder.append(volPlace);
		builder.append(", ");
		builder.append(latitude);
		builder.append(", ");
		builder.append(longitude);
		builder.append(", ");
		builder.append(volSubject);
		return builder.toString();
	}

}
