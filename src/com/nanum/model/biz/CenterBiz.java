/**
 * 
 */
package com.nanum.model.biz;

import java.sql.Connection;

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
}
