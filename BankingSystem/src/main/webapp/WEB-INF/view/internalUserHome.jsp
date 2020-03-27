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
			
			#leftDiv, #leftDiv div, #rightDiv, #rightDiv div{
				display: inline-block;
				width: 48%;
				font-size: large;
			}
			
			#leftDiv div label{
				display: block;
				margin: 0 0 45px 0;
			}
			
			#rightDiv div label{
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
			
			#transactions, #accounts, #log{
				display: none;
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
			
			#newAccDiv, #modifyAcc{
				width: 100%;
				display: none;
			}
		</style>
		<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
		<link href="https://fonts.googleapis.com/css?family=Squada+One&display=swap" rel="stylesheet">
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div id="container">
			<h1>Hello internal user - ${name}</h1>
			<ul class="nav nav-tabs">
			  <li class="active" id="pr" onclick="change(this)"><a href="#">Profile</a></li>
			  <li id="va" onclick="change(this)"><a href="#">View/Authorize Transactions</a></li>
			  <li id="am" onclick="change(this)"><a href="#">Account Management</a></li>
			  <li id="tl" onclick="change(this)"><a href="#">Requests</a></li>
			</ul>
			<div id="profile">
				<div id="leftDiv">
					<div>
						<label>Name:</label>
						<label>EmployeeID:</label>
						<label>DOB:</label>
					</div>
					<div>
						<label>${name}</label>
						<label>${employeeID}</label>
						<label>${dob}</label>
					</div>
				</div>
				<div id="rightDiv">
					<div>
						<label>Address:</label>	
						<label>City:</label>
						<label>Zip:</label>
						<label>State:</label>
					</div>
					<div>
					    <input type="text" class="form-control" id="address" >
					    <input type="text" class='form-control' id="city">
					  	<input type="text" class='form-control' id="zip" >
				  		<select class="form-control" id="state" >
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
			</div>
			<div id="transactions">
				<h1>Transactions</h1>
				<hr class="divider"/>
				<div id="listDiv" class="table-wrapper-scroll-y my-custom-scrollbar">
					<table class="table table-bordered mb-0">
						<thead>
						  <tr>
						    <th scope="col">Pending Transactions</th>
						    <th scope="col"></th>
						  </tr>
						</thead>
						<tbody>
						  <c:forEach items="${list}" var="tList">
						  	<tr>
						  		<td>
						  			${tList}
						  		</td>
						  		<td><input type="checkbox" class="form-check-input"></td>
						  	</tr>
						  </c:forEach>
						</tbody>
					</table>
				</div>
				<div id="authSide">
					<button class="btn btn-primary btn-md" onclick="window.location.href= '/welcome'">Authorize</button>
				</div>
			</div>
			<div id="accounts">
				<h1>Account List</h1>
				<hr class="divider"/>
				<div id="accountDiv">
					<select class="custom-select" size="8" style="width: 60%;">
					  <c:forEach items="${accountList}" var="aList">
					  	<option>${aList}</option>
					  </c:forEach>
					</select>
				</div>
				<div id="hm" style="display: inline-block; text-align: center;">
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
						      <input type="text" name ="firstName"  class="form-control" id="firstName"  placeholder="FirstName" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="lastName" class="col-sm-5 col-form-label">Last Name:</label>
						    <div class="col-sm-7">
						      <input type="text" name = "lastName" class="form-control" id="lastName" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="username" class="col-sm-5 col-form-label">Username:</label>
						    <div class="col-sm-7">
						      <input type="text" name = "username" class="form-control" id="username">
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="password" class="col-sm-5 col-form-label">Password:</label>
						    <div class="col-sm-7">
						      <input type="password" name="password" class="form-control" id="password" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="cPassword" class="col-sm-5 col-form-label">Confirm Password:</label>
						    <div class="col-sm-7">
						      <input type="password" name="cPassword" class="form-control" id="cPassword">
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="address" class="col-sm-5 col-form-label">Address:</label>
						    <div class="col-sm-7">
						      <input type="text" name="address" class="form-control" id="address" >
						    </div>
						    </div>
						   <div class="form-group row">
						    <label for="email" class="col-sm-5 col-form-label">Email:</label>
						    <div class="col-sm-7">
						      <input type="text" name="email" class="form-control" id="email" >
						    </div>
						    </div>
						   <div class="form-group row">
						    <label for="mobile" class="col-sm-5 col-form-label">Phone Number:</label>
						    <div class="col-sm-7">
						      <input type="text" name="mobile" class="form-control" id="mobile" >
						    </div>
						  </div>
						  <div id="bottom">
						  	<label>City:</label>
						  	<input type="text" name="city" class='form-control' id="city" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
						  	<label>Zip:</label>
						  	<input type="text" name="zip" class='form-control' id="zip" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
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
						      <input type="text" name ="firstName"  class="form-control" id="firstName"  placeholder="FirstName" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="lastName" class="col-sm-5 col-form-label">Last Name:</label>
						    <div class="col-sm-7">
						      <input type="text" name = "lastName" class="form-control" id="lastName" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="username" class="col-sm-5 col-form-label">Username:</label>
						    <div class="col-sm-7">
						      <input type="text" name = "username" class="form-control" id="username">
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="password" class="col-sm-5 col-form-label">Password:</label>
						    <div class="col-sm-7">
						      <input type="password" name="password" class="form-control" id="password" >
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="address" class="col-sm-5 col-form-label">Address:</label>
						    <div class="col-sm-7">
						      <input type="text" name="address" class="form-control" id="address" >
						    </div>
						    </div>
						   <div class="form-group row">
						    <label for="email" class="col-sm-5 col-form-label">Email:</label>
						    <div class="col-sm-7">
						      <input type="text" name="email" class="form-control" id="email" >
						    </div>
						    </div>
						   <div class="form-group row">
						    <label for="mobile" class="col-sm-5 col-form-label">Phone Number:</label>
						    <div class="col-sm-7">
						      <input type="text" name="mobile" class="form-control" id="mobile" >
						    </div>
						  </div>
						  <div id="bottom">
						  	<label>City:</label>
						  	<input type="text" name="city" class='form-control' id="city" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
						  	<label>Zip:</label>
						  	<input type="text" name="zip" class='form-control' id="zip" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
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
					      	<input type="submit" value="Modify" class="btn btn-primary btn-md" style="margin: 30px auto 0 auto;">
					      </div>
						  </div>
						</form>
					</div>
				</div>
			</div>
			<div id="log">
				<h1>Requests</h1>
				<hr class="divider"/>
				<div id="listDiv" class="table-wrapper-scroll-y my-custom-scrollbar">
					<table class="table table-bordered mb-0">
						<thead>
						  <tr>
						    <th scope="col">Requests</th>
						    <th scope="col"></th>
						  </tr>
						</thead>
						<tbody>
						  <c:forEach items="${list}" var="tList">
						  	<tr>
						  		<td>
						  			${tList}
						  		</td>
						  		<td><input type="checkbox" class="form-check-input"></td>
						  	</tr>
						  </c:forEach>
						</tbody>
					</table>
					<div style="text-align: center !important;">
						<button class="btn btn-info btn-md">Authorize</button>
					</div>
					
				</div>
			</div>
			 
		</div>
	<script>
		var active = "pr";
		var mDiv = "profile";
		function change(el){
			el.className = 'active';
			document.getElementById(active).className = '';
			document.getElementById(mDiv).style.display = 'none';

			active = el.id;
			
			switch(active){
				case 'pr':
					mDiv = "profile";
					break;
				case 'va':
					mDiv = "transactions";
					break;
				case 'am':
					mDiv = "accounts";
					break;
				case 'tl':
					mDiv = "log";
					break;
			}
			
			console.log(mDiv);
			document.getElementById(mDiv).style.display = 'block';
			console.log(active);
		}
		
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