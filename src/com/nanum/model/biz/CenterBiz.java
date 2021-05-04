/**
 * 
 */
package com.nanum.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.nanum.dto.VolInfoDto;
import com.nanum.model.dao.CenterDao;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;

/**
 * 센터회원 관리 업무 로직을 위한 클래스
 *
 */
public class CenterBiz {
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
	
	
	
}
