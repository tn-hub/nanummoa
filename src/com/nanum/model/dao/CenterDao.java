/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nanum.dto.CenterInfoDto;
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
			MessageEntity messageEntity = new MessageEntity("error", 2);
			throw new CommonException(messageEntity);
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
	 * 봉사활동 신청승인(봉사 상태 변경)
	 * 
	 * @param conn
	 * @param checkDate
	 * @throws Exception 
	 */
	public void applyGeneral(Connection conn, String checkDate) throws Exception {
		String sql = "update vol_apply_list set vol_status = 1 where vol_apply_no = ?"; 
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, Integer.parseInt(checkDate));

			int rows = stmt.executeUpdate();
			
			if (rows == 0) {
				throw new Exception();
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}
	
	/**
	 * 봉사활동 승인취소
	 * 
	 * @param conn
	 * @param volApplyNo
	 * @throws Exception 
	 */
	public void closeApply(Connection conn, int volApplyNo) throws Exception {
		String sql = "update vol_apply_list set vol_status = 0 where vol_apply_no = ?"; 
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, volApplyNo);

			int rows = stmt.executeUpdate();
			
			if (rows == 0) {
				throw new Exception();
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(stmt);
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
	public void insertCenterInfo(Connection conn, CenterInfoDto dto) throws CommonException {
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
	
	/**
	 * 센터회원 정보 조회
	 * @param conn
	 * @param dto CenterMemberDto
	 * @throws CommonException
	 */
	public void selectCenterMemberInfo(Connection conn, CenterMemberDto dto) throws CommonException {
		String sql = "select * from center_member where c_id = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCenterId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setCenterName(rs.getString("c_name"));
				dto.setCenterMobile(rs.getString("c_mobile"));
				dto.setCenterEmail(rs.getString("c_email"));
				dto.setAppStatus(rs.getString("app_status"));
				dto.setEntryDate(rs.getString("entry_date"));
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
	 * 센터 정보 조회
	 * @param conn
	 * @param dto CenterMemberDto
	 * @throws CommonException
	 */
	public void selectCenterInfo(Connection conn, CenterInfoDto dto) throws CommonException {
		String sql = "select * from center_info where c_id = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCenterId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setCenterName(rs.getString("c_name"));
				dto.setCenterEntryDate(rs.getString("c_entry_date"));
				dto.setCenterZipCode(rs.getString("c_zipcode"));
				dto.setCenterAddress(rs.getString("c_address"));
				dto.setRegisterCode(rs.getString("register_code"));
				dto.setService(rs.getString("service_subject"));
				dto.setCeoName(rs.getString("ceo_name"));
				dto.setCeoMobile(rs.getString("ceo_mobile"));
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
	 * 센터회원 회원 정보 수정
	 */
	public void updateCenterMember(Connection conn, CenterMemberDto dto) throws CommonException {
		String sql = "update center_member "
				+ "set c_pass = ?, c_name = ?, c_mobile = ?, c_email = ? "
				+ "where c_id = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCenterPass());
			pstmt.setString(2, dto.getCenterName());
			pstmt.setString(3, dto.getCenterMobile());
			pstmt.setString(4, dto.getCenterEmail());
			pstmt.setString(5, dto.getCenterId());
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
	 * 센터 정보 수정
	 */
	public void updateCenter(Connection conn, CenterInfoDto dto) throws CommonException {
		String sql = "update center_info "
				+ "set  c_entry_date = ?, c_zipcode = ?, c_address = ?, register_code = ?, "
				+ "service_subject = ?, ceo_name = ?, ceo_mobile = ?"
				+ "where c_id = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCenterEntryDate());
			pstmt.setString(2, dto.getCenterZipCode());
			pstmt.setString(3, dto.getCenterAddress());
			pstmt.setString(4, dto.getRegisterCode());
			pstmt.setString(5, dto.getService());
			pstmt.setString(6, dto.getCeoName());
			pstmt.setString(7, dto.getCeoMobile());
			pstmt.setString(8, dto.getCenterId());
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
	 * 센터회원 회원탈퇴
	 */
	public void deleteCenterMember(Connection conn, String centerId) throws CommonException {
		String sql = "delete center_member where c_id = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
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
	 * 센터정보 삭제
	 */
	public void deleteCenter(Connection conn, String centerId) throws CommonException {
		String sql = "delete center_info where c_id = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
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
