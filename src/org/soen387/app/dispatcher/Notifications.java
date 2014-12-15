package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.ChallengePlayerCommand;
import org.soen387.domain.command.ViewNotificationsCommand;
import org.soen387.domain.command.WithdrawChallengeCommand;
import org.soen387.domain.command.exception.CannotChallengeSelfException;
import org.soen387.domain.command.exception.CannotWithdrawNotOpenChallengeException;
import org.soen387.domain.command.exception.CannotWithdrawSomeoneElsesChallengeException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.command.exception.OnlyOneOngoingGameBetweenPlayersException;
import org.soen387.domain.command.exception.OnlyOneOpenChallengeBetweenPlayersException;

public class Notifications extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		if(isSubmission) {
			
		} else {
			try {
				new ViewNotificationsCommand(myHelper).execute();
				UoW.getCurrent().commit();
				forward("notifications.jsp");
			} catch (final NeedToBeLoggedInException e) {
				fail("You need to be logged in to view notifications.");
			} catch (final CommandException | SQLException | IllegalAccessException | InstantiationException | MapperException e) {
				fail("blah");
			}
		}
	}
}