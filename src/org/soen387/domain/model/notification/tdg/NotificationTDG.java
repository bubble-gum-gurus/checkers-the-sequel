package org.soen387.domain.model.notification.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class NotificationTDG {
	public static final String TABLE_NAME = "Notification";
	public static final String COLUMNS = "id, version, status, recipient, seen ";
	public static final String TRUNCATE_TABLE = "TRUNCATE TABLE  " + TABLE_NAME + ";";
	public static final String DROP_TABLE = "DROP TABLE  " + TABLE_NAME + ";";
	public static final String CREATE_TABLE ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" 
			+ "id BIGINT, "
			+ "version BIGINT, "
			+ "status INT, "
			+ "recipient BIGINT, "
			+ "seen BOOLEAN, "
			+ "PRIMARY KEY(id) "
			+ ");";

	public static void createTable() throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		Statement update = con.createStatement();
		update.execute(CREATE_TABLE);
	}

	public static void dropTable() throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		Statement update = con.createStatement();
		update.execute(TRUNCATE_TABLE);
		update = con.createStatement();
		update.execute(DROP_TABLE);
	}
	
	
	public static final String INSERT = "INSERT INTO " + TABLE_NAME + " (" + COLUMNS + ") "
			+ "VALUES (?,?,?,?,?);";
	public static int insert(long id, long version, int status, long recipient, boolean seen) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT);
		ps.setLong(1,id);
		ps.setLong(2,version);
		ps.setInt(3,status);
		ps.setLong(4,recipient);
		ps.setBoolean(5,seen);
		return ps.executeUpdate();
	}
	
	public static final String UPDATE = "UPDATE " + TABLE_NAME + " set version = version+1, status=?, recipient=?, seen=? "
			+ " WHERE id=? AND version=?;";
	public static int update(long id, long version, int status, long recipient, boolean seen) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE);
		ps.setInt(1,status);
		ps.setLong(2,recipient);
		ps.setBoolean(3,seen);
		ps.setLong(4,id);
		ps.setLong(5,version);
		return ps.executeUpdate();
	}
	
	public static final String DELETE = "DELETE FROM " + TABLE_NAME + " "
			+ "WHERE id=? AND version=?;";
	public static int delete(long id, long version) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE);
		ps.setLong(1,id);
		ps.setLong(2,version);
		return ps.executeUpdate();
	}
	

	
	private static long maxId = 0;
	public static final String GET_MAX_ID = "SELECT max(id) AS max FROM " + TABLE_NAME + ";";
	public static synchronized long getMaxId() throws SQLException {
		if(maxId==0) {
			Connection con = DbRegistry.getDbConnection();
			PreparedStatement ps = con.prepareStatement(GET_MAX_ID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				maxId = rs.getLong("max");
			}
		}
		return ++maxId;
	}
}
