/**
 * 
 */
package com.nanum.model.biz;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.LocalDto;
import com.nanum.dto.ServiceCategoryDto;
import com.nanum.dto.VolApplyListDto;
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

	/**
	 * 봉사대상 목록 조회
	 * @param serviceCategoryList
	 * @throws CommonException
	 */
	public void getServiceCategoryList(ArrayList<ServiceCategoryDto> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			gDao.selectServiceCategory(conn, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
		
	}

	/**
	 * 봉사신청내역 조회
	 * @param generalId
	 * @param volApplyList
	 */
	public void searchVolApplyList(String generalId, ArrayList<HashMap<String, Object>> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			gDao.searchVolApplyList(conn, generalId, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
		
	}

	/**
	 * 봉사신청 취소
	 * @param generalId
	 * @param volDetailNo
	 * @throws CommonException
	 */
	public void cancelVol(String generalId, String volApplyNo, String volDetailNo) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			gDao.cancelVol(conn, generalId, volApplyNo);
			gDao.updateApplyCount(conn, volDetailNo, -1);
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
	 * 일반회원 상세 조회
	 * @param dto GeneralMemberDto
	 * @throws CommonException
	 */
	public void getGeneralInfo(GeneralMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			gDao.selectGeneralInfo(conn, dto);
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
	 * 봉사신청
	 * @param volDetailNos
	 * @throws CommonException
	 */
	public void enrollVol(String generalId, String[] volDetailNos) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			for (String volDetailNo : volDetailNos) {
				System.out.println("[biz] 봉사신청 volDetailNo" + volDetailNo);
				gDao.enrollVol(conn, generalId, volDetailNo);
				gDao.updateApplyCount(conn, volDetailNo, 1);
				JdbcTemplate.commit(conn);
			}
		} catch (CommonException e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 봉사신청
	 */
	public void enrollVol(String generalId, String volDetailNo) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			System.out.println("[biz] 봉사신청 volDetailNo" + volDetailNo);
			gDao.enrollVol(conn, generalId, volDetailNo);
			gDao.updateApplyCount(conn, volDetailNo, 1);
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
	 * 일반회원 내 정보 수정
	 * @param dto GeneralMemberDto
	 * @throws CommonException
	 */
	public void updateGeneralMember(GeneralMemberDto dto) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			gDao.updateGeneralMember(conn, dto);
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
	 * 봉사상세조회(통합)
	 */
	public void getVolInfo(String volInfoNo, HashMap<String, Object> resultMap) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			gDao.getVolInfo(conn, volInfoNo, resultMap);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
		
	}

	/**
	 * 봉사상세조회(날짜별)
	 */
	public void volInfoByDate(String generalId, String volInfoNo, ArrayList<HashMap<String, Object>> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			gDao.volInfoByDate(conn, generalId, volInfoNo, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 일반회원 회원탈퇴
	 * @param generalId 일반회원 아이디
	 * @throws CommonException
	 */
	public void deleteGeneralMember(String generalId) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			gDao.deleteGeneralMember(conn, generalId);
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
	 * 봉사 확인서 내역 조회
	 * @param generalId 일반회원 아이디
	 * @param list ArrayList<HashMap<String, Object>>
	 * @throws CommonException
	 */
	public void getConfirmationList(String generalId, ArrayList<HashMap<String, Object>> list) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			gDao.searchConfirmationList(conn, generalId, list);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	public void getConfirmation(HashMap<String, String> selectInfo, HashMap<String, String> map) throws CommonException {
		Connection conn = JdbcTemplate.getConnection();
		try {
			gDao.searchConfirmation(conn, selectInfo, map);
		} catch (CommonException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
}
