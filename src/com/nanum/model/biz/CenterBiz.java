/**
 * 
 */
package com.nanum.model.biz;

import java.sql.Connection;
import java.util.ArrayList;


import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.CenterVolDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.VolApplyListDto;
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
	public void centerVolList(String centerId, ArrayList<CenterVolDto> list, CenterVolDto voDto)
			throws CommonException {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.centerVolList(centerId, conn, list);
			dao.listIndex(centerId, conn, voDto);
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
	public void recruitList(String centerId, ArrayList<CenterVolDto> list, CenterVolDto voDto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.recruitList(centerId, conn, list);
			dao.listIndex(centerId, conn, voDto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 센터회원 봉사 목록(종료)
	 * 
	 * @throws CommonException
	 */
	public void deadlineList(String centerId, ArrayList<CenterVolDto> list, CenterVolDto voDto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.deadlineList(centerId, conn, list);
			dao.listIndex(centerId, conn, voDto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 봉사 신청자 목록 조회
	 * 
	 * @param centerId
	 * @param list
	 * @throws CommonException
	 */
	public void applyList(String centerId, int volInfoNo, ArrayList<VolApplyListDto> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.applyList(conn, centerId, volInfoNo, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 봉사 신청자 정보 조회
	 * 
	 * @param centerId
	 * @param general
	 * @param list
	 * @throws CommonException
	 */
	public void applicantInfo(String centerId, int volInfoNo, GeneralMemberDto general, ArrayList<VolApplyListDto> list)
			throws CommonException {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.applicantInfo(conn, centerId, volInfoNo, general, list);
			dao.generalInfo(conn, general);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}

	}

	/**
	 * 봉사활동 신청승인
	 * 
	 * @param string
	 * @throws Exception
	 */
	public void applyGeneral(String checkDate) throws Exception {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.applyGeneral(conn, checkDate);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 봉사활동 승인취소
	 * 
	 * @param volApplyNo
	 * @throws Exception
	 */

	public void closeApply(int volApplyNo) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.closeApply(conn, volApplyNo);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
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
	public void addCenterMember(CenterMemberDto cMemberDto, CenterInfoDto centerDto) throws CommonException {
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
	
	/**
	 * 센터회원 및 센터정보 조회
	 * 
	 * @throws CommonException
	 */
	public void getCenterMemberInfo(CenterMemberDto centerMemberDto, CenterInfoDto centerDto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.selectCenterMemberInfo(conn, centerMemberDto);
			dao.selectCenterInfo(conn, centerDto);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	/**
	 * 센터회원 및 센터정보 수정
	 * @param cMemberDto CenterMemberDto
	 * @param centerDto CenterInfo
	 * @throws CommonException
	 */
	public void updateCenterMember(CenterMemberDto cMemberDto, CenterInfoDto centerDto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			cDao.updateCenterMember(conn, cMemberDto);
			cDao.updateCenter(conn, centerDto);
			JdbcTemplate.commit(conn);
		} catch (CommonException e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 센터회원 및 센터정보 삭제
	 * @param centerId 센터회원 아이디
	 * @throws CommonException
	 */
	public void deleteCenterMember(String centerId) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			cDao.deleteCenter(conn, centerId);
			cDao.deleteCenterMember(conn, centerId);
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
