/**
 * 
 */
package com.nanum.dto;

/**
 * <pre>
 * 문의하기 답글
 * </pre>
 * 
 * @author 메타쓰리
 *
 */
public class QnAReplyDto {
	/** 답글번호 */
	private int replyNo;
	
	/** 게시글 번호 */
	private int qnaNo;

	/** 관리자아이디 */
	private String adminId;

	/** 내용 */
	private String replyContents;

	/** 작성일 */
	private String replyWriteDate;

	/**
	 * 기본생성자
	 */
	public QnAReplyDto() {
	}

	/**
	 * @param replyNo        답글번호
	 * @param qnaNo			  게시글 번호
	 * @param adminId        관리자아이디
	 * @param replyContents  내용
	 * @param replyWriteDate 작성일
	 */
	public QnAReplyDto(int replyNo, int qnaNo, String adminId, String replyContents, String replyWriteDate) {
		super();
		this.replyNo = replyNo;
		this.qnaNo = qnaNo;
		this.adminId = adminId;
		this.replyContents = replyContents;
		this.replyWriteDate = replyWriteDate;
	}

	/**
	 * @return the replyNo
	 */
	public int getReplyNo() {
		return replyNo;
	}

	/**
	 * @param replyNo the replyNo to set
	 */
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
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
	 * @return the replyContents
	 */
	public String getReplyContents() {
		return replyContents;
	}

	/**
	 * @param replyContents the replyContents to set
	 */
	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
	}

	/**
	 * @return the replyWriteDate
	 */
	public String getReplyWriteDate() {
		return replyWriteDate;
	}

	/**
	 * @param replyWriteDate the replyWriteDate to set
	 */
	public void setReplyWriteDate(String replyWriteDate) {
		this.replyWriteDate = replyWriteDate;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(replyNo);
		builder.append(", ");
		builder.append(qnaNo);
		builder.append(", ");
		builder.append(adminId);
		builder.append(", ");
		builder.append(replyContents);
		builder.append(", ");
		builder.append(replyWriteDate);
		return builder.toString();
	}

	

}
