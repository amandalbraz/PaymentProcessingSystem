<%@page import="comp3095.amtrix2.models.UserModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Assignment 2</a>
  <div id="navbarNav">
  	<span class="navbar-text">
		Welcome <%=((UserModel)session.getAttribute("user")).getFirstName()%>
  	</span>
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="/Comp3095_Amtrix2/">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/Comp3095_Amtrix2/profile">My Profile</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/Comp3095_Amtrix2/credit">Credit</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Inbox</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Support</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/Comp3095_Amtrix2/logout">Logout</a>
      </li>
      <li class="nav-item active" />
    </ul>
  </div>
</nav>