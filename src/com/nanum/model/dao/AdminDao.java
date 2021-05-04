/**
 * 
 */
package com.nanum.model.dao;

/**
 * 관리자 Dao 클래스
 *
 */
public class AdminDao {
	private static AdminDao instance = new AdminDao();
	
	public AdminDao() {}
	
	public static AdminDao getInstance() {
		return instance;
	}
	
	
}
