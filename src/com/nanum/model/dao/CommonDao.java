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
import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.LocalDto;
import com.nanum.dto.QnADto;
import com.nanum.dto.QnAReplyDto;
import com.nanum.dto.SearchAllDto;
import com.nanum.dto.ServiceCategoryDto;
import com.nanum.dto.VolBlockDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.dto.VolInfoDto;
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
	 * 
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

			if (rs.next()) {
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

			if (rs.next()) {
				dto.setCenterId(rs.getString("c_id"));
				dto.setCenterPass(rs.getString("c_pass"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setCenterMobile(rs.getString("c_mobile"));
				dto.setCenterEmail(rs.getString("c_email"));
				dto.setAppStatus(rs.getString("app_status"));
			}

		} catch (SQLException e) {
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

			if (rs.next()) {
				dto.setAdminId(rs.getString("a_id"));
				dto.setAdminPass(rs.getString("a_pass"));
				dto.setAdminName(rs.getString("a_name"));
				dto.setAdminMobile(rs.getString("a_mobile"));
				dto.setAdminEmail(rs.getString("a_email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 일반회원 아이디 찾기
	 * 
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

			if (rs.next()) {
				dto.setGeneralId(rs.getString("g_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 센터회원 아이디 찾기
	 * 
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

			if (rs.next()) {
				center.setCenterId(rs.getString("c_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 일반회원 비밀번호 찾기(이메일 검색)
	 *  
	 * @param conn
	 * @param dto
	 * @throws CommonException 
	 */
	public void findPw(Connection conn, GeneralMemberDto dto) throws CommonException {
		//String sql = "select lpad('*',1) || substr(g_email,2) as g_email from general_member where g_id=?";
		String sql = "select g_email from general_member where g_id=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getGeneralId());

			rs = stmt.executeQuery();

			if (rs.next()) {
				dto.setGeneralEmail(rs.getString("g_email"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
	/**
	 * 센터회원 비밀번호 찾기(이메일 검색)
	 *  
	 * @param conn
	 * @param dto
	 * @throws CommonException 
	 */
	public void findPw(Connection conn, CenterMemberDto dto) throws CommonException {
		//String sql = "select lpad('*',1) || substr(c_email,2) as c_email from center_member where c_id=?";
		String sql = "select c_email from center_member where c_id=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getCenterId());

			rs = stmt.executeQuery();

			if (rs.next()) {
				dto.setCenterEmail(rs.getString("c_email"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
	/**
	 * 일반회원 아이디/비밀번호 찾기(이메일 인증)
	 * @param conn
	 * @param name
	 * @param email
	 * @throws CommonException 
	 */
	public void checkEmail(Connection conn, GeneralMemberDto dto) throws CommonException {
		String sql = "select * from general_member where g_name = ? and g_email = ?";	
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getGeneralName());
			stmt.setString(2, dto.getGeneralEmail());

			rs = stmt.executeQuery();

			if (rs.next()) {
				dto.setGeneralId(rs.getString("g_id"));
				dto.setGeneralPass(rs.getString("g_pass"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
	/**
	 * 센터회원 아이디/비밀번호 찾기(이메일 인증)
	 * 
	 * @param conn
	 * @param dto
	 * @throws CommonException
	 */
	public void checkEmail(Connection conn, CenterMemberDto dto) throws CommonException {
		String sql = "select * from center_member where c_name = ? and c_email = ?";	
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getCenterName());
			stmt.setString(2, dto.getCenterEmail());

			rs = stmt.executeQuery();

			if (rs.next()) {
				dto.setCenterId(rs.getString("c_id"));
				dto.setCenterPass(rs.getString("c_pass"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 일반회원 새비밀번호 설정
	 * 
	 * @param conn
	 * @param dto
	 * @throws Exception 
	 */
	public void newPw(Connection conn, GeneralMemberDto dto) throws Exception {
		String sql = "update general_member set g_pass = ? where g_email=?"; 
		
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, dto.getGeneralPass());
			stmt.setString(2, dto.getGeneralEmail());

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
	 * 센터회원 새비밀번호 설정
	 * 
	 * @param conn
	 * @param dto
	 * @throws Exception 
	 */
	public void newPw(Connection conn, CenterMemberDto dto) throws Exception {
		String sql = "update center_member set c_pass = ? where c_email=?"; 
		
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, dto.getCenterPass());
			stmt.setString(2, dto.getCenterEmail());

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
	 * 문의글 일반회원
	 * @param conn
	 * @param dto
	 * @throws CommonException
	 */
	public void insertQna_gen(Connection conn, QnADto dto) throws CommonException  {
		String sql = "insert into qna(q_no, g_id, q_title, q_contents, q_write_date) values(q_no.nextval, ?, ?, ?, sysdate)";
		
		PreparedStatement stmt = null; //초기화 
	
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
			
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 문의글 목록 조회
	 * @param conn
	 * @param qnaList
	 * @param searchOpt
	 * @param searchText
	 * @param lastNum 
	 * @param sartNum 
	 * @throws CommonException
	 */
	public void qnaList(Connection conn, ArrayList<QnADto> qnaList, String searchOpt, String searchText, Integer sartNum, Integer lastNum) throws CommonException  {

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select  ");
		sql.append("  q.q_no  ");
		sql.append("  , q.q_title  ");
		sql.append("  , case when q.g_id is not null then (select g.g_name from general_member g where g.g_id = q.g_id)  ");
		sql.append("    else (select c.c_name from center_member c where c.c_id = q.c_id) end as qnaWriter "); 
		sql.append("  , q.q_write_date  ");
		sql.append("  , (select case when count(1) > 0 then 'Y' else 'N' end  from qna_reply r where r.q_no = q.q_no) as answerYn  ");
		sql.append(" from qna q");
		
		
		if ("T".equals(searchOpt)) {
			sql.append(" where q.q_title like '%'|| ? ||'%' ");
		}else if ("C".equals(searchOpt)) {
			sql.append(" where q.q_contents like '%'|| ? ||'%' ");
		}else if  ("W".equals(searchOpt)) {
			sql.append(" where q.g_id in (select g.g_id from general_member g where g.g_name like '%'||?||'%') ");
			sql.append(" or q.c_id in (select c.c_id from center_member c where c.c_name like '%'||?||'%')  ");
		} else {
			sql.append(" where q.q_title like '%'|| ? ||'%' ");
			sql.append(" or q.q_contents like '%'|| ? ||'%' ");
			sql.append(" or q.g_id in (select g.g_id from general_member g where g.g_name like '%'||?||'%') ");
			sql.append(" or q.c_id in (select c.c_id from center_member c where c.c_name like '%'||?||'%') ");
		}
		
		sql.append(" order by q_no desc");
		
		// 페이징 추가 쿼리 								
		String page_sql = "select b.* from (select a.*, rownum as page_num from (" + sql.toString() + ") a ) b where page_num between ? and ?";

		 
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(page_sql);// 쿼리 담기
			
			if ("T".equals(searchOpt) || "C".equals(searchOpt) ) {
				stmt.setString(1, searchText);
				stmt.setInt(2, sartNum);
				stmt.setInt(3, lastNum);
			}else if  ("W".equals(searchOpt)){
				stmt.setString(1, searchText);
				stmt.setString(2, searchText);
				stmt.setInt(3, sartNum);
				stmt.setInt(4, lastNum);
			} else {
				stmt.setString(1, searchText);
				stmt.setString(2, searchText);
				stmt.setString(3, searchText);
				stmt.setString(4, searchText);
				stmt.setInt(5, sartNum);
				stmt.setInt(6, lastNum);
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
		sql.append(" q.q_no  ");
		sql.append(" , q.q_title  ");
		sql.append(" , case when q.g_id is not null then (select g.g_name from general_member g where g.g_id = q.g_id)  ");
		sql.append(" else (select c.c_name from center_member c where c.c_id = q.c_id) end as qnaWriter  "); 
		sql.append(" , q.q_write_date  ");
		sql.append(" , q.q_contents ");
		sql.append(" , q.g_id ");
		sql.append(" , q.c_id ");
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
				dto.setGeneralId(rs.getString("g_id"));
				dto.setCenterId(rs.getString("c_id"));
			}
			
		} catch (SQLException e) {
			
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
			
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}
		
	}

	/**
	 * 자원봉사 상세조회
	 * @param conn
	 * @param dto
	 * @param volInfoNo
	 * @throws CommonException
	 */
	public void selectVolInfo(Connection conn, VolInfoDto dto, int volInfoNo) throws CommonException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select  ");
		sql.append("   v.vol_info_no  ");
		sql.append("   , v.c_id ");
		sql.append("   , v.v_title ");
		sql.append("   , (select sum(d.apply_count) from vol_detail d where d.vol_info_no = v.vol_info_no group by d.vol_info_no) apply_count ");
		sql.append("   , (select max(d.total_count) from vol_detail d where d.vol_info_no = v.vol_info_no group by d.vol_info_no) total_count ");
		sql.append("   , (select case when count(1) > 0 then '모집중' else '모집마감' end from vol_detail d where d.vol_info_no = v.vol_info_no and d.rec_status = 0) rec_status ");
		sql.append("   , v.v_content ");
		sql.append("   , v.vol_write_date  ");
		sql.append("   , to_char(v.start_time,'YYYY-MM-DD') as vol_start_date ");
		sql.append("   , to_char(v.end_time,'YYYY-MM-DD') as vol_end_date  ");
		sql.append("   , to_char(v.start_time,'HH24:MI') as start_time ");
		sql.append("   , to_char(v.end_time,'HH24:MI') as end_time  ");
		sql.append("   , v.start_date ");
		sql.append("   , v.end_date ");
		sql.append("   , v.v_type ");
		sql.append("   , v.v_place ");
		sql.append("   , v.latitude ");
		sql.append("   , v.longitude ");
		sql.append("   , v.v_subject ");
		sql.append("   , v.category_no ");
		sql.append("   , (select category_name from vol_category c where c.category_no = v.category_no) as category_name");
		sql.append("   , m.c_name ");
		sql.append("   , m.c_mobile ");
		sql.append("   , c.c_address ");
		sql.append("   , m.c_email ");
		sql.append(" from vol_info v, center_member m, center_info c  ");
		sql.append(" where v.vol_info_no = ? ");
		sql.append("   and v.c_id = m.c_id ");
		sql.append("   and v.c_id = c.c_id ");
		  
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {

			stmt = conn.prepareStatement(sql.toString()); 
			
			stmt.setInt(1, volInfoNo);
				
			rs = stmt.executeQuery();
			
			if(rs.next()) { 
				dto.setVolInfoNo(rs.getInt("vol_info_no")); // 글번호 
				dto.setCenterId(rs.getString("c_id")); // 센터회원아이디
				dto.setVolTitle(rs.getString("v_title")); // 제목
				dto.setApplyCount(rs.getInt("apply_count")); // 신청인원
				dto.setTotalCount(rs.getInt("total_count")); // 모집인원
				dto.setRecStatuse(rs.getString("rec_status")); // 모집상태
				dto.setVolContents(rs.getString("v_content")); // 내용
				dto.setStartTime(rs.getDate("vol_start_date")); // 봉사 시작일
				dto.setEndTime(rs.getDate("vol_end_date"));  // 봉사 마감일
				dto.setVolStartTime(rs.getString("start_time"));  // 봉사 시작 시간
				dto.setVolEndTime(rs.getString("end_time"));  // 봉사 마감 시간
				dto.setStartDate(rs.getDate("start_date")); //  모집시작일
				dto.setEndDate(rs.getDate("end_date"));    // 모집마감일
				dto.setVolType(rs.getString("v_type")); // 봉사자 유형
				dto.setVolPlace(rs.getString("v_place")); // 봉사장소
				dto.setLatitude(rs.getString("latitude")); // 위도
				dto.setLongitude(rs.getString("longitude")); // 경도
				dto.setVolSubject(rs.getString("v_subject")); // 
				dto.setName(rs.getString("c_name"));    // 담당자명
				dto.setMobile(rs.getString("c_mobile")); // 전화번호 
				dto.setAddress(rs.getString("c_address")); // 주소 
				dto.setCategoryNo(rs.getString("category_no")); // 봉사분야 번호
				dto.setCategoryName(rs.getString("category_name"));	// 봉사 분야 
				dto.setEmail(rs.getString("c_email"));	// 봉사 분야 
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 봉사상세조회
	 * @param conn
	 * @param map
	 * @param volInfoNo
	 */
	public void selectVolInfo(Connection conn, HashMap<String, Object> map, int volInfoNo) throws CommonException{
		String sql = "select \r\n" + 
				"i.vol_info_no, i.c_id, i.v_title, i.v_content, i.vol_write_date\r\n" + 
				", to_char(i.start_time,'HH24:MI'), to_char(i.end_time,'HH24:MI'), to_char(i.start_date,'yyyy-mm-dd'), to_char(i.end_date,'yyyy-mm-dd') \r\n" + 
				", i.category_no, i.local_no, i.v_type, i.v_place, i.v_subject, min(d.total_count) as total_count\r\n" + 
				", min(d.vol_date) as 봉사시작일, max(d.vol_date) as 봉사종료일, i.latitude, i.longitude\r\n" + 
				"from vol_info i, vol_detail d\r\n" + 
				"where i.vol_info_no = d.vol_info_no\r\n" + 
				"and i.vol_info_no = ? \r\n" + 
				"group by i.vol_info_no, i.c_id, i.v_title, i.v_content, i.vol_write_date, \r\n" + 
				"to_char(i.start_time,'HH24:MI'), i.start_time, 'HH24:MI', to_char(i.end_time,'HH24:MI'), i.end_time, \r\n" + 
				"'HH24:MI', to_char(i.start_date,'yyyy-mm-dd'), i.start_date, 'yyyy-mm-dd', to_char(i.end_date,'yyyy-mm-dd'), \r\n" + 
				"i.end_date, 'yyyy-mm-dd', i.category_no, i.local_no, i.v_type, \r\n" + 
				"i.v_place, i.v_subject, i.latitude, i.longitude";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, volInfoNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				map.put("volInfoNo", rs.getString(1));
				map.put("centerId", rs.getString(2));
				map.put("volTitle", rs.getString(3));
				map.put("volContents", rs.getString(4));
				map.put("volWriteDate", rs.getString(5));
				map.put("startTime", rs.getString(6));
				map.put("endTime", rs.getString(7));
				map.put("startDate", rs.getString(8));
				map.put("endDate", rs.getString(9));
				map.put("categoryNo", rs.getString(10));
				map.put("localNo", rs.getString(11));
				map.put("volType", rs.getString(12));
				map.put("volPlace", rs.getString(13));
				map.put("volSubject", rs.getString(14));
				map.put("totalCount", rs.getString(15));
				map.put("startVolDate", rs.getString(16));
				map.put("endVolDate", rs.getString(17));
				map.put("latitude", rs.getString(18));
				map.put("longitude", rs.getString(19));
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
	 * 문의글 전체조회 건수
	 * @param conn
	 * @param cdto
	 * @param searchText 
	 * @param searchOpt 
	 * @throws CommonException
	 */
	public void selectQnaListTotCnt(Connection conn, QnADto cdto, String searchOpt, String searchText) throws CommonException{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) as tot_cnt ");
		sql.append(" from qna q ");
		
		if (searchText != null && searchText != "") {
			if ("T".equals(searchOpt)) {
				sql.append(" where q.q_title like '%'|| ? ||'%' ");
			}else if ("C".equals(searchOpt)) {
				sql.append(" where q.q_contents like '%'|| ? ||'%' ");
			}else if  ("W".equals(searchOpt)) {
				sql.append(" where q.g_id in (select g.g_id from general_member g where g.g_name like '%'||?||'%') ");
				sql.append(" or q.c_id in (select c.c_id from center_member c where c.c_name like '%'||?||'%')  ");
			} else {
				sql.append(" where q.q_title like '%'|| ? ||'%' ");
				sql.append(" or q.q_contents like '%'|| ? ||'%' ");
				sql.append(" or q.g_id in (select g.g_id from general_member g where g.g_name like '%'||?||'%') ");
				sql.append(" or q.c_id in (select c.c_id from center_member c where c.c_name like '%'||?||'%') ");
			}
		}
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql.toString());// 쿼리 담기
			
			if (searchText != null && searchText != "") {
				if ("T".equals(searchOpt) || "C".equals(searchOpt) ) {
					stmt.setString(1, searchText);
				}else if  ("W".equals(searchOpt)){
					stmt.setString(1, searchText);
					stmt.setString(2, searchText);
				} else {
					stmt.setString(1, searchText);
					stmt.setString(2, searchText);
					stmt.setString(3, searchText);
					stmt.setString(4, searchText);
				}
			}
			
			rs = stmt.executeQuery();
			
			if(rs.next()) { 
				cdto.setTotCnt(rs.getInt("tot_cnt"));  
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 지역 목록 조회(메인)
	 * 
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
			while (rs.next()) {
				LocalDto dto = new LocalDto();
				dto.setLocalNo(rs.getString(1));
				dto.setLocalName(rs.getString(2));

				localMap.put(rs.getString(1), dto);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 봉사카테고리 목록 조회(메인)
	 * 
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
			while (rs.next()) {
				VolCategoryDto dto = new VolCategoryDto();
				dto.setCategoryNo(rs.getString(1));
				dto.setCategoryName(rs.getString(2));

				categoryMap.put(rs.getString(1), dto);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 자원봉사 목록 조회(메인)
	 * 
	 * @param conn
	 * @param volList
	 */
	public void searchVol(Connection conn, ArrayList<VolBlockDto> volList) throws CommonException {
		String sql = "select i.vol_info_no as 글번호, i.v_title as 제목, i.category_no as 카테고리번호, i.local_no as 지역번호, "
				+ "to_char(i.start_date,'yyyy-mm-dd') as 모집시작일, to_char(i.end_date,'yyyy-mm-dd') as 모집마감일,"
				+ " min(d.vol_date) as 봉사시작일, max(d.vol_date) as 봉사종료일  " 
				+ "from vol_info i, vol_detail d " 
				+ "where i.vol_info_no = d.vol_info_no "
				+ "group by i.vol_info_no, i.v_title, i.category_no, i.local_no, i.start_date, i.end_date "
				+ "order by 1 desc";
		
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
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 자원봉사 목록 조회(메인) : map
	 */
	public void searchVolMapList(Connection conn, ArrayList<HashMap<String, Object>> list) throws CommonException {
		String sql = "select i.vol_info_no as 글번호, i.v_title as 제목, i.category_no as 카테고리번호, i.local_no as 지역번호, "
				+ "to_char(i.start_date,'yyyy-mm-dd') as 모집시작일, to_char(i.end_date,'yyyy-mm-dd') as 모집마감일,"
				+ " min(d.vol_date) as 봉사시작일, max(d.vol_date) as 봉사종료일, d.rec_status  " 
				+ "from vol_info i, vol_detail d " 
				+ "where i.vol_info_no = d.vol_info_no "
				+ "group by i.vol_info_no, i.v_title, i.category_no, i.local_no, i.start_date, i.end_date, d.rec_status "
				+ "order by 9";
		HashMap<String, Object> map = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				map = new HashMap<String, Object>();
				map.put("volInfoNo", rs.getInt(1));
				map.put("volTitle", rs.getString(2));
				map.put("categoryNo", rs.getString(3));
				map.put("localNo", rs.getString(4));
				map.put("startDate", rs.getString(5));
				map.put("endDate", rs.getString(6));
				map.put("startVolDate", rs.getString(7));
				map.put("endVolDate", rs.getString(8));
				map.put("recStatus", rs.getString(9));
				list.add(map);
			} 
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
	/**
	 * 봉사모집 리스트 전체 조회
	 * @param conn
	 * @param list ArrayList<CenterVolDto>
	 * @throws CommonException
	 * @return 봉사모집 전체 수 조회를 위한 sql문
	 */
	public String searchVolList(Connection conn, ArrayList<HashMap<String, Object>> list, HashMap<String, String> searchMap) throws CommonException {
		String sql = "select i.vol_info_no as 글번호, i.v_title as 제목, vc.category_name as 봉사분야, "
				+ "i.start_date as 모집시작일, i.end_date as 모집마감일, min(d.vol_date) as 봉사시작일, "
				+ "max(d.vol_date) as 봉사종료일, c_name as 단체이름, min(d.rec_status) as 모집상태, "
				+ "i.end_date - trunc(sysdate) as 마감일수, i.c_id as 센터회원아이디 ";
		
		String countSql = "from vol_info i left join vol_detail d on (i.vol_info_no = d.vol_info_no) "
				+ "left join center_info c on (i.c_id = c.c_id) " 
				+ "left join vol_category vc on (vc.category_no = i.category_no) ";
				
		
				if (!searchMap.get("local").equals("0") || !searchMap.get("category").equals("0") || !searchMap.get("service").equals("0") ||
					searchMap.get("volType") != null || searchMap.get("volTitle") != null || searchMap.get("centerName") != null) {
					
					String whereSql = "where ";
					if (!searchMap.get("local").equals("0")) {
						whereSql  += "i.v_place like '%" + searchMap.get("local") + "%' and ";
					}
					if (!searchMap.get("category").equals("0")) {
						whereSql  += "i.category_no = '" + searchMap.get("category") + "' and ";
					}
					if (!searchMap.get("service").equals("0")) {
						whereSql  += "i.v_subject = '" + searchMap.get("service") + "' and ";
					}
					if (searchMap.get("volType") != null) {
						whereSql  += "i.v_type like '%" + searchMap.get("volType") + "%' and ";
					}
					if (searchMap.get("volTitle") != null) {
						whereSql  += "i.v_title like '%" + searchMap.get("volTitle") + "%' and ";
					}
					if (searchMap.get("centerName") != null) {
						whereSql  += "c.c_name like '%" + searchMap.get("centerName") + "%' and ";
					}
					
					int index = whereSql.lastIndexOf("and");
					whereSql = whereSql.substring(0, index - 1) + " ";
					
					countSql += whereSql;
				}
				
				countSql += "group by i.vol_info_no, i.v_title, vc.category_name, i.category_no, "
				+ "i.start_date, i.end_date, c_name, i.c_id having ";
				
				if (searchMap.get("status") != null) {
					countSql += "min(d.rec_status) = " + Integer.parseInt(searchMap.get("status")) + " and ";
				}
				
				countSql += "min(d.vol_date) >= ? and max(d.vol_date) <= ? ";
				sql += countSql;
				sql += "order by 마감일수";
				
				
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, searchMap.get("volStart"));
			stmt.setString(2, searchMap.get("volEnd"));
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
			return countSql;
		} catch (SQLException e) {
			
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
	public int volListTotalCount(Connection conn, HashMap<String, String> searchMap, String countSql) throws CommonException {
		String sql = "select count(*) "
				+ "from (select i.vol_info_no "
				+ countSql
				+ ")";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, searchMap.get("volStart"));
			stmt.setString(2, searchMap.get("volEnd"));
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
		
		return 0;
	}
	
	/**
	 * 문의하기 게시글 댓글 조회
	 * @param conn
	 * @param qnaNo 게시글 번호
	 * @param list ArrayList<QnAReplyDto>
	 * @throws CommonException
	 */
	public void selectQnaReply(Connection conn, String qnaNo, ArrayList<QnAReplyDto> list) throws CommonException {
		String sql = "select r_no, a_id, r_contents, to_char(r_write_date, 'yyyy-mm-dd') from qna_reply where q_no = ? order by 4, 1";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, qnaNo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				QnAReplyDto dto = new QnAReplyDto();
				dto.setReplyNo(rs.getInt(1));
				dto.setAdminId(rs.getString(2));
				dto.setReplyContents(rs.getString(3));
				dto.setReplyWriteDate(rs.getString(4));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	
	/** 통합검색 
	 * @param lastNum 
	 * @param sartNum */
	public void searchAllList(Connection conn, ArrayList<SearchAllDto> saList, String searchAllOpt,String searchAllText, Integer sartNum, Integer lastNum) throws CommonException {
		StringBuilder sql = new StringBuilder();
		
		if (searchAllOpt.equals("V")) {
			sql.append(" select ");
			sql.append("   '[자원봉사] ' division_name ");
			sql.append("   , 'vol' as division_sub ");
			sql.append("   , v.vol_info_no as info_no ");
			sql.append("   , (select c.c_name from center_member c where c.c_id = v.c_id) as writer "); 
			sql.append("   , v.v_title as title ");
			sql.append("   , case when length(v.v_content) < 200 then v.v_content ");
			sql.append("     else substr(v.v_content, 0, 200)||'...' end contents "); 
			sql.append("   , rownum as page_num ");
			sql.append("  from vol_info v ");
			sql.append("   where v.v_title like  '%'|| ? || '%' ");
			sql.append("   or v.v_content like '%'|| ? || '%' ");
			
			sql.append("  order by v.vol_info_no desc ");
			
		}else if (searchAllOpt.equals("Q")) {
			sql.append(" select ");
			sql.append("   '[QNA] 'as division_name ");
			sql.append("   , 'qna' as division_sub ");
			sql.append("   , q.q_no as info_no ");
			sql.append("   , case when q.g_id is not null then (select g.g_name from general_member g where g.g_id = q.g_id) ");
			sql.append("    else (select c.c_name from center_member c where c.c_id = q.c_id)  end writer ");
			sql.append("   , q.q_title as title ");
			sql.append("   , case when length(q.q_contents) < 200 then q.q_contents ");
			sql.append("     else substr(q.q_contents, 0, 200)||'...' end contents ");
			sql.append("   , rownum as page_num ");
			sql.append("   from qna q ");
			sql.append("   where q.q_title like '%'|| ? || '%' ");
			sql.append("   or q.q_contents like  '%'|| ? || '%' ");
			
			sql.append("  order by q.q_no desc ");
			
		}else {
			sql.append(" select division_name, division_sub, info_no,writer, title, contents, rownum as page_num from (  ");
			sql.append(" select ");
			sql.append("   '[자원봉사] ' division_name ");
			sql.append("   , 'vol' as division_sub ");
			sql.append("   , v.vol_info_no as info_no ");
			sql.append("   , (select c.c_name from center_member c where c.c_id = v.c_id) as writer "); 
			sql.append("   , v.v_title as title ");
			sql.append("   , case when length(v.v_content) < 200 then v.v_content ");
			sql.append("     else substr(v.v_content, 0, 200)||'...' end contents ");
			sql.append("  from vol_info v ");
			sql.append("   where v.v_title like  '%'|| ? || '%' ");
			sql.append("   or v.v_content like '%'|| ? || '%' ");
			
			sql.append(" union ");
			
			sql.append(" select ");
			sql.append("   '[QNA] 'as division_name ");
			sql.append("   , 'qna' as division_sub ");
			sql.append("   , q.q_no as info_no ");
			sql.append("   , case when q.g_id is not null then (select g.g_name from general_member g where g.g_id = q.g_id) ");
			sql.append("    else (select c.c_name from center_member c where c.c_id = q.c_id)  end writer ");
			sql.append("   , q.q_title as title ");
			sql.append("   , case when length(q.q_contents) < 200 then q.q_contents ");
			sql.append("     else substr(q.q_contents, 0, 200)||'...' end contents ");
			sql.append("   from qna q ");
			sql.append("   where q.q_title like '%'|| ? || '%' ");
			sql.append("   or q.q_contents like  '%'|| ? || '%' ");
			sql.append("  ) ");
		
		}
		
		// 페이징 추가 쿼리 
		String page_sql = "select * from ("+sql.toString()+") where page_num between ? and ?";
				
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(page_sql);// 쿼리 담기
			
		    if (searchAllOpt.equals("V") || searchAllOpt.equals("Q")) {
				stmt.setString(1, searchAllText);
				stmt.setString(2, searchAllText);
				stmt.setInt(3, sartNum);
				stmt.setInt(4, lastNum);
		    }else {
		    	stmt.setString(1, searchAllText);
				stmt.setString(2, searchAllText);
				stmt.setString(3, searchAllText);
				stmt.setString(4, searchAllText);
				stmt.setInt(5, sartNum);
				stmt.setInt(6, lastNum);
		    }
			
			rs = stmt.executeQuery();
			
			SearchAllDto dto = null;
			
			// 담기 
			while(rs.next()) {
				dto = new SearchAllDto(); // 담는곳 선언 
				dto.setDvisionName(rs.getNString("division_name"));
				dto.setDivisionSub(rs.getString("division_sub"));
				dto.setInfoNo(rs.getInt("info_no"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));

				saList.add(dto); // list 담기 
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
	
	/**
	 * 지역별 신청자 수
	 * @param conn
	 * @param list ArrayList<HashMap<String, Object>>
	 * @throws CommonException
	 */
	public void selectLocalStatistics(Connection conn, ArrayList<HashMap<String, Object>> list) throws CommonException {
		String sql = "select rownum, a.*\r\n" + 
				"from (select l.local_name, sum(d.apply_count)\r\n" + 
				"    from local l left join vol_info i on(l.local_no = i.local_no)\r\n" + 
				"    left join  vol_detail d on (i.vol_info_no = d.vol_info_no)\r\n" + 
				"    group by l.local_name\r\n" + 
				"    having sum(d.apply_count) is not null\r\n" + 
				"    order by 2 desc) a\r\n" + 
				"where rownum <= 5";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("localName", rs.getString(2));
				map.put("count", rs.getString(3));
				
				list.add(map);
			}
			} catch (SQLException e) {
				
				e.printStackTrace();
				throw new CommonException();
			} finally {
				JdbcTemplate.close(rs);
				JdbcTemplate.close(stmt);
			}
		}
	
	/**
	 * 분야별 자원봉사 모집 현황
	 * @param conn
	 * @param list ArrayList<HashMap<String, Object>>
	 * @throws CommonException
	 */
	public void selectCategoryStatistics(Connection conn, ArrayList<HashMap<String, Object>> list) throws CommonException {
		String sql = "select rownum, a.*\r\n" + 
				"from (select c.category_name, count(i.vol_info_no)\r\n" + 
				"    from vol_category c left join  vol_info i on (c.category_no = i.category_no)\r\n" + 
				"    group by c.category_name\r\n" + 
				"    order by 2 desc) a\r\n" + 
				"where rownum <= 5";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("categoryName", rs.getString(2));
				map.put("count", rs.getString(3));
				
				list.add(map);
			}
			} catch (SQLException e) {
				
				e.printStackTrace();
				throw new CommonException();
			} finally {
				JdbcTemplate.close(rs);
				JdbcTemplate.close(stmt);
			}
		}
	
	/**
	 * 이용자별 가입 현황
	 * @param conn
	 * @param map HashMap<String, Integer
	 * @throws CommonException
	 */
	public void selectMemberStatistics(Connection conn, HashMap<String, Integer> map) throws CommonException {
		String sql = "select (select count(*)\r\n" + 
				"from general_member\r\n" + 
				"where birthday > SYSDATE - (INTERVAL '19' YEAR)) as 청소년,\r\n" + 
				"(select count(*)\r\n" + 
				"from general_member\r\n" + 
				"where birthday <= SYSDATE - (INTERVAL '19' YEAR)) as 성인,\r\n" + 
				"(select count(*)\r\n" + 
				"from center_member) as 센터회원\r\n" + 
				"from dual";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				map.put("teenager", rs.getInt(1));
				map.put("adult", rs.getInt(2));
				map.put("centerMember", rs.getInt(3));
			}
			} catch (SQLException e) {
				
				e.printStackTrace();
				throw new CommonException();
			} finally {
				JdbcTemplate.close(rs);
				JdbcTemplate.close(stmt);
			}
		}
	
	/**
	 * 봉사모집 전체조회 페이징 추가
	 * @param conn
	 * @param list ArrayList<HashMap<String, Object>>
	 * @param searchMap HashMap<String, String>
	 * @param startNum
	 * @param lastNum
	 * @throws CommonException
	 */
	public void searchVolListWithPaging(Connection conn, ArrayList<HashMap<String, Object>> list, HashMap<String, String> searchMap, int startNum, int lastNum) throws CommonException {
		String sql = "select i.vol_info_no as 글번호, i.v_title as 제목, vc.category_name as 봉사분야, "
				+ "i.start_date as 모집시작일, i.end_date as 모집마감일, min(d.vol_date) as 봉사시작일, "
				+ "max(d.vol_date) as 봉사종료일, c_name as 단체이름, min(d.rec_status) as 모집상태, "
				+ "i.end_date - trunc(sysdate) as 마감일수, i.c_id as 센터회원아이디 ";
		
		String countSql = "from vol_info i left join vol_detail d on (i.vol_info_no = d.vol_info_no) "
				+ "left join center_info c on (i.c_id = c.c_id) " 
				+ "left join vol_category vc on (vc.category_no = i.category_no) ";
				
		
				if (!searchMap.get("local").equals("0") || !searchMap.get("category").equals("0") || !searchMap.get("service").equals("0") ||
					searchMap.get("volType") != null || searchMap.get("volTitle") != null || searchMap.get("centerName") != null) {
					
					String whereSql = "where ";
					if (!searchMap.get("local").equals("0")) {
						whereSql  += "i.v_place like '%" + searchMap.get("local") + "%' and ";
					}
					if (!searchMap.get("category").equals("0")) {
						whereSql  += "i.category_no = '" + searchMap.get("category") + "' and ";
					}
					if (!searchMap.get("service").equals("0")) {
						whereSql  += "i.v_subject = '" + searchMap.get("service") + "' and ";
					}
					if (searchMap.get("volType") != null) {
						whereSql  += "i.v_type like '%" + searchMap.get("volType") + "%' and ";
					}
					if (searchMap.get("volTitle") != null) {
						whereSql  += "i.v_title like '%" + searchMap.get("volTitle") + "%' and ";
					}
					if (searchMap.get("centerName") != null) {
						whereSql  += "c.c_name like '%" + searchMap.get("centerName") + "%' and ";
					}
					
					int index = whereSql.lastIndexOf("and");
					whereSql = whereSql.substring(0, index - 1) + " ";
					
					countSql += whereSql;
				}
				
				countSql += "group by i.vol_info_no, i.v_title, vc.category_name, i.category_no, "
				+ "i.start_date, i.end_date, c_name, i.c_id having ";
				
				if (searchMap.get("status") != null) {
					countSql += "min(d.rec_status) = " + Integer.parseInt(searchMap.get("status")) + " and ";
				}
				
				countSql += "min(d.vol_date) >= ? and max(d.vol_date) <= ? ";
				sql += countSql;
				sql += "order by 마감일수";
			String page_sql = "select b.* from (select a.*, rownum as page_num from (" + sql + ") a ) b where page_num between ? and ?";
				
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(page_sql);

			stmt.setString(1, searchMap.get("volStart"));
			stmt.setString(2, searchMap.get("volEnd"));
			stmt.setInt(3, startNum);
			stmt.setInt(4, lastNum);
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
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}


	/**
	 * 통합검색
	 * 
	 * @param conn
	 * @param aDto
	 * @param searchAllOpt
	 * @param searchAllText
	 * @throws CommonException
	 */
	public void selectSearchAllListTotCnt(Connection conn, SearchAllDto aDto, String searchAllOpt, String searchAllText) throws CommonException {
		StringBuilder sql = new StringBuilder();
		
		if (searchAllOpt.equals("V")) {
			sql.append(" select count(1) as tot_cnt ");
			sql.append("  from vol_info v ");
			sql.append("   where v.v_title like  '%'|| ? || '%' ");
			sql.append("   or v.v_content like '%'|| ? || '%' ");
			
		}else if (searchAllOpt.equals("Q")) {
			sql.append(" select count(1) as tot_cnt ");
			sql.append("   from qna q ");
			sql.append("   where q.q_title like '%'|| ? || '%' ");
			sql.append("   or q.q_contents like  '%'|| ? || '%' ");
			
		}else {
			
			sql.append(" select count(1) as tot_cnt from (  ");
			sql.append(" select v.vol_info_no, v.v_title, v.v_content ");
			sql.append("  from vol_info v ");
			sql.append("   where v.v_title like  '%'|| ? || '%' ");
			sql.append("   or v.v_content like '%'|| ? || '%' ");
			
			sql.append(" union ");
			
			sql.append(" select q.q_no, q.q_title, q.q_contents ");
			sql.append("   from qna q ");
			sql.append("   where q.q_title like '%'|| ? || '%' ");
			sql.append("   or q.q_contents like  '%'|| ? || '%' ");
			sql.append(" ) ");
			
		
		}
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(sql.toString());// 쿼리 담기
			
		    if (searchAllOpt.equals("V") || searchAllOpt.equals("Q")) {
				stmt.setString(1, searchAllText);
				stmt.setString(2, searchAllText);
		    }else {
		    	stmt.setString(1, searchAllText);
				stmt.setString(2, searchAllText);
				stmt.setString(3, searchAllText);
				stmt.setString(4, searchAllText);
		    }
			
			rs = stmt.executeQuery();
			
			// 담기 
			if(rs.next()) {
				aDto.setTotCnt(rs.getInt("tot_cnt"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
}



