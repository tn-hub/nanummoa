/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nanum.dto.CenterInfo;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.VolInfoDto;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;
import com.nanum.util.MessageEntity;

/**
 * 센터회원 Dao 클래스
 *
 */
public class CenterDao {
	private static CenterDao instance = new CenterDao();

	public CenterDao() {}

	public static CenterDao getInstance() {
		return instance;
	}
	
	/**
	 * 아이디 중복 체크
	 */
	public boolean isCenterId(Connection conn, String centerId) throws CommonException {
		String sql = "select 1 from center_member where c_id = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
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
	 * 센터회원 봉사 목록
	 * 
	 * @param conn
	 * @throws CommonException 
	 */
	public void centerVolList(String centerId,Connection conn,ArrayList<VolInfoDto> list) throws CommonException {
		String sql = "select * from vol_info where c_id=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				VolInfoDto dto =  new VolInfoDto();;	
				
				dto.setVolInfoNo(rs.getInt("vol_info_no"));
				dto.setCenterId(rs.getString("c_id"));
				dto.setVolTitle(rs.getString("v_title"));
				dto.setVolContents(rs.getString("v_content"));
				dto.setVolWriteDate(rs.getDate("vol_write_date"));
				dto.setStartTime(rs.getDate("start_time"));
				dto.setEndTime(rs.getDate("end_time"));
				dto.setStartDate(rs.getDate("start_date"));
				dto.setEndDate(rs.getDate("end_date"));
				dto.setCategoryNo(rs.getString("category_no"));
				dto.setLocalNo(rs.getString("local_no"));
				dto.setVolSubject(rs.getString("v_subject"));
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
	 * 센터회원 회원가입
	 */
	public void insertCenterMember(Connection conn, CenterMemberDto dto) throws CommonException {
		String sql = "insert into center_member (c_id, c_pass, c_name, c_mobile, c_email, app_status) values(?,?,?,?,?,?)";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCenterId());
			pstmt.setString(2, dto.getCenterPass());
			pstmt.setString(3, dto.getCenterName());
			pstmt.setString(4, dto.getCenterMobile());
			pstmt.setString(5, dto.getCenterEmail());
			pstmt.setString(6, dto.getAppStatus());
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
	 * 센터정보 등록
	 */
	public void insertCenterInfo(Connection conn, CenterInfo dto) throws CommonException {
		String sql = "insert into center_info values(?,?,?,?,?,?,?,?,?)";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCenterId());
			pstmt.setString(2, dto.getCenterName());
			pstmt.setString(3, dto.getCenterEntryDate());
			pstmt.setString(4, dto.getCenterZipCode());
			pstmt.setString(5, dto.getCenterAddress());
			pstmt.setString(6, dto.getRegisterCode());
			pstmt.setString(7, dto.getService());
			pstmt.setString(8, dto.getCeoName());
			pstmt.setString(9, dto.getCeoMobile());
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
