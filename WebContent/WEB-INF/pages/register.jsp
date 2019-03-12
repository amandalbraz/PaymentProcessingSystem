<%@page import="comp3095.amtrix2.helpers.RecaptchaHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String error = (String)request.getAttribute("error");

String errMsg = null;
if (error != null) {
	if (error.contentEquals("recaptcha")) {
		errMsg = "Failed captcha validation";
	} else if (error.contentEquals("server")) {
		errMsg = "Unexpected error";
	} else if (error.contentEquals("fail")) {
		errMsg = "Something happened";
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<jsp:include page="/WEB-INF/head.jsp" />
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
	
	<div class="container">
		<h1 class="page-title">Register</h1>
		<hr>
		<form method="POST" action="register">
			<div class="form-group">
				<label for="email">First Name</label>
				<input class="form-control" type="text" placeholder="Enter First Name" name="firstName" required>
			</div>
			<div class="form-group">
				<label for="email">Last Name</label>
				<input class="form-control" type="text" placeholder="Enter Last Name" name="lastName" required>
			</div>
			<div class="form-group">
				<label for="email">Email</label>
				<input class="form-control" type="email" placeholder="Enter Email" name="email" required>
			</div>
			<div class="form-group">
				<label for="email">Password</label>
				<input class="form-control" type="password" placeholder="Enter Password" name="password" required>
			</div>
			<div class="form-group">
				<label for="dob">Date of birth</label>
				<input class="form-control" type="date" name="dob" required/>
			</div>
			<% if (RecaptchaHelper.Enabled) { %>
				<jsp:include page="/WEB-INF/recaptcha.jsp" />
			<% } %>
			<div class="form-group">
				<div class="row">
					<div class="col-6">
						<button class="btn btn-primary btn-block" type="submit">Register</button>
					</div>
					<div class="col-6">
						<button class="btn btn-secondary btn-block" type="reset">Reset Form</button>
					</div>			
				</div>
			</div>
		</form>
		<% if (errMsg != null) { %>
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
				<%=errMsg%>
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
   					<span aria-hidden="true">&times;</span>
					</button>
			</div>
		<% } %>
	</div>
	
<jsp:include page="/WEB-INF/foot.jsp" />
</body>
</html>