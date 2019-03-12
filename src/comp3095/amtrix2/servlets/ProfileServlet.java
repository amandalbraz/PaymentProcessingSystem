package comp3095.amtrix2.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comp3095.amtrix2.helpers.RedirectHelper;
import comp3095.amtrix2.helpers.RequestHelper;
import comp3095.amtrix2.helpers.SessionHelper;
import comp3095.amtrix2.helpers.Urls;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 4L;
    public ProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!SessionHelper.isStarted(request)) {
			RedirectHelper.login(response);
			return;
		}
		RequestHelper.dispatch(Urls.ProfileJsp, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
