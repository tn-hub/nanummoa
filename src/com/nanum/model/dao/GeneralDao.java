/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.LocalDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;
import com.nanum.util.MessageEntity;

/**
 * 일반회원 Dao 클래스
 *
 */
public class GeneralDao {
	private static GeneralDao instance = new GeneralDao();

	public GeneralDao() {}

	public static GeneralDao getInstance() {
		return instance;
	}
	
	/**
	 * 지역 검색
	 */
	public void selectLocal(Connection conn, ArrayList<LocalDto> list) throws CommonException {
		String sql = "select * from local";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			LocalDto dto = null;
			while(rs.next()) {
				dto = new LocalDto();
				dto.setLocalNo(rs.getString("local_no"));
				dto.setLocalName(rs.getString("local_name"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}
	
	/**
	 * 봉사분야 검색
	 */
	public void selectVolCategory(Connection conn, ArrayList<VolCategoryDto> list) throws CommonException {
		String sql = "select * from vol_category";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			VolCategoryDto dto = null;
			while(rs.next()) {
				dto = new VolCategoryDto();
				dto.setCategoryNo(rs.getString("category_no"));
				dto.setCategoryName(rs.getString("category_name"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}
	
	/**
	 * 아이디 중복 체크
	 */
	public boolean isGeneralId(Connection conn, String generalId) throws CommonException {
		String sql = "select 1 from general_member where g_id = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, generalId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return false;
	}
	
	/**
	 * 일반회원 회원가입
	 */
	public void insertGeneralMember(Connection conn, GeneralMemberDto dto) throws CommonException {
		String sql = "insert into general_member values(?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println(dto.getGeneralId());
			System.out.println("localNo : " + dto.getLocalNo());
			pstmt.setString(1, dto.getGeneralId());
			pstmt.setString(2, dto.getGeneralPass());
			pstmt.setString(3, dto.getGeneralName());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getBirthday());
			pstmt.setString(6, dto.getGeneralZipCode());
			pstmt.setString(7, dto.getGeneralAddress());
			pstmt.setString(8, dto.getGeneralMobile());
			pstmt.setString(9, dto.getGeneralEmail());
			pstmt.setString(10, dto.getCategoryNo());
			pstmt.setString(11, dto.getLocalNo());
			int rows = pstmt.executeUpdate();
			System.out.println("rows : " + rows);
			if (rows != 1) {
				throw new Exception();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(pstmt);
		}
	}
	
	/**
	 * 일반회원 정보 조회
	 * @param conn
	 * @param dto GeneralMemberDto
	 * @throws CommonException
	 */
	public void selectGeneralInfo(Connection conn, GeneralMemberDto dto) throws CommonException {
		String sql = "select * from general_member where g_id = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getGeneralId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("쿼리 들어옴");
				dto.setGeneralName(rs.getString("g_name"));
				dto.setGender(rs.getString("gender"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setGeneralZipCode(rs.getString("g_zipcode"));
				dto.setGeneralAddress(rs.getString("g_address"));
				dto.setGeneralMobile(rs.getString("g_mobile"));
				dto.setGeneralEmail(rs.getString("g_email"));
				dto.setCategoryNo(rs.getString("category_no"));
				dto.setLocalNo(rs.getString("local_no"));
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}
	
	/**
	 * 일반회원 회원 정보 수정
	 */
	public void updateGeneralMember(Connection conn, GeneralMemberDto dto) throws CommonException {
		String sql = "update general_member "
				+ "set g_pass = ?, g_name = ?, gender = ?, birthday = ?, g_zipcode = ?, g_address = ?, "
				+ "g_mobile = ?, g_email = ?, category_no = ?, local_no = ? "
				+ "where g_id = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getGeneralPass());
			pstmt.setString(2, dto.getGeneralName());
			pstmt.setString(3, dto.getGender());
			pstmt.setString(4, dto.getBirthday());
			pstmt.setString(5, dto.getGeneralZipCode());
			pstmt.setString(6, dto.getGeneralAddress());
			pstmt.setString(7, dto.getGeneralMobile());
			pstmt.setString(8, dto.getGeneralEmail());
			pstmt.setString(9, dto.getCategoryNo());
			pstmt.setString(10, dto.getLocalNo());
			pstmt.setString(11, dto.getGeneralId());
			int rows = pstmt.executeUpdate();
			System.out.println("rows : " + rows);
			if (rows != 1) {
				throw new Exception();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(pstmt);
		}
	}
	
	/**
	 * 일반회원 회원탈퇴
	 */
	public void deleteGeneralMember(Connection conn, String generalId) throws CommonException {
		String sql = "delete general_member where g_id = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, generalId);
			int rows = pstmt.executeUpdate();
			System.out.println("rows : " + rows);
			if (rows != 1) {
				throw new Exception();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(pstmt);
		}
	}
}
