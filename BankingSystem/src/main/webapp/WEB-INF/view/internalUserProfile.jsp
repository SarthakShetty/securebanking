<%@ page isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Group 12 Bank</title>
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
			
			#profile{
				position: relative;
			}
			
			h1, h3,p, label{
				color: white !important;
			}
			
			#rightDiv{
				position: absolute;
				top: 0;
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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</head>
	<body>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<label class="navbar-brand" style="margin: 0 !important;">Group 12 Bank</label>
		  <ul class="navbar-nav">
			<li class="active nav-item"  ><a class="nav-link" href="/internalUser/profile">Profile</a></li>
			  <li class =" nav-item" ><a class="nav-link" href="/internalUser/transactions">View/Authorize Transactions</a></li>
				<li class ="nav-item" ><a class="nav-link" href="/internalUser/Requests">Requests</a></li>
			  <c:if test="${role == 'tier2' || role == 'tier1' }">
			  	<li class =" nav-item"  ><a class="nav-link" href="/internalUser/accountManagement">Account Management</a></li>
			  </c:if>
			  <c:if test="${role == 'admin'}">
				  	<li class =" nav-item"  ><a class="nav-link" href="/internalUser/accountManagement/admin">Account Management</a></li>
				  	<li class ="nav-item" ><a class="nav-link" href="/admin/systemLogs">System Log</a></li>
				</c:if>
		  </ul>
		   <ul class="navbar-nav ml-auto">
			  <li  class ="nav-item" ><a class="nav-link" href="/employee/logout">Logout</a></li>
		  </ul>
		</nav>
		<div id="container">
			<h1>Profile</h1>
			<hr class="divider">
			<div id="profile">
				<div id="leftDiv">
					<div>
						<label>Name:</label>
						<label>LastName:</label>
						<label>Username: </label>
						<label>Phone Number: </label>
					</div>
					<div>
						<label>${Firstname}</label>
						<label>${Lastname}</label>
						<label>${username}</label>
						<label>${phone}</label>
					</div>
				</div>
				<div id="rightDiv">
					<div>
						<label>Email: </label>
						<label>Address:</label>	
						
					</div>
					<div>
						<label>${email}</label>
						<label>${address}</label>
					</div>
				</div>
			</div>
			
			 
		</div>
	<script>
		
	</script>
	</body>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>