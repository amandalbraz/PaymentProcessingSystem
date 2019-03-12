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
import comp3095.amtrix2.helpers.StringHelper;
import comp3095.amtrix2.helpers.Urls;
import comp3095.amtrix2.models.CreditCardModel;


@WebServlet("/credit")
public class CreditServlet extends HttpServlet {

	private static final long serialVersionUID = 5L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!SessionHelper.isStarted(request)) {
			RedirectHelper.login(response);
			return;
		}
		redirect(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!SessionHelper.isStarted(request)) {
			RedirectHelper.login(response);
			return;
		}
		
		int cardType = Integer.parseInt(request.getParameter("card_type"));
		String expires = request.getParameter("expires");
		String cardHolderName = request.getParameter("card_holder_name");
		String cardNumber = request.getParameter("card_number");
		
		
		if (expires.isEmpty() || cardHolderName.isEmpty() || cardNumber.isEmpty()) {
			request.setAttribute("error", "missingfield");
			redirect(request, response);
			return;
		}
		
		//CreditCardModel model = new CreditCardModel(cardType, cardHolderName, cardNumber, expiry, userId, isDefault)
	}
	private void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestHelper.dispatch(Urls.CreditJsp, request, response);
	}
}
