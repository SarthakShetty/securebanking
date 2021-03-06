<%@ page isELIgnored="false"%>
<html>
	<head>
		<title>Sign Up</title>
		<style>
			body{
				font-family: "Sqauda One" !important;
				background-image: linear-gradient(to right, #0F2027, #203A43, #2C5364);
			}
			
			#container{
				margin: 20px auto;
				text-align: center;
			}
			
			#email{
				margin: 10px auto 50px auto;
			}
			
			#OTPKey{
				margin: 10px auto 20px auto;
			}
			
			h1, label{
				color: white !important;
			}
			
			
			.divider{
				border-top: 1px solid #8c8b8b;
				
			}
			
			#form{
				text-align: center;
				width: 50%;
				margin: auto;
			}
			
			input[type="text"], input[type='password']{
				width: 60%;
			}
		</style>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
		<link href="https://fonts.googleapis.com/css?family=Squada+One&display=swap" rel="stylesheet">
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <label class="navbar-brand" style="margin: 0 !important;">Group 12 Bank</label>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarNav">
		    <ul class="nav navbar-nav ml-auto">
		      <li class="nav-item"><a  class="nav-link" href="/"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
		    </ul>
		   </div>
    	</nav>
		<div id="container">
			<div style="width: 50%; margin: auto;">
				<h1>Create New Account - OTP Authentication</h1>
			</div>
			
			<div id="form">
				<hr class="divider">
				<form action="/verifyOTP" method="POST">
					<div class="md-form">
						<input name="email" type="text" value="${customer_email}" id="email" class="form-control" readonly="readonly">
						<input name="OTP" type="password" placeholder="OTP Key"  id="OTPKey" class="form-control" style="margin: 20px 0 0 0;"/>
					</div>
					<hr class="divider">
					<div>
						<input type="submit" class="btn btn-primary btn-md" value="Next" style="margin: 20px auto 0 auto;"/>
						<p style="margin: 20px 0 0 0;"><font color="red">${error_msg}</font></p>
					</div>
				</form>
			</div>
		</div>
		
	</body>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>