/**
 * 
 */
package com.nanum.dto;

/**
 * <pre>
 * 센터정보
 * 
 * </pre>
 * @author 메타쓰리
 *
 */
public class CenterInfoDto {
	/** 센터회원아이디 */
	private String centerId;
	
	/** 센터회원 휴대폰*/
	private String centerMobile;

	/** 센터이름 */
	private String centerName;

	/** 센터등록일자 */
	private String centerEntryDate;

	/** 우편번호 */
	private String centerZipCode;

	/** 기본주소 */
	private String centerAddress;

	/** 등록번호 */
	private String registerCode;

	/** 서비스대상 */
	private String service;

	/** 대표이름 */
	private String ceoName;

	/** 대표연락처 */
	private String ceoMobile;
	
	/** 가입 대기 건수 */
	private int totAcceptCnt;
	
	/** 가입신청일자 */
	private String cmemberEntryDate;
	
	/** 센터멤버이름 */
	private String cmemberName;
	
	/** 센터멤버전화번호 */
	private String cmemberMobile;
	
	/** 센터멤버이메일 */
	private String cmemberEmail;
	
	/** 전체건수  */
	private int totCnt;
	
	/** 구분 */
	private String gubun;
	
	

	/**
	 * 기본 생성자
	 */
	public CenterInfoDto() {
	}


	/**
	 * @param centerId			센터회원아이디
	 * @param centerMobile 		센터회원 휴대폰
	 * @param centerName     	 센터이름
	 * @param centerEntryDate 	센터등록일자
	 * @param centerZipCode   	우편번호
	 * @param centerAddress   	기본주소
	 * @param registerCode   	 등록번호
	 * @param service        	 서비스대상
	 * @param ceoName         	대표이름
	 * @param ceoMobile       	대표연락처
	 * @param totAcceptCnt	   	가입 대기 건수
	 * @param cmemberEntryDate 	센터신청일자
	 * @param cmemberName    	센터멤버이름
	 * @param cmemberMobile 	 센터멤버전화번호
	 * @param cmemberEmail   	센터멤버이메일
	 * @param totCnt   			전체건수
	 *  @param gubun			구분
	 */
	

	/**
	 * @param centerId
	 * @param centerMobile
	 * @param centerName
	 * @param centerEntryDate
	 * @param centerZipCode
	 * @param centerAddress
	 * @param registerCode
	 * @param service
	 * @param ceoName
	 * @param ceoMobile
	 * @param totAcceptCnt
	 * @param cmemberEntryDate
	 * @param cmemberName
	 * @param cmemberMobile
	 * @param cmemberEmail
	 * @param totCnt
	 * @param gubun
	 */
	public CenterInfoDto(String centerId, String centerMobile, String centerName, String centerEntryDate,
			String centerZipCode, String centerAddress, String registerCode, String service, String ceoName,
			String ceoMobile, int totAcceptCnt, String cmemberEntryDate, String cmemberName, String cmemberMobile,
			String cmemberEmail, int totCnt, String gubun) {
		super();
		this.centerId = centerId;
		this.centerMobile = centerMobile;
		this.centerName = centerName;
		this.centerEntryDate = centerEntryDate;
		this.centerZipCode = centerZipCode;
		this.centerAddress = centerAddress;
		this.registerCode = registerCode;
		this.service = service;
		this.ceoName = ceoName;
		this.ceoMobile = ceoMobile;
		this.totAcceptCnt = totAcceptCnt;
		this.cmemberEntryDate = cmemberEntryDate;
		this.cmemberName = cmemberName;
		this.cmemberMobile = cmemberMobile;
		this.cmemberEmail = cmemberEmail;
		this.totCnt = totCnt;
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
	 * @return the centerEntryDate
	 */
	public String getCenterEntryDate() {
		return centerEntryDate;
	}



	/**
	 * @param centerEntryDate the centerEntryDate to set
	 */
	public void setCenterEntryDate(String centerEntryDate) {
		this.centerEntryDate = centerEntryDate;
	}



	/**
	 * @return the centerZipCode
	 */
	public String getCenterZipCode() {
		return centerZipCode;
	}



	/**
	 * @param centerZipCode the centerZipCode to set
	 */
	public void setCenterZipCode(String centerZipCode) {
		this.centerZipCode = centerZipCode;
	}



	/**
	 * @return the centerAddress
	 */
	public String getCenterAddress() {
		return centerAddress;
	}



	/**
	 * @param centerAddress the centerAddress to set
	 */
	public void setCenterAddress(String centerAddress) {
		this.centerAddress = centerAddress;
	}



	/**
	 * @return the registerCode
	 */
	public String getRegisterCode() {
		return registerCode;
	}



	/**
	 * @param registerCode the registerCode to set
	 */
	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}



	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}



	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}



	/**
	 * @return the ceoName
	 */
	public String getCeoName() {
		return ceoName;
	}



	/**
	 * @param ceoName the ceoName to set
	 */
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}



	/**
	 * @return the ceoMobile
	 */
	public String getCeoMobile() {
		return ceoMobile;
	}



	/**
	 * @param ceoMobile the ceoMobile to set
	 */
	public void setCeoMobile(String ceoMobile) {
		this.ceoMobile = ceoMobile;
	}



	/**
	 * @return the totAcceptCnt
	 */
	public int getTotAcceptCnt() {
		return totAcceptCnt;
	}



	/**
	 * @param totAcceptCnt the totAcceptCnt to set
	 */
	public void setTotAcceptCnt(int totAcceptCnt) {
		this.totAcceptCnt = totAcceptCnt;
	}



	/**
	 * @return the cmemberEntryDate
	 */
	public String getCmemberEntryDate() {
		return cmemberEntryDate;
	}



	/**
	 * @param cmemberEntryDate the cmemberEntryDate to set
	 */
	public void setCmemberEntryDate(String cmemberEntryDate) {
		this.cmemberEntryDate = cmemberEntryDate;
	}



	/**
	 * @return the cmemberName
	 */
	public String getCmemberName() {
		return cmemberName;
	}



	/**
	 * @param cmemberName the cmemberName to set
	 */
	public void setCmemberName(String cmemberName) {
		this.cmemberName = cmemberName;
	}



	/**
	 * @return the cmemberMobile
	 */
	public String getCmemberMobile() {
		return cmemberMobile;
	}



	/**
	 * @param cmemberMobile the cmemberMobile to set
	 */
	public void setCmemberMobile(String cmemberMobile) {
		this.cmemberMobile = cmemberMobile;
	}



	/**
	 * @return the cmemberEmail
	 */
	public String getCmemberEmail() {
		return cmemberEmail;
	}



	/**
	 * @param cmemberEmail the cmemberEmail to set
	 */
	public void setCmemberEmail(String cmemberEmail) {
		this.cmemberEmail = cmemberEmail;
	}



	/**
	 * @return the totCnt
	 */
	public int getTotCnt() {
		return totCnt;
	}



	/**
	 * @param totCnt the totCnt to set
	 */
	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
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
		builder.append(centerMobile);
		builder.append(", ");
		builder.append(centerName);
		builder.append(", ");
		builder.append(centerEntryDate);
		builder.append(", ");
		builder.append(centerZipCode);
		builder.append(", ");
		builder.append(centerAddress);
		builder.append(", ");
		builder.append(registerCode);
		builder.append(", ");
		builder.append(service);
		builder.append(", ");
		builder.append(ceoName);
		builder.append(", ");
		builder.append(ceoMobile);
		builder.append(", ");
		builder.append(totAcceptCnt);
		builder.append(", ");
		builder.append(cmemberEntryDate);
		builder.append(", ");
		builder.append(cmemberName);
		builder.append(", ");
		builder.append(cmemberMobile);
		builder.append(", ");
		builder.append(cmemberEmail);
		builder.append(", ");
		builder.append(totCnt);
		builder.append(", ");
		builder.append(gubun);
		return builder.toString();
	}

}
