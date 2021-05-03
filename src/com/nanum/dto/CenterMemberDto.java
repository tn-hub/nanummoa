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
	 */
	public CenterMemberDto(String centerId, String centerPass, String centerName, String centerMobile, String centerEmail,
			String appStatus) {
		this.centerId = centerId;
		this.centerPass = centerPass;
		this.centerName = centerName;
		this.centerMobile = centerMobile;
		this.centerEmail = centerEmail;
		this.appStatus = appStatus;
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
		return builder.toString();
	}

}
