package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.ChallengePlayerCommand;
import org.soen387.domain.command.WithdrawChallengeCommand;
import org.soen387.domain.command.exception.CannotChallengeSelfException;
import org.soen387.domain.command.exception.CannotWithdrawNotOpenChallengeException;
import org.soen387.domain.command.exception.CannotWithdrawSomeoneElsesChallengeException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.command.exception.OnlyOneOngoingGameBetweenPlayersException;
import org.soen387.domain.command.exception.OnlyOneOpenChallengeBetweenPlayersException;

public class WithdrawChallenge extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		if(isSubmission) {
			try {
				new WithdrawChallengeCommand(myHelper).execute();
				UoW.getCurrent().commit();
				forward("challenge.jsp");
			} catch (final NeedToBeLoggedInException e) {
				fail("You need to be logged in to challenge a player.");
			} catch (final CannotWithdrawSomeoneElsesChallengeException e) {
				fail("You can't withdraw someone else's challenge...");
			} catch (final CannotWithdrawNotOpenChallengeException e) {
				fail("You can't withdraw an unopen challenge....");
			} catch (InstantiationException | IllegalAccessException | MapperException | SQLException | CommandException e) {
				fail(e); //UoW went to crap
			}
		} else {
			//I guess this would give an HTML form? Is there a use for GET?
			//There's something in SOENEA that filters on this, but let's hold off
		}
	}
}