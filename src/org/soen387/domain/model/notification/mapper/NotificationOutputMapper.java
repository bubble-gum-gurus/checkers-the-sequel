package org.soen387.domain.model.notification.mapper;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.model.notification.Notification;
import org.soen387.domain.model.notification.tdg.NotificationTDG;

public class NotificationOutputMapper {
	
	public void insert(Notification n) throws MapperException {
		try {
			NotificationTDG.insert(n.getId(), n.getVersion(), n.getStatus().getId(), n.getRecipient().getId(), n.getSeen(), n.getOther());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	public void update(Notification n) throws MapperException, LostUpdateException {
		try {
			int count = NotificationTDG.update(n.getId(), n.getVersion(), n.getStatus().getId(), n.getRecipient().getId(), n.getSeen(), n.getOther());
			if(count==0) throw new LostUpdateException("Lost Update editing player with id " + n.getId());
			n.setVersion(n.getVersion()+1);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	
	public void delete(Notification n) throws MapperException, LostUpdateException {
		try {
			int count = NotificationTDG.delete(n.getId(), n.getVersion());
			if(count==0) throw new LostUpdateException("Lost Update deleting player with id " + n.getId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
}
