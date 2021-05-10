/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public AdminDao() {}
	
	public static AdminDao getInstance() {
		return instance;
	}

	public void selCenterInfo(Connection conn, ArrayList<CenterInfoDto> list) throws CommonException{
		String sql = "select c_name, c_id, service_subject from center_info";

		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			CenterInfoDto dto = null;
			
			while(rs.next()) {
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

	public void selCenterAcceptListToCnt(Connection conn, CenterInfoDto cDto) throws CommonException{
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
			
			if(rs.next()) { 
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
	 * @param conn
	 * @param centerActList
	 * @param lastNum 
	 * @param sartNum 
	 * @throws CommonException
	 */
	public void selCenterAcceptList(Connection conn, ArrayList<CenterInfoDto> centerActList, Integer sartNum, Integer lastNum) throws CommonException{
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
		sql.append("  and  c.app_status = 0  ");
		sql.append("  order by c.entry_date  ");
		 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		 // 페이징 추가 쿼리 
		String page_sql = "select * from ("+sql.toString()+") where page_num between ? and ?";
		
		
		try {
			stmt = conn.prepareStatement(page_sql); 
			stmt.setInt(1, sartNum);
			stmt.setInt(2, lastNum);
			
			rs = stmt.executeQuery();
			
			CenterInfoDto dto = null;
			
			while(rs.next()) { 
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

	public void upAcceptCenter(Connection conn, String centerId) throws CommonException{
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
		}  finally {
			JdbcTemplate.close(stmt);
		}
	}

	public void DeleteCenter(Connection conn, String centerId) throws CommonException{
		
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
		}  finally {
			JdbcTemplate.close(stmt);
		}
		
		
	}
	
	
	/**
	 * 댓글 등록
	 * @param conn
	 * @param replyDto QnAReplyDto
	 * @throws CommonException
	 */
	public void insertReply(Connection conn, QnAReplyDto replyDto) throws CommonException{
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
		}  finally {
			JdbcTemplate.close(stmt);
		}
		
	}
	
	/**
	 * 댓글 삭제
	 * @param conn
	 * @param rNo 댓글번호
	 * @throws CommonException
	 */
	public void deleteReply(Connection conn, int rNo) throws CommonException{
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
		}  finally {
			JdbcTemplate.close(stmt);
		}
		
	}
	
	/**
	 * 댓글 삭제
	 * @param conn
	 * @param qNo 문의글번호
	 * @throws CommonException
	 */
	public void deleteAllReply(Connection conn, int qNo) throws CommonException{
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
		}  finally {
			JdbcTemplate.close(stmt);
		}
		
	}
	
	/**
	 * 댓글 수정
	 * @param conn
	 * @param rNo 댓글번호
	 * @param content 내용
	 * @throws CommonException
	 */
	public void updateReply(Connection conn, int rNo, String content) throws CommonException{
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
		}  finally {
			JdbcTemplate.close(stmt);
		}
		
	}

	public void selectGetGenralMinList(Connection conn, ArrayList<GeneralMemberDto> glist) throws CommonException{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select 'gen' as gubun, g_id, g_name, g_email from general_member ");
		 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql.toString()); 
			
			rs = stmt.executeQuery();
			
			GeneralMemberDto dto = null;
			
			while(rs.next()) { 
				dto = new GeneralMemberDto();
				dto.setGubun(rs.getString("gubun"));
				dto.setGeneralId(rs.getString("g_id"));
				dto.setGeneralName(rs.getString("g_name"));
				dto.setGeneralEmail(rs.getString("g_email"));
				
				glist.add(dto);
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

	public void selectCenterMinList(Connection conn, ArrayList<CenterMemberDto> list) throws CommonException{
		StringBuilder sql = new StringBuilder();
		
		
		sql.append(" select 'cen' as gubun, c_id, c_name, c_email from center_member ");
		 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			stmt = conn.prepareStatement(sql.toString()); 
			
			rs = stmt.executeQuery();
			
			CenterMemberDto dto = null;
			
			while(rs.next()) { 
				dto = new CenterMemberDto();
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
	
}
