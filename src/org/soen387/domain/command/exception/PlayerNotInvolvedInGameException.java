package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class PlayerNotInvolvedInGameException extends CommandException {
	private static final long serialVersionUID = -4696062455077810697L;

	public PlayerNotInvolvedInGameException() {
		super();
	}

	public PlayerNotInvolvedInGameException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlayerNotInvolvedInGameException(String message) {
		super(message);
	}

	public PlayerNotInvolvedInGameException(Throwable cause) {
		super(cause);
	}

}
