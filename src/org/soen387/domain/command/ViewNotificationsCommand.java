package org.soen387.domain.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.IdentityBasedProducer;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;
import org.soen387.domain.model.notification.INotification;
import org.soen387.domain.model.notification.mapper.NotificationInputMapper;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.mapper.PlayerInputMapper;

public class ViewNotificationsCommand extends CheckersCommand  {
	public ViewNotificationsCommand(Helper helper) {
		super(helper);
	}
	
	@SetInRequestAttribute
	@Source(sources=PermalinkSource.class)
	@IdentityBasedProducer(mapper=NotificationInputMapper.class)
	public INotification notification;
	
	@Override
	public void process() throws CommandException {
		if(currentPlayer == null) {
			throw new NeedToBeLoggedInException();
		}
		if(notification.getRecipient().getId() != currentPlayer.getId()) {
			throw new NeedToBeLoggedInException();
		}
		if (notification.getSeen()) {
			throw new NeedToBeLoggedInException();
		}
		helper.setRequestAttribute("notification", notification);
		notification.setSeen(true);
		try {
			UoW.getCurrent().registerDirty(notification);
		} catch (MissingMappingException | MapperException e) {
			e.printStackTrace();
			throw new CommandException();
		}
	}

}
