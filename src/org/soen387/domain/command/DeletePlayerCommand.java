package org.soen387.domain.command;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.IdentityBasedProducer;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.exception.CannotChallengeSelfException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.command.exception.OnlyOneOngoingGameBetweenPlayersException;
import org.soen387.domain.command.exception.OnlyOneOpenChallengeBetweenPlayersException;
import org.soen387.domain.model.challenge.ChallengeFactory;
import org.soen387.domain.model.challenge.ChallengeStatus;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.model.checkerboard.GameStatus;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;
import org.soen387.domain.model.notification.INotification;
import org.soen387.domain.model.notification.NotificationFactory;
import org.soen387.domain.model.notification.NotificationStatus;
import org.soen387.domain.model.notification.mapper.NotificationInputMapper;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.mapper.PlayerInputMapper;

public class DeletePlayerCommand extends CheckersCommand {

	public DeletePlayerCommand(Helper helper) {
		super(helper);
	}

	@SetInRequestAttribute
	@Source(sources=PermalinkSource.class)
	@IdentityBasedProducer(mapper=PlayerInputMapper.class)
	public IPlayer player;
	
	@Override
	public void process() throws CommandException {
		try {
			if(currentPlayer==null || currentPlayer.getId() != player.getId()) {
				throw new NeedToBeLoggedInException();
			}
			
			List<IChallenge> challenges = ChallengeInputMapper.find(player);
			for (IChallenge challenge : challenges) {
				if (challenge.getChallengee().getId() == currentPlayer.getId()) {
					List<INotification> notifications = NotificationInputMapper.findUnseenChallenge(challenge);
					for (INotification notification : notifications) {
						UoW.getCurrent().registerRemoved(notification);
					}
					UoW.getCurrent().registerRemoved(challenge);
				} else {
					challenge.setStatus(ChallengeStatus.Refused);
					NotificationFactory.createNew(false, NotificationStatus.REFUSED, challenge.getChallengee(), challenge.getId());
					UoW.getCurrent().registerDirty(challenge);
				}
			}
			
			List<ICheckerBoard> games = CheckerBoardInputMapper.find(player);
			for (ICheckerBoard game : games) {
				IPlayer otherPlayer = game.getFirstPlayer().getId() == currentPlayer.getId() ? game.getSecondPlayer() : game.getFirstPlayer();
				NotificationFactory.createNew(false, NotificationStatus.CONCEDED, otherPlayer, game.getId());
				NotificationFactory.createNew(false, NotificationStatus.WON, otherPlayer, game.getId());
				game.setStatus(GameStatus.Won);
				UoW.getCurrent().registerDirty(game);
			}

		} catch (MapperException | SQLException e) {
			throw new CommandException(e);
		}
	}

}
