/**
 * 
 */
package com.nanum.dto;

import java.util.Date;

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

	/** 봉사시작일시 */
	private Date startTime;

	/** 봉사마감일시 */
	private Date endTime;

	/** 모집시작일 */
	private Date startDate;

	/** 모집마감일 */
	private Date endDate;

	/** 봉사분야번호 */
	private String categoryNo;
	
	/** 봉사 분야*/
	private String categoryName;

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
	
	/** 신청인원 */
	private int applyCount;
	
	/** 모집인원 */
	private int totalCount;
	
	/** 모집상태 */
	private String recStatuse;
	
	/** 봉사 시작 시간 */
	private String volStartTime;
	
	/** 봉사 마감 시간 */
	private String volEndTime;
	
	/** 담당자명 */
	private String name;
	
	/** 전화번호 */
	private String mobile;
	
	/** 주소 */
	private String address;
	
	/** 이메일 */
	private String email;
	

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
	 * @param startTime    봉사시작일시
	 * @param endTime      봉사마감일시
	 * @param startDate    모집시작일
	 * @param endDate      모집마감일
	 * @param categoryNo   봉사분야번호
	 * @param categoryName 봉사 분야
	 * @param localNo      지역번호
	 * @param volType      봉사자유형
	 * @param volPlace     봉사장소
	 * @param latitude     위도
	 * @param longitude    경도
	 * @param volSubject   봉사대상
	 * @param totalCount   모집인원 
	 * @param recStatuse   모집상태 
	 * @param volStartTime 봉사시작시간
	 * @param volEndTime   봉사마감시간
	 * @param name   담당자명 
	 * @param mobile   전화번호 
	 * @param address   주소 
	 */
	public VolInfoDto(int volInfoNo, String centerId, String volTitle, String volContents, Date volWriteDate,
			Date startTime, Date endTime, Date startDate, Date endDate, String categoryNo, String categoryName,
			String localNo, String volType, String volPlace, String latitude, String longitude, String volSubject,
			int applyCount, int totalCount, String recStatuse, String volStartTime, String volEndTime, String name,
			String mobile, String address, String email) {
		super();
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
		this.categoryName = categoryName;
		this.localNo = localNo;
		this.volType = volType;
		this.volPlace = volPlace;
		this.latitude = latitude;
		this.longitude = longitude;
		this.volSubject = volSubject;
		this.applyCount = applyCount;
		this.totalCount = totalCount;
		this.recStatuse = recStatuse;
		this.volStartTime = volStartTime;
		this.volEndTime = volEndTime;
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.email = email;
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
	
	

	/**
	 * @return the applyCount
	 */
	public int getApplyCount() {
		return applyCount;
	}

	/**
	 * @param applyCount the applyCount to set
	 */
	public void setApplyCount(int applyCount) {
		this.applyCount = applyCount;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the recStatuse
	 */
	public String getRecStatuse() {
		return recStatuse;
	}

	/**
	 * @param recStatuse the recStatuse to set
	 */
	public void setRecStatuse(String recStatuse) {
		this.recStatuse = recStatuse;
	}

	/**
	 * @return the volStartTime
	 */
	public String getVolStartTime() {
		return volStartTime;
	}

	/**
	 * @param volStartTime the volStartTime to set
	 */
	public void setVolStartTime(String volStartTime) {
		this.volStartTime = volStartTime;
	}

	/**
	 * @return the volEndTime
	 */
	public String getVolEndTime() {
		return volEndTime;
	}

	/**
	 * @param volEndTime the volEndTime to set
	 */
	public void setVolEndTime(String volEndTime) {
		this.volEndTime = volEndTime;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
		builder.append(categoryName);
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
		builder.append(", ");
		builder.append(applyCount);
		builder.append(", ");
		builder.append(totalCount);
		builder.append(", ");
		builder.append(recStatuse);
		builder.append(", ");
		builder.append(volStartTime);
		builder.append(", ");
		builder.append(volEndTime);
		builder.append(", ");
		builder.append(name);
		builder.append(", ");
		builder.append(mobile);
		builder.append(", ");
		builder.append(address);
		builder.append(", ");
		builder.append(email);
		return builder.toString();
	}
}
