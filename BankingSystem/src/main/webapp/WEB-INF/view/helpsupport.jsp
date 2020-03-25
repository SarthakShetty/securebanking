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
		  <ul class="navbar-nav">
		    <li class="nav-item" id="acc" ><a class="nav-link" href="/customer/Account">Account</a></li>
			  <li id="rp" class =" nav-item" ><a class="nav-link" href="/customer/transferEmailPhone">Transfer/Make Payment</a></li>
			  <li id="tba" class =" nav-item"  ><a class="nav-link" href="/customer/transferBA">Transfer Between Accounts</a></li>
			  <li id="cd" class ="nav-item" ><a class="nav-link" href="/customer/CreditDebit">Credit/Debit</a></li>
			  <li id="mp" class ="nav-item" ><a class="nav-link" href="/customer/payment">Make Payment</a></li>
			  <li id="pr" class ="nav-item" ><a class="nav-link" id="" href="/customer/profile">Profile</a></li>
			  <li id="am" class ="nav-item" ><a class="nav-link" href="/customer/accountManagement">Account Management</a></li>
			  <li id="hs" class ="active nav-item" ><a class="nav-link" href="/customer/helpSupport">Help and Support</a></li>
		  </ul>
		</nav>
		<%
			out.print(session.getAttribute("user_id"));
		%>
		<div id="container">
			<h1>Hello ${user_id}</h1>
			<h2>${FirstName}</h2>
			
			<div id="helpsup" style="text-align: center;">
				<h2>Schedule an appointment</h2>
				<form action="">
				  <h3>Select a date </h3>
				  <input type="date" id="dateS" name="dates" style="display: block; margin: 10px auto">
				  <input type="submit" class="btn btn-info btn-md">
				</form>
				
			</div>
		</div>
	<script>
		
		
		
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
		        url : '/login',
		        method : 'POST',
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