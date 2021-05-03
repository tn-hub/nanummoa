/**
 * 
 */
package com.nanum.dto;

/**
 * <pre>
 * 관리자
 * </pre>
 * 
 * @author 메타쓰리
 *
 */
public class AdminMemberDto {
	/** 관리자아이디 */
	private String adminId;

	/** 관리자비밀번호 */
	private String adminPass;

	/** 이름 */
	private String adminName;

	/** 연락처 */
	private String adminMobile;

	/** 이메일 */
	private String adminEmail;

	/**
	 * 기본생성자
	 */
	public AdminMemberDto() {
	}

	/**
	 * 전체생성자
	 * 
	 * @param adminId     관리자아이디
	 * @param adminPass   관리자비밀번호
	 * @param adminName   이름
	 * @param adminMobile 연락처
	 * @param adminEmail  이메일
	 */
	public AdminMemberDto(String adminId, String adminPass, String adminName, String adminMobile, String adminEmail) {
		this.adminId = adminId;
		this.adminPass = adminPass;
		this.adminName = adminName;
		this.adminMobile = adminMobile;
		this.adminEmail = adminEmail;
	}

	/**
	 * @return the adminId
	 */
	public String getAdminId() {
		return adminId;
	}

	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return the adminPass
	 */
	public String getAdminPass() {
		return adminPass;
	}

	/**
	 * @param adminPass the adminPass to set
	 */
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	/**
	 * @return the adminName
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * @param adminName the adminName to set
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	/**
	 * @return the adminMobile
	 */
	public String getAdminMobile() {
		return adminMobile;
	}

	/**
	 * @param adminMobile the adminMobile to set
	 */
	public void setAdminMobile(String adminMobile) {
		this.adminMobile = adminMobile;
	}

	/**
	 * @return the adminEmail
	 */
	public String getAdminEmail() {
		return adminEmail;
	}

	/**
	 * @param adminEmail the adminEmail to set
	 */
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(adminId);
		builder.append(", ");
		builder.append(adminPass);
		builder.append(", ");
		builder.append(adminName);
		builder.append(", ");
		builder.append(adminMobile);
		builder.append(", ");
		builder.append(adminEmail);
		return builder.toString();
	}

}
