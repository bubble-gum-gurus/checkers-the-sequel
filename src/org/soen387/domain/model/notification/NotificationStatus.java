package org.soen387.domain.model.notification;

public enum NotificationStatus {
	TURN,
	TIED,
	WON,
	CONCEDED,
	LOSS,
	STARTED,
	ISSUED,
	ACCEPTED,
	REFUSED;
	
	public int getId() {
		return this.ordinal();
	}
}
