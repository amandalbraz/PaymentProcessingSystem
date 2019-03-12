<%@ page import="comp3095.amtrix2.models.AddressModel"%>
<%@ page import="comp3095.amtrix2.helpers.DatabaseHelper"%>
<%@ page import="comp3095.amtrix2.models.UserModel"%>
<%@ page import="comp3095.amtrix2.helpers.SessionHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
UserModel model = (UserModel)session.getAttribute("user");
DatabaseHelper db = new DatabaseHelper();
AddressModel billingAddress = db.getBillingAddress(model);
AddressModel shippingAddress = db.getShippingAddress(model);

if (billingAddress == null) billingAddress = AddressModel.Empty;
if (shippingAddress == null) shippingAddress = AddressModel.Empty;

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile</title>
<jsp:include page="/WEB-INF/head.jsp" />
</head>
<body class="full-height">
	<jsp:include page="/WEB-INF/navbar.jsp" />
	<h1>My Profile</h1>
	<div class="container">
		<div class="row full-height">
			<div class="col-6" style="border-style: solid;">
				<form method="POST">
					<table class="table">
						<tr>
							<th>First Name:</th>
							<td><input class="form-control" type="text" name="firstname" value="<%=model.getFirstName()%>" /></td>
						</tr>
						<tr>
							<th>Last Name:</th>
							<td><input class="form-control" type="text" name="lastname" value="<%=model.getLastName()%>" /></td>
						</tr>
						<tr>
							<th>Email:</th>
							<td><input class="form-control" type="text" name="email" value="<%=model.getEmail()%>" /></td>
						</tr>
						<tr>
							<th>Birthday:</th>
							<td><input class="form-control" type="date" name="dob" value="<%=model.getDob()%>" /></td>
						</tr>
						<tr>
							<th>Address:</th>
							<td><input class="form-control" type="text" name="address" value="<%=billingAddress.getAddress()%>" /></td>
						</tr>
						<tr>
							<th>City:</th>
							<td><input class="form-control" type="text" name="email" value="<%=billingAddress.getCity()%>" /></td>
						</tr>
						<tr>
							<th>Country</th>
							<td><input class="form-control" type="text" name="email" value="<%=billingAddress.getCountry()%>" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="col-6 full-height">
				<div class="row full-height" style="border-style: solid;">
					<% if (billingAddress != null) { %>
						<h4>Preferred Billing Address</h4>
						<br />
						<table class="table">
							<tr>
								<th>Address:</th>
								<td><%=billingAddress.getAddress()%></td>
							</tr>
							<tr>
								<th>City:</th>
								<td><%=billingAddress.getCity()%></td>
							</tr>
							<tr>
								<th>Country</th>
								<td><%=billingAddress.getCountry()%></td>
							</tr>
						</table>
					<% } %>
					<hr />
					<% if (shippingAddress != null) { %>
						<h4>Preferred Shipping Address</h4>
						<br />
						<table class="table">
							<tr>
								<th>Address:</th>
								<td><%=shippingAddress.getAddress()%></td>
							</tr>
							<tr>
								<th>City:</th>
								<td><%=shippingAddress.getCity()%></td>
							</tr>
							<tr>
								<th>Country</th>
								<td><%=shippingAddress.getCountry()%></td>
							</tr>
						</table>
					<% } %>
				</div>
			</div>
		</div>
	</div>
</body>
</html>