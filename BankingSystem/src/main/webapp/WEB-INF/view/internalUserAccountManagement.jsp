<%@ page isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Login</title>
		<style>
			#container{
				width: 60%;
				margin: 100px auto;
			}
			
			.divider{
				border-top: 1px solid #8c8b8b;
				
			}
			
			body{
				font-family: "Sqauda One" !important;
				background-image: linear-gradient(to right, #0F2027, #203A43, #2C5364);
				
			}
			
			
			h1, h3,p, label{
				color: white !important;
			}
		
			
			#accounts{
				position: relative;
				margin: 20px 0 0 0;
			}
			#accountDiv{
				display: inline;
				height: 100px;
				width: 70%;
				position: absolute;
				top: 0;
			}
			
			.my-custom-scrollbar {
				position: relative;
				height: 300px;
				overflow: auto;
			}
			.table-wrapper-scroll-y {
				display: block;
			}
			
		
			
			#newAccDiv, #modifyAcc{
				width: 100%;
				display: none;
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
			<li class=" nav-item"  ><a class="nav-link" href="/internalUser/profile">Profile</a></li>
			  <li class ="  nav-item" ><a class="nav-link" href="/internalUser/transactions">View/Authorize Transactions</a></li>
			  <li class =" active nav-item"  ><a class="nav-link" href="/internalUser/accountManagement">Account Management</a></li>
			  <li class ="nav-item" ><a class="nav-link" href="/internalUser/Requests">Requests</a></li>
			  
		  </ul>
		</nav>
		<div id="container">
			<h1>Account Management</h1>
			<hr class="divider" />
			
			<div id="accounts">
				<div id="accountDiv">
					<select class="custom-select" size="8">
					  <c:forEach items="${accountList}" var="aList">
					  	<option>${aList}</option>
					  </c:forEach>
					</select>
				</div>
				<div id="hm" style="display: inline; text-align: right;">
					<form action="">
						<input type="button" value="Delete" class="btn btn-info "/>
					</form>
					<form action="">
						<input type="button" onclick="modifyAcc()" value="Modify" class="btn btn-info" /> 
					</form>
					<form action="">
						<input type="button" onclick="showNewAcc()" value="Add Account" class="btn btn-info"/>
					</form>
				</div>
				<div id="newAccDiv">
					<h3>Add Account</h3>
					<div id="form">
						<hr class="divider">
						<form action="/confirmationAccoun" method="POST">
						  <div class="form-group row">
						    <label for="firstName" class="col-sm-5 col-form-label">First Name:</label>
						    <div class="col-sm-7">
						      <input type="text" name ="firstNameAdd"  class="form-control" id="firstName1"  placeholder="First Name" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="lastName" class="col-sm-5 col-form-label">Last Name:</label>
						    <div class="col-sm-7">
						      <input type="text" name = "lastNameAdd" class="form-control" id="lastName1" placeholder="Last Name" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="username" class="col-sm-5 col-form-label">Username:</label>
						    <div class="col-sm-7">
						      <input type="text" name = "usernameAdd" class="form-control" id="username1" placeholder="Username">
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="password" class="col-sm-5 col-form-label">Password:</label>
						    <div class="col-sm-7">
						      <input type="password" name="passwordAdd" class="form-control" id="password1" placeholder="Password" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="cPassword" class="col-sm-5 col-form-label">Confirm Password:</label>
						    <div class="col-sm-7">
						      <input type="password" name="cPasswordAdd" class="form-control" id="cPassword1" placeholder="Confirm Password">
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="address" class="col-sm-5 col-form-label">Address:</label>
						    <div class="col-sm-7">
						      <input type="text" name="addressAdd" class="form-control" id="address1" placeholder="Address">
						    </div>
						    </div>
						   <div class="form-group row">
						    <label for="email" class="col-sm-5 col-form-label">Email:</label>
						    <div class="col-sm-7">
						      <input type="text" name="emailAdd" class="form-control" id="email1" placeholder="Email">
						    </div>
						    </div>
						   <div class="form-group row">
						    <label for="mobile" class="col-sm-5 col-form-label">Phone Number:</label>
						    <div class="col-sm-7">
						      <input type="text" name="phoneAdd" class="form-control" id="mobile1" placeholder="Phone Number" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="mobile" class="col-sm-5 col-form-label">Age:</label>
						    <div class="col-sm-7">
						      <input type="text" name="ageAdd" class="form-control" id="age1" placeholder="Age" >
						    </div>
						  </div>s
						  <div id="bottom">
						  	<label>City:</label>
						  	<input type="text" name="cityAdd" placeholder="City" class='form-control' id="city" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
						  	<label>Zip:</label>
						  	<input type="text" name="zipAdd" placeholder="ZIP" class='form-control' id="zip" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
						  	<label>State:</label>
						  	<select class="form-control" name="stateAdd" id="state" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
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
					      	<input type="submit" value="Finish" class="btn btn-primary btn-md" style="margin: 30px auto 0 auto;">
					      </div>
						  </div>
						</form>
					</div>
				</div>
				<div id="modifyAcc">
					<h3>Modify</h3>
					<div id="form">
						<hr class="divider">
						<form action="/confirmationAccoun" method="POST">
						  <div class="form-group row">
						    <label for="firstName" class="col-sm-5 col-form-label">First Name:</label>
						    <div class="col-sm-7">
						      <input type="text" name ="firstNameModify"  class="form-control" id="firstName" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="lastName" class="col-sm-5 col-form-label">Last Name:</label>
						    <div class="col-sm-7">
						      <input type="text" name = "lastNameModify" class="form-control" id="lastName" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="username" class="col-sm-5 col-form-label">Username:</label>
						    <div class="col-sm-7">
						      <input type="text" name = "usernameModify" class="form-control" id="username" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="password" class="col-sm-5 col-form-label">Password:</label>
						    <div class="col-sm-7">
						      <input type="password" name="passwordModify" class="form-control" id="password" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="address" class="col-sm-5 col-form-label">Address:</label>
						    <div class="col-sm-7">
						      <input type="text" name="addressModify" class="form-control" id="address" >
						    </div>
						    </div>
						   <div class="form-group row">
						    <label for="email" class="col-sm-5 col-form-label">Email:</label>
						    <div class="col-sm-7">
						      <input type="text" name="emailModify" class="form-control" id="email" >
						    </div>
						    </div>
						   <div class="form-group row">
						    <label for="mobile" class="col-sm-5 col-form-label">Phone Number:</label>
						    <div class="col-sm-7">
						      <input type="text" name="phoneModify" class="form-control" id="mobile" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="mobile" class="col-sm-5 col-form-label">Age:</label>
						    <div class="col-sm-7">
						      <input type="text" name="ageModify" class="form-control" id="mobile" >
						    </div>
						  </div>
						  <div id="bottom">
						  	<label>City:</label>
						  	<input type="text" name="cityModify" class='form-control' id="city" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
						  	<label>Zip:</label>
						  	<input type="text" name="zipModify" class='form-control' id="zip" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
						  	<label>State:</label>
						  	<select class="form-control" name="stateModify" id="state" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
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
					      	<input type="submit" value="Modify" class="btn btn-primary btn-md" style="margin: 30px auto 0 auto;">
					      </div>
						  </div>
						</form>
					</div>
				</div>
			</div>
			 
		</div>
	<script>
	function showNewAcc(){
		document.getElementById('newAccDiv').style.display = 'block';
		document.getElementById('modifyAcc').style.display = 'none';
	}
	
	function modifyAcc(){
		document.getElementById('modifyAcc').style.display = 'block';
		document.getElementById('newAccDiv').style.display = 'none';
	}
	</script>
	</body>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>