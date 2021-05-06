/**
 * 
 */
package com.nanum.dto;

/**
 * <pre>
 * 봉사대상 
 * 
 * </pre>
 * @author 메타쓰리
 *
 */
public class ServiceCategoryDto {
	/** 지역번호 */
	private String serviceNo;

	/** 지역이름 */
	private String serviceName;

	/**
	 * 기본생성자
	 */
	public ServiceCategoryDto() {
	}

	/**
	 * 전체생성자
	 * @param serviceNo
	 * @param serviceName
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
