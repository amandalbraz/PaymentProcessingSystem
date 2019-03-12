package comp3095.amtrix2.servlets;

import java.io.IOException;
import java.sql.Date;

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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (SessionHelper.isStarted(request)) {
			RedirectHelper.index(response);
			return;
		}
		RequestHelper.dispatch(Urls.RegisterJsp, request, response);
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
			RequestHelper.dispatch(Urls.RegisterJsp, request, response);
			return;
		}
		// Session is actually created here
		HttpSession session = request.getSession();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String dob = request.getParameter("dob");
		
		try {
			DatabaseHelper helper = new DatabaseHelper();
			UserModel user = helper.register(new UserModel(firstName, lastName, email, password, "user", null, Date.valueOf(dob), -1, -1, null, null));
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
		RequestHelper.dispatch(Urls.RegisterJsp, request, response);
	}

}
