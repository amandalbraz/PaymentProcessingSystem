<%@ page import="comp3095.amtrix2.models.AddressModel"%>
<%@ page import="comp3095.amtrix2.helpers.DatabaseHelper"%>
<%@ page import="comp3095.amtrix2.models.UserModel"%>
<%@ page import="comp3095.amtrix2.helpers.SessionHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	UserModel model = (UserModel) session.getAttribute("user");
	DatabaseHelper db = new DatabaseHelper();
	AddressModel billingAddress = db.getBillingAddress(model);
	AddressModel shippingAddress = db.getShippingAddress(model);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Credit Profile</title>
<jsp:include page="/WEB-INF/head.jsp" />
</head>
<body class="full-height">
	<jsp:include page="/WEB-INF/navbar.jsp" />
	<h1>Credit Profile</h1>
	<div class="container">
		<div class="row full-height">
			<form method="POST">
				<div class="form-check-inline">
					<div class="form-group">
						<label class="control-label">Card Type</label>
					</div>
					<div class="form-group">
						<div class="radio">
							<label class="radio-inline control-label"> <input
								type="radio" name="card_type" value="1" checked>
								Mastercard
							</label>
						</div>
					</div>
					<div class="form-group">
						<div class="radio">
							<label class="radio-inline control-label"> <input
									type="radio" name="card_type" value="2">
								Visa
							</label>
						</div>
					</div>
					<div class="form-group">
						<div class="radio">
							<label class="radio-inline control-label"> <input
								type="radio" name="card_type" value="3">
								American Express
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="expires">Expiration Date</label>
					<input class="form-control" type="month" name="expires" required>
				</div>
				<div class="form-group">
					<label for="card_holder_name">Card Holder Name</label>
					<input class="form-control" type="text" name="card_holder_name" required>
				</div>
				<div class="form-group">
					<label for="card_number">Card Number</label>
					<input class="form-control" type="text" name="card_number" required>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-6">
							<button class="btn btn-primary btn-block" type="submit">Add Card</button>
						</div>
						<div class="col-6">
							<button class="btn btn-secondary btn-block" type="reset">Reset Form</button>
						</div>			
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>