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
			
			#transfer, #transferB, #credit, #payment, #profile, #accMan, #helpsup{
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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		
	</head>
	<body>
		<div id="container">
			<h1>Hello ${user_id}</h1>
			<ul class="nav nav-tabs">
			  <li class="active" id="acc" onclick="change(this)"><a href="#">Account</a></li>
			  <li id="rp" onclick="change(this)"><a href="#">Transfer/Make Payment</a></li>
			  <li id="tba" onclick="change(this)"><a href="#">Transfer Between Accounts</a></li>
			  <li id="cd" onclick="change(this)"><a href="#">Credit/Debit</a></li>
			  <li id="mp" onclick="change(this)"><a href="#">Make Payment</a></li>
			  <li id="pr" onclick="change(this)"><a id="profileLink" href="#">Profile</a></li>
			  <li id="am" onclick="change(this)"><a href="#">Account Management</a></li>
			  <li id="hs" onclick="change(this)"><a href="#">Help and Support</a></li>
			</ul>
			<h2>${FirstName}</h2>
			<div id="account">
				<h2 style="color: white;">Balance</h3>
				<div id="leftDiv">
					<c:forEach items="${accountList}" var="aList">
						<label>Account ${aList}: </label>
					</c:forEach>
				</div>
				<div id="rightDiv">
					<c:forEach items="${accountBList}" var="bList">
						<label>$ ${bList}</label>
					</c:forEach>
				</div> 
				
			</div>
			<div id="transfer">
				<div id="accTransfer">
					<h2>Transfer Via Account Number</h2>
					<hr class="divider"/>
					<form action="">
						<div>
							<h3>From Account</h3>
							<select>
								<c:forEach items="${accountList}" var="aList">
									<option>${aList}</option>
								</c:forEach>
							</select>
						</div>
						<div>
							<h3>To</h3>
							<label>
						    	<input type="radio" name="custoptions" id="treq" autocomplete="off"> Customer
						  	</label>
						  	<label>
						    	<input type="radio" name="custoptions" id="preq" autocomplete="off"> Merchant
						  	</label>
						  	<input type="text" placeholder="Account Number" name="accNumber" style="display: block;"/>
						</div>
						<div>
							<h3>Type of Request</h3>
							<label>
						    	<input type="radio" name="request" id="treq" autocomplete="off"> Transfer
						  	</label>
						  	<label>
						    	<input type="radio" name="request" id="preq" autocomplete="off"> Request Payment
						  	</label>
						</div>
						<div >
							<h2>Amount</h2>
							<input type="text" placeholder="Amount" name="accAmount" style="display: block;" />
						</div>
						<div style="text-align: center;">
							<input  type="button" value="Request" class="btn btn-md btn-info" id="accButton" onclick="checkModal(this)" style="margin: 25px 0 0 0;">
							<input  type="submit" id="accButtonH"  style="display: none;">
						</div>
					</form>
				</div>
				<div id="emailphonetransfer">
					<h2>Transfer Via Email/Phone</h2>
					<hr class="divider"/>
					<form action="">
						<div>
							<h3>From Account</h3>
							<select>
								<c:forEach items="${accountList}" var="aList">
									<option>${aList}</option>
								</c:forEach>
							</select>
						</div>
						<div>
							<h3>To</h3>
							
						  	<input type="text" placeholder="Phone Number" name="phoneNumver" style="display: block; margin: 0 auto 30px auto;"/>
						  	<input type="text" placeholder="Email Address" name="emailAddress" style="display: block;"/>
						</div>
						<div>
							<h3>Type of Request</h3>
							<label>
						    	<input type="radio" name="request" id="treq" autocomplete="off"> Transfer
						  	</label>
						  	<label>
						    	<input type="radio" name="request" id="preq" autocomplete="off"> Request Payment
						  	</label>
						</div>
						<div >
							<h2>Amount</h2>
							<input type="text" placeholder="Amount" name="peAmount" style="display: block;" />
						</div>
						<div style="text-align: center;">
							<input  type="button" value="Request" class="btn btn-md btn-info" id="peButton" onclick="checkModal(this)" style="margin: 25px 0 0 0;">
							<input  type="submit" id="peButtonH"  style="display: none;">
						</div>
					</form>
				</div>
			</div>
			<div id="transferB" class="row" style="position: relative;">
				<div class="col">
					<h2>From Account</h2>
					<select>
						<c:forEach items="${accountList}" var="aList">
							<option>${aList}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col">
					<h2>To Account</h2>
					<select>
						<c:forEach items="${accountList}" var="aList">
							<option>${aList}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col">
					<h2>Amount</h2>
					<input type="text" placeholder="Amount" name="transferAmount" id="tbAmount" style="display: block;" />
				</div>
				<div class="col" style="position: absolute; right: 10%; top: 40%;">
					<form action="/">
						<input type="button" class="btn btn-md btn-info" value="Transfer" id="tButton" onclick="checkModal(this)" style="margin: 20px 0 0 0;">
						<input type="submit" style="display: none;" id="tButtonH">
					</form>
				</div>
			</div>
			<div id="credit" style="position: relative;">
				<div>
					<h2>From Account</h2>
					<select>
						<c:forEach items="${accountList}" var="aList">
							<option>${aList}</option>
						</c:forEach>
					</select>
				</div>
				<div >
					<h2>Amount</h2>
					<input type="text" placeholder="Amount" name="transferAmount" id="cdAmount" style="display: block;" />
				</div>
				<div class="col" style="position: absolute; right: 35%; top: 25%;">
					<form action="">
						<input type="button" id="cdbutton" class="btn btn-md btn-info" data-toggle="modal" data-target="#myModal" value="Credit/Debit" style="margin: 20px 0 0 0;">
					</form>
				</div>
			</div>
			<div id="payment">
				<div>
					<h2>Requested From</h2>
					<c:forEach items="${requestListNames}" var="nList">
						<label>${nList}</label>
					</c:forEach>
				</div>
				<div >
					<h2>Amount</h2>
					<c:forEach items="${requestListAmount}" var="aList">
						<label>${aList}</label>
					</c:forEach>
				</div>
				<div>
					<h2>Account to Pay From</h2>
					<c:forEach items="${requestListNames}" var="x">
						<select>
							<c:forEach items="${accountList}" var="aList">
								<option>${aList}</option>
							</c:forEach>
						</select>
					</c:forEach>
				</div>
				<div class="col" style="margin: 0 0 0 0 !important;">
					<c:forEach items="${requestListNames}" var="x">
						<button class="btn btn-sm btn-info">Make Payment</button>
					</c:forEach>
				</div>
			</div>
			<div id="profile">
				<div id="nameInfo">
					<div>
						<label style="display: inline-block;">Name:</label>
						<label style="display: inline-block;">${name}</label>
					</div>
					<div>
						<div style="display: block;"><h3>Accounts</h3></div>
						<div style="display: inline-block;">
							<c:forEach items="${accountList}" var="aList">
								<label style="display: block;">${aList}</label>
							</c:forEach>
						</div>
						
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
			<div id="accMan" style="text-align: center;">
				<div style="margin: 20px 0 0 0; display: block;">
					<h2>Banking Statements</h2>
					<hr class="divider"/>
				  	<button class="btn btn-info btn-md">Download Banking Statements</button>
				</div>
				<div style="margin: 40px 0 0 0; display: block;">
					<h2>Delete an Account</h2>
					<hr class="divider"/>
				  	<select style="display: block; margin: 20px auto;">
				  		<c:forEach items="${accountList}" var="aList">
				  			<option>${aList}</option>
				  		</c:forEach>
				  	</select>
				  	<button class="btn btn-md btn-info">Submit Request</button>
				</div>
				<div style="margin: 40px 0 0 0; display: block;">
					<h2>Create New Account</h2>
					<hr class="divider">
				</div>
				
				<div id="form">
					
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
				      	<input type="submit" value="Submit Request" class="btn btn-info btn-md" style="margin: 30px auto 0 auto;">
				      </div>
					  </div>
					</form>
				</div>
			</div>
			<div id="helpsup" style="text-align: center;">
				<h2>Schedule an appointment</h2>
				<form action="">
				  <h3>Select a date </h3>
				  <input type="date" id="dateS" name="dates" style="display: block; margin: 10px auto">
				  <input type="submit" class="btn btn-info btn-md">
				</form>
				
			</div>
			
			<div class="modal fade" id="myModal" role="dialog" style="display: none;">
			    <div class="modal-dialog" >
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title" style="color: red;" id="modalHead"></h4>
			        </div>
			        <div class="modal-body">
			          <p style="color: black !important;" id="modalmsg"></p>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">Credit</button>
			          <button type="button" class="btn btn-default" data-dismiss="modal" >Debit</button>
			        </div>
			      </div>
			      
			    </div>
			 </div>
			 
			 <div class="modal fade" id="myModal2" role="dialog" style="display: none;">
			    <div class="modal-dialog" >
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title" style="color: red;" id="modalHead">Attention</h4>
			        </div>
			        <div class="modal-body">
			          <p style="color: black !important;" id="modalmsg">The specified amount caused this request to be considered a critical transaction.</p>
			        </div>
			        <div class="modal-footer">
			          
			        </div>
			      </div>
			      
			    </div>
			 </div>
			 <input type="button" style="display: none;" data-toggle="modal" data-target="#myModal2" id="hiddenBut">
			 
			<div class="modal fade" id="myModal3" role="dialog" style="display: none;">
			    <div class="modal-dialog" >
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title" style="color: red;" id="modalHead">Attention</h4>
			        </div>
			        <div class="modal-body">
			          <p style="color: black !important;" id="modalmsg">The specified amount caused this request to be considered a critical transaction.</p>
			        </div>
			        <div class="modal-footer">
			          
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
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title" style="color: red;" id="modalHead">Attention</h4>
			        </div>
			        <div class="modal-body">
			          <p style="color: black !important;" id="modalmsg">The specified amount caused this request to be considered a critical transaction.</p>
			        </div>
			        <div class="modal-footer">
			          
			        </div>
			      </div>
			      
			    </div>
			 </div>
			 <input type="button" style="display: none;" data-toggle="modal" data-target="#myModal4" id="hiddenBut3">
		</div>
	<script>
		var active = "acc";
		var mDiv = "account";
		function change(el){
			el.className = 'active';
			document.getElementById(active).className = '';
			document.getElementById(mDiv).style.display = 'none';

			active = el.id;
			
			switch(active){
				case 'pr':
					mDiv = "profile";
					break;
				case 'acc':
					mDiv = "account";
					break;
				case 'rp':
					mDiv = "transfer";
					break;
				case 'tba':
					mDiv = "transferB";
					break;
				case 'cd':
					mDiv = "credit";
					break;
				case 'mp':
					mDiv = "payment";
					break;
				case 'am':
					mDiv = "accMan";
					break;
				case 'hs':
					mDiv = "helpsup";
					break;
			}
			
			
			document.getElementById(mDiv).style.display = 'block';
			
			
		}
		
		
		$("#cdbutton").on("click", function(){
			if(document.getElementById("cdAmount").value > 1000){
				document.getElementById("modalmsg").innerText = "This will be considered a critical transaction and will need to be approved. Credit or Debit?";
				document.getElementById("modalHead").innerText = "Attention";
				document.getElementById("modalHead").style.color = "red";
			}
			else
			{
				document.getElementById("modalmsg").innerText = "Credit or Debit?";
				document.getElementById("modalHead").innerText = "Which Account?";
				document.getElementById("modalHead").style.color = "black";
			}
		});
	
		function checkModal(el){
			if(el.id == "tButton"){
				if(document.getElementById("tbAmount").value > 1000){
					document.getElementById("hiddenBut").click();
					
				}
				else
					document.getElementById("tButtonH").click();
			}
			else if(el.id == "accButton"){
				if(document.getElementById("accAmount").value > 1000){
					document.getElementById("hiddenBut2").click();
				}
				else
					document.getElementById("accButtonH").click();
			}
			else if(el.id == "peButton"){
				if(document.getElementById("peAmount").value > 1000){
					document.getElementById("hiddenBut3").click();
				}
				else
					document.getElementById("peButtonH").click();
			}
		}
		
		$('#myModal2').on('hidden.bs.modal', function () {
			document.getElementById("tButtonH").click();
		});
		$('#myModal3').on('hidden.bs.modal', function () {
			document.getElementById("peButtonH").click();
		});
		$('#myModal4').on('hidden.bs.modal', function () {
			document.getElementById("accButtonH").click();
		});
		
		$("#profileLink").click(function(){
		    $.ajax({
		        url : '/customer/profile',
		        method : 'GET',
		        async : false,
		        complete : function(data) {
		            console.log(data.responseText);
		        }
		    });

		});
	</script>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>