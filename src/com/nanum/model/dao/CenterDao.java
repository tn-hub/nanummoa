/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;

import com.nanum.dto.VolDetailDto;
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
	 * 봉사글등록
	 */
	public void addVolInfo(Connection conn, VolInfoDto dto) throws CommonException {
		String sql = "insert into vol_info values (vol_info_seq.nextval,?,?,?,null,to_date(?,'HH24:MI'),to_date(?,'HH24:MI'),?,?,?,?,?,?,?,?,?)";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCenterId());
			pstmt.setString(2, dto.getVolTitle());
			pstmt.setString(3, dto.getVolContents());
			System.out.println("dao에서 시간");
			System.out.println(dto.getStartTime());
			System.out.println(dto.getStartTime().getTime());
			System.out.println(new java.sql.Date(dto.getStartTime().getTime()));
			pstmt.setDate(4, new java.sql.Date(dto.getStartTime().getTime()));
			pstmt.setDate(5, new java.sql.Date(dto.getEndTime().getTime()));
			pstmt.setDate(6, new java.sql.Date(dto.getStartDate().getTime()));
			pstmt.setDate(7, new java.sql.Date(dto.getEndDate().getTime()));
			pstmt.setString(8, dto.getCategoryNo());
			pstmt.setString(9, dto.getLocalNo());
			pstmt.setString(10,dto.getVolType());
			pstmt.setString(11,dto.getVolPlace());
			pstmt.setString(12,dto.getLatitude());
			pstmt.setString(13,dto.getLongitude());
			pstmt.setString(14,dto.getVolSubject());
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
	
	public void addVolInfo(Connection conn, HashMap<String, Object> map) throws CommonException {
		String sql = "insert into vol_info values (vol_info_seq.nextval,?,?,?,sysdate,to_date(?,'HH24:MI'),to_date(?,'HH24:MI'),?,?,?,?,?,?,?,?,?)";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf( map.get("centerId")));
			pstmt.setString(2, (String) map.get("volTitle"));
			pstmt.setString(3, (String) map.get("volContents"));
			
			pstmt.setString(4, (String) map.get("startTime"));
			pstmt.setString(5, (String) map.get("endTime"));
			pstmt.setString(6, (String) map.get("startDate"));
			pstmt.setString(7, (String) map.get("endDate"));
			
			pstmt.setString(8, (String) map.get("categoryNo"));
			pstmt.setString(9, (String) map.get("localNo"));
			pstmt.setString(10, (String) map.get("volType"));
			pstmt.setString(11, (String) map.get("volPlace"));
			pstmt.setString(12, (String) map.get("latitude"));
			pstmt.setString(13, (String) map.get("longitude"));
			pstmt.setString(14, (String) map.get("volSubject"));
			
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
	 * 봉사 상세등록(날짜별)
	 */
	public void addVolDetail(Connection conn, VolDetailDto dto) throws CommonException {
		String sql = "insert into vol_detail values(vol_detail_seq.nextval,?,?,0,?,'0')";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getVolInfoNo());
			pstmt.setString(2, dto.getVolDate());
			pstmt.setInt(3, dto.getTotalCount());
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
	 * 봉사 현재시퀀스 가져오기
	 */
	public int getVolInfoNoCurrentSeq(Connection conn) throws CommonException {
		String sql = "SELECT vol_info_seq.nextval, vol_info_seq.CURRVAL FROM DUAL";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("시퀀스조회  : " + rs.getInt(1) + ", " + rs.getInt(2));
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return 0;
	}

	
}
