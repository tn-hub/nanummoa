/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.nanum.dto.AdminMemberDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.LocalDto;
import com.nanum.dto.VolBlockDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;


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
				dto.setBirthday(rs.getString("BIRTHDAY"));
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
		String sql = "select * from center_MEMBER where c_id = ? and c_pass = ?";
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
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 자원봉사 목록 조회(메인)
	 * @param conn
	 * @param volList
	 */
	public void searchVol(Connection conn, ArrayList<VolBlockDto> volList) throws CommonException {
		String sql = "select i.vol_info_no as 글번호, i.v_title as 제목, i.category_no as 카테고리번호, i.local_no as 지역번호, "
				+ "to_char(i.start_date,'yyyy-mm-dd') as 모집시작일, to_char(i.end_date,'yyyy-mm-dd') as 모집마감일,"
				+ " min(d.vol_date) as 봉사시작일, max(d.vol_date) as 봉사종료일  " 
				+ "from vol_info i, vol_detail d " 
				+ "where i.vol_info_no = d.vol_info_no and d.rec_status = 0 "
				+ "group by i.vol_info_no, i.v_title, i.category_no, i.local_no, i.start_date, i.end_date "
				+ "order by 6";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				VolBlockDto dto = new VolBlockDto();
				dto.setVolInfoNo(rs.getInt(1));
				dto.setVolTitle(rs.getString(2));
				dto.setCategoryNo(rs.getString(3));
				dto.setLocalNo(rs.getString(4));
				dto.setStartDate(rs.getString(5));
				dto.setEndDate(rs.getString(6));
				dto.setStartVolDate(rs.getString(7));
				dto.setEndVolDate(rs.getString(8));
				volList.add(dto);
			} 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 봉사카테고리 목록 조회(메인)
	 * @param conn
	 * @param categoryMap
	 */
	public void searchVolCategory(Connection conn, HashMap<String, VolCategoryDto> categoryMap) throws CommonException {
		String sql = "select * from vol_category";
	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				VolCategoryDto dto = new VolCategoryDto();
				dto.setCategoryNo(rs.getString(1));
				dto.setCategoryName(rs.getString(2));
				
				categoryMap.put(rs.getString(1), dto);
			} 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
		
	}

	/**
	 * 지역 목록 조회(메인)
	 * @param conn
	 * @param localMap
	 * @throws CommonException
	 */
	public void searchLocal(Connection conn, HashMap<String, LocalDto> localMap) throws CommonException {
		String sql = "select * from local";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				LocalDto dto = new LocalDto();
				dto.setLocalNo(rs.getString(1));
				dto.setLocalName(rs.getString(2));
				
				localMap.put(rs.getString(1), dto);
			} 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
		
	}
	
}

