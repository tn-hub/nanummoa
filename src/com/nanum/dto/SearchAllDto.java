/**
 * 
 */
package com.nanum.dto;

/**
 * <pre>
 * 통합검색
 * </pre>
 * 
 * @author 메타쓰리
 *
 */
public class SearchAllDto {

	/** 검색 구분 명*/
	private String dvisionName;
	
	/** 검색 구분 */
	private String divisionSub;
	
	/** 검색 키 */
	private int infoNo;
	
	/** 작성자 */
	private String writer;
	
	/** 제목 */
	private String title;
	
	/** 내용 */
	private String contents;
	
	
	
	public SearchAllDto() {}


	/**
	 * @param dvisionName
	 * @param divisionSub
	 * @param infoNo
	 * @param writer
	 * @param title
	 * @param contents
	 */
	public SearchAllDto(String dvisionName, String divisionSub, int infoNo, String writer, String title,
			String contents) {
		super();
		this.dvisionName = dvisionName;
		this.divisionSub = divisionSub;
		this.infoNo = infoNo;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}


	/**
	 * @return the dvisionName
	 */
	public String getDvisionName() {
		return dvisionName;
	}


	/**
	 * @param dvisionName the dvisionName to set
	 */
	public void setDvisionName(String dvisionName) {
		this.dvisionName = dvisionName;
	}


	/**
	 * @return the divisionSub
	 */
	public String getDivisionSub() {
		return divisionSub;
	}


	/**
	 * @param divisionSub the divisionSub to set
	 */
	public void setDivisionSub(String divisionSub) {
		this.divisionSub = divisionSub;
	}


	/**
	 * @return the infoNo
	 */
	public int getInfoNo() {
		return infoNo;
	}


	/**
	 * @param infoNo the infoNo to set
	 */
	public void setInfoNo(int infoNo) {
		this.infoNo = infoNo;
	}


	/**
	 * @return the writer
	 */
	public String getWriter() {
		return writer;
	}


	/**
	 * @param writer the writer to set
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(dvisionName);
		builder.append(", ");
		builder.append(divisionSub);
		builder.append(", ");
		builder.append(infoNo);
		builder.append(", ");
		builder.append(writer);
		builder.append(", ");
		builder.append(title);
		builder.append(", ");
		builder.append(contents);
		return builder.toString();
	}
}
