/**
 * 
 */
package com.nanum.model.biz;

import java.sql.Connection;

import com.nanum.dto.AdminMemberDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.GeneralMemberDto;
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
	 * @param email	이메일
	 * @throws CommonException 
	 */
	public void findId(GeneralMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.findId(conn,dto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(conn);
		}
		
	}
	/**
	 * 센터회원 아이디 찾기
	 * @param center
	 * @throws CommonException 
	 */
	public void findId(CenterMemberDto center) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.findId(conn,center);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(conn);
		}
	}
}
