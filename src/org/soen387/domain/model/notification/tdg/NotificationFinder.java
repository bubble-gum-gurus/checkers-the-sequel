package org.soen387.domain.model.notification.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.soen387.domain.model.notification.NotificationStatus;

public class NotificationFinder {
	
	/**
	 *  These methods were just copied & pasted from the player finder.  They're 
	 *  probably not applicable..... .
	 */
	public static final String FIND = "SELECT " + NotificationTDG.COLUMNS + " FROM " + NotificationTDG.TABLE_NAME + " WHERE id=?;";
	public static ResultSet find(long id) throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND);
		ps.setLong(1,id);
		return ps.executeQuery();
	}
	
	public static final String FIND_BY_PLAYER = "SELECT " + NotificationTDG.COLUMNS + " FROM " + NotificationTDG.TABLE_NAME + " WHERE recipient=?;";
	public static ResultSet findByPlayer(long player) throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_BY_PLAYER);
		ps.setLong(1,player);
		return ps.executeQuery();
	}

	
	public static final String FIND_UNSEEN_BY_CHALLENGE = "SELECT " + NotificationTDG.COLUMNS + " FROM " + NotificationTDG.TABLE_NAME + " WHERE status=? AND other=? AND unseen=1;";
	public static ResultSet findUnseenByChallenge(long challenge) throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_UNSEEN_BY_CHALLENGE);
		ps.setInt(1,NotificationStatus.ISSUED.getId());
		ps.setLong(2, challenge);
		
		return ps.executeQuery();
	}
	
	
	public static final String FIND_ALL = "SELECT " + NotificationTDG.COLUMNS + " FROM " + NotificationTDG.TABLE_NAME + ";";
	public static ResultSet findAll() throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_ALL);
		return ps.executeQuery();
	}
	
	public static final String FIND_UNSEEN_BY_PLAYER = "SELECT " + NotificationTDG.COLUMNS + " FROM " + NotificationTDG.TABLE_NAME + " WHERE recipient=? AND unseen=1;";
	public static ResultSet findUnseenByPlayer(long player) throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_UNSEEN_BY_PLAYER);
		ps.setLong(1,player);
		return ps.executeQuery();
	}
}
