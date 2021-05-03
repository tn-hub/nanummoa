/**
 * 
 */
package com.nanum.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.LocalDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.model.dao.GeneralDao;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;

/**
 * 일반회원 관리 업무 로직을 위한 클래스
 *
 */
public class GeneralBiz {
	private GeneralDao gDao = GeneralDao.getInstance();
	
	/**
	 * 지역 조회 메서드
	 * @param list ArrayList<LocalDto>
	 * @throws CommonException
	 */
	public void getLocalList(ArrayList<LocalDto> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			gDao.selectLocal(conn, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 봉사분야 조회 메서드
	 * @param list ArrayList<VolCategoryDto>
	 * @throws CommonException
	 */
	public void getVolCategoryList(ArrayList<VolCategoryDto> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			gDao.selectVolCategory(conn, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 아이디 중복 체크 메서드
	 * @param generalId 아이디
	 * @return 아이디가 있으면 true, 없으면 false
	 * @throws CommonException
	 */
	public boolean isGeneralId(String generalId) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			return gDao.isGeneralId(conn, generalId);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
		
	}
	
	
	/**
	 * 일반회원 회원가입
	 * @param dto GeneralMemberDto
	 * @throws CommonException
	 */
	public void addGeneralMember(GeneralMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			gDao.insertGeneralMember(conn, dto);
			JdbcTemplate.commit(conn);
		} catch (CommonException e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
}
