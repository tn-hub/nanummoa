/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nanum.dto.CenterVolDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.VolApplyListDto;
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

	public CenterDao() {
	}

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

			if (rs.next()) {
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
	public void centerVolList(String centerId, Connection conn, ArrayList<CenterVolDto> list) throws CommonException {
		String sql = "select  \n" + "vi.vol_info_no\n" + ", vi.v_title \n" + ", vi.start_date\n" + ", vi.end_date\n"
				+ ", min(vd.vol_date) as 봉사시작일\n" + ", max(vd.vol_date) as 봉사종료일\n" + ", min(vd.rec_status) as 모집중\n"
				+ ", max(vd.rec_status) as 마감\n" + ", ci.c_name\n" + ", vc.category_name\n"
				+ ", round(vi.end_date - sysdate,0) as deadline\n"
				+ "from vol_info vi,vol_detail vd, center_member cm, center_info ci,vol_category vc\n"
				+ "where vi.vol_info_no = vd.vol_info_no\n" + "and vi.c_id = cm.c_id\n" + "and cm.c_id = ci.c_id\n"
				+ "and vi.category_no = vc.category_no\n" + "and cm.c_id= ?\n"
				+ "group by vi.vol_info_no, vi.v_title, vi.start_date, vi.end_date,ci.c_name,vc.category_name order by end_date desc";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CenterVolDto dto = new CenterVolDto();

				dto.setVolInfoNo(rs.getInt("vol_info_no"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setVolTitle(rs.getString("v_title"));
				dto.setStartDate(rs.getDate("start_date"));
				dto.setEndDate(rs.getDate("end_date"));
				dto.setVolStart(rs.getDate("봉사시작일"));
				dto.setVolEnd(rs.getDate("봉사종료일"));
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
	 * 센터회원 봉사 목록(모집중)
	 * 
	 * @param conn
	 * @throws CommonException
	 */
	public void recruitList(String centerId, Connection conn, ArrayList<CenterVolDto> list) throws CommonException {
		String sql = "select  \n" + "vi.vol_info_no\n" + ", vi.v_title \n" + ", vi.start_date\n" + ", vi.end_date\n"
				+ ", min(vd.vol_date) as 봉사시작일\n" + ", max(vd.vol_date) as 봉사종료일\n" + ", min(vd.rec_status) as 모집중\n"
				+ ", max(vd.rec_status) as 마감\n" + ", ci.c_name\n" + ", vc.category_name\n"
				+ ", round(vi.end_date - sysdate,0) as deadline\n"
				+ "from vol_info vi,vol_detail vd, center_member cm, center_info ci,vol_category vc\n"
				+ "where vi.vol_info_no = vd.vol_info_no\n" + "and vi.c_id = cm.c_id\n" + "and cm.c_id = ci.c_id\n"
				+ "and vi.category_no = vc.category_no\n" + "and cm.c_id= ?\n"
				+ "group by vi.vol_info_no, vi.v_title, vi.start_date, vi.end_date,ci.c_name,vc.category_name\n"
				+ "having min(vd.rec_status) = 0 order by end_date desc";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CenterVolDto dto = new CenterVolDto();

				dto.setVolInfoNo(rs.getInt("vol_info_no"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setVolTitle(rs.getString("v_title"));
				dto.setStartDate(rs.getDate("start_date"));
				dto.setEndDate(rs.getDate("end_date"));
				dto.setVolStart(rs.getDate("봉사시작일"));
				dto.setVolEnd(rs.getDate("봉사종료일"));
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
	 * 센터회원 봉사 목록(종료)
	 * 
	 * @param conn
	 * @throws CommonException
	 */
	public void deadlineList(String centerId, Connection conn, ArrayList<CenterVolDto> list) throws CommonException {
		String sql = "select  \n" + "vi.vol_info_no\n" + ", vi.v_title \n" + ", vi.start_date\n" + ", vi.end_date\n"
				+ ", min(vd.vol_date) as 봉사시작일\n" + ", max(vd.vol_date) as 봉사종료일\n" + ", min(vd.rec_status) as 모집중\n"
				+ ", max(vd.rec_status) as 마감\n" + ", ci.c_name\n" + ", vc.category_name\n"
				+ ", round(vi.end_date - sysdate,0) as deadline\n"
				+ "from vol_info vi,vol_detail vd, center_member cm, center_info ci,vol_category vc\n"
				+ "where vi.vol_info_no = vd.vol_info_no\n" + "and vi.c_id = cm.c_id\n" + "and cm.c_id = ci.c_id\n"
				+ "and vi.category_no = vc.category_no\n" + "and cm.c_id= ?\n"
				+ "group by vi.vol_info_no, vi.v_title, vi.start_date, vi.end_date,ci.c_name,vc.category_name\n"
				+ "having min(vd.rec_status) = 1 order by end_date desc";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CenterVolDto dto = new CenterVolDto();

				dto.setVolInfoNo(rs.getInt("vol_info_no"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setVolTitle(rs.getString("v_title"));
				dto.setStartDate(rs.getDate("start_date"));
				dto.setEndDate(rs.getDate("end_date"));
				dto.setVolStart(rs.getDate("봉사시작일"));
				dto.setVolEnd(rs.getDate("봉사종료일"));
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
		String sql = "select count(vi.vol_info_no) - sum(to_number(vd.rec_status)) as list_index from vol_info vi,vol_detail vd\n"
				+ "where vi.vol_info_no = vd.vol_info_no and\n" + "vi.c_id = ?\n" + "GROUP by vi.vol_info_no order by end_date desc ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
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

	/**
	 * 봉사 신청자 목록 조회
	 * 
	 * @param conn
	 * @param centerId
	 * @param list
	 * @throws CommonException
	 */
	public void applyList(Connection conn, String centerId, int volInfoNo, ArrayList<VolApplyListDto> list)
			throws CommonException {
		String sql = "select vi.v_title,gm.g_name,va.apply_date,va.g_id,vi.vol_info_no\n"
				+ "from vol_apply_list va, general_member gm, vol_detail vd, vol_info vi\n"
				+ "where va.g_id = gm.g_id and va.vol_detail_no = vd.vol_detail_no \n"
				+ "and vd.vol_info_no = vi.vol_info_no and vi.c_id = ? and vi.vol_info_no = ?\n"
				+ "group by vi.v_title,gm.g_name,va.apply_date,va.g_id,vi.vol_info_no order by apply_date\n";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			pstmt.setInt(2, volInfoNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				VolApplyListDto dto = new VolApplyListDto();
				dto.setVolInfoNo(rs.getInt("vol_info_no"));
				dto.setApplyDate(rs.getDate("apply_date"));
				dto.setGeneralId(rs.getString("g_id"));
				dto.setVolTitle(rs.getString("v_title"));
				dto.setGeneralName(rs.getString("g_name"));

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
	 * 봉사 신청자 신청 조회
	 * 
	 * @param centerId
	 * @param volInfoNo
	 * @param general
	 * @param list
	 * @throws CommonException
	 */
	public void applicantInfo(Connection conn, String centerId, int volInfoNo, GeneralMemberDto general,
			ArrayList<VolApplyListDto> list) throws CommonException {
		String sql = "select vi.v_title,gm.g_name,va.apply_date,va.g_id,vi.vol_info_no,\n" + 
				"va.vol_apply_no,va.vol_detail_no,va.vol_status,vd.vol_date,vd.apply_count,vd.total_count,vd.rec_status\n" + 
				"from vol_apply_list va, general_member gm, vol_detail vd, vol_info vi\n" + 
				"where va.g_id = gm.g_id and va.vol_detail_no = vd.vol_detail_no\n" + 
				"and vd.vol_info_no = vi.vol_info_no and vi.c_id = ? and vi.vol_info_no = ? and va.g_id = ?\n" + 
				"group by vi.v_title,gm.g_name,va.apply_date,va.g_id,vi.vol_info_no,\n" + 
				"va.vol_apply_no,va.vol_detail_no,va.vol_status,vd.vol_date,vd.apply_count,vd.total_count,vd.rec_status order by vol_date";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			pstmt.setInt(2, volInfoNo);
			pstmt.setString(3, general.getGeneralId());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				VolApplyListDto dto = new VolApplyListDto();
				dto.setVolInfoNo(rs.getInt("vol_info_no"));
				dto.setApplyDate(rs.getDate("apply_date"));
				dto.setGeneralId(rs.getString("g_id"));
				dto.setVolTitle(rs.getString("v_title"));
				dto.setGeneralName(rs.getString("g_name"));
				dto.setVolApplyNo(rs.getInt("vol_apply_no"));
				dto.setVolDetailNo(rs.getInt("vol_detail_no"));
				dto.setVolStatus(rs.getInt("vol_status"));
				dto.setVolDate(rs.getString("vol_date"));
				dto.setApplyCount(rs.getInt("apply_count"));
				dto.setTotalCount(rs.getInt("total_count"));
				dto.setRecStatus(rs.getString("rec_status"));
				
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
	 * 봉사 신청자 정보 조회
	 * 
	 * @param conn
	 * @param general
	 * @throws CommonException 
	 */
	public void generalInfo(Connection conn, GeneralMemberDto general) throws CommonException {
		String sql = "select gm.g_id,gm.g_name,gm.gender,gm.birthday,gm.g_address,gm.g_mobile\n" + 
				",gm.g_email,vc.category_name,l.local_name\n" + 
				"from general_member gm, vol_category vc,local l\n" + 
				"where gm.category_no = vc.category_no and gm.local_no = l.local_no\n" + 
				"and g_id=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, general.getGeneralId());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				general.setGeneralId(rs.getString("g_id"));
				general.setGeneralName(rs.getString("g_name"));
				general.setGender(rs.getString("gender"));
				general.setBirthday(rs.getString("birthday"));
				general.setGeneralAddress(rs.getString("g_address"));
				general.setGeneralMobile(rs.getString("g_mobile"));
				general.setGeneralEmail(rs.getString("g_email"));
				general.setCategoryName(rs.getString("category_name"));
				general.setLocalName(rs.getString("local_name"));
				
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
}
