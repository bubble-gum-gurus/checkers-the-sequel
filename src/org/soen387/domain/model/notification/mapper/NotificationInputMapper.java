package org.soen387.domain.model.notification.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.UserProxy;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.notification.INotification;
import org.soen387.domain.model.notification.Notification;
import org.soen387.domain.model.notification.NotificationFactory;
import org.soen387.domain.model.notification.NotificationStatus;
import org.soen387.domain.model.notification.tdg.NotificationFinder;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.PlayerProxy;

public class NotificationInputMapper implements IdentityBasedProducer {
	@IdentityBasedProducerMethod
	public static Notification find(long id) throws SQLException,
			DomainObjectNotFoundException {
		Notification p = null;
		try {
			p = IdentityMap.get(id, Notification.class);
		} catch (final ObjectRemovedException | DomainObjectNotFoundException e) {
			// Do I care if it has been removed?
		}
		if (p != null)
			return p;

		final ResultSet rs = NotificationFinder.find(id);
		if (rs.next()) {
			p = buildNotification(rs);
			rs.close();
			return p;
		}
		throw new DomainObjectNotFoundException(
				"Could not create a Player with id \"" + id + "\"");
	}
	
	public static List<INotification> findUnseenChallenge(IChallenge challenge) throws SQLException, MapperException {
		try {
			ResultSet rs = NotificationFinder.findUnseenByChallenge(challenge.getId());
			return buildCollection(rs);
		} catch (final SQLException e) {
			throw new MapperException(e);
		}
	}
	
	public static List<INotification> find(IPlayer player) throws SQLException, MapperException {
		try {
			ResultSet rs = NotificationFinder.findByPlayer(player.getId());
			return buildCollection(rs);
		} catch (final SQLException e) {
			throw new MapperException(e);
		}
	}
	
	public static List<INotification> findUnseen(IPlayer player) throws SQLException, MapperException {
		try {
			ResultSet rs = NotificationFinder.findUnseenByPlayer(player.getId());
			return buildCollection(rs);
		} catch (final SQLException e) {
			throw new MapperException(e);
		}
	}

	public static List<INotification> buildCollection(ResultSet rs)
			throws SQLException, DomainObjectNotFoundException {
		final ArrayList<INotification> l = new ArrayList<INotification>();
		while (rs.next()) {
			final long id = rs.getLong("id");
			Notification p = null;
			try {
				p = IdentityMap.get(id, Notification.class);
			} catch (final ObjectRemovedException | DomainObjectNotFoundException e) {
				// Do I care if it has been removed?
			}
			if (p == null) {
				p = buildNotification(rs);

			}
			l.add(p);
		}
		return l;
	}

	public static List<INotification> findAll() throws MapperException {
		try {
			final ResultSet rs = NotificationFinder.findAll();
			return buildCollection(rs);
		} catch (final SQLException e) {
			throw new MapperException(e);
		}
	}

	private static Notification buildNotification(ResultSet rs) throws SQLException {
		return NotificationFactory.createClean(rs.getLong("id"), rs
				.getLong("version"), rs.getBoolean("seen"), NotificationStatus.values()[rs.getInt("status")], 
				new PlayerProxy(rs.getLong("recipient")), rs.getLong("other"));
	}
}
