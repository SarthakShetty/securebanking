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
				width: 40%;
				margin: 100px auto;
				
			}
			
			
			h1, label, p{
				color: white !important;
			}
			
			label{
				width: 20%;
				margin: 30px 20px 0 0;
				
			}
			.divider{
				border-top: 1px solid #8c8b8b;
				
			}
			
			#em{
				width: 70%;
				display: inline-block !important;
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
			<div>
				<h1>Forgot Password</h1>
				<label style="width: 100% !important;"> Please enter your email to receive OTP code and move onto the next step.</label>
			</div>
			<hr class="divider" />
			<div>
				<form action="/forgotPasswordOTP" method="POST">
					<label>Email:</label>
					<input type="text" placeholder="Email" name="email" id="em" class="form-control">
					<div style="text-align: center;"> 
						<input type="submit" class="btn btn-primary btn-md" value="Next" style="margin: 30px auto 0 auto;">
					</div>
				</form>
			</div>
		</div>
		
	</body>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>