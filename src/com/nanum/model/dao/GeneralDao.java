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
			conn = JdbcTemplate.getConnection();
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
			MessageEntity messageEntity = new MessageEntity("error", 2);
			throw new CommonException(messageEntity);
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
			conn = JdbcTemplate.getConnection();
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
			MessageEntity messageEntity = new MessageEntity("error", 2);
			throw new CommonException(messageEntity);
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
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, generalId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageEntity messageEntity = new MessageEntity("error", 2);
			throw new CommonException(messageEntity);
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
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println(dto.getGeneralId());
			System.out.println("localNo : " + dto.getLocalNo());
			pstmt.setString(1, dto.getGeneralId());
			pstmt.setString(2, dto.getGeneralPass());
			pstmt.setString(3, dto.getGeneralName());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getBirthday());
			pstmt.setString(6, dto.getGeneralZipCode());
			pstmt.setString(7, dto.getGeneralAdress());
			pstmt.setString(8, dto.getGeneralMobile());
			pstmt.setString(9, dto.getGeneralEmail());
			pstmt.setString(10, "1");
			pstmt.setString(11, "1");
			int rows = pstmt.executeUpdate();
			System.out.println("rows : " + rows);
			if (rows != 1) {
				throw new Exception();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageEntity messageEntity = new MessageEntity("error", 2);
			throw new CommonException(messageEntity);
		} finally {
			JdbcTemplate.close(pstmt);
		}
	}
}
