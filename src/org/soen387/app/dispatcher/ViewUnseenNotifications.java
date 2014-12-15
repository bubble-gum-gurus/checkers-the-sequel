package org.soen387.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.ViewUnseenNotificationsCommand;

public class ViewUnseenNotifications extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			new ViewUnseenNotificationsCommand(myHelper).execute();
			UoW.getCurrent().commit();
			forward("notifications.jsp");
		} catch (Exception e) {
			fail("HAHA HA HAHAHAH YOU FAIL");
		}
	}
	
}
