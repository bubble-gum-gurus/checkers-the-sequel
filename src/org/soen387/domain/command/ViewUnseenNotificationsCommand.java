package org.soen387.domain.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.model.notification.mapper.NotificationInputMapper;
import org.soen387.domain.model.notification.tdg.NotificationFinder;

public class ViewUnseenNotificationsCommand extends CheckersCommand {

	public ViewUnseenNotificationsCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		
		try {
			if (currentPlayer == null) {
				throw new NeedToBeLoggedInException();
			}
			
			helper.setRequestAttribute("notifications", NotificationInputMapper.findUnseen(currentPlayer));
		} catch (Exception e) {
			throw new CommandException();
		}
	}

}
