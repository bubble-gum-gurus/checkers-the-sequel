package org.soen387.app.dispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.domain.command.ViewGameCommand;
import org.soen387.domain.command.MakeMoveCommand;

public class Game extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		if(isSubmission) {
			try {
				new MakeMoveCommand(myHelper).execute();
				forward("checkerboard.jsp");
			} catch (final CommandException e) {
				fail(e);
				}
		}
		else {
			try {
				new ViewGameCommand(myHelper).execute();
				forward("checkerboard.jsp");
			} catch (final CommandException e) {
				fail(e);
			}
		}
	}
}