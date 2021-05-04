/**
 * 
 */
package com.nanum.model.biz;

import java.sql.Connection;
import java.util.ArrayList;


import com.nanum.dto.CenterInfoDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.CenterVolDto;
import com.nanum.dto.VolInfoDto;
import com.nanum.model.dao.CenterDao;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;

/**
 * 센터회원 관리 업무 로직을 위한 클래스
 *
 */
public class CenterBiz {
	private CenterDao cDao = CenterDao.getInstance();

	/**
	 * 아이디 중복 체크 메서드
	 * 
	 * @param generalId 아이디
	 * @return 아이디가 있으면 true, 없으면 false
	 * @throws CommonException
	 */
	public boolean isCenterId(String centerId) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();

		try {
			return cDao.isCenterId(conn, centerId);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}

	}

	private CenterDao dao = CenterDao.getInstance();

	/**
	 * 센터회원 봉사 목록
	 * 
	 * @throws CommonException
	 */
	public void centerVolList(String centerId, ArrayList<CenterVolDto> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.centerVolList(centerId, conn, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 센터회원 봉사 목록(모집중)
	 * 
	 * @throws CommonException
	 */
	public void recruitList(String centerId, ArrayList<CenterVolDto> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.recruitList(centerId, conn, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 센터회원 회원가입
	 * @param cMemberDto CenterMemberDto
	 * @param centerDto CenterInfo
	 * @throws CommonException
	 */
	public void addGeneralMember(CenterMemberDto cMemberDto, CenterInfoDto centerDto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			cDao.insertCenterMember(conn, cMemberDto);
			cDao.insertCenterInfo(conn, centerDto);
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
