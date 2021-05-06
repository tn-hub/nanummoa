/**
 * 
 */
package com.nanum.model.biz;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.nanum.dto.VolDetailDto;
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
	 * @throws CommonException 
	 */
	public void centerVolList(String centerId,ArrayList<VolInfoDto> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.centerVolList(centerId,conn,list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 봉사글등록
	 */
	public void addVolInfo(VolInfoDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.addVolInfo(conn, dto);
			JdbcTemplate.commit(conn);
			int volInfoNo = getVolInfoNoCurrentSeq();
			dto.setVolInfoNo(volInfoNo);
		} catch (CommonException e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	public void addVolInfo(HashMap<String, Object> map) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.addVolInfo(conn, map);
			JdbcTemplate.commit(conn);
			int volInfoNo = getVolInfoNoCurrentSeq();
			map.put("volInfoNo", volInfoNo);
		} catch (CommonException e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
		
	}
	/**
	 * 봉사글 등록 후 현재시퀀스값가져오기(volInfoNo)
	 */
	public int getVolInfoNoCurrentSeq() throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			return dao.getVolInfoNoCurrentSeq(conn);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 봉사 상세등록(날짜별)
	 */
	public void addVolDetail(VolDetailDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.addVolDetail(conn, dto);
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
