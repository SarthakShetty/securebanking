<%@ page isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Login</title>
		<style>
			#container{
				width: 70%;
				margin: 100px auto;
			}
			
			.divider{
				border-top: 1px solid #8c8b8b;
				
			}
			
			body{
				font-family: "Sqauda One" !important;
				background-image: linear-gradient(to right, #0F2027, #203A43, #2C5364);
				
			}
			
			
			h1, h2, h3, p, label{
				color: white !important;
			}
			
			
			
		</style>
		<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
		<link href="https://fonts.googleapis.com/css?family=Squada+One&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
		
	</head>
	<body>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		  <ul class="navbar-nav">
		    <li class="nav-item" id="acc" ><a class="nav-link" href="/customer/Account">Account</a></li>
			  <li id="rp" class =" nav-item" ><a class="nav-link" href="/customer/transferEmailPhone">Transfer/Make Payment</a></li>
			  <li id="tba" class =" nav-item"  ><a class="nav-link" href="/customer/transferBA">Transfer Between Accounts</a></li>
			  <li id="cd" class ="nav-item" ><a class="nav-link" href="/customer/CreditDebit">Credit/Debit</a></li>
			  <li id="mp" class ="nav-item" ><a class="nav-link" href="/customer/payment">Payment Requests</a></li>
			  <li id="pr" class ="nav-item" ><a class="nav-link" id="" href="/customer/profile">Profile</a></li>
			  <li id="am" class ="nav-item" ><a class="nav-link" href="/customer/accountManagement">Account Management</a></li>
			  <li id="hs" class ="active nav-item" ><a class="nav-link" href="/customer/helpSupport">Help and Support</a></li>
		  </ul>
		</nav>
		<%-- <%
			out.print(session.getAttribute("user_id"));
		%> --%>
		<div id="container">
			<h1>Schedule an Appointment</h1>
			<hr class="divider" />
			<div id="helpsup" style="text-align: center;">
				<form action="/customer/schedule">
				  <h3>Select a date </h3>
				  <input type="date" id="dateS" name="dates" value="2020-04-10" style="display: block; margin: 10px auto">
				  <input type="submit" class="btn btn-info btn-md" id="sub">
				</form>
				
			</div>
		</div>
	<script>
	</script>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>