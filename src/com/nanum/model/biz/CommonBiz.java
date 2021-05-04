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
import com.nanum.dto.LocalDto;
import com.nanum.dto.VolBlockDto;
import com.nanum.dto.VolCategoryDto;
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

}
