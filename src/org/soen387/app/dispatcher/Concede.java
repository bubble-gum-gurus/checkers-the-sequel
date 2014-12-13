	package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.ChallengePlayerCommand;
import org.soen387.domain.command.ConcedeCommand;
import org.soen387.domain.command.exception.CannotChallengeSelfException;
import org.soen387.domain.command.exception.GameIsNotOngoingException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.command.exception.OnlyOneOngoingGameBetweenPlayersException;
import org.soen387.domain.command.exception.OnlyOneOpenChallengeBetweenPlayersException;
import org.soen387.domain.command.exception.PlayerNotInvolvedInGameException;

public class Concede extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		if(isSubmission) {
			try {
				new ConcedeCommand(myHelper).execute();
				UoW.getCurrent().commit();
				forward("checkerboard.jsp");
			} catch (final NeedToBeLoggedInException e) {
				fail("You need to be logged in to challenge a player.");
			} catch (final GameIsNotOngoingException e) {
				fail("You can't challenge yourself.");
			} catch (final PlayerNotInvolvedInGameException e) {
				fail("You may only have one open challenge against another player.");
			} catch (final CommandException e) {
				fail(e);
			} catch (InstantiationException | IllegalAccessException | MapperException | SQLException e) {
				fail(e); //UoW went to crap
			}
		} else {
			//I guess this would give an HTML form? Is there a use for GET?
			//There's something in SOENEA that filters on this, but let's hold off
		}
	}

}
