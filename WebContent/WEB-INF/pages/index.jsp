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

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<jsp:include page="/WEB-INF/head.jsp" />
</head>
<body class="full-height">
	<jsp:include page="/WEB-INF/navbar.jsp" />
	<h1>Dashboard</h1>
	<div class="container">
		<div class="row full-height">
			<div class="col-6" style="border-style: solid;">
				<h2>Recent Activity</h2>
				
				<h4>Last Login Activity: <%=model.getFriendlyLastLogin()%></h4>
				<hr />
				<h4>Last User Profile Update: <%=model.getFriendlyLastProfileUpdate()%></h4>
				<hr />
				<h4>TODO: Inbox</h4>
			</div>
			<div class="col-6 full-height">
				<div class="row half-height" style="border-style: solid; padding: 8px;">
					<h4>Current Profile</h4>
					<br />
					<table class="table">
						<tr>
							<th>First Name:</th>
							<td><%=model.getFirstName()%></td>
						</tr>
						<tr>
							<th>Last Name:</th>
							<td><%=model.getLastName()%></td>
						</tr>
						<tr>
							<th>Email:</th>
							<td><%=model.getEmail()%></td>
						</tr>
						<tr>
							<th>Birthday:</th>
							<td><%=model.getFriendlyDob()%></td>
						</tr>
						<% if (billingAddress != null) { %>
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
						<% } %>
					</table>
				</div>
				<div class="row half-height" style="border-style: solid;">
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