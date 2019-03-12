package comp3095.amtrix2.helpers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public final class RedirectHelper {
	private RedirectHelper() { }
	
	public static void login(HttpServletResponse response) throws IOException {
		response.sendRedirect(Urls.Login);
	}
	public static void register(HttpServletResponse response) throws IOException {
		response.sendRedirect(Urls.Register);
	}
	public static void index(HttpServletResponse response) throws IOException {
		response.sendRedirect(Urls.Index);
	}
}
