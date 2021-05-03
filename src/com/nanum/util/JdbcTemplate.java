package com.nanum.util;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

/**
 * <PRE>
 * 
 * DB Connection을 얻어오고 반환하는 등의 기본적인 기능들을 모아둔 Util 클래스
 * 
 * </PRE>
 * 
 * @author LG CNS 경영기술교육원.
 * @version 1.0, 2010/01/01
 */
public class JdbcTemplate {

    /**
     * 디폴트 생성자
     */
    public JdbcTemplate() {
    }

    /**
     * Connection을 연결한 후 멤버 attribute 인 conn 에 Connection 객체를 세팅한 후 리턴한다.
     * 
     * @return Connection
     */
    public static Connection getConnection() {
    	Connection conn = null;
        
        try {
        	System.out.println("[debug] DataSource getConnection()...");
            // Connection Poll 코드 변경
        	Context initContext = new InitialContext();
        	DataSource ds = (DataSource)initContext.lookup("java:comp/env/jdbc/Oracle11g");
        	conn = ds.getConnection();
            conn.setAutoCommit(false); // 기본 트랜잭션 true 설정 => false => 개발자가 sql 성공수행시 명시적으로 commit
        } catch (Exception e) {
            System.out.println("[JdbcTemplate.getConnection] : " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * DB와 Connect되었는지 여부를 Return 한다.
     * 
     * @return DB와 Connect 되었는지 여부.
     */
    public static boolean isConnected(Connection conn) {

        boolean validConnection = true;

        try {
            if (conn == null || conn.isClosed())
                validConnection = false;
        } catch (SQLException e) {
            validConnection = false;
            e.printStackTrace();
        }
        return validConnection;
    }

    /**
     * Connection 객체를 시스템에 반환한다.
     */
    public static void close(Connection conn) {

        if (isConnected(conn)) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Statement를 Close 한다.
     * 
     * @param stmt
     *            Statement 객체.
     */
    public static void close(Statement stmt) {

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ResultSet을 Close 한다.
     * 
     * @param result
     *            ResultSet 객체.
     */
    public static void close(ResultSet rset) {

        try {
            if (rset != null)
                rset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 지금까지의 트랜잭션을 Commit 처리한다.
     */
    public static void commit(Connection conn) {

        try {
            if (isConnected(conn)) {
                conn.commit(); // 개발자가 명시적으로 commit 반영
                System.out.println("[JdbcTemplate.commit] : DB Successfully Committed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 지금까지의 트랜잭션을 Rollback 처한다.
     */
    public static void rollback(Connection conn) {

        try {
            if (isConnected(conn)) {
                conn.rollback();
                System.out.println("[JdbcTemplate.rollback] : DB Successfully Rollbacked!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
