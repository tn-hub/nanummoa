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

	/**
	 * 기본 생성자
	 */
	public CenterInfoDto() {
	}

	
	/**
	 * 전체 생성자
	 * 
	 * @param centerId        센터회원아이디
	 * @param centerName      센터이름
	 * @param centerEntryDate 센터등록일자
	 * @param centerZipCode   우편번호
	 * @param centerAddress   기본주소
	 * @param registerCode    등록번호
	 * @param service         서비스대상
	 * @param ceoName         대표이름
	 * @param ceoMobile       대표연락처
	 * @param totAcceptCnt	   가입 대기 건수
	 */
	 
	public CenterInfoDto(String centerId, String centerName, String centerEntryDate, String centerZipCode,
			String centerAddress, String registerCode, String service, String ceoName, String ceoMobile,
			int totAcceptCnt) {
		super();
		this.centerId = centerId;
		this.centerName = centerName;
		this.centerEntryDate = centerEntryDate;
		this.centerZipCode = centerZipCode;
		this.centerAddress = centerAddress;
		this.registerCode = registerCode;
		this.service = service;
		this.ceoName = ceoName;
		this.ceoMobile = ceoMobile;
		this.totAcceptCnt = totAcceptCnt;
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


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(centerId);
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
		return builder.toString();
	}
}
