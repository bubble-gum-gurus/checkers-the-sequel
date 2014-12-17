package org.soen387.domain.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.IdentityBasedProducer;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;
import org.soen387.domain.model.notification.mapper.NotificationInputMapper;

public class ViewNotificationCommand extends CheckersCommand  {
	public ViewNotificationCommand(Helper helper) {
		super(helper);
	}
	
	@Override
	public void process() throws CommandException {
		try {
			if(currentPlayer == null) {
				throw new NeedToBeLoggedInException();
			}
			helper.setRequestAttribute("notifications", NotificationInputMapper.find(currentPlayer));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CommandException();
		} catch (MapperException e) {
			throw new CommandException();
		}
	}

}
