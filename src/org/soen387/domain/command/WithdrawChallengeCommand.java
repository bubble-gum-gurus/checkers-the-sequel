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
import org.soen387.domain.command.exception.CannotWithdrawNotOpenChallengeException;
import org.soen387.domain.command.exception.CannotWithdrawSomeoneElsesChallengeException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.command.exception.OnlyOneOngoingGameBetweenPlayersException;
import org.soen387.domain.command.exception.OnlyOneOpenChallengeBetweenPlayersException;
import org.soen387.domain.model.challenge.Challenge;
import org.soen387.domain.model.challenge.ChallengeFactory;
import org.soen387.domain.model.challenge.ChallengeStatus;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.model.challenge.mapper.ChallengeOutputMapper;
import org.soen387.domain.model.checkerboard.GameStatus;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.mapper.PlayerInputMapper;

public class WithdrawChallengeCommand extends CheckersCommand {

	public WithdrawChallengeCommand(Helper helper) {
		super(helper);
	}

	@SetInRequestAttribute
	@Source(sources=PermalinkSource.class)
	@IdentityBasedProducer(mapper=ChallengeInputMapper.class)
	public IChallenge challenge;
	
	@Override
	public void process() throws CommandException {
		try {
			if(currentPlayer==null) {
				throw new NeedToBeLoggedInException();
			}
			
			if(challenge.getChallenger().getId() != currentPlayer.getId()) {
				throw new CannotWithdrawSomeoneElsesChallengeException();
			}
			
			if(challenge.getStatus() != ChallengeStatus.Open) {
				throw new CannotWithdrawNotOpenChallengeException();
			}
			
			ChallengeOutputMapper o = new ChallengeOutputMapper();
			o.delete((Challenge) challenge);

		} catch (MapperException e) {
			throw new CommandException();
		}
	}

}
