/**
 * 
 */
package com.nanum.dto;

import java.util.Date;

/**
 * <pre>
 * 봉사확인서
 * </pre>
 * 
 * @author 메타쓰리
 *
 */
public class VolConfirmationDto {
	/** 발급번호 */
	private String volConNo;

	/** 일반회원아이디 */
	private String generalId;

	/** 센터회원아이디 */
	private String centerId;

	/** 내용 */
	private String contents;

	/** 봉사상세정보번호 */
	private String volDetailNo;

	/** 발급일 */
	private Date volDate;

	/**
	 * 기본생성자
	 */
	public VolConfirmationDto() {
	}

	/**
	 * @param volConNo    발급번호
	 * @param generalId   일반회원아이디
	 * @param centerId    센터회원아이디
	 * @param contents    내용
	 * @param volDetailNo 봉사상세정보번호
	 * @param volDate     발급일
	 */
	public VolConfirmationDto(String volConNo, String generalId, String centerId, String contents, String volDetailNo,
			Date volDate) {
		this.volConNo = volConNo;
		this.generalId = generalId;
		this.centerId = centerId;
		this.contents = contents;
		this.volDetailNo = volDetailNo;
		this.volDate = volDate;
	}

	/**
	 * @return the volConNo
	 */
	public String getVolConNo() {
		return volConNo;
	}

	/**
	 * @param volConNo the volConNo to set
	 */
	public void setVolConNo(String volConNo) {
		this.volConNo = volConNo;
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
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * @return the volDetailNo
	 */
	public String getVolDetailNo() {
		return volDetailNo;
	}

	/**
	 * @param volDetailNo the volDetailNo to set
	 */
	public void setVolDetailNo(String volDetailNo) {
		this.volDetailNo = volDetailNo;
	}

	/**
	 * @return the volDate
	 */
	public Date getVolDate() {
		return volDate;
	}

	/**
	 * @param volDate the volDate to set
	 */
	public void setVolDate(Date volDate) {
		this.volDate = volDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(volConNo);
		builder.append(", ");
		builder.append(generalId);
		builder.append(", ");
		builder.append(centerId);
		builder.append(", ");
		builder.append(contents);
		builder.append(", ");
		builder.append(volDetailNo);
		builder.append(", ");
		builder.append(volDate);
		return builder.toString();
	}

}
