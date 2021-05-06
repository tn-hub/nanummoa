/**
 * 
 */
package com.nanum.dto;

/**
 * 봉사 대상
 *
 */
public class ServiceCategoryDto {
	/** 봉사 대상 카테고리 번호*/
	private String serviceNo;
	
	/** 봉사 대상 이름*/
	private String serviceName;
	
	/** 기본 생성자*/
	public ServiceCategoryDto() {}

	/**
	 * 전체 데이터 초기화 생성자
	 * @param serviceNo 카테고리 번호
	 * @param serviceName 봉사대상이름
	 */
	public ServiceCategoryDto(String serviceNo, String serviceName) {
		this.serviceNo = serviceNo;
		this.serviceName = serviceName;
	}

	/**
	 * @return the serviceNo
	 */
	public String getServiceNo() {
		return serviceNo;
	}

	/**
	 * @param serviceNo the serviceNo to set
	 */
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(serviceNo);
		builder.append(", ");
		builder.append(serviceName);
		return builder.toString();
	}

}
