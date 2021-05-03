/**
 * 
 */
package com.nanum.dto;

import java.sql.Date;

/**
 * <pre>
 * 문의하기 게시판
 * 
 * </pre>
 * 
 * @author 메타쓰리
 *
 */
public class QnADto {
	/** 글번호 */
	private int qnaNo;

	/** 일반회원아이디 */
	private String generalId;

	/** 센터회원아이디 */
	private String centerId;

	/** 제목 */
	private String qnaTitle;

	/** 내용 */
	private String qnaContents;

	/** 작성일 */
	private Date qnaWriteDate;

	/**
	 * 기본생성자
	 */
	public QnADto() {
	}

	/**
	 * 전체 생성자
	 * 
	 * @param qnaNo        글번호
	 * @param generalId    일반회원아이디
	 * @param centerId     센터회원아이디
	 * @param qnaTitle     제목
	 * @param qnaContents  내용
	 * @param qnaWriteDate 작성일
	 */
	public QnADto(int qnaNo, String generalId, String centerId, String qnaTitle, String qnaContents,
			Date qnaWriteDate) {
		this.qnaNo = qnaNo;
		this.generalId = generalId;
		this.centerId = centerId;
		this.qnaTitle = qnaTitle;
		this.qnaContents = qnaContents;
		this.qnaWriteDate = qnaWriteDate;
	}

	/**
	 * @return the qnaNo
	 */
	public int getQnaNo() {
		return qnaNo;
	}

	/**
	 * @param qnaNo the qnaNo to set
	 */
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
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
	 * @return the qnaTitle
	 */
	public String getQnaTitle() {
		return qnaTitle;
	}

	/**
	 * @param qnaTitle the qnaTitle to set
	 */
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	/**
	 * @return the qnaContents
	 */
	public String getQnaContents() {
		return qnaContents;
	}

	/**
	 * @param qnaContents the qnaContents to set
	 */
	public void setQnaContents(String qnaContents) {
		this.qnaContents = qnaContents;
	}

	/**
	 * @return the qnaWriteDate
	 */
	public Date getQnaWriteDate() {
		return qnaWriteDate;
	}

	/**
	 * @param qnaWriteDate the qnaWriteDate to set
	 */
	public void setQnaWriteDate(Date qnaWriteDate) {
		this.qnaWriteDate = qnaWriteDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(qnaNo);
		builder.append(", ");
		builder.append(generalId);
		builder.append(", ");
		builder.append(centerId);
		builder.append(", ");
		builder.append(qnaTitle);
		builder.append(", ");
		builder.append(qnaContents);
		builder.append(", ");
		builder.append(qnaWriteDate);
		return builder.toString();
	}

}
