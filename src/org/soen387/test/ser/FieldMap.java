package org.soen387.test.ser;

import java.util.HashMap;
import java.util.Map;

import org.dsrg.soenea.service.threadLocal.ThreadLocalTracker;

public class FieldMap extends ThreadLocal<Map<String, String>> {
	public static FieldMap current = new FieldMap();
	
	@Override
	protected Map<String, String> initialValue() {
		// TODO Auto-generated method stub
		Map<String, String> myMap = new HashMap<String, String>();
		
		/*
		 * 
		 * This is the URL for your application. Note that it should have no trailing slash
		 * 
		 */
		myMap.put("BASE_URL", "http://localhost:8080/CheckersGame");

		/*
		 * 
		 * If you need a parameter to set it to return an xml response, fill it in here. This
		 * defaults to what we've used in the tutorials, I think.
		 * 
		 */
		myMap.put("XML_PARAM", "mode");
		myMap.put("XML_VALUE", "xml");
		
		/*
		 * 
		 * Each of the Use Cases is represented by a PageController, effectively (though this system 
		 * supports FrontControllers of some flavors as well as group PageControllers). Each Use Case
		 * Needs it's path, starting with a slash. The names of parameters follow. UC 7 has an exception
		 * In the event that you pass a different form of status, this supports the common variances I 
		 * have seen. 
		 * 
		 */
		
		//UC 1
		myMap.put("LOGIN_PATH", "/Game/LogIn");
		myMap.put("USERNAME_PARAM", "username");
		myMap.put("PASSWORD_PARAM", "password");

		//UC 2
		myMap.put("LOGOUT_PATH", "/Game/LogOut");
		
		
		/*
		 * 
		 * Note that I use the same params for username and password
		 * as in LogIn. If you're not doing so already, you should do so
		 * 
		 */
		//UC 3
		myMap.put("REGISTER_PATH", "/Game/Register");
		myMap.put("FIRSTNAME_PARAM", "firstName");
		myMap.put("LASTNAME_PARAM", "lastName");
		myMap.put("EMAIL_PARAM", "email");
		
		//UC 4
		myMap.put("LIST_PLAYERS_PATH", "/Game/ListPlayers");
		
		//UC 5
		myMap.put("VIEW_PLAYER_STATS_PATH", "/Game/Player/$id$");
		
		//UC 6		
		myMap.put("CHALLENGE_PLAYER_PATH", "/Game/Player/$id$/Challenge");
		
		/*
		 * By default I accept a status and either 1 or 2, the ordinal values of the ChallengeStatus.
		 * The testsuite responds either true or false for accepting, passing the acceptValue if
		 * it is true and the refuseValue if it was false. This should provide enough flexibility.
		 */
		
		//UC 7
		myMap.put("RESPOND_TO_CHALLNGE_PATH", "/Game/Challenge/$id$");
		myMap.put("CHALLENGE_VERSION_PARAM", "version");
		myMap.put("CHALLENGE_RESPONSE_PARAM", "status");
		myMap.put("CHALLENGE_ACCEPT_VALUE", "1");
		myMap.put("CHALLENGE_REFUSE_VALUE", "2");
		
		//UC 8
		myMap.put("LIST_GAMES_PATH", "/Game/ListGames");
		
		//UC 9
		myMap.put("VIEW_GAME_PATH", "/Game/Board/$id$");
		
		//UC 10
		myMap.put("LIST_CHALLENGES_PATH", "/Game/Challenge/");
		
		return myMap;
	}
	
	static {
		ThreadLocalTracker.registerThreadLocal(current);
	}
}
