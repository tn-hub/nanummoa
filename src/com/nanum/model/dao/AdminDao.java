/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.nanum.dto.AdminMemberDto;
import com.nanum.dto.CenterInfoDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.LocalDto;
import com.nanum.dto.QnAReplyDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;

/**
 * 관리자 Dao 클래스
 *
 */
public class AdminDao {
	private static AdminDao instance = new AdminDao();

	public AdminDao() {
	}

	public static AdminDao getInstance() {
		return instance;
	}

	public void selCenterInfo(Connection conn, ArrayList<CenterInfoDto> list) throws CommonException {
		String sql = "select c_name, c_id, service_subject from center_info";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			CenterInfoDto dto = null;

			while (rs.next()) {
				dto = new CenterInfoDto();
				dto.setCenterName(rs.getString("c_name"));
				dto.setCenterId(rs.getString("c_id"));
				dto.setService(rs.getString("service_subject"));

				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	public void selCenterAcceptListToCnt(Connection conn, CenterInfoDto cDto) throws CommonException {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) as tot_cnt ");
		sql.append("  from center_member c, center_info i ");
		sql.append("  where c.app_status = 0 ");
		sql.append("  and c.c_id = i.c_id ");

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();

			if (rs.next()) {
				cDto.setTotAcceptCnt(rs.getInt("tot_cnt"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 가입대기 승인 목록
	 * 
	 * @param conn
	 * @param centerActList
	 * @param lastNum
	 * @param sartNum
	 * @throws CommonException
	 */
	public void selCenterAcceptList(Connection conn, ArrayList<CenterInfoDto> centerActList) throws CommonException {
		StringBuilder sql = new StringBuilder();

		sql.append(" select  ");
		sql.append("  c.c_id  ");
		sql.append("  , i.c_name   ");
		sql.append("  , to_char(c.entry_date,'yyyy-mm-dd') as member_entry_date ");
		sql.append("  , c.c_name as member_name ");
		sql.append("  , c.c_mobile as member_mobile ");
		sql.append("  , c.c_email as member_email ");
		sql.append("  , i.register_code ");
		sql.append("  , i.c_entry_date ");
		sql.append("  , i.c_address ");
		sql.append("  , i.ceo_name ");
		sql.append("  , i.ceo_mobile ");
		sql.append("  , i.service_subject ");
		sql.append("  from center_member c, center_info i  ");
		sql.append("  where c.c_id = i.c_id  ");
		sql.append("  and  c.app_status = 0  ");
		sql.append("  order by c.entry_date  ");

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();

			CenterInfoDto dto = null;

			while (rs.next()) {
				dto = new CenterInfoDto();
				dto.setCenterId(rs.getString("c_id"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setCmemberEntryDate(rs.getString("member_entry_date"));
				dto.setCmemberName(rs.getString("member_name"));
				dto.setCmemberMobile(rs.getString("member_mobile"));
				dto.setCmemberEmail(rs.getString("member_email"));
				dto.setRegisterCode(rs.getString("register_code"));
				dto.setCenterEntryDate(rs.getString("c_entry_date"));
				dto.setCenterAddress(rs.getString("c_address"));
				dto.setCeoName(rs.getString("ceo_name"));
				dto.setCeoMobile(rs.getString("ceo_mobile"));
				dto.setService(rs.getString("service_subject"));

				centerActList.add(dto);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	public void upAcceptCenter(Connection conn, String centerId) throws CommonException {
		StringBuilder sql = new StringBuilder();

		sql.append(" update center_member ");
		sql.append(" set app_status = 1 ");
		sql.append(" where c_id = ? ");

		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, centerId);

			int rows = stmt.executeUpdate();

			if (rows == 0) {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}

	public void DeleteCenter(Connection conn, String centerId) throws CommonException {

		// center_member 삭제
		StringBuilder sql = new StringBuilder();

		sql.append(" delete from center_info ");
		sql.append(" where c_id = ? ");

		// center_member 삭제
		StringBuilder sql2 = new StringBuilder();

		sql2.append(" delete from center_member ");
		sql2.append(" where c_id = ? ");

		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;

		try {
			// center_member 삭제
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, centerId);

			int rows = stmt.executeUpdate();

			if (rows == 0) {
				throw new Exception();
			}

			// center_member 삭제
			stmt2 = conn.prepareStatement(sql2.toString());
			stmt2.setString(1, centerId);

			int rows2 = stmt2.executeUpdate();

			if (rows2 == 0) {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}

	}

	/**
	 * 댓글 등록
	 * 
	 * @param conn
	 * @param replyDto QnAReplyDto
	 * @throws CommonException
	 */
	public void insertReply(Connection conn, QnAReplyDto replyDto) throws CommonException {
		String sql = "insert into qna_reply (r_no, q_no, a_id, r_contents) values (qna_reply_seq.nextval, ?, ?, ?)";

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, replyDto.getQnaNo());
			stmt.setString(2, replyDto.getAdminId());
			stmt.setString(3, replyDto.getReplyContents());

			int rows = stmt.executeUpdate();

			if (rows != 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}

	}

	/**
	 * 댓글 삭제
	 * 
	 * @param conn
	 * @param rNo  댓글번호
	 * @throws CommonException
	 */
	public void deleteReply(Connection conn, int rNo) throws CommonException {
		String sql = "delete from qna_reply where r_no = ?";

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, rNo);

			int rows = stmt.executeUpdate();

			if (rows != 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}

	}

	/**
	 * 댓글 삭제
	 * 
	 * @param conn
	 * @param qNo  문의글번호
	 * @throws CommonException
	 */
	public void deleteAllReply(Connection conn, int qNo) throws CommonException {
		String sql = "delete from qna_reply where q_no = ?";

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, qNo);

			int rows = stmt.executeUpdate();

			if (rows != 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}

	}

	/**
	 * 댓글 수정
	 * 
	 * @param conn
	 * @param rNo     댓글번호
	 * @param content 내용
	 * @throws CommonException
	 */
	public void updateReply(Connection conn, int rNo, String content) throws CommonException {
		String sql = "update qna_reply set r_contents = ? where r_no = ?";

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, content);
			stmt.setInt(2, rNo);
			int rows = stmt.executeUpdate();

			if (rows != 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}

	}

	/** 관리자 센터회원 목록 보기 */
	public void selectGetGenralMinList(Connection conn, ArrayList<GeneralMemberDto> list) throws CommonException {
		StringBuilder sql = new StringBuilder();

		sql.append(" select 'gen' as gubun, g_id, g_name, g_email from general_member ");

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();

			GeneralMemberDto dto = null;

			while (rs.next()) {
				dto = new GeneralMemberDto();

				dto.setGubun(rs.getString("gubun"));
				dto.setGeneralId(rs.getString("g_id"));
				dto.setGeneralName(rs.getString("g_name"));
				dto.setGeneralEmail(rs.getString("g_email"));

				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}

	}

	public void selectCenterMinList(Connection conn, ArrayList<CenterMemberDto> list) throws CommonException {
		StringBuilder sql = new StringBuilder();

		sql.append(" select 'cen' as gubun, c_id, c_name, c_email from center_member where app_status=1");

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();

			CenterMemberDto dto = null;

			while (rs.next()) {
				dto = new CenterMemberDto();

				System.out.println("++++" + rs.getString("gubun"));
				System.out.println("++++" + rs.getString("c_id"));
				System.out.println("++++" + rs.getString("c_name"));
				System.out.println("++++" + rs.getString("c_email"));

				dto.setGubun(rs.getString("gubun"));
				dto.setCenterId(rs.getString("c_id"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setCenterEmail(rs.getString("c_email"));

				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 관리자 상세조회
	 * 
	 * @param conn
	 * @param dto
	 * @throws CommonException
	 */
	public void selectAdminInfo(Connection conn, AdminMemberDto dto) throws CommonException {
		String sql = "select * from admin_member where a_id = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAdminId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setAdminId(rs.getString("a_id"));
				dto.setAdminPass(rs.getString("a_pass"));
				dto.setAdminName(rs.getString("a_name"));
				dto.setAdminMobile(rs.getString("a_mobile"));
				dto.setAdminEmail(rs.getString("a_email"));
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
	 * 관리자 정보 수정
	 * 
	 * @param conn
	 * @param dto
	 * @throws CommonException
	 */
	public void updateAdminMember(Connection conn, AdminMemberDto dto) throws CommonException {
		String sql = "update admin_member set a_pass = ?, a_name = ? ,a_mobile = ?, a_email = ? where a_id = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAdminPass());
			pstmt.setString(2, dto.getAdminName());
			pstmt.setString(3, dto.getAdminMobile());
			pstmt.setString(4, dto.getAdminEmail());
			pstmt.setString(5, dto.getAdminId());
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

	/** 관리자 일반회원 목록 보기 */
	public void selectGetGenralDetatilList(Connection conn, ArrayList<GeneralMemberDto> list) throws CommonException {
		StringBuilder sql = new StringBuilder();

		sql.append(" select 'gen' as gubun, g_id, g_name, g_email from general_member ");

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();

			GeneralMemberDto dto = null;

			while (rs.next()) {
				dto = new GeneralMemberDto();

				dto.setGubun(rs.getString("gubun"));
				dto.setGeneralId(rs.getString("g_id"));
				dto.setGeneralName(rs.getString("g_name"));
				dto.setGeneralEmail(rs.getString("g_email"));

				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}

	}

	public void selectGenralDetailListTotCnt(Connection conn, GeneralMemberDto dto) throws CommonException {
		StringBuilder sql = new StringBuilder();

		sql.append(" select count(*) as tot_cnt from general_member ");

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();

			if (rs.next()) {
				dto.setTotCnt(rs.getInt("tot_cnt"));
				dto.setGubun("gen");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}

	}

	public void selectCenterDetailListCnt(Connection conn, CenterInfoDto dto) throws CommonException {
		StringBuilder sql = new StringBuilder();

		sql.append(" select count(*) as tot_cnt from center_member where app_status = 1  ");

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();

			if (rs.next()) {
				dto.setTotCnt(rs.getInt("tot_cnt"));
				dto.setGubun("cen");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}

	}

	/** 관리자가 일반회원 상세 보기 */
	public void selectGeneralDetail(Connection conn, GeneralMemberDto dto, String generalId) throws CommonException {
		StringBuilder sql = new StringBuilder();

		sql.append(" select   ");
		sql.append("  g.g_id  ");
		sql.append("  , g.g_name  ");
		sql.append("  , g.gender  ");

		sql.append("  , to_char(to_date(birthday, 'yyyymmdd'), 'yyyy-mm-dd') as birthday ");

		sql.append("  , g.g_zipcode  ");
		sql.append("  , g.g_address  ");
		sql.append("  , g.g_mobile  ");
		sql.append("  , g.g_email  ");
		sql.append("  , g.category_no  ");
		sql.append("  , g.local_no   ");
		sql.append("  , v.category_name  ");
		sql.append("  , l.local_name  ");
		sql.append(" from general_member g, vol_category v, local l  ");
		sql.append(" where g.category_no = v.category_no(+)  ");
		sql.append(" and g.local_no = l.local_no(+)  ");
		sql.append(" and g.g_id = ?   ");

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, generalId);

			rs = stmt.executeQuery();

			if (rs.next()) {
				dto.setGeneralId(rs.getString("g_id"));
				dto.setGeneralName(rs.getString("g_name"));
				dto.setGender(rs.getString("gender"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setGeneralAddress(rs.getString("g_address"));
				dto.setGeneralMobile(rs.getString("g_mobile"));
				dto.setGeneralEmail(rs.getString("g_email"));
				dto.setCategoryName(rs.getString("category_name"));
				dto.setLocalName(rs.getString("local_name"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}

	}

	/** 관리자가 센터회원 상세 보기 */
	public void selectCenterDetail(Connection conn, CenterInfoDto dto, String centerId) throws CommonException {
		StringBuilder sql = new StringBuilder();

		sql.append(" select  ");
		sql.append("  c.c_id  ");
		sql.append("  , i.c_name   ");
		sql.append("  , to_char(c.entry_date,'yyyy-mm-dd') as member_entry_date ");
		sql.append("  , rownum as page_num  ");
		sql.append("  , c.c_name as member_name ");
		sql.append("  , c.c_mobile as member_mobile ");
		sql.append("  , c.c_email as member_email ");
		sql.append("  , i.register_code ");
		sql.append("  , i.c_entry_date ");
		sql.append("  , i.c_address ");
		sql.append("  , i.ceo_name ");
		sql.append("  , i.ceo_mobile ");
		sql.append("  , i.service_subject ");
		sql.append("  from center_member c, center_info i  ");
		sql.append("  where c.c_id = i.c_id  ");
		sql.append("   and  c.c_id = ?  ");

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, centerId);

			rs = stmt.executeQuery();

			if (rs.next()) {
				dto.setCenterId(rs.getString("c_id"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setCmemberEntryDate(rs.getString("member_entry_date"));
				dto.setCmemberName(rs.getString("member_name"));
				dto.setCmemberMobile(rs.getString("member_mobile"));
				dto.setCmemberEmail(rs.getString("member_email"));
				dto.setRegisterCode(rs.getString("register_code"));
				dto.setCenterEntryDate(rs.getString("c_entry_date"));
				dto.setCenterAddress(rs.getString("c_address"));
				dto.setCeoName(rs.getString("ceo_name"));
				dto.setCeoMobile(rs.getString("ceo_mobile"));
				dto.setService(rs.getString("service_subject"));

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 봉사 확인서 내역 조회
	 */
	public void searchConfirmationList(Connection conn, ArrayList<HashMap<String, Object>> list)
			throws CommonException {
		String sql = "select i.vol_info_no, vc.vol_con_no, i.v_title, c.c_name, \r\n" + "(select min(vol_date)\r\n"
				+ "from vol_detail\r\n" + "where vol_info_no = i.vol_info_no) as 봉사시작일,\r\n"
				+ "(select max(vol_date)\r\n" + "from vol_detail\r\n"
				+ "where vol_info_no = i.vol_info_no) as 봉사마감일, \r\n"
				+ "to_char(i.start_time, 'HH24:MI') as 시작시간, to_char(i.end_time, 'HH24:MI') as 마감시간, \r\n" + "vc.g_id "
				+ "from vol_info i, vol_detail d, center_info c, vol_confirmation vc\r\n"
				+ "where i.vol_info_no = d.vol_info_no\r\n" + "and i.c_id = c.c_id\r\n" + "and i.c_id = vc.c_id\r\n"
				+ "and vc.vol_info_no = d.vol_info_no \r\n"
				+ "group by i.vol_info_no, vc.vol_con_no, i.v_title, c.c_name, i.start_time, i.end_time, vc.vol_date, vc.g_id\r\n"
				+ "order by vc.vol_date desc";

		System.out.println(sql);
		HashMap<String, Object> map = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map = new HashMap<String, Object>();
				map.put("volInfoNo", rs.getString(1));
				map.put("volConNo", rs.getString(2));
				map.put("volTitle", rs.getString(3));
				map.put("centerName", rs.getString(4));
				map.put("startDate", rs.getString(5));
				map.put("endDate", rs.getString(6));
				map.put("startTime", rs.getString(7));
				map.put("endTime", rs.getString(8));
				map.put("gId", rs.getString(9));

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

	public void generalDelete(Connection conn, String generalId) throws CommonException{
		String sql_vol_conf = " delete from vol_confirmation where g_id = ? ";
		String sql_qna_rel = " delete from qna_reply where q_no in (select q_no from qna where g_id  = ?) ";
		String sql_qna = " delete from qna where g_id = ? ";
		String sql_vol_list = " delete from vol_apply_list where g_id = ? ";
		String sql_gen_mem = " delete from general_member where g_id = ? ";
		

		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		PreparedStatement stmt4 = null;
		PreparedStatement stmt5 = null;
		
		
		try {
			
			stmt2 = conn.prepareStatement(sql_vol_conf);
			stmt2.setString(1, generalId);
			stmt2.executeUpdate();
			
			stmt3 = conn.prepareStatement(sql_qna_rel);
			stmt3.setString(1, generalId);
			stmt3.executeUpdate();
			
			stmt4 = conn.prepareStatement(sql_qna);
			stmt4.setString(1, generalId);
			stmt4.executeUpdate();
			
			stmt5 = conn.prepareStatement(sql_vol_list);
			stmt5.setString(1, generalId);
			stmt5.executeUpdate();
			
			stmt = conn.prepareStatement(sql_gen_mem);
			stmt.setString(1, generalId);

			int rows = stmt.executeUpdate();

			if (rows == 0) {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}
		
	}

	public void centerDelete(Connection conn, String centerId) throws CommonException{

		System.out.println("DAO centerId =" + centerId);
		String sql_vol_list = " delete from vol_apply_list where g_id  = ? ";
		String sql_qna_rel = " delete from qna_reply where q_no in (select q_no from qna where c_id  = ? )";
		String sql_qna = " delete from qna where c_id  = ? ";
		String sql_vol_conf = " delete from vol_confirmation where c_id =  ? ";
		String sql_vol_appl_list = " delete from vol_apply_list where vol_detail_no in (select vol_detail_no from vol_detail where vol_info_no in (select vol_info_no from vol_info where c_id = ?))";
		String sql_vol_dtl = " delete from vol_detail where vol_info_no in (select vol_info_no from vol_info where c_id = ?)" ;
		String sql_vol_info = " delete from vol_info where c_id = ? ";
		String sql_cen_info = " delete from center_info where c_id = ? ";
		String sql_cen_mem = " delete from center_member where c_id  = ? ";


		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		PreparedStatement stmt4 = null;
		PreparedStatement stmt5 = null;
		PreparedStatement stmt6 = null;
		PreparedStatement stmt7 = null;
		PreparedStatement stmt8 = null;
		PreparedStatement stmt9 = null;

		try {
			
			
			stmt2 = conn.prepareStatement(sql_vol_list);
			stmt2.setString(1, centerId);
			stmt2.executeUpdate();
			
			stmt3 = conn.prepareStatement(sql_qna_rel);
			stmt3.setString(1, centerId);
			stmt3.executeUpdate();
			
			stmt4 = conn.prepareStatement(sql_qna);
			stmt4.setString(1, centerId);
			stmt4.executeUpdate();
			
			stmt5 = conn.prepareStatement(sql_vol_conf);
			stmt5.setString(1, centerId);
			stmt5.executeUpdate();
			
			stmt6 = conn.prepareStatement(sql_vol_appl_list);
			stmt6.setString(1, centerId);
			stmt6.executeUpdate();
			
			stmt7 = conn.prepareStatement(sql_vol_dtl);
			stmt7.setString(1, centerId);
			stmt7.executeUpdate();
			
			stmt8 = conn.prepareStatement(sql_vol_info);
			stmt8.setString(1, centerId);
			stmt8.executeUpdate();
			
			stmt9 = conn.prepareStatement(sql_cen_info);
			stmt9.setString(1, centerId);
			stmt9.executeUpdate();
			
			stmt = conn.prepareStatement(sql_cen_mem);
			stmt.setString(1, centerId);

			int rows = stmt.executeUpdate();

			if (rows == 0) {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}
		
	}
}
