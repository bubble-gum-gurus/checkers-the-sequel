package org.soen387.domain.model.notification;

import org.dsrg.soenea.domain.interf.IDomainObject;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.Player;

public interface INotification extends IDomainObject<Long> {

	public abstract boolean getSeen ();
	public abstract void setSeen (boolean seen);
	public abstract NotificationStatus getStatus();
	public abstract void setStatus (NotificationStatus status);
	public abstract IPlayer getRecipient ();
	public abstract void setRecipient(IPlayer player);
	public abstract long getOther ();
	public abstract void setOther (long other);
}
