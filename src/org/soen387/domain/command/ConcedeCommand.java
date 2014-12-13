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
import org.soen387.domain.command.exception.GameIsNotOngoingException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.command.exception.OnlyOneOngoingGameBetweenPlayersException;
import org.soen387.domain.command.exception.OnlyOneOpenChallengeBetweenPlayersException;
import org.soen387.domain.command.exception.PlayerNotInvolvedInGameException;
import org.soen387.domain.model.challenge.ChallengeFactory;
import org.soen387.domain.model.challenge.ChallengeStatus;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.model.checkerboard.GameStatus;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.mapper.PlayerInputMapper;

public class ConcedeCommand extends CheckersCommand {

	public ConcedeCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@SetInRequestAttribute
	@Source(sources=PermalinkSource.class)
	@IdentityBasedProducer(mapper=CheckerBoardInputMapper.class)
	public ICheckerBoard checkerboard;

	@Override
	public void process() throws CommandException {		
		try {
		
			if(currentPlayer==null) {
				throw new NeedToBeLoggedInException();
			}
			
			if(checkerboard.getStatus() != GameStatus.Ongoing) {
				throw new GameIsNotOngoingException();
			}
			
			if(checkerboard.getFirstPlayer().getId() != currentPlayer.getId()
					&& checkerboard.getSecondPlayer().getId() != currentPlayer.getId()) {
				throw new PlayerNotInvolvedInGameException();
			}
		
			checkerboard.setStatus(GameStatus.Won);
			UoW.getCurrent().registerDirty(checkerboard);
		} catch (MapperException e) {
			throw new CommandException();
		} finally {}
	}

}
