/**
 * 
 */
package com.nanum.dto;

import java.util.Date;

/**
 * <pre>
 * 봉사신청내역
 * 
 * </pre>
 * 
 * @author 메타쓰리
 *
 */
public class VolApplyListDto {
	/** 봉사신청번호 */
	private int volApplyNo;

	/** 신청일 */
	private Date applyDate;

	/** 일반회원아이디 */
	private String generalId;

	/** 봉사상세정보번호 */
	private int volDetailNo;

	/** 봉사상태 */
	private int volStatus;

	/**
	 * 기본 생성자
	 */
	public VolApplyListDto() {
	}

	/**
	 * 전체 생성자
	 * @param volApplyNo  봉사신청번호
	 * @param applyDate   신청일
	 * @param generalId   일반회원아이디
	 * @param volDetailNo 봉사상세정보번호
	 * @param volStatus   봉사상태
	 */
	public VolApplyListDto(int volApplyNo, Date applyDate, String generalId, int volDetailNo, int volStatus) {
		this.volApplyNo = volApplyNo;
		this.applyDate = applyDate;
		this.generalId = generalId;
		this.volDetailNo = volDetailNo;
		this.volStatus = volStatus;
	}

	/**
	 * @return the volApplyNo
	 */
	public int getVolApplyNo() {
		return volApplyNo;
	}

	/**
	 * @param volApplyNo the volApplyNo to set
	 */
	public void setVolApplyNo(int volApplyNo) {
		this.volApplyNo = volApplyNo;
	}

	/**
	 * @return the applyDate
	 */
	public Date getApplyDate() {
		return applyDate;
	}

	/**
	 * @param applyDate the applyDate to set
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
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
	 * @return the volStatus
	 */
	public int getVolStatus() {
		return volStatus;
	}

	/**
	 * @param volStatus the volStatus to set
	 */
	public void setVolStatus(int volStatus) {
		this.volStatus = volStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(volApplyNo);
		builder.append(", ");
		builder.append(applyDate);
		builder.append(", ");
		builder.append(generalId);
		builder.append(", ");
		builder.append(volDetailNo);
		builder.append(", ");
		builder.append(volStatus);
		return builder.toString();
	}

}
