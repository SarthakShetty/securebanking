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
			
			
			h1, h2, p, label{
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
			
			#transfer, #transferB, #credit, #payment, #profile, #accMan{
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
			
			#transferB div, #credit div{
				display: inline-block;
				margin: 0 100px 0 0;
			}
			
			#credit input{
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
			<h1>Hello ${name}</h1>
			<ul class="nav nav-tabs">
			  <li class="active" id="acc" onclick="change(this)"><a href="#">Account</a></li>
			  <li id="rp" onclick="change(this)"><a href="#">Transfer/Make Payment</a></li>
			  <li id="tba" onclick="change(this)"><a href="#">Transfer Between Accounts</a></li>
			  <li id="cd" onclick="change(this)"><a href="#">Credit/Debit</a></li>
			  <li id="mp" onclick="change(this)"><a href="#">Make Payment</a></li>
			  <li id="pr" onclick="change(this)"><a href="#">Profile</a></li>
			  <li id="am" onclick="change(this)"><a href="#">Account Management</a></li>
			</ul>
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
				<form action="">
					<div>
						<h2>From Account</h2>
						<hr class="divider"/>
						<select>
							<c:forEach items="${accountList}" var="aList">
								<option>${aList}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						<h2>To</h2>
						<hr class="divider"/>
						<label>
					    	<input type="radio" name="custoptions" id="treq" autocomplete="off"> Customer
					  	</label>
					  	<label>
					    	<input type="radio" name="custoptions" id="preq" autocomplete="off"> Merchant
					  	</label>
					  	<input type="text" placeholder="Account Number" name="accNumber" style="display: block;"/>
					</div>
					<div>
						<h2>Type of Request</h2>
						<hr class="divider"/>
						<label>
					    	<input type="radio" name="request" id="treq" autocomplete="off"> Transfer
					  	</label>
					  	<label>
					    	<input type="radio" name="request" id="preq" autocomplete="off"> Request Payment
					  	</label>
					</div>
					<div style="text-align: center;">
						<input  type="submit" value="Request" class="btn btn-md btn-info" style="margin: 25px 0 0 0;">
					</div>
				</form>
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
					<input type="text" placeholder="Amount" name="transferAmount" style="display: block;" />
				</div>
				<div class="col" style="position: absolute; right: 10%; top: 40%;">
					<form action="">
						<input type="submit" class="btn btn-md btn-info" value="Transfer" style="margin: 20px 0 0 0;">
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
					<input type="text" placeholder="Amount" name="transferAmount" style="display: block;" />
				</div>
				<div class="col" style="position: absolute; right: 35%; top: 25%;">
					<form action="">
						<input type="submit" class="btn btn-md btn-info" value="Debit" style="margin: 20px 0 0 0;">
						<input type="submit" class="btn btn-md btn-info" value="Credit" style="margin: 20px 0 0 0;"/>
					</form>
				</div>
			</div>
			<div id="payment">
				<h1>Transactions Log</h1>
				<hr class="divider"/>
				<div id="listDiv" class="table-wrapper-scroll-y my-custom-scrollbar">
					<table class="table table-bordered mb-0">
						<thead>
						  <tr>
						    <th scope="col">Transactions</th>
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
			</div>
			<div id="profile">
				<h1>Transactions Log</h1>
				<hr class="divider"/>
				<div id="listDiv" class="table-wrapper-scroll-y my-custom-scrollbar">
					<table class="table table-bordered mb-0">
						<thead>
						  <tr>
						    <th scope="col">Transactions</th>
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
			</div>
			<div id="accMan">
				<h1>Transactions Log</h1>
				<hr class="divider"/>
				<div id="listDiv" class="table-wrapper-scroll-y my-custom-scrollbar">
					<table class="table table-bordered mb-0">
						<thead>
						  <tr>
						    <th scope="col">Transactions</th>
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
			</div>
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
			}
			
			console.log(mDiv);
			document.getElementById(mDiv).style.display = 'block';
			console.log(active);
		}
		
		function showNewAcc(){
			document.getElementById('newAccDiv').style.display = 'block';
		}
	</script>
	</body>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>