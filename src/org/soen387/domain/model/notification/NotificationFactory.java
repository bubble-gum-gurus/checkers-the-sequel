package org.soen387.domain.model.notification;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.model.notification.tdg.NotificationTDG;
import org.soen387.domain.model.player.IPlayer;

public class NotificationFactory {

	public static Notification createNew(boolean seen, NotificationStatus status, IPlayer recipient, long other) throws SQLException, MapperException{
		return createNew(NotificationTDG.getMaxId(), 1, seen, status, recipient, other);
	}

	public static Notification createNew(long id, long version, boolean seen, NotificationStatus status, IPlayer recipient, long other) throws SQLException, MapperException{
		final Notification p = new Notification(id, version, seen, status, recipient, other);
		UoW.getCurrent().registerNew(p);
		return p;
	}
	
	public static Notification createClean(long id, long version, boolean seen, NotificationStatus status, IPlayer recipient, long other) {
		Notification p = new Notification(id, version, seen, status, recipient, other);
		UoW.getCurrent().registerClean(p);
		return p;
	}

}
