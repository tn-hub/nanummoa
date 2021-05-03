/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nanum.dto.AdminMemberDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;
import com.nanum.util.MessageEntity;


/**
 * 공통 Dao 클래스
 *
 */
public class CommonDao {
	private static CommonDao instance = new CommonDao();

	public CommonDao() {
	}

	public static CommonDao getInstance() {
		return instance;
	}
	/**
	 * 일반회원 로그인
	 * @param conn
	 * @param dto
	 * @throws CommonException 
	 */
	public void login(Connection conn, GeneralMemberDto dto) throws CommonException {
		String sql = "select * from GENERAL_MEMBER where g_id = ? and g_pass = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getGeneralId());
			stmt.setString(2, dto.getGeneralPass());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				dto.setGeneralId(rs.getString("g_id"));
				dto.setGeneralPass(rs.getString("g_pass"));
				dto.setGeneralName(rs.getString("g_name"));
				dto.setGender(rs.getString("gender"));
				dto.setBirthday(rs.getDate("birthday")); 
				dto.setGeneralZipCode(rs.getString("g_zipcode"));
				dto.setGeneralAddress(rs.getString("g_address"));
				dto.setGeneralMobile(rs.getString("g_mobile"));
				dto.setGeneralEmail(rs.getString("g_email"));
				dto.setCategoryNo(rs.getString("category_no"));
				dto.setLocalNo(rs.getString("local_no"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
//			MessageEntity message = new MessageEntity("error", 2);
//			message.setLinkTitle("로그인");
//			message.setUrl("/exercise/exe02/teacher/login.html");
			
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
	/**
	 * 센터회원 로그인
	 * 
	 * @param conn
	 * @param dto
	 * @throws CommonException 
	 */
	public void login(Connection conn, CenterMemberDto dto) throws CommonException {
		String sql = "select * from GENERAL_MEMBER where g_id = ? and g_pass = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getCenterId());
			stmt.setString(2, dto.getCenterPass());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				dto.setCenterId(rs.getString("c_id"));
				dto.setCenterPass(rs.getString("c_pass"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setCenterMobile(rs.getString("c_mobile"));
				dto.setCenterEmail(rs.getString("c_email"));
				dto.setAppStatus(rs.getString("app_status"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
//			MessageEntity message = new MessageEntity("error", 2);
//			message.setLinkTitle("로그인");
//			message.setUrl("/exercise/exe02/teacher/login.html");
			
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 관리자 로그인
	 * 
	 * @param conn
	 * @param dto
	 * @throws CommonException 
	 */
	public void login(Connection conn, AdminMemberDto dto) throws CommonException {
		String sql = "select * from ADMIN_MEMBER where a_id = ? and a_pass = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getAdminId());
			stmt.setString(2, dto.getAdminPass());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				dto.setAdminId(rs.getString("a_id"));
				dto.setAdminPass(rs.getString("a_pass"));
				dto.setAdminName(rs.getString("a_name"));
				dto.setAdminMobile(rs.getString("a_mobile"));
				dto.setAdminEmail(rs.getString("a_email"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
//			MessageEntity message = new MessageEntity("error", 2);
//			message.setLinkTitle("로그인");
//			message.setUrl("/exercise/exe02/teacher/login.html");
			
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
}

