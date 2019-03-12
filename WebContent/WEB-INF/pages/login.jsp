<%@page import="comp3095.amtrix2.helpers.RecaptchaHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String error = (String)request.getAttribute("error");

String errMsg = null;
if (error != null) {
	if (error.contentEquals("recaptcha")) {
		errMsg = "There was an error in the captcha validation";
	} else if (error.contentEquals("login")) {
		errMsg = "Invalid username/password combination";
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<jsp:include page="/WEB-INF/head.jsp" />
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
	<div class="container full-height">
		<h1 class="page-title">Login</h1>
		<hr>
		<form action="login" method="POST">
			<div class="form-group">
				<label for="email">Email</label>
				<input class="form-control" type="text" placeholder="Enter Email" value="admin@domain.ca" name="email" required>
			</div>
			<div class="form-group">
				<label for="password">Password</label>
		 		<input class="form-control" type="password" placeholder="Enter Password" value="P@ssword!" name="password" required>
			</div>
			<% if (RecaptchaHelper.Enabled) { %>
				<jsp:include page="/WEB-INF/recaptcha.jsp" />
			<% } %>
			<div class="form-group">
				<div class="row">
					<div class="col-6">
						<button class="btn btn-primary btn-block" type="submit">Login</button>
					</div>
					<div class="col-6">
						<a class="btn btn-success btn-block" href="register">Register</a>
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