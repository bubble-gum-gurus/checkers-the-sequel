package org.soen387.domain.model.notification;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.domain.model.notification.mapper.NotificationInputMapper;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.mapper.PlayerInputMapper;

public class NotificationProxy extends DomainObjectProxy<Long, Notification> implements INotification {

	protected NotificationProxy(Long id) {
		super(id);
	}


	@Override
	public boolean getSeen() {
		return getInnerObject().getSeen();
	}

	@Override
	public void setSeen(boolean seen) {
		getInnerObject().setSeen(seen);
	}

	@Override
	public NotificationStatus getStatus() {
		return getInnerObject().getStatus();
	}

	@Override
	public void setStatus(NotificationStatus status) {
		getInnerObject().setStatus(status);
	}

	@Override
	public IPlayer getRecipient() {
		return getInnerObject().getRecipient();
	}

	@Override
	public void setRecipient(IPlayer player) {
		getInnerObject().setRecipient(player);
	}

	@Override
	protected Notification getFromMapper(Long id) throws MapperException,
					DomainObjectCreationException {
		try {
			return NotificationInputMapper.find(id);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
}
