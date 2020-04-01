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
			
			input[type=radio]{
				margin: 0 10px 0 10px !important;			
			}
			
			#transferB div{
				display: inline-block;
				margin: 0 100px 0 0;
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
			  <li id="rp" class =" nav-item" ><a class="nav-link" href="/customer/transferEmailPhone">Transfer/Make Payment</a></li>
			  <li id="tba" class =" active nav-item"  ><a class="nav-link" href="/customer/transferBA">Transfer Between Accounts</a></li>
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
			<h1>Transfer Between Accounts</h1>
			<hr class="divider" />
			<form action="/customer/transferFunds">
				<div id="transferB" class="row" style="position: relative;">
					<div class="col">
						<h2>From Account</h2>
						<select name="from_acc">

							<c:forEach items="${accountList}" var="aList">
								<option>${aList.acc_id}(Balance : ${aList.curr_bal})</option>
							</c:forEach>
						</select>
					</div>
					<div class="col">
						<h2>To Account</h2>

						<select name="to_acc">

							<c:forEach items="${accountList}" var="aList">
								<option>${aList.acc_id}(Balance : ${aList.curr_bal})</option>
							</c:forEach>
						</select>
					</div>
					<div class="col">
						<h2>Amount</h2>
						<input type="text" placeholder="Amount" name="transferAmount" id="tbAmount" style="display: block;" value="0" />

						<div id="error" style="display: none;">
							<p style="margin: 20px 0 0 0;"><font color="red">Please enter a valid amount.</font></p>
						</div>

					</div>
				</div>
				<div style="display: block; text-align: center; margin: 20px 0 0 0;">
					<input type="button" class="btn btn-md btn-info" value="Transfer" id="tButton" onclick="checkModal(this)" style="margin: 20px 0 0 0;">
					<input type="submit" style="display: none;" id="tButtonH">
					<p style="margin: 20px 0 0 0;"><font color="red">${error_msg}</font></p>
					<p style="margin: 20px 0 0 0;"><font color="green">${msg}</font></p>
				</div>

			</form>
			<div class="modal fade" id="myModal1" role="dialog" style="display: none;">
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
			          
			        </div>
			      </div>
			      
			    </div>
			 </div>
			 <input type="button" style="display: none;" data-toggle="modal" data-target="#myModal1" id="hiddenBut">
		</div>
	<script>
		
	function checkModal(el){
		if(el.id == "tButton"){
			if(document.getElementById("tbAmount").value > 1000){
			if(document.getElementById("tbAmount").value < 0 || document.getElementById("tbAmount").value.length == 0){
				document.getElementById("error").style.display = 'block';
				
			}
			else if(document.getElementById("tbAmount").value > 1000){
				document.getElementById("hiddenBut").click();
				
			}
			else
				document.getElementById("tButtonH").click();
		}
		}
	}
		
	</script>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>