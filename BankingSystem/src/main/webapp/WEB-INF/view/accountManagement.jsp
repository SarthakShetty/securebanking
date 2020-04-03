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
		  
		<ul class="navbar-nav ml-auto">
		    
			  <li  class ="nav-item" ><a class="nav-link" href="/customer/logout">Logout</a></li>
		  </ul>
		  	
		</nav>
	<%-- 	<%
			out.print(session.getAttribute("user_id"));
		%> --%>
		<div id="container">
			<ul class="nav nav-tabs">
			  <li class="nav-item active" id="pr" onclick="change(this)">
			    <a class="nav-link" href="#" style="color: white;">Banking Statements</a>
			  </li>
			  <li class="nav-item" id="va" onclick="change(this)">
			    <a class="nav-link" style="color: white;" href="#">Delete an Account</a>
			  </li>
			  <li class="nav-item" id="dp" onclick="change(this)">
			    <a class="nav-link" style="color: white;" href="#">Create a new Account</a>
			  </li>
			</ul>
			
			<div id="accMan" style="text-align: center;">
				<div id="bank" style="margin: 20px 0 0 0; display: block;">
					<form action="/customer/downloadBankingStatements" >
						<h2>Banking Statements</h2>
						<hr class="divider"/>
						${msg_works }
						<div class="row">
							<div class="col">
								<div class="row">
									<div class="col-6">
										<label>Which account do you want to statements from?
											<%-- <select name="account" id="t" style="display: block; margin: 20px auto;">
									  			<c:forEach items="${accountList}" var="list">
									  				<option value="${accountList.number }">${list.number }</option>
									  			</c:forEach>
										  	</select> --%>
										</label>
									</div>
									<div class="col-6">
										<label for="t">How many months do you want statements from? 
											<!-- <select name="time" id="t" style="display: block; margin: 20px auto;">
									  			<option value="1">1 month</option>
									  			<option value="6">6 months</option>
									  			<option value="12">12 months</option>
										  	</select> -->
										</label>
									</div>
									
								</div>
							</div>
						</div>
					  	<input type="submit" class="btn btn-info btn-md" value="Download Banking Statements"/>
				  	</form>
				</div>
				<div  id ="del" style="margin: 40px 0 0 0; display: none;">
					<form action="/customer/accountManagement/0" method="post">
						<h2>Delete an Account</h2>
						<hr class="divider"/>
						<div class="row">
							<div class="col">
								<%-- <select name="account" style="display: block; margin: 20px auto;">
							  		<c:forEach items="${accountList}" var="aList">
							  			<option value="${aList.acc_id}">${aList.acc_id}</option>
							  		</c:forEach>
							  	</select> --%>
							</div>
						</div>
					  	
					  	<input type="submit" class="btn btn-info btn-md" value="Submit Request"/>
				  	</form>
				</div>
				<div id="create" style="margin: 40px 0 0 0; display: none;">
					<h2>Create New Account</h2>
					<hr class="divider">
					<div id="form">
					
					<form action="/customer/accountManagement/1" method="post">
					  <div class="form-group row">
					    <label for="intialdeposit" class="col-sm-5 col-form-label">Initial deposit:</label>
					    <div class="col-sm-7">
					      <input type="text" name ="intialdeposit"  class="form-control" id="deposit"  placeholder="DepositAmount" >
					    </div>
					  </div>
				      <div>
							<h3 style="margin: 20px 0 20px 0; color: white;">Type of Account</h3>
							<label>
						    	<input type="radio" name="type_account" id="treq" autocomplete="off" checked value="credit"> Credit
						  	</label>
						  	<label>
						    	<input type="radio" name="type_account" id="preq" autocomplete="off" value="saving"> Saving
						  	</label>
						  	<label>
						    	<input type="radio" name="type_account" id="preq" autocomplete="off" value="current"> Current
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
		</div>
	<script>
		var active = "pr";
		var mDiv = "bank";
		function change(el){
			el.className = 'nav-item active';
			document.getElementById(active).className = 'nav-item';
			document.getElementById(mDiv).style.display = 'none';
	
			active = el.id;
			
			switch(active){
				case 'pr':
					mDiv = "bank";
					break;
				case 'va':
					mDiv = "del";
					break;
				case 'dp':
					mDiv = "create";
					break;
			}
			
	
			document.getElementById(mDiv).style.display = 'block';
			
		}
	</script>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>