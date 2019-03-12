package comp3095.amtrix2.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHelper {
	public static boolean isStarted(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("user") != null;
	}
	
}
