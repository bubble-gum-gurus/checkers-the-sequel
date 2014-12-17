package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.ListGamesCommand;
import org.soen387.domain.command.ViewNotificationCommand;

public class Notification extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		try {
			new ViewNotificationCommand(myHelper).execute();
			UoW.getCurrent().commit();
			forward("notification.jsp");
		} catch (final CommandException e) {
			fail(e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e);
		}
	}
}
