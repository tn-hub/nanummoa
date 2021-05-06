/**
 * 
 */
package com.nanum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nanum.dto.CenterInfoDto;
import com.nanum.util.CommonException;
import com.nanum.util.JdbcTemplate;

/**
 * 관리자 Dao 클래스
 *
 */
public class AdminDao {
	private static AdminDao instance = new AdminDao();
	
	public AdminDao() {}
	
	public static AdminDao getInstance() {
		return instance;
	}

	public void selCenterInfo(Connection conn, ArrayList<CenterInfoDto> list) throws CommonException{
		String sql = "select c_name, c_id, service_subject from center_info";

		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			CenterInfoDto dto = null;
			
			while(rs.next()) {
				dto = new CenterInfoDto();
				dto.setCenterName(rs.getString("c_name"));
				dto.setCenterId(rs.getString("c_id"));
				dto.setService(rs.getString("service_subject"));
				
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	public void selCenterAcceptListToCnt(Connection conn, CenterInfoDto cDto) throws CommonException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) as tot_cnt ");
		sql.append("  from center_member c, center_info i ");
		sql.append("  where c.app_status = 0 ");
		sql.append("  and c.c_id = i.c_id ");
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {

			stmt = conn.prepareStatement(sql.toString()); 
			rs = stmt.executeQuery();
			
			if(rs.next()) { 
				cDto.setTotAcceptCnt(rs.getInt("tot_cnt"));  
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 가입대기 승인 목록
	 * @param conn
	 * @param centerActList
	 * @throws CommonException
	 */
	public void selCenterAcceptList(Connection conn, ArrayList<CenterInfoDto> centerActList) throws CommonException{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select ");
		sql.append("  c.c_id ");
		sql.append(" , i.c_name ");
		sql.append(" , to_char(c.entry_date,'yyyy-mm-dd') as entry_date ");
		sql.append(" from center_member c, center_info i ");
		sql.append(" where c.c_id = i.c_id ");
		sql.append(" and  c.app_status = 0 ");
		sql.append(" order by c.entry_date ");
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql.toString()); 
			rs = stmt.executeQuery();
			
			CenterInfoDto dto = null;
			
			while(rs.next()) { 
				
				dto = new CenterInfoDto();
				dto.setCenterId(rs.getString("c_id"));
				dto.setCenterName(rs.getString("c_name"));
				dto.setCenterEntryDate(rs.getString("entry_date"));
				
				centerActList.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CommonException();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	public void upAcceptCenter(Connection conn, String centerId) throws CommonException{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update center_member ");
		sql.append(" set app_status = 1 ");
		sql.append(" where c_id = ? ");
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, centerId);
			
			int rows = stmt.executeUpdate();
			
			if (rows == 0) {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}  finally {
			JdbcTemplate.close(stmt);
		}
	}

	public void DeleteCenter(Connection conn, String centerId) throws CommonException{
		
		// center_member 삭제
		StringBuilder sql = new StringBuilder();
		
		sql.append(" delete from center_info ");
		sql.append(" where c_id = ? ");
		
		// center_member 삭제
		StringBuilder sql2 = new StringBuilder();
		
		sql2.append(" delete from center_member ");
		sql2.append(" where c_id = ? ");
	

		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		
		try {
			// center_member 삭제
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, centerId);
			
			int rows = stmt.executeUpdate();
			
			if (rows == 0) {
				throw new Exception();
			}
			
			// center_member 삭제
			stmt2 = conn.prepareStatement(sql2.toString());
			stmt2.setString(1, centerId);
			
			int rows2 = stmt2.executeUpdate();
			
			if (rows2 == 0) {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}  finally {
			JdbcTemplate.close(stmt);
		}
		
		
	}
}
