<%@ page isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Group 12 Bank</title>
		<style>
			#container{
				width: 70%;
				margin: 50px auto;
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
			
			
			input[type=radio]{
				margin: 0 10px 0 10px !important;			
			}

			
			
			#acctransfer, #emailphonetransfer{
				display: block;
				text-align: center;
			}
			
			#transfer input{
				margin: auto;
			}
			
			h3{
				margin: 30px 0 5px 0 !important;
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
			  <li id="rp" class ="active nav-item" ><a class="nav-link" href="/customer/transferEmailPhone">Transfer/Make Payment</a></li>
			  <li id="tba" class =" nav-item"  ><a class="nav-link" href="/customer/transferBA">Transfer Between Accounts</a></li>
			  <li id="cd" class ="nav-item" ><a class="nav-link" href="/customer/CreditDebit">Credit/Debit</a></li>
			  <li id="mp" class ="nav-item" ><a class="nav-link" href="/customer/payment">Payment Requests</a></li>
			  <li id="pr" class ="nav-item" ><a class="nav-link" id="" href="/customer/profile">Profile</a></li>
			  <li id="am" class ="nav-item" ><a class="nav-link" href="/customer/accountManagement">Account Management</a></li>
			  <li id="hs" class ="nav-item" ><a class="nav-link" href="/customer/helpSupport">Help and Support</a></li>
		  </ul>
		  <ul class="navbar-nav ml-auto">
			  <li  class ="nav-item" ><a class="nav-link" href="/customer/logout">Logout</a></li>
		  </ul>
		</nav>
		
		<div id="container">
			<ul class="nav nav-tabs">
			  <li class="nav-item active" id="pr" onclick="change(this)">
			    <a class="nav-link" href="#" style="color: white;">Transfer by Account/Request By User</a>
			  </li>
			  <li class="nav-item" id="va" onclick="change(this)">
			    <a class="nav-link" style="color: white;" href="#">Transfer/Request by Email/Phone</a>
			  </li>
			</ul>
			
			<div id="transfer">
				
				<div id="accTransfer">
				
				<p style="margin: 20px 0 0 0;"><font color="red">${error_msg}</font></p>
				<p style="margin: 20px 0 0 0;"><font color="green">${msg}</font></p>
					<form action="/customer/transferFundsOtherAccount" method="post">
						<div class="row">
							<div class="col">
								<h3> My Account</h3>
								<select name="from_acc">
									<c:forEach items="${accounts}" var="aList">
									<%-- <option value="${aList.acc_id}">${aList.acc_id}(Balance : ${aList.curr_bal})</option>  --%>
									</c:forEach>
								</select>
							</div>
							<div class="col">
								<h3>Type of Request</h3>
								<label>
							    	<input type="radio" name="request" id="treq"  value="transfer"> Transfer
							  	</label>
							  	
							  	<label>
							    	<input type="radio" name="request" id="treq"  value="request"> Request Payment
							  	</label>
							  	
							</div>
							<div class="col">
								<h3>Transfer to Account</h3>
							  	<input type="text" placeholder="Account Number" name="accNumber" id="accNum" style="display: block;"/>
	
							  	<div id="errorTo" style="display: none;">
									<p style="margin: 20px 0 0 0;"><font color="red">Please enter a valid account number.</font></p>
								</div>
	
							</div>
							<div class="col">
								<h3>Request from User</h3>
							  	<input type="text" placeholder="Username" name="accUsername" id="accUser" style="display: block;"/>
	
							  	<div id="errorUser" style="display: none;">
									<p style="margin: 20px 0 0 0;"><font color="red">Please enter a valid username.</font></p>
								</div>
	
							</div>
						</div>
						
						<div class="col" >
								<h3>Amount</h3>
								<input type="text" placeholder="Amount" name="accAmount" id="accAmount" style="display: block;" />
								<div id="error" style="display: none;">
									<p style="margin: 20px 0 0 0;"><font color="red">Please enter a valid amount.</font></p>
								</div>

							</div>
						<div style="text-align: center;">
							<input  type="button" value="Request" class="btn btn-md btn-info" id="accButton" onclick="checkModal(this)" style="margin: 25px 0 0 0;">
							<input  type="submit" id="accButtonH"  style="display: none;">
							

						</div>
						<div id="errorEmpty" style="display: none;">
								<p style="margin: 20px 0 0 0;"><font color="red"></font></p>
							</div>

						</form>
					</div>

					
				</div>
				<div id="emailphonetransfer" style="display: none;">
				<p style="margin: 20px 0 0 0;"><font color="red">${error_msg}</font></p>
				<p style="margin: 20px 0 0 0;"><font color="green">${msg}</font></p>
				
					<form action="/customer/transferFundsEmailPhone" method="post">
						<div class="row">
							<div class="col">
								<h3>From Account</h3>
								<select name="from_accP">
									<c:forEach items="${accounts}" var="aList">
										<%-- <option value="${aList.acc_id}">${aList.acc_id}(Balance : ${aList.curr_bal})</option>  --%>
									</c:forEach>
								</select>
							</div>
							<div class="col">
								<h3>Phone/Email</h3>
								<label>
							    	<input type="radio" name="type" id="phoneEmail" autocomplete="off" value="phone"> Phone
							  	</label>
							  	<label>
							    	<input type="radio" name="type" id="phoneEmail" autocomplete="off" value="email"> Email
							  	</label>
							  	<div id="error3" style="display: none;">
								<p style="margin: 20px 0 0 0;"><font color="red">Please select at least one of the options.</font></p>
								</div>
							</div>
							<div class="col">
								<h3>To</h3>
								
							  	<input type="text" placeholder="Phone Number" name="phoneNumber" id="pn" style="display: block; margin: 0 auto 30px auto;" disabled/>
							  	<input type="text" placeholder="Email Address" name="emailAddress" id="ea" style="display: block; margin: 0 auto 30px auto;" disabled/>
							  	<div id="error2" style="display: none;">
								<p style="margin: 20px 0 0 0;"><font color="red">Please enter a valid phone number or email.</font></p>
								</div>
							</div>
							
							
						</div>
						<div class="col">
								<h3>Amount</h3>
								<input type="text" placeholder="Amount" name="peAmount" id="peAmount" style="display: block; margin: 0 auto 30px auto;" />
								<div id="error1" style="display: none;">
									<p style="margin: 20px 0 0 0;"><font color="red">Please enter a valid amount.</font></p>
								</div>
	
							</div>
						<div style="text-align: center;">
							<input  type="button" value="Request" class="btn btn-md btn-info" id="peButton" onclick="checkModal(this)" style="margin: 25px 0 0 0;">
							<input  type="submit" id="peButtonH"  style="display: none;">

							
						</div>
					</form>
			</div>
			<div class="modal fade" id="myModal3" role="dialog" style="display: none;">
			    <div class="modal-dialog" >
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			         <h4 class="modal-title" style="color: red;" id="modalHead">Attention</h4>
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			         
			        </div>
			        <div class="modal-body">
			          <p style="color: black !important;" id="modalmsg">The specified amount caused this request to be considered a critical transaction.</p>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
			        </div>
			      </div>
			      
			    </div>
			 </div>
			 <input type="button" style="display: none;" data-toggle="modal" data-target="#myModal3" id="hiddenBut2">
			 
			 <div class="modal fade" id="myModal4" role="dialog" style="display: none;">
			    <div class="modal-dialog" >
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			        <h4 class="modal-title" style="color: red;" id="modalHead">Attention</h4>
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          
			        </div>
			        <div class="modal-body">
			          <p style="color: black !important;" id="modalmsg">The specified amount caused this request to be considered a critical transaction.</p>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
			        </div>
			      </div>
			      
			    </div>
			 </div>
			 <input type="button" style="display: none;" data-toggle="modal" data-target="#myModal4" id="hiddenBut3">
		</div>
	<script>
		$('input:radio[name="type"]').change(function(){
			var x = this.value;
			if(x == 'phone'){
				document.getElementById("pn").disabled = false;
				document.getElementById("pn").focus();
				document.getElementById("ea").disabled = true;
			}
			else{
				document.getElementById("ea").disabled = false;
				document.getElementById("ea").focus();
				document.getElementById("pn").disabled = true;
			}
		}); 
		
		$('input:radio[name="request"]').change(function(){
			var x = this.value;
			if(x == 'transfer'){
				document.getElementById("accNum").disabled = false;
				document.getElementById("accNum").focus();
				document.getElementById("accUser").disabled = true;
			}
			else{
				
				document.getElementById("accUser").disabled = false;
				document.getElementById("accUser").focus();
				document.getElementById("accNum").disabled = true;
			}
		}); 
		var active = "pr";
		var mDiv = "accTransfer";
		function change(el){
			el.className = 'nav-item active';
			document.getElementById(active).className = 'nav-item';
			document.getElementById(mDiv).style.display = 'none';
	
			active = el.id;
			
			switch(active){
				case 'pr':
					mDiv = "accTransfer";
					break;
				case 'va':
					mDiv = "emailphonetransfer";
					break;
			}
			
	
			document.getElementById(mDiv).style.display = 'block';
			
		}
		
		
		function checkModal(el){
			if(el.id == "accButton"){
				
					if((document.getElementById("accNum").value.length == 0 || document.getElementById("accNum").value < 0) && document.getElementById("accUser").value.length == 0){
						document.getElementById("errorEmpty").style.display = 'block';
						document.getElementById("errorEmpty").innerText = "Please enter a valid account number or username";
					}
					else if(document.getElementById("accAmount").value > 1000){
						document.getElementById("hiddenBut2").click();
					}
					else{
						document.getElementById("accButtonH").click();
					}
				
				
				
			}
			else if(el.id == "peButton"){
				console.log(el);
				/* if(!document.getElementById("phone").checked && !document.getElementById("email").checked){
					document.getElementById("error3").style.display = 'block';
				}
				else if(document.getElementById("phone").checked){
					if(document.getElementById("pn").value.length == 0){
						document.getElementById("error2").style.display = 'block';
						
					}
					
				}
				else if(document.getElementById("email").checked){
					if(document.getElementById("ea").value.length == 0){
						document.getElementById("error2").style.display = 'block';
						
					}
				} */
				
				if(document.getElementById("peAmount").value < 0 || document.getElementById("peAmount").value.length == 0){
					document.getElementById("error1").style.display = 'block';
					
				}
				else if(document.getElementById("peAmount").value > 1000){
					document.getElementById("hiddenBut3").click();
				}
				else
					document.getElementById("peButtonH").click();

			}
		}
		
		$('#myModal3').on('hidden.bs.modal', function () {
			document.getElementById("accButtonH").click();
		});
		
		$('#myModal4').on('hidden.bs.modal', function () {
			document.getElementById("peButtonH").click();
		});
		
		
		
	</script>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>