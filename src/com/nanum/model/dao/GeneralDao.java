/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.LocalDto;
import com.nanum.dto.ServiceCategoryDto;
import com.nanum.dto.VolApplyListDto;
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

	/**
	 * 봉사대상 조회
	 */
	public void selectServiceCategory(Connection conn, ArrayList<ServiceCategoryDto> list) throws CommonException {
		String sql = "select * from service_category";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ServiceCategoryDto dto = null;
			while(rs.next()) {
				dto = new ServiceCategoryDto();
				dto.setServiceNo(rs.getString(1));
				dto.setServiceName(rs.getString(2));
				
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
	 * 봉사신청내역 조회
	 */
	public void searchVolApplyList(Connection conn, String generalId, ArrayList<HashMap<String, Object>> list) throws CommonException {
		String sql = "select \r\n" + 
				"i.vol_info_no\r\n" + 
				", d.vol_detail_no\r\n" + 
				", a.vol_apply_no\r\n" + 
				", i.v_title\r\n" + 
				", d.rec_status\r\n" + 
				", a.apply_date\r\n" + 
				", d.vol_date \r\n" + 
				",to_char(i.start_time, 'HH24:MI')\r\n" + 
				",to_char(i.end_time, 'HH24:MI')\r\n" + 
				"from vol_info i, vol_detail d, vol_apply_list a\r\n" + 
				"where i.vol_info_no = d.vol_info_no \r\n" + 
				"and d.vol_detail_no = a.vol_detail_no\r\n" + 
				"and a.g_id = ? \r\n" + 
				"order by 6, 7";
		System.out.println(sql);
		HashMap<String, Object> map = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, generalId);
			rs = pstmt.executeQuery();
			System.out.println("[dao] generalId : "+generalId);
			while(rs.next()) {
				map = new HashMap<String, Object>();
				map.put("volInfoNo", rs.getString(1));
				map.put("volDetailNo", rs.getString(2));
				map.put("volApplyNo", rs.getString(3));
				map.put("volTitle", rs.getString(4));
				map.put("recStatus", rs.getString(5));
				map.put("applyDate", rs.getDate(6));
				map.put("volDate", rs.getDate(7));
				map.put("startTime", rs.getString(8));
				map.put("endTime", rs.getString(9));
				
				list.add(map);
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
	 * 봉사신청 취소
	 */
	public void cancelVol(Connection conn, String generalId, String volApplyNo) throws CommonException {
		String sql = "delete from vol_apply_list where g_id =? and vol_apply_no = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, generalId);
			pstmt.setString(2, volApplyNo);
			int rows = pstmt.executeUpdate();
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
	 * 봉사신청
	 */
	public void enrollVol(Connection conn, String generalId, String volDetailNo) throws CommonException {
		String sql = "insert into vol_apply_list values(vol_apply_seq.nextval, sysdate, ?, ?, 0)";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, generalId);
			pstmt.setString(2, volDetailNo);
			int rows = pstmt.executeUpdate();
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
	 * 봉사상세조회(통합)
	 */
	public void getVolInfo(Connection conn, String volInfoNo, HashMap<String, Object> map) throws CommonException {
		String sql = "select * from vol_apply_list where g_id = ?";
		System.out.println(sql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, volInfoNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				map.put("", "");
				map.put("", "");
				map.put("", "");
				map.put("", "");
				map.put("", "");
				map.put("", "");
				map.put("", "");
				map.put("", "");
				map.put("", "");
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
	 * 봉사상세조회(날짜별) - 봉사신청화면
	 * @param volInfoNo2 
	 */
	public void volInfoByDate(Connection conn, String generalId, String volInfoNo, ArrayList<HashMap<String, Object>> list) throws CommonException {
		String sql = "select \r\n" + 
				"i.vol_info_no as 글번호,\r\n" + 
				"d.vol_detail_no as 상세번호,\r\n" + 
				"i.v_title as 제목,\r\n" + 
				"d.vol_date as 봉사실행일,\r\n" + 
				"d.rec_status as 모집상태,\r\n" + 
				"d.apply_count as 신청인원,\r\n" + 
				"d.total_count as 마감인원\r\n" + 
				"from vol_info i, vol_detail d\r\n" + 
				"where i.vol_info_no = d.vol_info_no \r\n" + 
				"and i.vol_info_no = ? \r\n" + 
				"and d.vol_detail_no not in (\r\n" + 
				"select \r\n" + 
				"d.vol_detail_no\r\n" + 
				"from vol_info i, vol_detail d, vol_apply_list a\r\n" + 
				"where i.vol_info_no = d.vol_info_no \r\n" + 
				"and d.vol_detail_no = a.vol_detail_no\r\n" + 
				"and a.g_id = ? )\r\n" + 
				"group by d.vol_detail_no, i.vol_info_no, i.v_title, d.vol_date, d.rec_status, \r\n" + 
				"d.apply_count, d.total_count\r\n" + 
				"order by 4";
		
		System.out.println(sql);
		HashMap<String, Object> map = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, volInfoNo);
			pstmt.setString(2, generalId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				map = new HashMap<String, Object>();
				map.put("volInfoNo", rs.getInt(1));
				map.put("volDetailNo", rs.getInt(2));
				map.put("volTitle", rs.getString(3));
				map.put("volDate", rs.getString(4));
				map.put("recStatus", rs.getString(5));
				map.put("applyCount", rs.getInt(6));
				map.put("totalCount", rs.getInt(7));
				
				list.add(map);
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
	 * 신청인원수 변경
	 * @param conn 
	 */
	public void updateApplyCount(Connection conn, String volDetailNo, int i) throws CommonException {
		String sql = "update vol_detail \r\n" + 
				"set apply_count = apply_count + ? \r\n" + 
				"where vol_detail_no = ?";
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i);
			pstmt.setString(2, volDetailNo);
			
			int rows = pstmt.executeUpdate();
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
