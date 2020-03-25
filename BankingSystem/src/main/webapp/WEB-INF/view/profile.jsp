<%@ page isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Group 12 Bank</title>
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
			
			#leftDiv, #leftDiv div, #rightDiv, #rightDiv div{
				display: inline-block;
				width: 48%;
				font-size: large;
			}
			
			#leftDiv label{
				display: block;
				margin: 0 0 20px 0;
			}
			
			#rightDiv label{
				display: block;
				margin: 0 0 20px 0;
			}
			
			#rightDiv div input,#rightDiv div select {
				display: block;
				width: 100%;
				margin: 0 0 10px 0;
			}
			#profile, #transactions, #accounts, #log{
				margin: 20px 0 0 0;
			}
			
			
			table{
				color: white !important;
			}
			
			#listDiv, #authSide{
				display: inline-block;
			}
			
			#listDiv{
				width: 70%;
				display: inline;
			}
			
			#authSide{
				width: 100%;
				text-align: center;
				
			}
			
			#accountDiv{
				display: inline;
				height: 100px;
			}
			
			.my-custom-scrollbar {
				position: relative;
				height: 300px;
				overflow: auto;
			}
			.table-wrapper-scroll-y {
				display: block;
			}
			
			#hm form{
				margin-bottom: 30px;
				margin-left: 50px;
			}
			
			#newAccDiv{
				width: 100%;
				display: none;
			}
			input[type=radio]{
				margin: 0 10px 0 10px !important;			
			}
			

			
			
			
			#editInfo{
				position: absolute;
				top: 0%;
				right: 30%;
			}
			
			#submitInfo{
				position: absolute;
				right: 0%;
				top: 15%;
			}
			
			#profile{
				position: relative;
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
			  <li id="mp" class ="nav-item" ><a class="nav-link" href="/customer/payment">Make Payment</a></li>
			  <li id="pr" class ="active nav-item" ><a class="nav-link" id="" href="/customer/profile">Profile</a></li>
			  <li id="am" class ="nav-item" ><a class="nav-link" href="/customer/accountManagement">Account Management</a></li>
			  <li id="hs" class ="nav-item" ><a class="nav-link" href="/customer/helpSupport">Help and Support</a></li>
		  </ul>
		</nav>
		<%
			out.print(session.getAttribute("user_id"));
		%>
		<div id="container">
			<h1>Profile</h1>
			<hr class="divider"/>
			<div id="profile">
				<div id="nameInfo">
					<div>
						<label style="display: inline-block;">Name:</label>
						<%-- <label style="display: inline-block;">${name}</label> --%>
					</div>
				</div>
				<div id="editInfo">
					<div class="form-group row">
				    <label for="phone" class="col-sm-5 col-form-label">Phone Number:</label>
				    <div class="col-sm-7">
				      <input type="text"  class="form-control" id="phone">
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="address" class="col-sm-5 col-form-label">Address:</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="address" >
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="email" class="col-sm-5 col-form-label">Email:</label>
				    <div class="col-sm-7">
				      <input type="text" name="email" class="form-control" id="email" >
				    </div>
				   </div>
				  <div id="bottom">
				  	<label>City:</label>
				  	<input type="text" class='form-control' id="city" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
				  	<label>Zip:</label>
				  	<input type="text" class='form-control' id="zip" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
				  	<label>State:</label>
				  	<select class="form-control" id="state" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
				        <option>AL</option>
				        <option>AK</option>
				        <option>AZ</option>
				        <option>AR</option>
				        <option>CA</option>
				        <option>CO</option>
				        <option>CT</option>
				        <option>DE</option>
				        <option>FL</option>
				        <option>GA</option>
				        <option>HI</option>
				        <option>ID</option>
				        <option>IL</option>
				        <option>IN</option>
				        <option>IA</option>
				        <option>KS</option>
				        <option>KY</option>
				        <option>LA</option>
				        <option>ME</option>
				        <option>MD</option>
				        <option>MA</option>
				        <option>MI</option>
				        <option>MN</option>
				        <option>MS</option>
				        <option>MO</option>
				        <option>MT</option>
				        <option>NE</option>
				        <option>NV</option>
				        <option>NH</option>
				        <option>NJ</option>
				        <option>NM</option>
				        <option>NY</option>
				        <option>NC</option>
				        <option>ND</option>
				        <option>OH</option>
				        <option>OK</option>
				        <option>OR</option>
				        <option>PA</option>
				        <option>RI</option>
				        <option>SC</option>
				        <option>SD</option>
				        <option>TN</option>
				        <option>TX</option>
				        <option>UT</option>
				        <option>VT</option>
				        <option>VA</option>
				        <option>WA</option>
				        <option>WV</option>
				        <option>WI</option>
				        <option>WY</option>
			      	</select>
			      
				  </div>
				</div>
				<div id="submitInfo">
					<button class="btn btn-md btn-info">Make Changes</button>
				</div>
			</div>
		</div>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>