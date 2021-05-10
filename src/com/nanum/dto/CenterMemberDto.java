/**
 * 
 */
package com.nanum.dto;

/**
 * <pre>
 * 봉사센터 회원
 * 
 * </pre>
 * @author 메타쓰리
 *
 */
public class CenterMemberDto {
	/** 센터회원아이디 */
	private String centerId;

	/** 센터회원비밀번호 */
	private String centerPass;

	/** 이름 */
	private String centerName;

	/** 연락처 */
	private String centerMobile;

	/** 이메일 */
	private String centerEmail;

	/** 승인상태 */
	private String appStatus;
	
	/** 가입일 */
	private String entryDate;
	
	/** 구분 */
	private String gubun;
	
	/**
	 * 기본 생성자
	 */
	public CenterMemberDto() {
	}

	/**
	 * 전체 생성자
	 * 
	 * @param centerId     센터회원아이디
	 * @param centerPass   센터회원비밀번호
	 * @param centerName   이름
	 * @param centerMobile 연락처
	 * @param centerEmail  이메일
	 * @param appStatus    승인상태
	 * @param entryDate	     가입일
	 */
	


	/**
	 * @param centerId
	 * @param centerPass
	 * @param centerName
	 * @param centerMobile
	 * @param centerEmail
	 * @param appStatus
	 * @param entryDate
	 * @param gubun
	 */
	public CenterMemberDto(String centerId, String centerPass, String centerName, String centerMobile,
			String centerEmail, String appStatus, String entryDate, String gubun) {
		super();
		this.centerId = centerId;
		this.centerPass = centerPass;
		this.centerName = centerName;
		this.centerMobile = centerMobile;
		this.centerEmail = centerEmail;
		this.appStatus = appStatus;
		this.entryDate = entryDate;
		this.gubun = gubun;
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
	 * @return the centerPass
	 */
	public String getCenterPass() {
		return centerPass;
	}

	/**
	 * @param centerPass the centerPass to set
	 */
	public void setCenterPass(String centerPass) {
		this.centerPass = centerPass;
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
	 * @return the centerMobile
	 */
	public String getCenterMobile() {
		return centerMobile;
	}

	/**
	 * @param centerMobile the centerMobile to set
	 */
	public void setCenterMobile(String centerMobile) {
		this.centerMobile = centerMobile;
	}

	/**
	 * @return the centerEmail
	 */
	public String getCenterEmail() {
		return centerEmail;
	}

	/**
	 * @param centerEmail the centerEmail to set
	 */
	public void setCenterEmail(String centerEmail) {
		this.centerEmail = centerEmail;
	}

	/**
	 * @return the appStatus
	 */
	public String getAppStatus() {
		return appStatus;
	}

	/**
	 * @param appStatus the appStatus to set
	 */
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	
	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
	

	/**
	 * @return the gubun
	 */
	public String getGubun() {
		return gubun;
	}

	/**
	 * @param gubun the gubun to set
	 */
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(centerId);
		builder.append(", ");
		builder.append(centerPass);
		builder.append(", ");
		builder.append(centerName);
		builder.append(", ");
		builder.append(centerMobile);
		builder.append(", ");
		builder.append(centerEmail);
		builder.append(", ");
		builder.append(appStatus);
		builder.append(", ");
		builder.append(entryDate);
		builder.append(", ");
		builder.append(gubun);
		return builder.toString();
	}

}
