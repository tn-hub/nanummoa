/**
 * 
 */
package com.nanum.dto;

import java.util.Date;

/**
 * <pre>
 * 일반회원
 * 
 * </pre>
 * @author 메타쓰리
 *
 */
public class GeneralMemberDto {
	/** 일반회원아이디 */
	private String generalId;

	/** 일반회원비밀번호 */
	private String generalPass;

	/** 이름 */
	private String generalName;

	/** 성별 */
	private String gender;

	/** 생년월일 */
	private String birthday;

	/** 우편번호 */
	private String generalZipCode;

	/** 기본주소 */
	private String generalAddress;

	/** 연락처 */
	private String generalMobile;

	/** 이메일 */
	private String generalEmail;

	/** 봉사분야번호 */
	private String categoryNo;

	/** 지역번호 */
	private String localNo;
	
	/** 카테고리 */
	private String categoryName;
	

	private String localName;
	
	/** 구분 */
	private String gubun;
	

	/**
	 * 기본 생성자
	 */
	public GeneralMemberDto() {}

	/**
	 * 전체 생성자
	 * 
	 * @param generalId      일반회원아이디
	 * @param generalPass    일반회원비밀번호
	 * @param generalName    이름
	 * @param gender         성별
	 * @param birthday       생년월일
	 * @param generalZipCode 우편번호
	 * @param generalAddress  기본주소
	 * @param generalMobile  연락처
	 * @param generalEmail   이메일
	 * @param categoryNo     봉사분야번호
	 * @param localNo        지역번호
	 * @param categoryName
	 * @param localName
	 */
	
	

	
	
	/**
	 * @param generalId
	 * @param generalPass
	 * @param generalName
	 * @param gender
	 * @param birthday
	 * @param generalZipCode
	 * @param generalAddress
	 * @param generalMobile
	 * @param generalEmail
	 * @param categoryNo
	 * @param localNo
	 * @param categoryName
	 * @param localName
	 * @param gubun
	 */
	public GeneralMemberDto(String generalId, String generalPass, String generalName, String gender, String birthday,
			String generalZipCode, String generalAddress, String generalMobile, String generalEmail, String categoryNo,
			String localNo, String categoryName, String localName, String gubun) {
		super();
		this.generalId = generalId;
		this.generalPass = generalPass;
		this.generalName = generalName;
		this.gender = gender;
		this.birthday = birthday;
		this.generalZipCode = generalZipCode;
		this.generalAddress = generalAddress;
		this.generalMobile = generalMobile;
		this.generalEmail = generalEmail;
		this.categoryNo = categoryNo;
		this.localNo = localNo;
		this.categoryName = categoryName;
		this.localName = localName;
		this.gubun = gubun;
	}
	
	

	/**
	 * @return the generalId
	 */
	public String getGeneralId() {
		return generalId;
	}

	/**
	 * @param generalId the generalId to set
	 */
	public void setGeneralId(String generalId) {
		this.generalId = generalId;
	}

	/**
	 * @return the generalPass
	 */
	public String getGeneralPass() {
		return generalPass;
	}

	/**
	 * @param generalPass the generalPass to set
	 */
	public void setGeneralPass(String generalPass) {
		this.generalPass = generalPass;
	}

	/**
	 * @return the generalName
	 */
	public String getGeneralName() {
		return generalName;
	}

	/**
	 * @param generalName the generalName to set
	 */
	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the generalZipCode
	 */
	public String getGeneralZipCode() {
		return generalZipCode;
	}

	/**
	 * @param generalZipCode the generalZipCode to set
	 */
	public void setGeneralZipCode(String generalZipCode) {
		this.generalZipCode = generalZipCode;
	}

	/**
	 * @return the generalAddress
	 */
	public String getGeneralAddress() {
		return generalAddress;
	}

	/**
	 * @param generalAddress the generalAddress to set
	 */
	public void setGeneralAddress(String generalAddress) {
		this.generalAddress = generalAddress;
	}

	/**
	 * @return the generalMobile
	 */
	public String getGeneralMobile() {
		return generalMobile;
	}

	/**
	 * @param generalMobile the generalMobile to set
	 */
	public void setGeneralMobile(String generalMobile) {
		this.generalMobile = generalMobile;
	}

	/**
	 * @return the generalEmail
	 */
	public String getGeneralEmail() {
		return generalEmail;
	}

	/**
	 * @param generalEmail the generalEmail to set
	 */
	public void setGeneralEmail(String generalEmail) {
		this.generalEmail = generalEmail;
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
		builder.append(generalId);
		builder.append(", ");
		builder.append(generalPass);
		builder.append(", ");
		builder.append(generalName);
		builder.append(", ");
		builder.append(gender);
		builder.append(", ");
		builder.append(birthday);
		builder.append(", ");
		builder.append(generalZipCode);
		builder.append(", ");
		builder.append(generalAddress);
		builder.append(", ");
		builder.append(generalMobile);
		builder.append(", ");
		builder.append(generalEmail);
		builder.append(", ");
		builder.append(categoryNo);
		builder.append(", ");
		builder.append(localNo);
		builder.append(", ");
		builder.append(categoryName);
		builder.append(", ");
		builder.append(localName);
		builder.append(", ");
		builder.append(gubun);
		return builder.toString();
	}

	
}
