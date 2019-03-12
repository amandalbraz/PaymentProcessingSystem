package comp3095.amtrix2.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.amtrix2.helpers.DatabaseHelper;
import comp3095.amtrix2.helpers.RecaptchaHelper;
import comp3095.amtrix2.helpers.RedirectHelper;
import comp3095.amtrix2.helpers.RequestHelper;
import comp3095.amtrix2.helpers.SessionHelper;
import comp3095.amtrix2.helpers.Urls;
import comp3095.amtrix2.models.UserModel;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;
	
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (SessionHelper.isStarted(request)) {
			RedirectHelper.index(response);
			return;
		}
		RequestHelper.dispatch(Urls.LoginJsp, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Session does not get created here, just checked for existence and logged in status
		if (SessionHelper.isStarted(request)) {
			RedirectHelper.index(response);
			return;
		}
		if (!RecaptchaHelper.verifyCaptcha(request)) {
			request.setAttribute("error", "recaptcha");
			RequestHelper.dispatch(Urls.LoginJsp, request, response);
			return;
		}
		HttpSession session = request.getSession();
		String email = request.getParameter("email"); 
		String password = request.getParameter("password");
		
		try {
			DatabaseHelper helper = new DatabaseHelper();
			UserModel user = helper.login(email, password);
			if (user == null) {
				request.setAttribute("error", "fail");
			} else {
				session.setAttribute("user", user);
				RedirectHelper.index(response);
				return;
			}
		} catch (Exception e) {
			request.setAttribute("error", "server");
		}
		RequestHelper.dispatch(Urls.LoginJsp, request, response);
	}

}
