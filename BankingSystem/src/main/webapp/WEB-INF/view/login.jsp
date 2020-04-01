<%@ page isELIgnored="false"%>
<html>
	<head>
		<title>Sign In</title>
		<style>
			#container{
				width: 40%;
				margin: 20px auto;
			}
			
			#loginForm{
				text-align: center;
			}
			
			#user{
				display: block;
			    margin: 10px auto 50px auto;
			    width: 60%;
			}
			
			#pass{
				display: block;
			    margin: 10px auto 20px auto;
			    width: 60%;
			}
			.divider{
				border-top: 1px solid #8c8b8b;
				
			}
			
			body{
				font-family: "Sqauda One" !important;
				background-image: linear-gradient(to right, #0F2027, #203A43, #2C5364);
				
			}
			
			input[type="radio"]{
				margin: auto 10px;
			}
			
			input[type="submit"]{
				margin: 20px auto;
			}
			
			#radioGroup input, #radioGroup label{
				margin: auto 10px;
			}
			
			h1, p, label{
				color: white !important;
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
				<h1>Sign In</h1>
			</div>
			<hr class="divider">
			<div id="loginForm">
				<form action="/login" method="POST">
					<div class="md-form">
						<input type="text" name="name" placeholder="Username"  id="user" class="form-control"/> 
						<input name="password" type="password" placeholder="Password"  id="pass" class="form-control"/>
						<label for="pass" style="margin: 0 auto 30px auto">Forgot Password? Click here - <a href="/forgotPassword">Recover Password</a></label>
					</div>
					<div>
					  <label>
					    <input type="radio" name="type_user" id="customer"  autocomplete="off" checked value="customer"> Customer
					  </label>
					  <label>
					    <input type="radio" name="type_user" id="employee" autocomplete="off" value="employee"> Employee
					  </label>
					</div>
					<div>
						<input type="submit" class="btn btn-primary btn-md" value="Sign In" style="margin: 30px auto 0 auto;"/>
					</div>
					<p style="margin: 20px 0 0 0;"><font color="red">${error_msg}</font></p>
					
				</form>
				<hr class="divider">
				<p>New User? Sign up here - <a href="/newAccount">Register</a></p>
			</div>
		</div>
		
	</body>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>