/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nanum.dto.CenterVolDto;
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
	public void centerVolList(String centerId,Connection conn,ArrayList<CenterVolDto> list) throws CommonException {
		String sql = "select \n" + 
				"vi.vol_info_no\n" + 
				", vi.v_title\n" + 
				", vi.start_date\n" + 
				", vi.end_date\n" + 
				", min(vd.vol_date) as vol_start\n" + 
				", max(vd.vol_date) as vol_end\n" + 
				", vd.rec_status\n" + 
				", ci.c_name\n" + 
				", vc.category_name\n" + 
				", round(vi.end_date - sysdate,0) as deadline\n" + 
				"from vol_info vi,vol_detail vd, center_member cm, center_info ci,vol_category vc\n" + 
				"where vi.vol_info_no = vd.vol_info_no \n" + 
				"and vi.c_id = cm.c_id \n" + 
				"and cm.c_id = ci.c_id\n" + 
				"and vi.category_no = vc.category_no\n" + 
				"and cm.c_id= ?\n" + 
				"group by vi.vol_info_no, vi.v_title, vi.start_date, vi.end_date, vd.rec_status,ci.c_name,vc.category_name";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				CenterVolDto dto =  new CenterVolDto();	
				
				dto.setVolInfoNo(rs.getInt("vol_info_no"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setVolTitle(rs.getString("v_title"));
				dto.setStartDate(rs.getDate("start_date"));
				dto.setEndDate(rs.getDate("end_date"));
				dto.setVolStart(rs.getDate("vol_start"));
				dto.setVolEnd(rs.getDate("vol_end"));
				dto.setCategoryName(rs.getString("category_name"));
				dto.setRecStatus(rs.getString("rec_status"));
				dto.setDeadline(rs.getInt("deadline"));
				
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
	 * 센터회원 봉사 목록(모집중)
	 * 
	 * @param conn
	 * @throws CommonException 
	 */
	public void recruitList(String centerId, Connection conn, ArrayList<CenterVolDto> list) throws CommonException {
		String sql = "select \n" + 
				"vi.vol_info_no\n" + 
				", vi.v_title\n" + 
				", vi.start_date\n" + 
				", vi.end_date\n" + 
				", min(vd.vol_date) as vol_start\n" + 
				", max(vd.vol_date) as vol_end\n" + 
				", vd.rec_status\n" + 
				", ci.c_name\n" + 
				", vc.category_name\n" + 
				", round(vi.end_date - sysdate,0) as deadline\n" + 
				"from vol_info vi,vol_detail vd, center_member cm, center_info ci,vol_category vc\n" + 
				"where vi.vol_info_no = vd.vol_info_no \n" + 
				"and vi.c_id = cm.c_id \n" + 
				"and cm.c_id = ci.c_id\n" + 
				"and vi.category_no = vc.category_no\n" + 
				"and cm.c_id= ? and rec_status in (0)\n"+ 
				"group by vi.vol_info_no, vi.v_title, vi.start_date, vi.end_date, vd.rec_status,ci.c_name,vc.category_name";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CenterVolDto dto =  new CenterVolDto();	
				
				dto.setVolInfoNo(rs.getInt("vol_info_no"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setVolTitle(rs.getString("v_title"));
				dto.setStartDate(rs.getDate("start_date"));
				dto.setEndDate(rs.getDate("end_date"));
				dto.setVolStart(rs.getDate("vol_start"));
				dto.setVolEnd(rs.getDate("vol_end"));
				dto.setCategoryName(rs.getString("category_name"));
				dto.setRecStatus(rs.getString("rec_status"));
				dto.setDeadline(rs.getInt("deadline"));
				
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
	 * 센터회원 봉사 목록(종료)
	 * 
	 * @param conn
	 * @throws CommonException 
	 */
	public void deadlineList(String centerId, Connection conn, ArrayList<CenterVolDto> list) throws CommonException {
		String sql = "select \n" + 
				"vi.vol_info_no\n" + 
				", vi.v_title\n" + 
				", vi.start_date\n" + 
				", vi.end_date\n" + 
				", min(vd.vol_date) as 봉사시작일\n" + 
				", max(vd.vol_date) as 봉사종료일\n" + 
				", min(vd.rec_status) as 모집중\n" + 
				", max(vd.rec_status) as 마감\n" + 
				", ci.c_name\n" + 
				", vc.category_name\n" + 
				", round(vi.end_date - sysdate,0) as deadline\n" + 
				"from vol_info vi,vol_detail vd, center_member cm, center_info ci,vol_category vc\n" + 
				"where vi.vol_info_no = vd.vol_info_no \n" + 
				"and vi.c_id = cm.c_id \n" + 
				"and cm.c_id = ci.c_id\n" + 
				"and vi.category_no = vc.category_no\n" + 
				"and cm.c_id= ?\n" + 
				"group by vi.vol_info_no, vi.v_title, vi.start_date, vi.end_date,ci.c_name,vc.category_name\n";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CenterVolDto dto =  new CenterVolDto();	
				
				dto.setVolInfoNo(rs.getInt("vol_info_no"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setVolTitle(rs.getString("v_title"));
				dto.setStartDate(rs.getDate("start_date"));
				dto.setEndDate(rs.getDate("end_date"));
				dto.setVolStart(rs.getDate("vol_start"));
				dto.setVolEnd(rs.getDate("vol_end"));
				dto.setCategoryName(rs.getString("category_name"));
				dto.setRecStatus(rs.getString("모집중"));
				dto.setDeadline(rs.getInt("deadline"));
				
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
	 * 센터 봉사활동 개수
	 * 
	 * @param centerId
	 * @param conn
	 * @param list
	 * @throws CommonException
	 */
	public void listIndex(String centerId, Connection conn, CenterVolDto voDto) throws CommonException {
		String sql = "select count(vi.vol_info_no) - sum(to_number(vd.rec_status)) as list_index from vol_info vi,vol_detail vd\n" + 
				"where vi.vol_info_no = vd.vol_info_no and\n" + 
				"vi.c_id = ?\n" + 
				"GROUP by vi.vol_info_no";
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				voDto.setListIndex(rs.getInt("list_index"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}
	
//	/**
//	 * 센터 봉사활동 개수(모집중)
//	 * 
//	 * @param centerId
//	 * @param conn
//	 * @param list
//	 * @throws CommonException
//	 */
//	public void recruitIndex(String centerId, Connection conn, CenterVolDto voDto) throws CommonException {
//		String sql = "select count(vi.vol_info_no) - sum(to_number(vd.rec_status)) from vol_info vi,vol_detail vd\n" + 
//				"where vi.vol_info_no = vd.vol_info_no and\n" + 
//				"vi.c_id = 'center01'\n" + 
//				"GROUP by vi.vol_info_no";
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, centerId);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				voDto.setListIndex(rs.getInt("list_index"));
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new CommonException();
//		} finally {
//			JdbcTemplate.close(rs);
//			JdbcTemplate.close(pstmt);
//		}
//	}
	
//	/**
//	 * 센터 봉사활동 개수(종료)
//	 * 
//	 * @param centerId
//	 * @param conn
//	 * @param list
//	 * @throws CommonException
//	 */
//	public void deadlineIndex(String centerId, Connection conn, CenterVolDto voDto) throws CommonException {
//		String sql = "select count(*) as list_index  from vol_info where c_id = ? rec_status not in(0)";
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, centerId);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				voDto.setListIndex(rs.getInt("list_index"));
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new CommonException();
//		} finally {
//			JdbcTemplate.close(rs);
//			JdbcTemplate.close(pstmt);
//		}
//	}
	
}
