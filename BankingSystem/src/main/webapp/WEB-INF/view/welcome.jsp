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
			
			
			h1, p, label{
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
			
			#transactions table{
				color: white !important;
			}
			
			#listDiv, #authSide{
				display: inline-block;
			}
			
			#listDiv{
				width: 70%;
			}
			
			#authSide{
				width: 25%;
				text-align: center;
				position: absolute;
				right: 15%;
			}
			
			.my-custom-scrollbar {
				position: relative;
				height: 300px;
				overflow: auto;
			}
			.table-wrapper-scroll-y {
				display: block;
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
			<h1>Internal User</h1>
			<ul class="nav nav-tabs">
			  <li class="active" id="pr" onclick="change(this)"><a href="#">Profile</a></li>
			  <li id="va" onclick="change(this)"><a href="#">View/Authorize Transactions</a></li>
			  <li id="am" onclick="change(this)"><a href="#">Account Management</a></li>
			  <li id="tl" onclick="change(this)"><a href="#">View Transaction Log</a></li>
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
				<h1>Pending Transactions</h1>
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
				
			</div>
			<div id="log">
			
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
	</script>
	</body>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>