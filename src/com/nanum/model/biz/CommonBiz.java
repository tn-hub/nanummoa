/**
 * 
 */
package com.nanum.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.nanum.dto.AdminMemberDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.QnADto;
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
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
		
	}

}
