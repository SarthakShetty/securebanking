<%@ page isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Login</title>
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
		</nav>
		<%-- <%
			out.print(session.getAttribute("user_id"));
		%> --%>
		<div id="container">
			
			<div id="transfer">
				<div id="accTransfer">
					<h2>Transfer Via Account Number</h2>
					<hr class="divider"/>
					<form action="/customer/transferFundsOtherAccount">
						<div>
							<h3>From Account</h3>
							<select>
								<c:forEach items="${accounts}" var="aList">
									<option>${aList}</option>
								</c:forEach>
							</select>
						</div>
						<div>
							<h3>To Account</h3>
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
							<input type="text" placeholder="Amount" name="accAmount" id="accAmount" style="display: block;" />
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
					<form action="/customer/transferFundsEmailPhone">
						<div>
							<h3>From Account</h3>
							<select>
								<c:forEach items="${accounts}" var="aList">
									<option>${aList}</option>
								</c:forEach>
							</select>
						</div>
						<div>
							<h3>Phone/Email</h3>
							<label>
						    	<input type="radio" name="medium" id="phone" autocomplete="off"> Phone
						  	</label>
						  	<label>
						    	<input type="radio" name="medium" id="email" autocomplete="off"> Email
						  	</label>
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
							<input type="text" placeholder="Amount" name="peAmount" id="peAmount" style="display: block;" />
						</div>
						<div style="text-align: center;">
							<input  type="button" value="Request" class="btn btn-md btn-info" id="peButton" onclick="checkModal(this)" style="margin: 25px 0 0 0;">
							<input  type="submit" id="peButtonH"  style="display: none;">
						</div>
					</form>
				</div>
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
		
	
		function checkModal(el){
			if(el.id == "accButton"){
				if(document.getElementById("accAmount").value > 1000){
					console.log("wef");
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
		
		$('#myModal3').on('hidden.bs.modal', function () {
			document.getElementById("tButtonH").click();
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