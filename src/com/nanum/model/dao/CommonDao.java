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
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.CenterVolDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.LocalDto;
import com.nanum.dto.QnADto;
import com.nanum.dto.ServiceCategoryDto;
import com.nanum.dto.VolBlockDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;


/**
 * 공통 Dao 클래스
 *
 */
public class CommonDao {
	private static CommonDao instance = new CommonDao();

	public CommonDao() {
	}

	public static CommonDao getInstance() {
		return instance;
	}
	
	/**
	 * 일반회원 로그인
	 * @param conn
	 * @param dto
	 * @throws CommonException 
	 */
	public void login(Connection conn, GeneralMemberDto dto) throws CommonException {
		String sql = "select * from GENERAL_MEMBER where g_id = ? and g_pass = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getGeneralId());
			stmt.setString(2, dto.getGeneralPass());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				dto.setGeneralId(rs.getString("g_id"));
				dto.setGeneralPass(rs.getString("g_pass"));
				dto.setGeneralName(rs.getString("g_name"));
				dto.setGender(rs.getString("gender"));
				dto.setBirthday(rs.getString("BIRTHDAY"));
				dto.setGeneralZipCode(rs.getString("g_zipcode"));
				dto.setGeneralAddress(rs.getString("g_address"));
				dto.setGeneralMobile(rs.getString("g_mobile"));
				dto.setGeneralEmail(rs.getString("g_email"));
				dto.setCategoryNo(rs.getString("category_no"));
				dto.setLocalNo(rs.getString("local_no"));
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
	 * 센터회원 로그인
	 * 
	 * @param conn
	 * @param dto
	 * @throws CommonException 
	 */
	public void login(Connection conn, CenterMemberDto dto) throws CommonException {
		String sql = "select * from center_MEMBER where c_id = ? and c_pass = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getCenterId());
			stmt.setString(2, dto.getCenterPass());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				dto.setCenterId(rs.getString("c_id"));
				dto.setCenterPass(rs.getString("c_pass"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setCenterMobile(rs.getString("c_mobile"));
				dto.setCenterEmail(rs.getString("c_email"));
				dto.setAppStatus(rs.getString("app_status"));
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
	 * 관리자 로그인
	 * 
	 * @param conn
	 * @param dto
	 * @throws CommonException 
	 */
	public void login(Connection conn, AdminMemberDto dto) throws CommonException {
		String sql = "select * from ADMIN_MEMBER where a_id = ? and a_pass = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getAdminId());
			stmt.setString(2, dto.getAdminPass());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				dto.setAdminId(rs.getString("a_id"));
				dto.setAdminPass(rs.getString("a_pass"));
				dto.setAdminName(rs.getString("a_name"));
				dto.setAdminMobile(rs.getString("a_mobile"));
				dto.setAdminEmail(rs.getString("a_email"));
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
	 * 일반회원 아이디 찾기 
	 * @param conn
	 * @param email
	 * @throws CommonException 
	 */
	public void findId(Connection conn, GeneralMemberDto dto) throws CommonException {
		String sql = "select g_id from general_member where g_name=? and g_email=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, dto.getGeneralName());
			stmt.setString(2, dto.getGeneralEmail());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				dto.setGeneralId(rs.getString("g_id"));
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
	 * 센터회원 아이디 찾기
	 * @param conn
	 * @param center
	 * @throws CommonException 
	 */
	public void findId(Connection conn, CenterMemberDto center) throws CommonException {
		String sql = "select c_id from center_member where c_name=? and c_email=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, center.getCenterName());
			stmt.setString(2, center.getCenterEmail());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				center.setCenterId(rs.getString("c_id"));
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
	////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 문의글 일반회원
	 * @param conn
	 * @param dto
	 * @throws CommonException
	 */
	public void insertQna_gen(Connection conn, QnADto dto) throws CommonException  {
		String sql = "insert into qna(q_no, g_id, q_title, q_contents, q_write_date) values(q_no.nextval, ?, ?, ?, sysdate)";
		
		PreparedStatement stmt = null; //초기화 
		System.out.println();
	
		try {
			 stmt = conn.prepareStatement(sql); // 실행 sql 넣기
			 stmt.setString(1, dto.getGeneralId());
		     stmt.setString(2, dto.getQnaTitle());
		     stmt.setString(3, dto.getQnaContents());
			
			int rows = stmt.executeUpdate(); // 실행
			
			if (rows == 0) {
				throw new Exception();
			}
		} catch (Exception e) {	// SQLException Exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}
	
	
	/**
	 * 문의글 등록 센터회원
	 * @param conn
	 * @param dto
	 * @throws CommonException
	 */
	public void insertQna_cen(Connection conn, QnADto dto) throws CommonException  {
		String sql = "insert into qna(q_no, c_id, q_title, q_contents, q_write_date) values(q_no.nextval, ?, ?, ?, sysdate)";
		
		PreparedStatement stmt = null; //초기화 
		
		try {
			 stmt = conn.prepareStatement(sql);// 실행 sql 넣기
			 
			 stmt.setString(1, dto.getCenterId());
		     stmt.setString(2, dto.getQnaTitle());
		     stmt.setString(3, dto.getQnaContents());
			
			int rows = stmt.executeUpdate(); // 실행
			
			if (rows == 0) {
				throw new Exception();
			}
		} catch (Exception e) {	// SQLException Exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 문의글 검색 조회
	 * @param conn
	 * @param qnaList
	 * @param searchOpt
	 * @param searchText
	 * @throws CommonException
	 */
	public void qnaList(Connection conn, ArrayList<QnADto> qnaList, String searchOpt, String searchText) throws CommonException  {

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select  ");
		sql.append("  q.q_no  ");
		sql.append("  , q.q_title  ");
		sql.append("  , case when q.g_id is not null then (select g.g_name from general_member g where g.g_id = q.g_id)  ");
		sql.append("    else (select c.c_name from center_member c where c.c_id = q.c_id) end as qnaWriter  "); 
		sql.append("  , q.q_write_date  ");
		sql.append("  , 'N' as answerYn  ");
		sql.append(" from qna q ");
		
		if ("T".equals(searchOpt)) {
			sql.append(" where q.q_title like '%'|| ? ||'%' ");
		}else if ("C".equals(searchOpt)) {
			sql.append(" where q.q_contents like '%'|| ? ||'%' ");
		}else if  ("W".equals(searchOpt)) {
			sql.append(" where q.g_id in (select g.g_id from general_member g where g.g_name like '%'||?||'%') ");
			sql.append("    or q.c_id in (select c.c_id from center_member c where c.c_name like '%'||?||'%')  ");
		}
		sql.append(" order by q_no desc   ");
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {

			stmt = conn.prepareStatement(sql.toString());// 쿼리 담기
			
			if ("T".equals(searchOpt) || "C".equals(searchOpt) ) {
				stmt.setString(1, searchText);
			}else if  ("W".equals(searchOpt)){
				stmt.setString(1, searchText);
				stmt.setString(2, searchText);
			}
			
			rs = stmt.executeQuery();
			
			QnADto dto = null;
			
			// 담기 
			while(rs.next()) {
				dto = new QnADto(); // 담는곳 선언 
				dto.setQnaNo(rs.getInt("q_no"));
				dto.setQnaTitle(rs.getString("q_title"));
				dto.setQnaWriter(rs.getString("qnaWriter"));
				dto.setQnaWriteDate(rs.getDate("q_write_date"));
				dto.setAnswerYn(rs.getString("answerYn"));	
				
				qnaList.add(dto); // list 담기 
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
	 * 문의글 상세
	 * @param conn
	 * @param dto
	 * @param qnaNo
	 * @throws CommonException
	 */
	public void qnaDetail(Connection conn, QnADto dto, String qnaNo) throws CommonException{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select  ");
		sql.append("  q.q_no  ");
		sql.append("  , q.q_title  ");
		sql.append("  , case when q.g_id is not null then (select g.g_name from general_member g where g.g_id = q.g_id)  ");
		sql.append("    else (select c.c_name from center_member c where c.c_id = q.c_id) end as qnaWriter  "); 
		sql.append("  , q.q_write_date  ");
		sql.append("  , q.q_contents ");
		sql.append(" from qna q ");
		sql.append(" where q_no = ? ");
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {

			stmt = conn.prepareStatement(sql.toString()); // 쿼리문 담기
			
			stmt.setString(1, qnaNo); // ? 파라미터값 넣기
				
			rs = stmt.executeQuery(); // 실행
			
			// 담기 
			if(rs.next()) { 
				dto.setQnaNo(rs.getInt("q_no"));
				dto.setQnaTitle(rs.getString("q_title"));
				dto.setQnaWriter(rs.getString("qnaWriter"));
				dto.setQnaWriteDate(rs.getDate("q_write_date"));
				dto.setQnaContents(rs.getString("q_contents"));
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
	 * 문의글 수정
	 * @param conn
	 * @param dto
	 * @throws CommonException
	 */
	public void qnaUpdate(Connection conn, QnADto dto) throws CommonException{
		System.out.println("수정 ======");
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update qna  ");
		sql.append("  set q_title = ?   ");
		sql.append("  , q_contents = ?  ");
		sql.append(" where q_no = ? "); //PK
		
		PreparedStatement stmt = null; //초기화 
		
		try {
			 stmt = conn.prepareStatement(sql.toString());// 실행 sql 넣기
			 
			 stmt.setString(1, dto.getQnaTitle());
		     stmt.setString(2, dto.getQnaContents());
		     stmt.setInt(3, dto.getQnaNo());
			
			int rows = stmt.executeUpdate(); // 실행
			
			if (rows == 0) {
				throw new Exception();
			}
		} catch (Exception e) {	// SQLException Exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}
		
	}

	
	/**
	 * 문의글 삭제
	 * @param conn
	 * @param qnaNo
	 * @throws CommonException
	 */
	public void qnaDelete(Connection conn, String qnaNo) throws CommonException{
		System.out.println("삭제 ======");
		String sql = "delete from qna where q_no = ?"; //PK
		
		PreparedStatement stmt = null; //초기화 
		
		try {
			 stmt = conn.prepareStatement(sql);// 실행 sql 넣기
			 
			 stmt.setInt(1, Integer.parseInt(qnaNo));
			
			int rows = stmt.executeUpdate(); // 실행
			
			if (rows == 0) {
				throw new Exception();
			}
		} catch (Exception e) {	// SQLException Exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}
		
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 지역 목록 조회(메인)
	 * @param conn
	 * @param localMap
	 * @throws CommonException
	 */
	public void searchLocal(Connection conn, HashMap<String, LocalDto> localMap) throws CommonException {
		String sql = "select * from local";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				LocalDto dto = new LocalDto();
				dto.setLocalNo(rs.getString(1));
				dto.setLocalName(rs.getString(2));
				
				localMap.put(rs.getString(1), dto);
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
	 * 봉사카테고리 목록 조회(메인)
	 * @param conn
	 * @param categoryMap
	 */
	public void searchVolCategory(Connection conn, HashMap<String, VolCategoryDto> categoryMap) throws CommonException {
		String sql = "select * from vol_category";
	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				VolCategoryDto dto = new VolCategoryDto();
				dto.setCategoryNo(rs.getString(1));
				dto.setCategoryName(rs.getString(2));
				
				categoryMap.put(rs.getString(1), dto);
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
	 * 자원봉사 목록 조회(메인)
	 * @param conn
	 * @param volList
	 */
	public void searchVol(Connection conn, ArrayList<VolBlockDto> volList) throws CommonException {
		String sql = "select i.vol_info_no as 글번호, i.v_title as 제목, i.category_no as 카테고리번호, i.local_no as 지역번호, "
				+ "to_char(i.start_date,'yyyy-mm-dd') as 모집시작일, to_char(i.end_date,'yyyy-mm-dd') as 모집마감일,"
				+ " min(d.vol_date) as 봉사시작일, max(d.vol_date) as 봉사종료일  " 
				+ "from vol_info i, vol_detail d " 
				+ "where i.vol_info_no = d.vol_info_no and d.rec_status = 0 "
				+ "group by i.vol_info_no, i.v_title, i.category_no, i.local_no, i.start_date, i.end_date "
				+ "order by 6";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				VolBlockDto dto = new VolBlockDto();
				dto.setVolInfoNo(rs.getInt(1));
				dto.setVolTitle(rs.getString(2));
				dto.setCategoryNo(rs.getString(3));
				dto.setLocalNo(rs.getString(4));
				dto.setStartDate(rs.getString(5));
				dto.setEndDate(rs.getString(6));
				dto.setStartVolDate(rs.getString(7));
				dto.setEndVolDate(rs.getString(8));
				volList.add(dto);
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
	 * 봉사 대상 조회
	 * @param conn
	 * @param categoryMap
	 */
	public void searchServiceCategory(Connection conn, ArrayList<ServiceCategoryDto> list) throws CommonException {
		String sql = "select * from service_category";
	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				ServiceCategoryDto dto = new ServiceCategoryDto();
				dto.setServiceNo(rs.getString("service_no"));
				dto.setServiceName(rs.getString("service_name"));
				
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
	 * 봉사모집 리스트 전체 조회(모집중)
	 * @param conn
	 * @param list ArrayList<CenterVolDto>
	 * @throws CommonException
	 */
	public void searchVolList(Connection conn, ArrayList<HashMap<String, Object>> list, String[] date) throws CommonException {
		String sql = "select i.vol_info_no as 글번호, i.v_title as 제목, vc.category_name as 봉사분야, "
				+ "i.start_date as 모집시작일, i.end_date as 모집마감일, min(d.vol_date) as 봉사시작일, "
				+ "max(d.vol_date) as 봉사종료일, c_name as 단체이름, min(d.rec_status) as 모집상태, "
				+ "i.end_date - trunc(sysdate) as 마감일수, i.c_id as 센터회원아이디 "
				+ "from vol_info i left join vol_detail d on (i.vol_info_no = d.vol_info_no) "
				+ "left join center_info c on (i.c_id = c.c_id) " 
				+ "left join vol_category vc on (vc.category_no = i.category_no)"
				+ "group by i.vol_info_no, i.v_title, vc.category_name, i.category_no, "
				+ "i.start_date, i.end_date, c_name, i.c_id "
				+ "having min(d.rec_status) = 0 and min(d.vol_date) >= ? and max(d.vol_date) <= ? "
				+ "order by 마감일수";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, date[0]);
			stmt.setString(2, date[1]);
			rs = stmt.executeQuery();
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("volInfoNo", rs.getInt(1));
				map.put("volTitle", rs.getString(2));
				map.put("categoryName", rs.getString(3));
				map.put("startDate", rs.getDate(4));
				map.put("endDate", rs.getDate(5));
				map.put("volStart", rs.getDate(6));
				map.put("volEnd", rs.getDate(7));
				map.put("centerName", rs.getString(8));
				map.put("recStatus", rs.getString(9));
				map.put("deadLine", rs.getInt(10));
				map.put("centerId", rs.getString(11));
				list.add(map);
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
	 * 봉사모집 전체 수 조회(모집중)
	 * @param conn
	 * @return 전체수 반환
	 * @throws CommonException
	 */
	public int volListTotalCount(Connection conn, String[] date) throws CommonException {
		String sql = "select count(*) "
				+ "from (select vol_info_no, min(rec_status) as 모집상태 "
				+ "from vol_detail "
				+ "group by vol_info_no "
				+ "having min(rec_status) = 0 and min(vol_date) >= ? and max(vol_date) <= ?)";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, date[0]);
			stmt.setString(2, date[1]);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
		
		return 0;
	}
	
}

