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

	/** 글번호 */
	private int volInfoNo;

	/** 봉사제목 */
	private String volTitle;

	/** 일반회원이름 */
	private String generalName;

	/**
	 * 기본 생성자
	 */
	public VolApplyListDto() {
	}

	/**
	 * 봉사 신청 내역 생성자
	 * 
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
	 * 봉사 신청자 목록 조회 생성자
	 * 
	 * @param volApplyNo  봉사신청번호
	 * @param applyDate   신청일
	 * @param generalId   일반회원아이디
	 * @param volDetailNo 봉사상세정보번호
	 * @param volStatus   봉사상태
	 * @param volInfoNo   글번호
	 * @param volTitle    봉사제목
	 * @param generalName 일반회원 이름
	 */
	public VolApplyListDto(int volApplyNo, Date applyDate, String generalId, int volDetailNo, int volStatus,
			int volInfoNo, String volTitle, String generalName) {
		super();
		this.volApplyNo = volApplyNo;
		this.applyDate = applyDate;
		this.generalId = generalId;
		this.volDetailNo = volDetailNo;
		this.volStatus = volStatus;
		this.volInfoNo = volInfoNo;
		this.volTitle = volTitle;
		this.generalName = generalName;
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
		builder.append(", ");
		builder.append(volInfoNo);
		builder.append(", ");
		builder.append(volTitle);
		builder.append(", ");
		builder.append(generalName);
		return builder.toString();
	}

}
