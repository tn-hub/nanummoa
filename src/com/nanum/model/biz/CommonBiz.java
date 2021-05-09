/**
 * 
 */
package com.nanum.model.biz;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import com.nanum.dto.AdminMemberDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.QnADto;
import com.nanum.dto.ServiceCategoryDto;
import com.nanum.dto.LocalDto;
import com.nanum.dto.VolBlockDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.dto.VolInfoDto;
import com.nanum.model.dao.CommonDao;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;

/**
 * 공통기능 관리를 위한 업무 로직 클래스
 *
 */
public class CommonBiz {
	private CommonDao dao = CommonDao.getInstance();

	/**
	 * 일반회원 로그인
	 * 
	 * @param dto
	 * @throws CommonException
	 */
	public void login(GeneralMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.login(conn, dto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 센터회원 로그인
	 * 
	 * @param dto
	 * @throws CommonException
	 */
	public void login(CenterMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.login(conn, dto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 관리자 로그인
	 * 
	 * @param dto
	 * @throws CommonException
	 */
	public void login(AdminMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.login(conn, dto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 일반회원 아이디 찾기
	 * 
	 * @param email 이메일
	 * @throws CommonException
	 */
	public void findId(GeneralMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.findId(conn, dto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 센터회원 아이디 찾기
	 * 
	 * @param center
	 * @throws CommonException
	 */
	public void findId(CenterMemberDto center) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.findId(conn, center);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 일반회원 비밀번호 찾기(이메일검색)
	 * 
	 * @param dto
	 * @throws CommonException
	 */
	public void findPw(GeneralMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.findPw(conn, dto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 센터회원 비밀번호 찾기(이메일검색)
	 * 
	 * @param dto
	 * @throws CommonException
	 */
	public void findPw(CenterMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.findPw(conn, dto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 일반 회원 아이디/비밀번호 찾기(이메일 인증)
	 * 
	 * @throws CommonException
	 */
	public void checkEmail(GeneralMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.checkEmail(conn, dto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 센터 회원 아이디/비밀번호 찾기(이메일 인증)
	 * 
	 * @param dto
	 * @throws CommonException
	 */
	public void checkEmail(CenterMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.checkEmail(conn, dto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 일반회원 새비밀번호 설정
	 * 
	 * @param dto
	 * @throws Exception 
	 */
	public void newPw(GeneralMemberDto dto) throws Exception {
		Connection conn = JdbcTemplate.getConnection();

			try {
				dao.newPw(conn, dto);
				JdbcTemplate.commit(conn);
			} catch (Exception e) {
				JdbcTemplate.rollback(conn);
				throw e;
			}finally {
				JdbcTemplate.close(conn);
			}
	}

	/**
	 * 센터회원 새비밀번호 설정
	 * @throws Exception 
	 */
	public void newPw(CenterMemberDto dto) throws Exception {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.newPw(conn, dto);
			JdbcTemplate.commit(conn);
		} catch (CommonException e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 문의글 등록 일반회원
	 * @param dto
	 * @throws CommonException
	 */
	public void addQna_gen(QnADto dto) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();// 비즈에서 커넥션 생성해서 dao전달
		
		try {
			dao.insertQna_gen(conn, dto);
			JdbcTemplate.commit(conn); // commit;
		} catch (CommonException e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);// rollback;
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	
	/**
	 * 문의글 등록 센터회원
	 * @param dto
	 * @throws CommonException
	 */
	public void addQna_cen(QnADto dto) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();// 비즈에서 커넥션 생성해서 dao전달
		
		try {
			dao.insertQna_cen(conn, dto);
			JdbcTemplate.commit(conn); // commit;
		} catch (CommonException e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);// rollback;
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 문의글 전체 조회
	 * @param qnaList
	 * @param searchOpt
	 * @param searchText
	 * @throws CommonException
	 */
	
	public void qnaList(ArrayList<QnADto> qnaList, String searchOpt, String searchText) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.qnaList(conn, qnaList, searchOpt, searchText);
			System.out.println("searchOpt : " +searchOpt );
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 문의글 상세 보기
	 * @param dto
	 * @param qnaNo
	 * @throws CommonException
	 */
	public void qnaDetail(QnADto dto, String qnaNo) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.qnaDetail(conn, dto, qnaNo);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 문의글 수정
	 * @param dto
	 * @throws CommonException
	 */
	public void qnaUpdate(QnADto dto)throws CommonException{
		Connection conn = JdbcTemplate.getConnection();// 비즈에서 커넥션 생성해서 dao전달
		
		try {
			dao.qnaUpdate(conn, dto);
			JdbcTemplate.commit(conn); // commit;
		} catch (CommonException e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);// rollback;
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 문의글 삭제
	 * @param qnaNo
	 * @throws CommonException
	 */
	public void qnaDelete(String qnaNo) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();// 비즈에서 커넥션 생성해서 dao전달
		
		try {
			dao.qnaDelete(conn, qnaNo);
			JdbcTemplate.commit(conn); // commit;
		} catch (CommonException e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);// rollback;
		}
	}

	/**
	 * 자원봉사 상세보기
	 * @param VolInfoDto
	 * @throws CommonException
	 */
	public void volDetailInfo(VolInfoDto dto, int volInfoNo) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.selectVolInfo(conn, dto, volInfoNo);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 봉사 상세조회
	 * @param map
	 * @param volInfoNo
	 */
	public void volDetailInfo(HashMap<String, Object> map, int volInfoNo) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.selectVolInfo(conn, map, volInfoNo);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 문의하게 게시판 건수
	 * @param cdto
	 * @throws CommonException
	 */
	public void qnaListTotCnt(QnADto cdto) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.selectQnaListTotCnt(conn, cdto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 자원봉사 목록 조회(메인)
	 * 
	 * @param dto
	 * @throws CommonException
	 */
	public void searchVol(ArrayList<VolBlockDto> volList) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.searchVol(conn, volList);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 봉사카테고리 목록 조회(메인)
	 * 
	 * @param categoryMap
	 */
	public void searchVolCategory(HashMap<String, VolCategoryDto> categoryMap) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.searchVolCategory(conn, categoryMap);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 지역 목록 조회(메인)
	 * 
	 * @param localMap
	 * @throws CommonException
	 */
	public void searchLocal(HashMap<String, LocalDto> localMap) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.searchLocal(conn, localMap);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 봉사 모집 전체 조회
	 * @param list ArrayList<CenterVolDto>
	 * @throws CommonException
	 * @return 봉사모집 전체 수 조회를 위한 sql문
	 */
	public String searchVolList(ArrayList<HashMap<String, Object>> list,  HashMap<String, String> searchMap) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			return dao.searchVolList(conn, list, searchMap);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 봉사 모집 전체 수
	 * @return 봉사 모집 전체 수 반환
	 * @throws CommonException
	 */
	public int volListTotalCount(HashMap<String, String> searchMap, String sql) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			return dao.volListTotalCount(conn, searchMap, sql);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 봉사 대상 전체 조회
	 * @param list ArrayList<ServiceCategoryDto>
	 * @throws CommonException 
	 */
	public void searchServiceCategory(ArrayList<ServiceCategoryDto> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.searchServiceCategory(conn, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 자원봉사 목록 조회(메인)
	 * @param list
	 * @throws CommonException
	 */
	public void searchVolMapList(ArrayList<HashMap<String, Object>> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.searchVolMapList(conn, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}



	
}
