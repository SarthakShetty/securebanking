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
			
			#transferB div, #credit div, #payment div{
				display: inline-block;
				margin: 0 100px 0 0;
			}
			
			#credit input, #payment label, #payment select{
				display: block;
				margin: 10px 0 0 0;
			}
			
			#payment button{
				display: block;
				margin: 10px 0 0 0;
			}
			
			#nameInfo, #editInfo, #submitInfo{
				display: inline-block;
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
			
			#acctransfer, #emailphonetransfer{
				display: inline;
				width: 40%;
				text-align: center;
			}
			
			#transfer input{
				margin: auto;
			}
			#acctransfer{
				margin: 0 50px 0 0;
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
		<label class="navbar-brand" style="margin: 0 !important;">Group 12 Bank</label>
		  <ul class="navbar-nav">
		    <li class="nav-item" id="acc" ><a class="nav-link" href="/customer/Account">Account</a></li>
			  <li id="rp" class ="nav-item" ><a class="nav-link" href="/customer/transferEmailPhone">Transfer/Make Payment</a></li>
			  <li id="tba" class =" nav-item"  ><a class="nav-link" href="/customer/transferBA">Transfer Between Accounts</a></li>
			  <li id="cd" class ="nav-item" ><a class="nav-link" href="/customer/CreditDebit">Credit/Debit</a></li>
			  <li id="mp" class ="nav-item" ><a class="nav-link" href="/customer/payment">Payment Requests</a></li>
			  <li id="pr" class ="nav-item" ><a class="nav-link" id="" href="/customer/profile">Profile</a></li>
			  <li id="am" class ="active nav-item" ><a class="nav-link" href="/customer/accountManagement">Account Management</a></li>
			  <li id="hs" class ="nav-item" ><a class="nav-link" href="/customer/helpSupport">Help and Support</a></li>
		  </ul>
		</nav>
	<%-- 	<%
			out.print(session.getAttribute("user_id"));
		%> --%>
		<div id="container">
			<h1>Account Management</h1>
			<hr class="divider" />
			<div id="accMan" style="text-align: center;">
				<div style="margin: 20px 0 0 0; display: block;">
					<form action="">
						<h2>Banking Statements</h2>
						<hr class="divider"/>
					  	<input type="submit" class="btn btn-info btn-md" value="Download Banking Statements"/>
				  	</form>
				</div>
				<div style="margin: 40px 0 0 0; display: block;">
					<form action="/customer/accountMangement/0">
						<h2>Delete an Account</h2>
						<hr class="divider"/>
					  	<select name="account" style="display: block; margin: 20px auto;">
					  		<c:forEach items="${accountList}" var="aList">
					  			<option>${aList}</option>
					  		</c:forEach>
					  	</select>
					  	<input type="submit" class="btn btn-info btn-md" value="Submit Request"/>
				  	</form>
				</div>
				<div style="margin: 40px 0 0 0; display: block;">
					<h2>Create New Account</h2>
					<hr class="divider">
				</div>
				
				<div id="form">
					
					<form action="/customer/accountMangement/1" method="POST">
					  <div class="form-group row">
					    <label for="firstName" class="col-sm-5 col-form-label">First Name:</label>
					    <div class="col-sm-7">
					      <input type="text" name ="firstName"  class="form-control" id="firstName"  placeholder="FirstName" >
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="lastName" class="col-sm-5 col-form-label">Last Name:</label>
					    <div class="col-sm-7">
					      <input type="text" name = "lastName" class="form-control" id="lastName" placeholder="Last Name">
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="username" class="col-sm-5 col-form-label">Username:</label>
					    <div class="col-sm-7">
					      <input type="text" name = "username" class="form-control" id="username" placeholder="Username">
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="password" class="col-sm-5 col-form-label">Password:</label>
					    <div class="col-sm-7">
					      <input type="password" name="password" class="form-control" id="password" placeholder="Password">
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="cPassword" class="col-sm-5 col-form-label">Confirm Password:</label>
					    <div class="col-sm-7">
					      <input type="password" name="cPassword" class="form-control" id="cPassword" placeholder="Confirm Password">
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="address" class="col-sm-5 col-form-label">Address:</label>
					    <div class="col-sm-7">
					      <input type="text" name="address" class="form-control" id="address" placeholder="Address">
					    </div>
					    </div>
					   <div class="form-group row">
					    <label for="email" class="col-sm-5 col-form-label">Email:</label>
					    <div class="col-sm-7">
					      <input type="text" name="email" class="form-control" id="email" placeholder="Email">
					    </div>
					   </div>
					   <div class="form-group row">
					    <label for="mobile" class="col-sm-5 col-form-label">Phone Number:</label>
					    <div class="col-sm-7">
					      <input type="text" name="mobile" class="form-control" id="mobile" placeholder="Phone Number">
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="email" class="col-sm-5 col-form-label">Age:</label>
					    <div class="col-sm-7">
					      <input type="text" name="age" class="form-control" id="age" placeholder="Age">
					    </div>
					   </div>
					  <div id="bottom">
					  	<label>City:</label>
					  	<input type="text" name="city" placeholder="City" class='form-control' id="city" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
					  	<label>Zip:</label>
					  	<input type="text" name="zip" placeholder="ZIP" class='form-control' id="zip" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
					  	<label>State:</label>
					  	<select class="form-control" name="state" id="state" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
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
				      <div>
							<h3 style="margin: 20px 0 20px 0; color: white;">Type of Account</h3>
							<label>
						    	<input type="radio" name="type_account" id="treq" autocomplete="off" checked value="credit"> Credit
						  	</label>
						  	<label>
						    	<input type="radio" name="type_account" id="preq" autocomplete="off" value="debit"> Debit
						  	</label>
						</div>
			      <div>
				      <div>
				      	<input type="submit" value="Submit Request" class="btn btn-info btn-md" style="margin: 30px auto 0 auto;">
				      	<p style="margin: 20px 0 0 0;"><font color="red">${error_msg}</font></p>
				      </div>
					  </div>
					</form>
				</div>
			</div>
		</div>
	<script>
		
	</script>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>