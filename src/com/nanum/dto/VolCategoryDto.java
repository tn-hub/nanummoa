/**
 * 
 */
package com.nanum.dto;

/**
 * <pre>
 * 봉사분야
 * 
 * </pre>
 * @author 메타쓰리
 *
 */
public class VolCategoryDto {
	/** 봉사분야 번호 */
	private String categoryNo;

	/** 봉사분야 이름 */
	private String categoryName;

	/**
	 * 기본 생성자
	 */
	public VolCategoryDto() {
	}

	/**
	 * 전체 생성자
	 * 
	 * @param categoryNo   봉사분야 번호
	 * @param categoryName 봉사분야 이름
	 */
	public VolCategoryDto(String categoryNo, String categoryName) {
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(categoryNo);
		builder.append(", ");
		builder.append(categoryName);
		return builder.toString();
	}

}
