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
import com.nanum.dto.VolCategoryDto;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;

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
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
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
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getGeneralId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
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
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, generalId);
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
	 * 봉사대상 조회
	 */
	public void selectServiceCategory(Connection conn, ArrayList<ServiceCategoryDto> list) throws CommonException {
		String sql = "select * from service_category";
		
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
				", to_char(i.start_time, 'HH24:MI')\r\n" + 
				", to_char(i.end_time, 'HH24:MI')\r\n" + 
				", a.vol_status\r\n "+
				"from vol_info i, vol_detail d, vol_apply_list a\r\n" + 
				"where i.vol_info_no = d.vol_info_no \r\n" + 
				"and d.vol_detail_no = a.vol_detail_no\r\n" + 
				"and a.g_id = ? \r\n" + 
				"order by 6 desc, 7";
		HashMap<String, Object> map = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, generalId);
			rs = pstmt.executeQuery();
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
				map.put("volStatus", rs.getString(10));
				
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
	
	/**
	 * 봉사 확인서 내역 조회
	 */
	public void searchConfirmationList(Connection conn, String generalId, ArrayList<HashMap<String, Object>> list) throws CommonException {
		String sql = "select i.vol_info_no, vc.vol_con_no, i.v_title, c.c_name, \r\n" + 
							"(select min(vol_date)\r\n" + 
								"from vol_detail\r\n" + 
								"where vol_info_no = i.vol_info_no) as 봉사시작일,\r\n" + 
							"(select max(vol_date)\r\n" + 
								"from vol_detail\r\n" + 
								"where vol_info_no = i.vol_info_no) as 봉사마감일, \r\n" + 
							"to_char(i.start_time, 'HH24:MI') as 시작시간, to_char(i.end_time, 'HH24:MI') as 마감시간 \r\n" + 
				"from vol_info i, vol_detail d, center_info c, vol_confirmation vc\r\n" + 
				"where i.vol_info_no = d.vol_info_no\r\n" + 
						"and i.c_id = c.c_id\r\n" + 
						"and i.c_id = vc.c_id\r\n" + 
						"and vc.vol_info_no = d.vol_info_no \r\n" + 
						"and vc.g_id = ?\r\n" + 
				"group by i.vol_info_no, vc.vol_con_no, i.v_title, c.c_name, i.start_time, i.end_time, vc.vol_date\r\n" + 
				"order by vc.vol_date desc";
		
		HashMap<String, Object> map = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, generalId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				map = new HashMap<String, Object>();
				map.put("volInfoNo", rs.getString(1));
				map.put("volConNo", rs.getString(2));
				map.put("volTitle", rs.getString(3));
				map.put("centerName", rs.getString(4));
				map.put("startDate", rs.getString(5));
				map.put("endDate", rs.getString(6));
				map.put("startTime", rs.getString(7));
				map.put("endTime", rs.getString(8));
				
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
	 * 봉사 확인서 다운 정보 출력
	 */
	public void searchConfirmation(Connection conn, HashMap<String, String> selectInfo, HashMap<String, String> map) throws CommonException {
		String sql = "select vc.vol_con_no, i.v_title, g.g_name, g.g_address, vc.contents, c.c_name, \r\n" + 
							"(select min(vol_date)\r\n" + 
									"from vol_detail\r\n" + 
									"where vol_info_no = ?) as 봉사시작일, \r\n" + 
							"(select max(vol_date)\r\n" + 
									"from vol_detail\r\n" + 
									"where vol_info_no = ?) as 봉사마감일,\r\n" + 
				"to_char(i.start_time, 'HH24:MI') as 시작시간,\r\n" + 
				"to_char(i.end_time, 'HH24:MI') as 마감시간,\r\n" + 
				"to_char(vc.vol_date, 'yyyy-mm-dd') as 발급일,\r\n" + 
							"(select count(*)\r\n" + 
								"from vol_detail d, vol_apply_list a\r\n" + 
								"where d.vol_detail_no = a.vol_detail_no \r\n" + 
										"and a.g_id = ? and d.vol_info_no = ? and a.vol_status = 2) as cnt\r\n" + 
				"from vol_info i, vol_detail d, center_info c, vol_confirmation vc, general_member g\r\n" + 
				"where i.vol_info_no = d.vol_info_no\r\n" + 
						"and i.c_id = c.c_id\r\n" + 
						"and i.c_id = vc.c_id\r\n" + 
						"and vc.vol_info_no = d.vol_info_no\r\n" + 
						"and vc.g_id = g.g_id\r\n" + 
						"and vc.vol_con_no = ?\r\n" + 
				"group by  vc.vol_con_no, g.g_name, g.g_address, vc.contents, i.v_title, c.c_name,\r\n" + 
							"i.start_time, i.end_time, vc.vol_date, vc.vol_date, i.v_title\r\n" + 
				"order by vc.vol_date desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selectInfo.get("volInfoNo"));
			pstmt.setString(2, selectInfo.get("volInfoNo"));
			pstmt.setString(3, selectInfo.get("generalId"));
			pstmt.setString(4, selectInfo.get("volInfoNo"));
			pstmt.setString(5, selectInfo.get("volConNo"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				map.put("volConNo", rs.getString(1));
				map.put("volTitle", rs.getString(2));
				map.put("generalName", rs.getString(3));
				map.put("generalAddress", rs.getString(4));
				map.put("contents", rs.getString(5));
				map.put("centerName", rs.getString(6));
				map.put("startDate", rs.getString(7));
				map.put("endDate", rs.getString(8));
				map.put("startTime", rs.getString(9));
				map.put("endTime", rs.getString(10));
				map.put("volDate", rs.getString(11));
				map.put("totalDate", rs.getString(12));
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
}
