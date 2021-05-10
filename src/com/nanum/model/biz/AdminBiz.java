/**
 * 
 */
package com.nanum.model.biz;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.nanum.dto.CenterInfoDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.QnAReplyDto;
import com.nanum.model.dao.AdminDao;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;

/**
 * 관리자회원 관리 업무 로직을 위한 클래스
 *
 */
public class AdminBiz {
	private AdminDao dao = AdminDao.getInstance();
	
	public void getCenterInto(ArrayList<CenterInfoDto> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.selCenterInfo(conn, list);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	public void getCenterAcceptListToCnt(CenterInfoDto cDto) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.selCenterAcceptListToCnt(conn, cDto);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	public void getCenterAcceptList(ArrayList<CenterInfoDto> centerActList, Integer sartNum, Integer lastNum) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.selCenterAcceptList(conn, centerActList, sartNum, lastNum);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	public void acceptCenter(String centerId) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.upAcceptCenter(conn, centerId);
			JdbcTemplate.commit(conn); // commit;
		}catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);// rollback;
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	public void refuseCenter(String centerId) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.DeleteCenter(conn, centerId);
			JdbcTemplate.commit(conn); // commit;
		}catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);// rollback;
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 댓글등록
	 * @param replyDto QnAReplyDto
	 * @throws CommonException
	 */
	public void addReply(QnAReplyDto replyDto) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.insertReply(conn, replyDto);
			JdbcTemplate.commit(conn);
		}catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	public void getGenralMinList(ArrayList<GeneralMemberDto> glist) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.selectGetGenralMinList(conn, glist);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
				JdbcTemplate.close(conn);
		}
			
	}
	
	/**
	 * 댓글 삭제
	 * @param rNo 댓글번호
	 * @throws CommonException
	 */
	public void deleteReply(int rNo) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.deleteReply(conn, rNo);
			JdbcTemplate.commit(conn);
		}catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
		
	}

	public void getCenterMinList(ArrayList<CenterMemberDto> list) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.selectCenterMinList(conn,list);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	
	/**
	 * 댓글 수정
	 * @param rNo 댓글 번호
	 * @param content 내용
	 * @throws CommonException
	 */
	public void updateReply(int rNo, String content) throws CommonException{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.updateReply(conn, rNo, content);
			JdbcTemplate.commit(conn);
		}catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 봉사 확인서 내역 조회
	 * @param list ArrayList<HashMap<String, Object>>
	 * @throws CommonException
	 */
	public void getConfirmationList(ArrayList<HashMap<String, Object>> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.searchConfirmationList(conn, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
}
