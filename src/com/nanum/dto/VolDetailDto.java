/**
 * 
 */
package com.nanum.dto;

/**
 * <pre>
 * 봉사 상세정보
 * 
 * </pre>
 * 
 * @author 메타쓰리
 *
 */
public class VolDetailDto {
	/** 봉사상세정보번호 */
	private int volDetailNo;

	/** 글번호 */
	private int volInfoNo;

	/** 봉사실행일 */
	private String volDate;

	/** 신청인원 */
	private int applyCount;

	/** 마감인원 */
	private int totalCount;

	/** 모집상태 */
	private String recStatus;

	/**
	 * 기본 생성자
	 */
	public VolDetailDto() {
	}

	/**
	 * 전체 생성자
	 * 
	 * @param volDetailNo 봉사상세정보번호
	 * @param volInfoNo   글번호
	 * @param volDate     봉사실행일
	 * @param applyCount  신청인원
	 * @param totalCount  마감인원
	 * @param recStatus   모집상태
	 */
	public VolDetailDto(int volDetailNo, int volInfoNo, String volDate, int applyCount, int totalCount,
			String recStatus) {
		this.volDetailNo = volDetailNo;
		this.volInfoNo = volInfoNo;
		this.volDate = volDate;
		this.applyCount = applyCount;
		this.totalCount = totalCount;
		this.recStatus = recStatus;
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
	 * @return the volDate
	 */
	public String getVolDate() {
		return volDate;
	}

	/**
	 * @param volDate the volDate to set
	 */
	public void setVolDate(String volDate) {
		this.volDate = volDate;
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
	 * @return the recStatus
	 */
	public String getRecStatus() {
		return recStatus;
	}

	/**
	 * @param recStatus the recStatus to set
	 */
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(volDetailNo);
		builder.append(", ");
		builder.append(volInfoNo);
		builder.append(", ");
		builder.append(volDate);
		builder.append(", ");
		builder.append(applyCount);
		builder.append(", ");
		builder.append(totalCount);
		builder.append(", ");
		builder.append(recStatus);
		return builder.toString();
	}

}
