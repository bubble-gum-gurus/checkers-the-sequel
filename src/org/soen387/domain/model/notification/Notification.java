package org.soen387.domain.model.notification;

import org.dsrg.soenea.domain.DomainObject;
import org.soen387.domain.model.player.IPlayer;

public class Notification extends DomainObject<Long> implements INotification {
	
	private long id;
	private long version;
	private boolean seen;
	private NotificationStatus status;
	private IPlayer recipient;
	private long other;

	public Notification (long id, long version, boolean seen, NotificationStatus status, IPlayer recipient, long other) {
		super(id, version);
		this.id = id;
		this.version = version;
		this.seen = seen;
		this.status = status;
		this.recipient = recipient;
		this.other = other;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public long getVersion() {
		return this.version;
	}

	@Override
	public void setVersion(long new_version) {
		this.version = new_version;
	}

	@Override
	public boolean getSeen() {
		return this.seen;
	}

	@Override
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	@Override
	public NotificationStatus getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

	@Override
	public IPlayer getRecipient() {
		return this.recipient;
	}

	@Override
	public void setRecipient(IPlayer player) {
		this.recipient = player;
	}
	
	@Override
	public long getOther () {
		return this.other;
	}
	
	@Override
	public void setOther(long other) {
		this.other = other;
	}

}
