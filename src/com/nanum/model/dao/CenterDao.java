/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.nanum.dto.VolDetailDto;
import com.nanum.dto.CenterInfoDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.CenterVolDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.VolApplyListDto;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;
import com.nanum.util.MessageEntity;
import com.sun.javafx.css.StyleCacheEntry.Key;

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
	 * 센터회원 봉사 목록(모집중)
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
				+ "group by vi.vol_info_no, vi.v_title, vi.start_date, vi.end_date,ci.c_name,vc.category_name\n"
				+ "having min(vd.rec_status) = 0 order by round(vi.end_date - sysdate,0),vol_info_no";

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
	 * 센터회원 봉사 목록(마감)
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
				+ "having min(vd.rec_status) = '1' order by end_date desc";

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
	 * 봉사 신청자 목록 조회
	 * 
	 * @param conn
	 * @param centerId
	 * @param list
	 * @throws CommonException
	 */
	public void applyList(Connection conn, String centerId, int volInfoNo, ArrayList<HashMap<String, Object>> list)
			throws CommonException {
		String sql = "select vi.v_title,gm.g_name,gm.g_mobile,va.g_id,vi.vol_info_no,max(va.vol_status) as 활동여부,vd.rec_status\n"
				+ "from vol_apply_list va, general_member gm, vol_detail vd, vol_info vi\n"
				+ "where va.g_id = gm.g_id and va.vol_detail_no = vd.vol_detail_no \n"
				+ "and vd.vol_info_no = vi.vol_info_no and vi.c_id = ? and vi.vol_info_no = ?\n"
				+ "group by vi.v_title,gm.g_name,gm.g_mobile,va.g_id,vi.vol_info_no,vd.rec_status\n";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			pstmt.setInt(2, volInfoNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HashMap<String, Object>	map = new HashMap<String, Object>();
				
				map.put("volInfoNo",rs.getInt("vol_info_no"));
				map.put("generalId",rs.getString("g_id"));
				map.put("volTitle",rs.getString("v_title"));
				map.put("generalName",rs.getString("g_name"));
				map.put("generalMobile",rs.getString("g_mobile"));
				map.put("activityStatus",rs.getString("활동여부"));
				map.put("recStatus",rs.getString("rec_status"));
				
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
	 * 봉사 신청자 신청 조회
	 * 
	 * @param centerId
	 * @param volInfoNo
	 * @param general
	 * @param list
	 * @throws CommonException
	 */
	public void applicantInfo(Connection conn, String centerId, int volInfoNo, GeneralMemberDto general,ArrayList<HashMap<String, Object>> list) throws CommonException {
		String sql = "select vi.v_title,gm.g_name,va.apply_date,va.g_id,vi.vol_info_no,\n"
				+ "va.vol_apply_no,va.vol_detail_no,va.vol_status,vd.vol_date,vd.apply_count,vd.total_count,vd.rec_status\n"
				+ "from vol_apply_list va, general_member gm, vol_detail vd, vol_info vi\n"
				+ "where va.g_id = gm.g_id and va.vol_detail_no = vd.vol_detail_no\n"
				+ "and vd.vol_info_no = vi.vol_info_no and vi.c_id = ? and vi.vol_info_no = ? and va.g_id = ?\n"
				+ "group by vi.v_title,gm.g_name,va.apply_date,va.g_id,vi.vol_info_no,\n"
				+ "va.vol_apply_no,va.vol_detail_no,va.vol_status,vd.vol_date,vd.apply_count,vd.total_count,vd.rec_status order by vol_date";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			pstmt.setInt(2, volInfoNo);
			pstmt.setString(3, general.getGeneralId());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				map.put("volInfoNo", rs.getInt("vol_info_no"));
				map.put("applyDate", rs.getDate("apply_date"));
				map.put("generalId", rs.getString("g_id"));
				map.put("volTitle", rs.getString("v_title"));
				map.put("generalName", rs.getString("g_name"));
				map.put("volApplyNo", rs.getInt("vol_apply_no"));
				map.put("volDetailNo", rs.getInt("vol_detail_no"));
				map.put("volStatus", rs.getInt("vol_status"));
				map.put("volDate", rs.getString("vol_date"));
				map.put("applyCount", rs.getInt("apply_count"));
				map.put("totalCount", rs.getInt("total_count"));
				map.put("recStatus", rs.getString("rec_status"));
				
				
				
				

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
	 * 봉사 신청자 정보 조회
	 * 
	 * @param conn
	 * @param general
	 * @throws CommonException
	 */
	public void generalInfo(Connection conn, GeneralMemberDto general) throws CommonException {
		String sql = "select gm.g_id,gm.g_name,gm.gender,gm.birthday,gm.g_address,gm.g_mobile\n"
				+ ",gm.g_email,vc.category_name,l.local_name\n" + "from general_member gm, vol_category vc,local l\n"
				+ "where gm.category_no = vc.category_no and gm.local_no = l.local_no\n" + "and g_id=?";

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
	 * 
	 * @param conn
	 * @param dto  CenterMemberDto
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
	 * 
	 * @param conn
	 * @param dto  CenterMemberDto
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
		String sql = "update center_member " + "set c_pass = ?, c_name = ?, c_mobile = ?, c_email = ? "
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
		String sql = "update center_info " + "set  c_entry_date = ?, c_zipcode = ?, c_address = ?, register_code = ?, "
				+ "service_subject = ?, ceo_name = ?, ceo_mobile = ?" + "where c_id = ?";
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

	/**
	 * 봉사글등록(map)
	 */
	public void addVolInfo(Connection conn, HashMap<String, Object> map) throws CommonException {
		String sql = "insert into vol_info values (vol_info_seq.nextval,?,?,?,sysdate,to_date(?,'HH24:MI'),to_date(?,'HH24:MI'),?,?,?,?,?,?,?,?,?)";
		System.out.println(sql);

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(map.get("centerId")));
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
	 * 날짜별 봉사 등록
	 */
	public void addVolDetail(Connection conn, int volInfoNo, String volDate, int totalCount) throws CommonException {
		String sql = "insert into vol_detail values(vol_detail_seq.nextval,?,?,0,?,'0')";
		System.out.println(sql);

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, volInfoNo);
			pstmt.setString(2, volDate);
			pstmt.setInt(3, totalCount);
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

			if (rs.next()) {
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
	
	/**
	 * 인증서 발급목록
	 *  
	 * @param centerId
	 * @param conn
	 * @param list
	 * @throws CommonException 
	 */
	public void issueList( Connection conn,String centerId, ArrayList<HashMap<String, Object>> list) throws CommonException {
		String sql = "select  \n" + "vi.vol_info_no\n" + ", vi.v_title \n" + ", vi.start_date\n" + ", vi.end_date\n"
				+ ", min(vd.vol_date) as 봉사시작일\n" + ", max(vd.vol_date) as 봉사종료일\n" + ", min(vd.rec_status) as 모집중\n"
				+ ", max(vd.rec_status) as 마감\n" + ", ci.c_name\n" + ", vc.category_name\n"
				+ ", round(vi.end_date - sysdate,0) as deadline\n"
				+ "from vol_info vi,vol_detail vd, center_member cm, center_info ci,vol_category vc\n"
				+ "where vi.vol_info_no = vd.vol_info_no\n" + "and vi.c_id = cm.c_id\n" + "and cm.c_id = ci.c_id\n"
				+ "and vi.category_no = vc.category_no\n" + "and cm.c_id= ?\n"
				+ "group by vi.vol_info_no, vi.v_title, vi.start_date, vi.end_date,ci.c_name,vc.category_name\n"
				+ "having min(vd.rec_status) = 2 order by 봉사종료일,vol_info_no";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, centerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HashMap<String, Object>	map = new HashMap<String, Object>();
				
				map.put("volInfoNo",rs.getInt("vol_info_no"));
				map.put("centerName",rs.getString("c_name"));
				map.put("volTitle",rs.getString("v_title"));
				map.put("startDate",rs.getDate("start_date"));
				map.put("endDate",rs.getDate("end_date"));
				map.put("volStart",rs.getDate("봉사시작일"));
				map.put("volEnd",rs.getDate("봉사종료일"));
				map.put("categoryName",rs.getString("category_name"));
				map.put("recStatus",rs.getString("모집중"));
				map.put("deadline",rs.getInt("deadline"));
				
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
	 * 인증서 발급인원 조회
	 * 
	 * @param conn
	 * @param centerId
	 * @param map
	 * @throws CommonException 
	 */
	public void volIssue(Connection conn, HashMap<String, Object> map) throws CommonException {
		String sql = "select gm.g_id,gm.g_name,gm.g_address,cm.c_id,vi.start_time,vi.end_time,va.vol_status,vd.rec_status,min(vd.vol_date) as 봉사시작일,max(vd.vol_date) as 봉사종료일,sysdate as 발급일,vi.vol_info_no\n" + 
				"from vol_apply_list va, vol_detail vd, vol_info vi,center_member cm,center_info ci, general_member gm\n" + 
				"where va.g_id = gm.g_id and va.vol_detail_no = vd.vol_detail_no and vd.vol_info_no = vi.vol_info_no and vi.c_id = cm.c_id and cm.c_id = ci.c_id\n" + 
				"and cm.c_id = ? and vi.vol_info_no = ? and gm.g_id  = ? and va.vol_status = '2' and vd.rec_status = '2' and\n" + 
				"vd.vol_detail_no in ( select vol_detail_no from vol_apply_list where g_id = ?)\n" + 
				"group by gm.g_id,gm.g_name,gm.g_address,cm.c_id,vi.start_time,vi.end_time,va.vol_status,vd.rec_status,vi.vol_info_no,vi.vol_info_no\n" + 
				"order by max(vd.vol_date) desc";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) map.get("centerId"));
			pstmt.setString(2, (String) map.get("volInfoNo"));
			pstmt.setString(3, (String) map.get("generalId"));
			pstmt.setString(4, (String) map.get("generalId"));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				map.put("generalId",rs.getString("g_id"));
				map.put("generalName",rs.getString("g_name"));
				map.put("generalAddress",rs.getString("g_address"));
				map.put("centerId",rs.getString("c_id"));
				map.put("startTime",rs.getDate("start_time"));
				map.put("endTime",rs.getDate("end_time"));
				map.put("volStatus",rs.getString("vol_status"));
				map.put("recStatus",rs.getString("rec_status"));
				map.put("volDate",rs.getString("봉사시작일"));
				map.put("today",rs.getDate("발급일"));
				map.put("volInfoNo",rs.getInt("vol_info_no"));
				
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
	 * 발급코드 체킹
	 * 
	 * @param conn
	 * @param map
	 * @throws CommonException 
	 */
	public boolean isVolCode(Connection conn, HashMap<String, Object> map) throws CommonException {
		String sql = "select * from vol_confirmation where vol_con_no = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) map.get("volCode"));
			rs = pstmt.executeQuery();

			if (rs.next()) {
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
	 * 인증서 발급
	 * 
	 * @param map
	 * @throws CommonException 
	 */
	public void insertIssue(Connection conn,String volCode,HashMap<String, Object> map) throws CommonException {
		String sql = "insert into vol_confirmation values(?,?,?,'내용하드코딩',?,?)";

		PreparedStatement pstmt = null;

		int rows = 0;
		try {
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, volCode);
				pstmt.setString(2, (String)map.get("generalId"));
				pstmt.setString(3, (String)map.get("centerId"));
				pstmt.setInt(4, (int)map.get("volInfoNo"));
				pstmt.setString(5, (String)map.get("volDate"));
				
				rows = pstmt.executeUpdate();
			
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
	 * 봉사 날짜별 데이터 삭제
	 * @param conn
	 * @param volInfoNo
	 */
	public void deleteVolDetail(Connection conn, int volInfoNo) throws CommonException {
		String sql = "delete from vol_detail where vol_info_no = ?";
		System.out.println(sql);

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, volInfoNo);
			int rows = pstmt.executeUpdate();
			System.out.println("rows : " + rows);
			if (rows < 1) {
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
	 * 봉사정보 삭제
	 * @param conn
	 * @param volInfoNo
	 */
	public void deleteVolInfo(Connection conn, int volInfoNo) throws CommonException {
		String sql = "delete from vol_info where vol_info_no = ?";
		System.out.println(sql);

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, volInfoNo);
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
	 * 활동상태 변경(활동 완료)
	 * 
	 * @param conn
	 * @param map
	 * @throws CommonException 
	 */
	public void checkVolStatus(Connection conn, String checkDates) throws CommonException {
		String sql = "update vol_apply_list set vol_status = '2' where vol_apply_no = ? and vol_status = '1'";
		System.out.println(sql);

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(checkDates));
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
	 * 인증서 폼
	 * 
	 * @param conn
	 * @param map
	 * @throws CommonException 
	 */
	public void issueList(Connection conn, HashMap<String, Object> map) throws CommonException {
		String sql = "select vc.vol_con_no,gm.g_name,gm.g_address,min(vd.vol_date) as 활동시작일,max(vd.vol_date) as 활동종료일,vi.start_time,vi.end_time,vc.vol_date,ci.c_name\n" + 
				"from vol_confirmation vc,vol_apply_list va, vol_detail vd, vol_info vi,center_member cm,center_info ci, general_member gm\n" + 
				"where vc.g_id = gm.g_id and vc.c_id = cm.c_id and vc.vol_info_no = vi.vol_info_no and va.g_id = gm.g_id and va.vol_detail_no = vd.vol_detail_no and vd.vol_info_no = vi.vol_info_no and vi.c_id = cm.c_id and cm.c_id = ci.c_id\n" + 
				"and cm.c_id = ? and va.vol_status = '2' and vd.rec_status = '2' and vi.vol_info_no = ? and\n" + 
				"vd.vol_detail_no in ( select vol_detail_no from vol_apply_list where g_id = ?)\n" + 
				"group by vc.vol_con_no,gm.g_name,gm.g_address,vi.start_time,vi.end_time,vc.vol_date,ci.c_name";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) map.get("centerId"));
			pstmt.setString(2, (String) map.get("volInfoNo"));
			pstmt.setString(3, (String) map.get("generalId"));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				map.put("volConNo",rs.getString("vol_con_no"));
				map.put("generalName",rs.getString("g_name"));
				map.put("generalAddress",rs.getString("g_address"));
				map.put("startDate",rs.getDate("활동시작일"));
				map.put("endDate",rs.getDate("활동종료일"));
				map.put("startTime",rs.getDate("start_time"));
				map.put("endTime",rs.getDate("end_time"));
				map.put("centerId",rs.getString("c_id"));
				map.put("volDate",rs.getDate("vol_date"));
				map.put("centerName",rs.getString("c_name"));
				
				System.out.println(map);
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
